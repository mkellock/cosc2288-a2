package com.cosc2288.controllers;

import java.sql.SQLException;

import com.cosc2288.models.IQuote;

public interface IQuoteController {

    /**
     * Loads a random quote
     * 
     * @return a quote
     * @throws SQLException
     */
    IQuote randomQuote() throws SQLException;

}