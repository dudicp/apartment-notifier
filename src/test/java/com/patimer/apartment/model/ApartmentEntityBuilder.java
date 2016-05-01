package com.patimer.apartment.model;

import java.util.UUID;

public class ApartmentEntityBuilder
{
    private String link = "http://www.madlan.co.il/bulletin/" + UUID.randomUUID();
    private Integer price = 2000000;
    private Double numberOfRooms = 4.0;
    private Integer floorNumber = 4;
    private Integer area = 100;
    private String address = "Ben-Gurion, Tel-Aviv";
    private SellerType sellerType = SellerType.Private;
    private AssetType assetType = AssetType.Apartment;

    public ApartmentEntityBuilder withLink(String link)
    {
        this.link = link;
        return this;
    }

    public ApartmentEntityBuilder withPrice(Integer price)
    {
        this.price = price;
        return this;
    }

    public ApartmentEntityBuilder withNumberOfRooms(Double numberOfRooms)
    {
        this.numberOfRooms = numberOfRooms;
        return this;
    }

    public ApartmentEntityBuilder withFloorNumber(Integer floorNumber)
    {
        this.floorNumber = floorNumber;
        return this;
    }

    public ApartmentEntityBuilder withArea(Integer area)
    {
        this.area = area;
        return this;
    }

    public ApartmentEntityBuilder withAddress(String address)
    {
        this.address = address;
        return this;
    }
    public ApartmentEntityBuilder withSellerType(SellerType sellerType)
    {
        this.sellerType = sellerType;
        return this;
    }
    public ApartmentEntityBuilder withAssetType(AssetType assetType)
    {
        this.assetType = assetType;
        return this;
    }

    public ApartmentEntity build()
    {
        return new ApartmentEntity(link, price, numberOfRooms, floorNumber, area, address, sellerType, assetType);
    }
}
