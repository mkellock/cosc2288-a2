/**
 * BaseController
 *
 * v1.0
 *
 * 2022-04-12
 *
 * © 2022 Matthew Kellock
 */
package com.cosc2288.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

abstract class BaseController implements IBaseController {
    private final String connectionString;

    /**
     * Constructor for the base controller
     * 
     * @param connectionString
     */
    protected BaseController(String connectionString) {
        this.connectionString = connectionString;
    }

    /**
     * Generates a new database connection
     * 
     * @return Connection
     * @throws SQLException
     */
    protected Connection newConnection() throws SQLException {
        Connection conn = null;

        // Get a connection object
        conn = DriverManager.getConnection(connectionString);

        return conn;
    }
}
