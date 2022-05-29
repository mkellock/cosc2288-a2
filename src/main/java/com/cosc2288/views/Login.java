/**
 * Login
 *
 * v1.0
 *
 * 2022-05-29
 *
 * © 2022 Matthew Kellock
 */
package com.cosc2288.views;

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

    private App app;

    
    /** 
     * Sets the app instance
     * @param app
     */
    public void setApp(App app) {
        this.app = app;
    }

    
    /** 
     * Handles the New User button event
     */
    @FXML
    private void newUser() {
        // Clear the form
        username.setText("");
        password.setText("");

        // Trigger the new user form
        app.newEditUser(false);
    }

    
    /** 
     * Handles the Ok button event
     */
    @FXML
    private void ok() {
        Alert loginAlert = new Alert(AlertType.ERROR);

        String usernameVal = username.getText();
        String passwordVal = password.getText();

        if (usernameVal.length() == 0) {
            loginAlert.setContentText("Please enter a username");
            loginAlert.show();
        } else if (passwordVal.length() == 0) {
            loginAlert.setContentText("Please enter a password");
            loginAlert.show();
        } else {
            // Clear the form
            username.setText("");
            password.setText("");

            // Set the login as OK
            app.loginOk(usernameVal, passwordVal);
        }
    }

}
