package com.patimer.apartment.model.setting;

import com.patimer.apartment.model.AssetType;

public class PredicateSettingEntity
{
    private double minNumberOfRooms;
    private double maxNumberOfRooms;
    private int maxPrice;
    private AssetType desiredAssetType;

    public PredicateSettingEntity(double minNumberOfRooms, double maxNumberOfRooms, int maxPrice, AssetType desiredAssetType)
    {
        this.minNumberOfRooms = minNumberOfRooms;
        this.maxNumberOfRooms = maxNumberOfRooms;
        this.maxPrice = maxPrice;
        this.desiredAssetType = desiredAssetType;
    }

    public double getMinNumberOfRooms()
    {
        return minNumberOfRooms;
    }

    public double getMaxNumberOfRooms()
    {
        return maxNumberOfRooms;
    }

    public int getMaxPrice()
    {
        return maxPrice;
    }

    public AssetType getDesiredAssetType()
    {
        return desiredAssetType;
    }
}
