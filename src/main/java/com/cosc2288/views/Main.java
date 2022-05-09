/**
 * ActionItem
 *
 * v1.0
 *
 * 2022-04-12
 *
 * © 2022 Matthew Kellock
 */
package com.cosc2288.views;

import java.sql.SQLException;

import com.cosc2288.controllers.QuoteController;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class Main {
    @FXML
    private Text quote;

    public void setQuote(String quoteMessage) {
        quote.setText(quoteMessage);
    }
}
