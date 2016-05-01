package com.patimer.apartment.searcher;


import com.patimer.apartment.model.ApartmentEntity;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class IntegrationTestMadlanApartmentSearcher
{
    @Test
    public void testRetrieveApartments() throws IOException
    {
        // given
        String neighborhoodSuffix = "local/רחובות/רחובות%20המדע";
        MadlanApartmentSearcherImpl madlanApartmentSearcher = new MadlanApartmentSearcherImpl(neighborhoodSuffix);

        // when
        List<ApartmentEntity> apartmentsList = madlanApartmentSearcher.retrieveApartments();

        // then
        Assert.assertNotNull(apartmentsList);
        Assert.assertTrue(apartmentsList.size() > 0);
    }
}
