package com.patimer.apartment.model;


import org.apache.commons.lang.Validate;

public class ApartmentEntity
{
    private String link;
    private Integer price;
    private Double numberOfRooms;
    private Integer floorNumber;
    private Integer area;
    private String address;
    private SellerType sellerType;
    private AssetType assetType;

    public ApartmentEntity(
        String link,
        Integer price,
        Double numberOfRooms,
        Integer floorNumber,
        Integer area,
        String address,
        SellerType sellerType,
        AssetType assetType
    )
    {
        Validate.notEmpty(link);
        Validate.isTrue(price == null || price > 0);
        Validate.isTrue(numberOfRooms == null || numberOfRooms > 0);
        Validate.isTrue(floorNumber == null || floorNumber > 0);
        Validate.isTrue(area == null || area > 0);
        Validate.notEmpty(address);

        this.link = link;
        this.price = price;
        this.numberOfRooms = numberOfRooms;
        this.floorNumber = floorNumber;
        this.area = area;
        this.address = address;
        this.sellerType = sellerType;
        this.assetType = assetType;
    }

    public String getLink()
    {
        return link;
    }

    public Integer getPrice()
    {
        return price;
    }

    public Double getNumberOfRooms()
    {
        return numberOfRooms;
    }

    public Integer getFloorNumber()
    {
        return floorNumber;
    }

    public Integer getArea()
    {
        return area;
    }

    public String getAddress()
    {
        return address;
    }

    public SellerType getSellerType()
    {
        return sellerType;
    }

    public double getPriceForMeter()
    {
        if(area != null && area != 0 )
        {
            return price / area;
        }
        else
        {
            return 0;
        }
    }

    public AssetType getAssetType()
    {
        return assetType;
    }

    @Override
    public String toString()
    {
        return "ApartmentEntity{" +
            "link='" + link + '\'' +
            ", price=" + price +
            ", numberOfRooms=" + numberOfRooms +
            ", floorNumber=" + floorNumber +
            ", area=" + area +
            ", address='" + address + '\'' +
            ", sellerType=" + sellerType +
            ", assetType=" + assetType +
            '}';
    }
}
