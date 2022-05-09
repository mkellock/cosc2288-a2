package com.cosc2288.views;

import javax.swing.JOptionPane;

import com.cosc2288.App;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class Login {
    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private void newUser() {
        App.newUser();
    }

    @FXML
    private void ok() {
        Alert loginAlert = new Alert(AlertType.ERROR);
        
        if (username.getLength() == 0) {
            loginAlert.setContentText("Please enter a username");
            loginAlert.show();
        } else if (password.getLength() == 0) {
            loginAlert.setContentText("Please enter a password");
            loginAlert.show();
        } else {
            App.ok(username.getText(), password.getText());
        }
    }

}
