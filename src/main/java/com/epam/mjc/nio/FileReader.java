package com.epam.mjc.nio;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;



public class FileReader {
    Logger logger = Logger.getLogger(FileReader.class.getName());

    public Profile getDataFromFile(File file) {
        Map<String, String> properties = new HashMap<>();
        try (BufferedReader input = new BufferedReader(new java.io.FileReader(file))) {
            String line;
            while (Objects.nonNull(line = input.readLine())) {
                String[] splited = line.split(": ");
                properties.put(splited[0], splited[1]);
            }
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Profile(properties.get("Name"),
                Integer.valueOf(properties.get("Age")),
                properties.get("Email"),
                Long.valueOf(properties.get("Phone")));
    }
}


