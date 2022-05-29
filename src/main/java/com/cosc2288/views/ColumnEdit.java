/**
 * ColumnEdit
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
import com.cosc2288.models.IProjectColumn;
import com.cosc2288.models.ProjectColumn;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ColumnEdit {

    @FXML
    private TextField column;

    private IApp app;
    private IProject project;
    private IProjectColumn projectColumn;

    /**
     * Sets the app instance
     * 
     * @param app
     */
    public void setApp(IApp app) {
        this.app = app;
    }

    /**
     * Sets the project item
     * 
     * @param project
     */
    public void setProject(IProject project) {
        // Set the project object
        this.project = project;
    }

    /**
     * Sets the project column
     * 
     * @param projectColumn
     */
    public void setColumn(IProjectColumn projectColumn) {
        // Set the project column object
        this.projectColumn = projectColumn;

        // Set the project text
        if (projectColumn != null) {
            this.column.setText(projectColumn.getName());
        }
    }

    /**
     * Handles the OK button event
     */
    @FXML
    private void ok() {
        Alert loginAlert = new Alert(AlertType.ERROR);

        // Validate we have a project name
        if (column.getLength() == 0) {
            loginAlert.setContentText("Please enter a column name");
            loginAlert.show();
        } else {
            // Create a new project object if we haven't specified one
            if (projectColumn == null) {
                projectColumn = new ProjectColumn();
            }

            // Set the project columns project
            projectColumn.setProjectId(project.getProjectId());

            // Set the column name
            projectColumn.setName(column.getText());

            // Fire the main project method
            app.projectColumnOk(projectColumn);
        }
    }

    /**
     * Handles the Cancel button event
     */
    @FXML
    private void cancel() {
        app.projectColumnCancel();
    }

}
