package com.patimer.apartment.service.notificationService;

import com.patimer.apartment.model.ApartmentEntity;

import java.util.List;

public class ConsoleNotificationServiceImpl implements NotificationService
{
    @Override
    public void notify(ApartmentEntity apartment)
    {
        System.out.println(apartment);
    }

    @Override
    public void notify(List<ApartmentEntity> apartments)
    {
        System.out.println(apartments);
    }
}
