package com.flinki.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigWriter {
    private static final String CONFIG_FILE_PATH = "src/main/Resources/config.properties";

    public static void updateProperty(String key, String value) throws IOException {
        File file = new File(CONFIG_FILE_PATH);
        FileInputStream in = new FileInputStream(file);
        Properties props = new Properties();
        props.load(in);
        in.close();

        props.setProperty(key, value);

        FileOutputStream out = new FileOutputStream(file);
        props.store(out, "Updated dynamically");
        out.close();
    }
    
}
