package com.cosc2288.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

abstract class BaseController {
    private final Connection connection;

    protected BaseController() {
        String dbConnSetting = "";
        Connection conn = null;

        try (
                FileInputStream settingsFile = 
                new FileInputStream("app.properties")) {
            Properties appSettings = new Properties();

            // load config.properties file
            appSettings.load(settingsFile);

            // This is where you add your config variables:
            dbConnSetting = (String) appSettings.get("connnection_string");
        } catch (IOException e) {
            // Trap the error (returning null if we have an exception)
            e.printStackTrace();
        }

        // If we have a value set for the db connection
        if (dbConnSetting.length() > 0) {
            try {
                // Get a connection object
                conn = DriverManager.getConnection(dbConnSetting);
            } catch (SQLException e) {
                // Trap the error (returning null if we have an exception)
                e.printStackTrace();
            }
        }

        this.connection = conn;
    }

    protected Connection getConnection() {
        return connection;
    }
}
