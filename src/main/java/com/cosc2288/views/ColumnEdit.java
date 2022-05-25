package com.cosc2288.views;

import java.sql.SQLException;

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

    public void setApp(App app) {
        this.app = app;
    }

    public void setProject(Project project) {
        // Set the project object
        this.project = project;
    }

    public void setColumn(ProjectColumn projectColumn) {
        // Set the project column object
        this.projectColumn = projectColumn;

        // Set the project text
        if (projectColumn != null) {
            this.column.setText(projectColumn.getName());
        }
    }

    @FXML
    private void ok() throws SQLException {
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

    @FXML
    private void cancel() {
        app.projectColumnCancel();
    }

}
