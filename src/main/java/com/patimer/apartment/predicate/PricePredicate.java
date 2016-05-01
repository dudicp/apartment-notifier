package com.patimer.apartment.predicate;


import com.patimer.apartment.model.ApartmentEntity;
import org.apache.commons.lang.Validate;

import java.util.function.Predicate;

public class PricePredicate implements Predicate<ApartmentEntity>
{
    private int max;
    private boolean allowNullValue;

    public PricePredicate(int max, boolean allowNullValue)
    {
        Validate.isTrue(max > 0);
        this.max = max;
        this.allowNullValue = allowNullValue;
    }

    @Override
    public boolean test(ApartmentEntity apartmentEntity)
    {
        if(apartmentEntity.getPrice() == null)
        {
            return allowNullValue;
        }
        else
        {
            return (apartmentEntity.getPrice() <= max);
        }
    }
}
