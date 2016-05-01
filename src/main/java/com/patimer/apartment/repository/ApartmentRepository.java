package com.patimer.apartment.repository;


import com.patimer.apartment.model.ApartmentEntity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface ApartmentRepository
{
    void persist(List<ApartmentEntity> apartments) throws IOException;

    List<ApartmentEntity> load() throws FileNotFoundException;
}
