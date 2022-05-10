package com.cosc2288.views;

import com.cosc2288.App;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Login {
    @FXML
    private TextField username;

    @FXML
    private TextField password;

    private App app;
    private Stage stage;

    public void setApp(App app) {
        this.app = app;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void newUser() throws IOException {
        app.newEditUser(stage, false);
    }

    @FXML
    private void ok() throws IOException, SQLException {
        Alert loginAlert = new Alert(AlertType.ERROR);

        if (username.getLength() == 0) {
            loginAlert.setContentText("Please enter a username");
            loginAlert.show();
        } else if (password.getLength() == 0) {
            loginAlert.setContentText("Please enter a password");
            loginAlert.show();
        } else {
            app.loginOk(username.getText(), password.getText(), stage);
        }
    }

}
