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

import com.cosc2288.App;
import com.cosc2288.models.Project;
import com.cosc2288.models.ProjectColumn;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ColumnEdit {

    @FXML
    private TextField column;

    private App app;
    private Project project;
    private ProjectColumn projectColumn;

    
    /** 
     * Sets the app instance
     * @param app
     */
    public void setApp(App app) {
        this.app = app;
    }

    
    /** 
     * Sets the project item
     * @param project
     */
    public void setProject(Project project) {
        // Set the project object
        this.project = project;
    }

    
    /** 
     * Sets the project column
     * @param projectColumn
     */
    public void setColumn(ProjectColumn projectColumn) {
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
