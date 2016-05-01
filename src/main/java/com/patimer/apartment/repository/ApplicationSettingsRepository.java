package com.patimer.apartment.repository;

import com.patimer.apartment.model.setting.ApplicationSettingEntity;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface ApplicationSettingsRepository
{
    ApplicationSettingEntity load() throws FileNotFoundException;

    void persist(ApplicationSettingEntity applicationSettingEntity) throws IOException;
}
