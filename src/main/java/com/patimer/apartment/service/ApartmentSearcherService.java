package com.patimer.apartment.service;

import com.patimer.apartment.model.ApartmentEntity;
import com.patimer.apartment.service.notificationService.NotificationService;

import java.io.FileNotFoundException;
import java.util.List;

public interface ApartmentSearcherService
{
    List<ApartmentEntity> find() throws FileNotFoundException;

    void registerNotificationService(NotificationService notificationService);

    void registerNotificationServices(List<NotificationService> notificationServices);

    void unregisterNotificationService(NotificationService notificationService);
}
