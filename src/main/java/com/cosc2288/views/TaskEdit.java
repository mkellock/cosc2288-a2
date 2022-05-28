package com.cosc2288.views;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

import com.cosc2288.App;
import com.cosc2288.models.ProjectTask;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

public class TaskEdit {

    @FXML
    private TextField task;

    @FXML
    private DatePicker dueDate;

    @FXML
    private TextArea description;

    @FXML
    private ProgressBar progress;

    @FXML
    private TextField addItemText;

    @FXML
    private VBox items;

    @FXML
    private Label onTrack;

    private App app;
    private UUID projectColumnId;
    private ProjectTask projectTask;

    public void setApp(App app) {
        this.app = app;
    }

    public void setColumn(UUID projectColumnId) {
        // Set the project column object
        this.projectColumnId = projectColumnId;
    }

    public void setProjectTask(ProjectTask projectTask) {
        this.projectTask = projectTask;

        if (projectTask != null) {
            task.setText(projectTask.getName());

            if (projectTask.getDueDate() > 0) {
                dueDate.setValue(LocalDate.ofEpochDay(projectTask.getDueDate()));
            }

            onDueDateChange();

            description.setText(projectTask.getDescription());
        }
    }

    @FXML
    private void addItem() {

    }

    @FXML
    private void ok() throws SQLException {
        Alert loginAlert = new Alert(AlertType.ERROR);

        // Validate we have a project name
        if (task.getLength() == 0) {
            loginAlert.setContentText("Please enter a task description");
            loginAlert.show();
        } else {
            // Create a new project object if we haven't specified one
            if (projectTask == null) {
                projectTask = new ProjectTask();
            }

            // Set the project fields
            projectTask.setName(task.getText());

            if (dueDate.getValue() != null) {
                projectTask.setDueDate(dueDate.getValue().toEpochDay());
            }

            projectTask.setDescription(description.getText());
            projectTask.setProjectColumnId(projectColumnId);

            // Fire the main project method
            app.projectTaskOk(projectTask);
        }
    }

    @FXML
    private void onDueDateChange() {
        if (dueDate.getValue() != null) {
            if (dueDate.getValue().isBefore(LocalDate.now())) {
                onTrack.setTextFill(Paint.valueOf("red"));
                onTrack.setText("Overdue");
            } else if (dueDate.getValue().minusDays(2).isBefore(LocalDate.now())) {
                onTrack.setTextFill(Paint.valueOf("darkorange"));
                onTrack.setText("Due soon");
            } else {
                onTrack.setTextFill(Paint.valueOf("lightgreen"));
                onTrack.setText("On track");
            }
        }

        onTrack.setVisible(dueDate.getValue() != null);
    }

    @FXML
    private void cancel() {
        app.projectTaskCancel();
    }
}
