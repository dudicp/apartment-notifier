package com.patimer.apartment.repository;

import com.patimer.apartment.model.setting.ApplicationSettingEntity;
import com.patimer.apartment.model.setting.ApplicationSettingEntityBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class TestApplicationSettingsRepository
{
    private final static String DEFAULT_TEST_CONFIGURATION_FILENAME = "test.apartments_searcher.conf";
    private ApplicationSettingsRepository applicationSettingsRepository;

    @Before
    public void setUp()
    {
        this.applicationSettingsRepository = new ApplicationSettingsRepositoryImpl(DEFAULT_TEST_CONFIGURATION_FILENAME);
    }

    @Test
    public void testPersist() throws IOException
    {
        // given
        ApplicationSettingEntity applicationSettingEntity = new ApplicationSettingEntityBuilder().build();

        // when
        applicationSettingsRepository.persist(applicationSettingEntity);

        // then
        ApplicationSettingEntity storedApplicationSettingEntity = applicationSettingsRepository.load();
        Assert.assertNotNull(storedApplicationSettingEntity);
    }
}
