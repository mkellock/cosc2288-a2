package com.cosc2288.views;

import com.cosc2288.App;
import java.io.IOException;
import java.sql.SQLException;
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

    public void setApp(App app) {
        this.app = app;
    }

    @FXML
    private void newUser() throws IOException {
        app.newEditUser(false);
    }

    @FXML
    private void ok() throws SQLException {
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
