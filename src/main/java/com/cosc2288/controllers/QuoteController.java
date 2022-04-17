/**
 * QuoteMessageController
 *
 * v1.0
 *
 * 2022-04-13
 *
 * © 2022 Matthew Kellock
 */
package com.cosc2288.controllers;

import com.cosc2288.models.Quote;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuoteController extends BaseController {
    /**
     * Constructor for the class
     */
    public QuoteController(String connectionString) {
        super(connectionString);
    }

    /**
     * Loads a random quote
     * 
     * @return a quote
     * @throws SQLException
     */
    public Quote randomQuote() throws SQLException {
        // The select query
        String sql = "SELECT message FROM quotes ORDER BY RANDOM() LIMIT 1";

        // Run the DB select statement
        try (Connection conn = this.newConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet queryResults = pstmt.executeQuery();

            // Assign the DB results to the result list
            queryResults.next();

            return new Quote(queryResults.getString("message"));
        } catch (SQLException e) {
            // Return that the operation failed
            e.printStackTrace();
            throw e;
        }
    }

}
