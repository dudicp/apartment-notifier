package com.patimer.apartment.predicate;

import com.patimer.apartment.model.ApartmentEntity;
import com.patimer.apartment.model.setting.PredicateSettingEntity;
import org.apache.commons.lang.Validate;

import java.util.function.Predicate;

public class ApartmentPredicateFactory
{
    public Predicate<ApartmentEntity> create(PredicateSettingEntity predicateSetting)
    {
        Validate.notNull(predicateSetting);

        NumberOfRoomsPredicate numberOfRoomsPredicate = createNumberOfRoomsPredicate(predicateSetting);
        PricePredicate pricePredicate = createPricePredicate(predicateSetting);
        AssetTypePredicate assetTypePredicate = createAssetTypePredicate(predicateSetting);
        return numberOfRoomsPredicate.and(pricePredicate).and(assetTypePredicate);
    }

    public NumberOfRoomsPredicate createNumberOfRoomsPredicate(PredicateSettingEntity predicateSetting)
    {
        Validate.notNull(predicateSetting);

        return new NumberOfRoomsPredicate(
                predicateSetting.getMinNumberOfRooms(),
                predicateSetting.getMaxNumberOfRooms(),
                true /*allowNullValue*/
            );
    }

    public PricePredicate createPricePredicate(PredicateSettingEntity predicateSetting)
    {
        return new PricePredicate(predicateSetting.getMaxPrice(), true /*allowNullValue*/);
    }

    public AssetTypePredicate createAssetTypePredicate(PredicateSettingEntity predicateSetting)
    {
        return new AssetTypePredicate(predicateSetting.getDesiredAssetType());
    }
}
