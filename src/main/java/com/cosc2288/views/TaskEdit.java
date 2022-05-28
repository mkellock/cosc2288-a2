package com.cosc2288.views;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

import com.cosc2288.App;
import com.cosc2288.models.ProjectTask;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.paint.Paint;

/*
 * Checklist functionality from https://stackoverflow.com/questions/28843858/javafx-8-listview-with-checkboxes
*/

public class TaskEdit {

    public static class ActionItem {
        private final UUID actionItemId;
        private final StringProperty name = new SimpleStringProperty();
        private final BooleanProperty on = new SimpleBooleanProperty();

        public ActionItem(UUID id, String name, boolean on) {
            this.actionItemId = id;
            setName(name);
            setOn(on);
        }

        public final StringProperty nameProperty() {
            return this.name;
        }

        public final String getName() {
            return this.nameProperty().get();
        }

        public final void setName(final String name) {
            this.nameProperty().set(name);
        }

        public final BooleanProperty onProperty() {
            return this.on;
        }

        public final boolean isOn() {
            return this.onProperty().get();
        }

        public final void setOn(final boolean on) {
            this.onProperty().set(on);
        }

        public final UUID getId() {
            return actionItemId;
        }

        @Override
        public String toString() {
            return getName();
        }

    }

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
    private ListView<ActionItem> actionItems;

    @FXML
    private Label onTrack;

    private App app;
    private UUID projectColumnId;
    private ProjectTask projectTask;

    public void initialize() {
        actionItems.setCellFactory(CheckBoxListCell.forListView(ActionItem::onProperty));
    }

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

    @FXML
    private void addItem() {
        ActionItem item = new ActionItem(UUID.randomUUID(), addItemText.getText(), false);

        // observe item's on property and display message if it changes:
        item.onProperty().addListener((obs, wasOn, isNowOn) ->
            System.out.println(item.getId().toString() + " changed on state from " + wasOn + " to " + isNowOn)
        );

        actionItems.getItems().add(item);

        addItemText.setText("");
    }

}
