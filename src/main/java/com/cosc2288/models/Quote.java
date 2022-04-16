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
     * @return the quote
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
