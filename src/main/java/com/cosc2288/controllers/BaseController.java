package com.cosc2288.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

abstract class BaseController {
    private final String connectionString;

    protected BaseController(String connectionString) {
        this.connectionString = connectionString;
    }

    protected Connection newConnection() throws SQLException {
        Connection conn = null;

        // Get a connection object
        conn = DriverManager.getConnection(connectionString);

        return conn;
    }
}
