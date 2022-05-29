/**
 * QuoteMessage
 *
 * v1.0
 *
 * 2022-04-12
 *
 * © 2022 Matthew Kellock
 */
package com.cosc2288.models;

public class Quote {

    private String message;

    /**
     * Constructs a quote
     */
    public Quote() {
        // Empty constructor
    }

    /**
     * Constructs a quote
     * 
     * @param message
     */
    public Quote(String message) {
        this.message = message;
    }

    /**
     * Gets the quote message
     * @return String
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the quote message
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
