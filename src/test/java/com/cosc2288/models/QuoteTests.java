/**
 * QuoteTests
 *
 * v1.0
 *
 * 2022-04-12
 *
 * © 2022 Matthew Kellock
 */
package com.cosc2288.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class QuoteTests {

    private static final String QUOTE = "Quote";

    @Test
    void shouldSetAndGetQuote() {
        Quote quote = new Quote();
        quote.setMessage(QUOTE);
        Assertions.assertEquals(QUOTE, quote.getMessage());
    }

}
