package com.patimer.apartment.service;


import com.patimer.apartment.model.ApartmentEntity;
import com.patimer.apartment.model.setting.ApplicationSettingEntity;
import com.patimer.apartment.predicate.ApartmentPredicateFactory;
import com.patimer.apartment.repository.ApartmentRepository;
import com.patimer.apartment.repository.ApplicationSettingsRepository;
import com.patimer.apartment.searcher.ApartmentSearcher;
import com.patimer.apartment.searcher.SearcherFactory;
import com.patimer.apartment.service.notificationService.NotificationService;
import com.patimer.apartment.service.notificationService.NotificationServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.helper.Validate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ApartmentSearcherServiceImpl implements ApartmentSearcherService
{
    private final static Logger log = LogManager.getLogger(ApartmentSearcherServiceImpl.class);

    private ApartmentRepository apartmentRepository;
    private ApplicationSettingsRepository applicationSettingsRepository;
    private List<ApartmentSearcher> cachedApartmentSearcher;
    private Predicate<ApartmentEntity> cachedPredicate;
    private boolean isInitialized = false;
    private List<NotificationService> notificationServices;

    public ApartmentSearcherServiceImpl(
        ApartmentRepository apartmentRepository,
        ApplicationSettingsRepository applicationSettingsRepository
    )
    {
        Validate.notNull(apartmentRepository);
        Validate.notNull(applicationSettingsRepository);

        this.apartmentRepository = apartmentRepository;
        this.applicationSettingsRepository = applicationSettingsRepository;
        this.notificationServices = new ArrayList<>();
    }

    public void registerNotificationService(NotificationService notificationService)
    {
        this.notificationServices.add(notificationService);
    }

    public void registerNotificationServices(List<NotificationService> notificationServices)
    {
        this.notificationServices.addAll(notificationServices);
    }

    public void unregisterNotificationService(NotificationService notificationService)
    {
        this.notificationServices.remove(notificationService);
    }

    private void init() throws FileNotFoundException
    {
        ApplicationSettingEntity applicationSettingEntity = applicationSettingsRepository.load();

        // 1. Create the searchers.
        this.cachedApartmentSearcher = new SearcherFactory().create(applicationSettingEntity.getApartmentSearchers());

        // 2. Create the predicate.
        this.cachedPredicate = new ApartmentPredicateFactory().create(applicationSettingEntity.getPredicateSettings());

        // 3. Create Notification Service.
        NotificationService notificationService =
            new NotificationServiceFactory().create(
                applicationSettingEntity.getNotificationServiceType(), applicationSettingEntity.getMailSetting()
            );

        registerNotificationService(notificationService);

        isInitialized = true;
    }

    public List<ApartmentEntity> find() throws FileNotFoundException
    {
        if(log.isDebugEnabled()){
            log.debug("Starting apartment searcher service.");
        }

        if(!isInitialized)
            init();

        // 1. Get all stored apartments from the repository.
        List<ApartmentEntity> storedApartments = apartmentRepository.load();

        List<ApartmentEntity> allMatchedApartments = new ArrayList<>();

        // 2. Retrieve apartment list from each searcher.
        for(ApartmentSearcher searcher : cachedApartmentSearcher)
        {
            try
            {
                // 2.1. Retrieve apartments
                List<ApartmentEntity> apartmentEntityList = searcher.retrieveApartments();

                // 2.2. Filter apartments by predicates (by price, number of rooms, etc.)
                Stream<ApartmentEntity> apartmentEntityStream = apartmentEntityList.stream().parallel();
                List<ApartmentEntity> searcherMatchedApartments = apartmentEntityStream.filter(cachedPredicate).collect(Collectors.toList());
                allMatchedApartments.addAll(searcherMatchedApartments);

                // 2.3. Persist
                apartmentRepository.persist(searcherMatchedApartments);
            }
            catch (IOException e)
            {
                // ignore and continue
                log.error("Failed to retrieve data from searcher: '" + searcher.getType() + "'", e);
            }
        }

        // 3. Find new or updated apartments.
        List<ApartmentEntity> newOrUpdatedApartments = delta(storedApartments, allMatchedApartments);

        // 4. Notify
        notify(newOrUpdatedApartments);

        if(log.isDebugEnabled()) {
            log.debug(
                "Apartment searcher service completed successfully (found " +
                    ((newOrUpdatedApartments != null)? newOrUpdatedApartments.size() : 0) +
                    " new or updated apartments)."
            );
        }

        return newOrUpdatedApartments;
    }

    private List<ApartmentEntity> delta(List<ApartmentEntity> storedApartments, List<ApartmentEntity> foundApartments)
    {
        List<ApartmentEntity> newOrUpdatedApartments = new ArrayList<>();

        if(foundApartments == null || foundApartments.isEmpty()){
            return new ArrayList<>();
        }

        if(storedApartments == null || storedApartments.isEmpty()) {
            return foundApartments;
        }

        // the unique identifier for apartment should be the link
        Map<String, ApartmentEntity> storedApartmentsAsMap = new HashMap<>();
        storedApartments.forEach(
            apartmentEntity -> storedApartmentsAsMap.put(apartmentEntity.getLink().toLowerCase(), apartmentEntity)
        );

        for(ApartmentEntity apartment : foundApartments)
        {
            if(!storedApartmentsAsMap.containsKey(apartment.getLink().toLowerCase()))
            {
                newOrUpdatedApartments.add(apartment);
            }
            else
            {
                ApartmentEntity storedApartment = storedApartmentsAsMap.get(apartment.getLink().toLowerCase());
                if(storedApartment.getPrice() > apartment.getPrice()){
                    newOrUpdatedApartments.add(apartment);
                }
            }
        }

        return newOrUpdatedApartments;
    }

    private void notify(List<ApartmentEntity> newOrUpdatedApartments)
    {
        if(newOrUpdatedApartments.size() > 0)
        {
            for (NotificationService notificationService : this.notificationServices)
            {
                notificationService.notify(newOrUpdatedApartments);
            }
        }
    }
}
