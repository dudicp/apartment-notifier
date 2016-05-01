package com.patimer.apartment.predicate;


import com.patimer.apartment.model.ApartmentEntity;
import com.patimer.apartment.model.AssetType;
import org.jsoup.helper.Validate;

import java.util.function.Predicate;

public class AssetTypePredicate implements Predicate<ApartmentEntity>
{
    private AssetType desiredType;

    public AssetTypePredicate(AssetType desiredType)
    {
        Validate.notNull(desiredType);
        this.desiredType = desiredType;
    }

    @Override
    public boolean test(ApartmentEntity apartmentEntity)
    {
        return (apartmentEntity.getAssetType() == desiredType);
    }
}
