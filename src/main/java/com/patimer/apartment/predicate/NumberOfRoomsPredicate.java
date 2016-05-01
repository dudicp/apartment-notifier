package com.patimer.apartment.predicate;

import com.patimer.apartment.model.ApartmentEntity;

import java.util.function.Predicate;


public class NumberOfRoomsPredicate implements Predicate<ApartmentEntity>
{
    private double min;
    private double max;
    private boolean allowNullValue;

    public NumberOfRoomsPredicate(double min, double max, boolean allowNullValue)
    {
        this.min = min;
        this.max = max;
        this.allowNullValue = allowNullValue;
    }

    @Override
    public boolean test(ApartmentEntity apartmentEntity)
    {
        if(apartmentEntity.getNumberOfRooms() == null)
        {
            return allowNullValue;
        }
        else
        {
            return (apartmentEntity.getNumberOfRooms() >= min && apartmentEntity.getNumberOfRooms() <= max);
        }
    }
}
