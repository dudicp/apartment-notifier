package com.patimer.apartment.searcher;

import com.patimer.apartment.model.setting.ApartmentSearcherEntity;
import org.apache.commons.lang.Validate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearcherFactory
{
    public ApartmentSearcher create(ApartmentSearcherEntity apartmentSearcherEntity)
    {
        Validate.notNull(apartmentSearcherEntity);

        switch (apartmentSearcherEntity.getSearcherType())
        {
            case Madlan:
                return createMadlanApartmentSearcher(apartmentSearcherEntity);
            default:
                throw new IllegalArgumentException("Unsupported searcher type: '" + apartmentSearcherEntity.getSearcherType() + "'");
        }
    }

    public List<ApartmentSearcher> create(List<ApartmentSearcherEntity> apartmentSearcherEntities)
    {
        Validate.notEmpty(apartmentSearcherEntities);

        List<ApartmentSearcher> result = new ArrayList<>();
        apartmentSearcherEntities.forEach( apartmentSearcherEntity -> result.add(create(apartmentSearcherEntity)));
        return result;
    }

    private MadlanApartmentSearcherImpl createMadlanApartmentSearcher(ApartmentSearcherEntity apartmentSearcherEntity)
    {
        return new MadlanApartmentSearcherImpl(apartmentSearcherEntity.getSuffix());
    }
}
