package com.patimer.apartment.model.setting;

import com.patimer.apartment.model.AssetType;

public class PredicateSettingEntityBuilder
{
    private double minNumberOfRooms = 3.5;
    private double maxNumberOfRooms = 4.5;
    private int maxPrice = 2000000;
    private AssetType desiredAssetType = AssetType.Apartment;

    public PredicateSettingEntityBuilder withMinNumberOfRooms(double minNumberOfRooms)
    {
        this.minNumberOfRooms = minNumberOfRooms;
        return this;
    }

    public PredicateSettingEntityBuilder withMaxNumberOfRooms(double maxNumberOfRooms)
    {
        this.maxNumberOfRooms = maxNumberOfRooms;
        return this;
    }

    public PredicateSettingEntityBuilder withMaxPrice(int maxPrice)
    {
        this.maxPrice = maxPrice;
        return this;
    }

    public PredicateSettingEntityBuilder withDesiredAssetType(AssetType desiredAssetType)
    {
        this.desiredAssetType = desiredAssetType;
        return this;
    }

    public PredicateSettingEntity build()
    {
        return new PredicateSettingEntity(minNumberOfRooms, maxNumberOfRooms, maxPrice, desiredAssetType);
    }
}
