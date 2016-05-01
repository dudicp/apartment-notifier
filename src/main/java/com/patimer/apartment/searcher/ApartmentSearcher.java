package com.patimer.apartment.searcher;


import com.patimer.apartment.model.ApartmentEntity;

import java.io.IOException;
import java.util.List;

public interface ApartmentSearcher
{
    List<ApartmentEntity> retrieveApartments() throws IOException;

    SearcherType getType();
}
