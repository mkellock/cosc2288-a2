/**
 * ProjectEdit
 *
 * v1.0
 *
 * 2022-05-29
 *
 * © 2022 Matthew Kellock
 */
package com.cosc2288.views;

import com.cosc2288.IApp;
import com.cosc2288.models.IProject;
import com.cosc2288.models.Project;
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

    private IApp app;
    private IProject proj = new Project();

    /**
     * Sets the App instance
     * 
     * @param app
     */
    public void setApp(IApp app) {
        this.app = app;
    }

    /**
     * Sets the project
     * 
     * @param project
     */
    public void setProject(IProject project) {
        // Set the project object
        this.proj = project;

        // Set the project text
        if (proj != null) {
            this.project.setText(proj.getName());
        }
    }

    /**
     * Handles the Ok button event
     */
    @FXML
    private void ok() {
        Alert loginAlert = new Alert(AlertType.ERROR);

        // Validate we have a project name
        if (project.getLength() == 0) {
            loginAlert.setContentText("Please enter a project name");
            loginAlert.show();
        } else {
            // Create a new project object if we haven't specified one
            if (proj == null) {
                proj = new Project();
            }

            // Set the project name
            proj.setName(project.getText());

            // Fire the main project method
            app.projectOk(proj);
        }
    }

    /**
     * Handles the Cancel button event
     */
    @FXML
    private void cancel() {
        app.projectCancel();
    }

}
