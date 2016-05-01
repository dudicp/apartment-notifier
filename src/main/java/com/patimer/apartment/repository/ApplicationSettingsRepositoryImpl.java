package com.patimer.apartment.repository;

import com.google.gson.Gson;
import com.patimer.apartment.model.setting.ApplicationSettingEntity;
import com.patimer.apartment.util.FileUtils;
import org.apache.commons.lang.Validate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ApplicationSettingsRepositoryImpl implements ApplicationSettingsRepository
{
    private String configurationFilename;

    public ApplicationSettingsRepositoryImpl(String configurationFilename)
    {
        Validate.notEmpty(configurationFilename);
        this.configurationFilename = configurationFilename;
    }

    public ApplicationSettingEntity load() throws FileNotFoundException
    {
        if(!FileUtils.isFileExists(configurationFilename)){
            throw new FileNotFoundException("Configuration file: '" + configurationFilename + "' not found.");
        }

        Gson gson = new Gson();
        BufferedReader jsonBufferedReader = FileUtils.readFile(configurationFilename);
        return gson.fromJson(jsonBufferedReader, ApplicationSettingEntity.class);
    }

    public void persist(ApplicationSettingEntity applicationSettingEntity) throws IOException
    {
        Gson gson = new Gson();
        String jsonText = gson.toJson(applicationSettingEntity);
        FileUtils.writeToFile(jsonText, configurationFilename);
    }
}
