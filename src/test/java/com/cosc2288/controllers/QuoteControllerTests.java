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

import java.sql.SQLException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QuoteControllerTests extends BaseTests {

    
    /** 
     * @throws SQLException
     */
    @Test
    void ShouldLoadRandomQuote() throws SQLException {
        // Instantiate a new controller
        QuoteController quoteController = new QuoteController(
                getTestConnString());

        // Assert that we retrieve a quote
        Assertions.assertNotNull(quoteController.randomQuote());
    }

}
