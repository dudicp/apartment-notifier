package com.patimer.apartment;


import com.patimer.apartment.model.ApartmentEntity;
import com.patimer.apartment.repository.ApartmentRepository;
import com.patimer.apartment.repository.ApartmentRepositoryImpl;
import com.patimer.apartment.repository.ApplicationSettingsRepository;
import com.patimer.apartment.repository.ApplicationSettingsRepositoryImpl;
import com.patimer.apartment.service.ApartmentSearcherService;
import com.patimer.apartment.service.ApartmentSearcherServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


public class SearcherMain
{
    private final static Logger log = LogManager.getLogger(SearcherMain.class);
    private final static String DEFAULT_DATABASE_FILENAME = "apartments_db.json";
    private final static String DEFAULT_CONF_FILENAME = "apartments_searcher.conf";


    public static void main(String[] args) throws Exception
    {
        ApplicationArguments applicationArguments = parseArgs(args);
        start(applicationArguments);
    }

    private static void printUsage()
    {
        System.out.println("apartment-notifer v0.1");
        System.out.println("usage: [-db <filename>] [-conf <filename>]");

        System.out.println("Options:");
        System.out.println("\t-db <filename>\tthe database filename (default: " + DEFAULT_DATABASE_FILENAME + ")");
        System.out.println("\t-conf <filename>\tthe configuration filename (default: " + DEFAULT_CONF_FILENAME + ")");
        System.out.println();
        System.exit(-1);
    }

    private static ApplicationArguments parseArgs(String[] args)
    {
        String databaseFilename = DEFAULT_DATABASE_FILENAME;
        String configurationFilename = DEFAULT_CONF_FILENAME;

        for(int i=0; i<args.length; i++)
        {
            if(args[i].equalsIgnoreCase("-db"))
            {
                if(i+1 != args.length) {
                    databaseFilename = args[i + 1];
                }
                else
                {
                    System.out.println("missing <filename> argument for -db");
                    printUsage();
                }
            }
            else if(args[i].equalsIgnoreCase("-conf"))
            {
                if(i+1 != args.length) {
                    configurationFilename = args[i + 1];
                }
                else
                {
                    System.out.println("missing <filename> argument for -conf");
                    printUsage();
                }
            }
        }

        return new ApplicationArguments(databaseFilename, configurationFilename);
    }

    private static void start(ApplicationArguments applicationArguments)
    {
        try
        {
            ApartmentRepository apartmentRepository =
                new ApartmentRepositoryImpl(applicationArguments.getDatabaseFilename());

            ApplicationSettingsRepository applicationSettingsRepository =
                new ApplicationSettingsRepositoryImpl(applicationArguments.getConfigurationFilename());

            ApartmentSearcherService apartmentSearcherService =
                new ApartmentSearcherServiceImpl(apartmentRepository, applicationSettingsRepository);

            List<ApartmentEntity> foundApartments = apartmentSearcherService.find();

            printSummary(foundApartments);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            log.error("Failed to search apartments", e);
            System.exit(-1);
        }
    }

    private static void printSummary(List<ApartmentEntity> matchedApartments)
    {
        System.out.println();
        System.out.println("Found total of " + matchedApartments.size() + " matching your search critria.");
        System.out.println();
    }
}
