package com.patimer.apartment.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils
{
    public static boolean isFileExists(String filename)
    {
        return Files.exists(Paths.get(filename));
    }

    public static void writeToFile(String text, String filename) throws IOException
    {
        FileWriter writer = new FileWriter(filename);
        writer.write(text);
        writer.close();
    }

    public static BufferedReader readFile(String filename) throws FileNotFoundException
    {
        return new BufferedReader(new FileReader(filename));
    }
}
