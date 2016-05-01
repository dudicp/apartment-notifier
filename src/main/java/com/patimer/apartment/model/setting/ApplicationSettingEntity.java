package com.patimer.apartment.model.setting;

import com.patimer.apartment.service.notificationService.NotificationServiceType;

import java.util.List;

public class ApplicationSettingEntity
{
    private PredicateSettingEntity predicateSettings;
    private List<ApartmentSearcherEntity> apartmentSearchers;
    private NotificationServiceType notificationServiceType;
    private MailSettingEntity mailSetting;

    public ApplicationSettingEntity(
        PredicateSettingEntity predicateSettings,
        List<ApartmentSearcherEntity> apartmentSearchers,
        NotificationServiceType notificationServiceType,
        MailSettingEntity mailSetting
    )
    {
        this.predicateSettings = predicateSettings;
        this.apartmentSearchers = apartmentSearchers;
        this.notificationServiceType = notificationServiceType;
        this.mailSetting = mailSetting;
    }

    public PredicateSettingEntity getPredicateSettings()
    {
        return predicateSettings;
    }

    public List<ApartmentSearcherEntity> getApartmentSearchers()
    {
        return apartmentSearchers;
    }

    public NotificationServiceType getNotificationServiceType()
    {
        return notificationServiceType;
    }

    public MailSettingEntity getMailSetting()
    {
        return mailSetting;
    }
}
