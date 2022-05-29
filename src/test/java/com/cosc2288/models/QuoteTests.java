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

    private static final String MESSAGE = "Quote";

    @Test
    void ShouldConstructAndGetAllProperties() {
        IQuote quote = new Quote(MESSAGE);
        Assertions.assertEquals(MESSAGE, quote.getMessage());
    }

    @Test
    void shouldSetAndGetQuote() {
        IQuote quote = new Quote();
        quote.setMessage(MESSAGE);
        Assertions.assertEquals(MESSAGE, quote.getMessage());
    }

}
