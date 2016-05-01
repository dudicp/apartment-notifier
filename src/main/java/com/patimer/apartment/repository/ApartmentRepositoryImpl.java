package com.patimer.apartment.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.patimer.apartment.model.ApartmentEntity;
import com.patimer.apartment.util.FileUtils;
import org.apache.commons.lang.Validate;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ApartmentRepositoryImpl implements ApartmentRepository
{
    private String databaseFilename;

    public ApartmentRepositoryImpl(String databaseFilename)
    {
        Validate.notEmpty(databaseFilename);
        this.databaseFilename = databaseFilename;
    }

    public void persist(List<ApartmentEntity> apartments) throws IOException
    {
        Gson gson = new Gson();
        String jsonText = gson.toJson(apartments);
        FileUtils.writeToFile(jsonText, databaseFilename);
    }

    public List<ApartmentEntity> load() throws FileNotFoundException
    {
        if(!FileUtils.isFileExists(databaseFilename)){
            return new ArrayList<>();
        }

        Gson gson = new Gson();
        BufferedReader jsonBufferedReader = FileUtils.readFile(databaseFilename);
        Type listType = new TypeToken<ArrayList<ApartmentEntity>>() {}.getType();
        return gson.fromJson(jsonBufferedReader, listType);
    }
}
