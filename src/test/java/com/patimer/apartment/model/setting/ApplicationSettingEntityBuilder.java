package com.patimer.apartment.model.setting;

import com.patimer.apartment.service.notificationService.NotificationServiceType;

import java.util.Collections;
import java.util.List;

public class ApplicationSettingEntityBuilder
{
    private PredicateSettingEntity predicateSettings = new PredicateSettingEntityBuilder().build();
    private List<ApartmentSearcherEntity> apartmentSearchers = Collections.singletonList(new ApartmentSearcherEntityBuilder().build());
    private NotificationServiceType notificationServiceType = NotificationServiceType.Email;
    private MailSettingEntity mailSetting = new MailSettingEntityBuilder().build();

    public ApplicationSettingEntityBuilder withPredicateSettings(PredicateSettingEntity predicateSettings)
    {
        this.predicateSettings = predicateSettings;
        return this;
    }

    public ApplicationSettingEntityBuilder withApartmentSearcherEntity(List<ApartmentSearcherEntity> apartmentSearchers)
    {
        this.apartmentSearchers = apartmentSearchers;
        return this;
    }

    public ApplicationSettingEntityBuilder withNotificationServiceType(NotificationServiceType notificationServiceType)
    {
        this.notificationServiceType = notificationServiceType;
        return this;
    }

    public ApplicationSettingEntityBuilder withMailSetting(MailSettingEntity mailSetting)
    {
        this.mailSetting = mailSetting;
        return this;
    }

    public ApplicationSettingEntity build()
    {
        return new ApplicationSettingEntity(predicateSettings, apartmentSearchers, notificationServiceType, mailSetting);
    }
}
