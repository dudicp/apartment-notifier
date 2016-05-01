package com.patimer.apartment.service.notificationService;

import com.patimer.apartment.model.ApartmentEntity;

import java.util.List;

public interface NotificationService
{
    void notify(ApartmentEntity apartment);

    void notify(List<ApartmentEntity> apartments);
}
