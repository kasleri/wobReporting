package com.wobReporting.config;

import com.wobReporting.WobReporting;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertiesLoader {

    public static Properties prop = null;

    public static Properties loadProperties() {
        if (prop == null) {

            try (InputStream input = WobReporting.class.getClassLoader().getResourceAsStream("application.properties")) {

                if (input == null) {
                    throw new IOException("Unable to find application.properties");
                }

                prop = new Properties();
                prop.load(input);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return prop;
    }

    public static String getProperty(String key) {
        if (prop == null) {
            loadProperties();
        }
        return prop.getProperty(key);
    }
}
