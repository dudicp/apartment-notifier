package com.patimer.apartment;


import org.apache.commons.lang.Validate;

public class ApplicationArguments
{
    private String databaseFilename;
    private String configurationFilename;

    public ApplicationArguments(String databaseFilename, String configurationFilename)
    {
        Validate.notEmpty(databaseFilename);
        Validate.notEmpty(configurationFilename);
        this.databaseFilename = databaseFilename;
        this.configurationFilename = configurationFilename;
    }

    public String getDatabaseFilename()
    {
        return databaseFilename;
    }

    public String getConfigurationFilename()
    {
        return configurationFilename;
    }
}
