package com.cosc2288.views;

import java.sql.SQLException;

import com.cosc2288.App;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ProjectEdit {
    @FXML
    private TextField project;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    private App app;

    public void setApp(App app) {
        this.app = app;
    }

    @FXML
    private void ok() throws SQLException {
        Alert loginAlert = new Alert(AlertType.ERROR);

        if (project.getLength() == 0) {
            loginAlert.setContentText("Please enter a project name");
            loginAlert.show();
        } else {
            app.projectOk(project.getText());
        }
    }

    @FXML
    private void cancel() {
        app.projectCancel();
    }

}
