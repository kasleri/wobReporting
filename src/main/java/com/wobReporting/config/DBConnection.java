package com.wobReporting.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    public static Connection getConnection() {
        Properties prop = PropertiesLoader.loadProperties();

        try {
            return DriverManager.getConnection(prop.getProperty("db.url"), prop.getProperty("db.user"), prop.getProperty("db.password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}