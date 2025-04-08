package com.flinki.Base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class configReader {
    private static Properties properties;

    static {
        try {
            properties = new Properties();
            FileInputStream fis = new FileInputStream("src/main/Resources/config.properties");
            properties.load(fis);
        } catch (IOException e) {
           System.out.println("File Not Found " +e.getMessage());
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
    
}
