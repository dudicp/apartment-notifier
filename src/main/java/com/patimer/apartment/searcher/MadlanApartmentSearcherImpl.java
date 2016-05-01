package com.patimer.apartment.searcher;


import com.patimer.apartment.model.ApartmentEntity;
import com.patimer.apartment.model.AssetType;
import com.patimer.apartment.model.SellerType;
import com.patimer.apartment.util.TypeConverter;
import org.apache.commons.lang.Validate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MadlanApartmentSearcherImpl implements ApartmentSearcher
{
    private static final String MADLAN_URL = "http://www.madlan.co.il//";

    private String neighborhoodSuffix;

    public MadlanApartmentSearcherImpl(String neighborhoodSuffix)
    {
        Validate.notEmpty(neighborhoodSuffix);
        this.neighborhoodSuffix = neighborhoodSuffix;
    }


    public List<ApartmentEntity> retrieveApartments() throws IOException
    {
        List<ApartmentEntity> apartmentEntities = new ArrayList<>();
        Document document = Jsoup.connect(MADLAN_URL + neighborhoodSuffix).get();

        Element apartmentTableElement =
            document.getElementById("forSaleTableCont").getElementsByTag("table").first();

        Elements apartmentRowsElements = apartmentTableElement.select("tr");

        for(Element apartmentRowElement : apartmentRowsElements)
        {
            Elements apartmentTableDataElements = apartmentRowElement.select("td");

            if(!apartmentTableDataElements.isEmpty())
            {
                Element linkElement = apartmentTableDataElements.get(0);

                String sellType = linkElement.getElementsByTag("a").first().text();

                if(sellType.equalsIgnoreCase("למכירה"))
                {
                    Element assetTypeElement = apartmentTableDataElements.get(1);
                    Element priceElement = apartmentTableDataElements.get(2);
                    Element numberOfRoomsElement = apartmentTableDataElements.get(3);
                    Element areaElement = apartmentTableDataElements.get(4);
                    Element floorNumberElement = apartmentTableDataElements.get(5);
                    Element addressElement = apartmentTableDataElements.get(6);
                    Element sellerTypeElement = apartmentTableDataElements.get(7);

                    AssetType assetType = convertToAssetTypeFromMadlanString(assetTypeElement.text());
                    Integer price = TypeConverter.convertToNullableIntegerFromString(priceElement.text(), true /*ignoreException*/);
                    Double numberOfRooms = TypeConverter.convertToNullableDoubleFromString(numberOfRoomsElement.text(), true /*ignoreException*/);
                    Integer area = TypeConverter.convertToNullableIntegerFromString(areaElement.text(), true /*ignoreException*/);
                    Integer floorNumber = TypeConverter.convertToNullableIntegerFromString(floorNumberElement.text(), true /*ignoreException*/);
                    String address = addressElement.text();
                    SellerType sellerType = convertToSellerTypeFromMadlanString(sellerTypeElement.text());
                    String link = MADLAN_URL + linkElement.getElementsByTag("a").first().attr("href");

                    ApartmentEntity apartmentEntity =
                        new ApartmentEntity(link, price, numberOfRooms, floorNumber, area, address, sellerType, assetType);

                    apartmentEntities.add(apartmentEntity);
                }
            }
        }

        return apartmentEntities;
    }

    public SearcherType getType()
    {
        return null;
    }

    private AssetType convertToAssetTypeFromMadlanString(String madlanValue)
    {
        if(madlanValue.equalsIgnoreCase("דירה"))
            return AssetType.Apartment;
        else if(madlanValue.equalsIgnoreCase("מגרש"))
            return AssetType.Ground;
        else if (madlanValue.equalsIgnoreCase("דירת גן"))
            return AssetType.GardenApartment;
        else return AssetType.Other;
    }

    private SellerType convertToSellerTypeFromMadlanString(String madlanValue)
    {
        if(madlanValue.equalsIgnoreCase("פרטי"))
            return SellerType.Private;
        else if(madlanValue.equalsIgnoreCase("מתווך"))
            return SellerType.BrokerageService;
        else return null;
    }
}
