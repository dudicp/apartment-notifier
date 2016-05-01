package com.patimer.apartment.repository;

import com.patimer.apartment.model.ApartmentEntity;
import com.patimer.apartment.model.ApartmentEntityBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestApartmentRepository
{
    private final static String DEFAULT_TEST_DATABASE_FILENAME = "test.apartments.json";
    private ApartmentRepository apartmentRepository;

    @Before
    public void setUp()
    {
        this.apartmentRepository = new ApartmentRepositoryImpl(DEFAULT_TEST_DATABASE_FILENAME);
    }

    @Test
    public void testPersistWhenNotEmpty() throws IOException
    {
        // given
        ApartmentEntity apartmentEntity1 = new ApartmentEntityBuilder().build();
        ApartmentEntity apartmentEntity2 = new ApartmentEntityBuilder().build();
        List<ApartmentEntity> apartments = Arrays.asList(apartmentEntity1, apartmentEntity2);

        // when
        apartmentRepository.persist(apartments);

        // then
        List<ApartmentEntity> storedApartments = apartmentRepository.load();
        Assert.assertNotNull(storedApartments);
        Assert.assertTrue(storedApartments.size() == apartments.size());
    }

    @Test
    public void testPersistWhenEmpty() throws IOException
    {
        // given
        List<ApartmentEntity> apartments = Collections.emptyList();

        // when
        apartmentRepository.persist(apartments);

        // then
        List<ApartmentEntity> storedApartments = apartmentRepository.load();
        Assert.assertNotNull(storedApartments);
        Assert.assertTrue(storedApartments.isEmpty());
    }

    @Test
    public void testPersistWhenNull() throws IOException
    {
        // given

        // when
        apartmentRepository.persist(null);

        // then
        List<ApartmentEntity> storedApartments = apartmentRepository.load();
        Assert.assertNull(storedApartments);
    }
}
