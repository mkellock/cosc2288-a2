/**
 * TaskEdit
 *
 * v1.0
 *
 * 2022-05-29
 *
 * © 2022 Matthew Kellock
 */
package com.cosc2288.views;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.UUID;
import com.cosc2288.App;
import com.cosc2288.models.ActionItem;
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
 * Checklist functionality from 
 * stackoverflow.com/questions/28843858/javafx-8-listview-with-checkboxes
*/

public class TaskEdit {

    /**
     * Class to represent a check list action item for the list
     */
    public static class CheckListActionItem {
        private final UUID actionItemId;
        private final StringProperty name = new SimpleStringProperty();
        private final BooleanProperty on = new SimpleBooleanProperty();

        /**
         * Constructor for the check list action item
         * @param id
         * @param name
         * @param on
         */
        public CheckListActionItem(UUID id, String name, boolean on) {
            this.actionItemId = id;
            setName(name);
            setOn(on);
        }

        /**
         * Returns the name property of the list item
         * @return StringProperty
         */
        public final StringProperty nameProperty() {
            return this.name;
        }

        /**
         * Gets the name of the list item
         * @return String
         */
        public final String getName() {
            return this.nameProperty().get();
        }

        /**
         * Sets the name of the list item
         * @param name
         */
        public final void setName(final String name) {
            this.nameProperty().set(name);
        }

        /**
         * Returns the checkstate of the list item
         * @return BooleanProperty
         */
        public final BooleanProperty onProperty() {
            return this.on;
        }

        /**
         * Returns if the current state of the list item is checked
         * @return
         */
        public final boolean isOn() {
            return this.onProperty().get();
        }

        /**
         * Sets the checked state of the list item
         * @param on
         */
        public final void setOn(final boolean on) {
            this.onProperty().set(on);
        }

        /**
         * Gets the ID of the list item
         * @return
         */
        public final UUID getId() {
            return actionItemId;
        }

        /**
         * Returns the name of the list item when calling toString
         */
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
    private ListView<CheckListActionItem> actionItems;

    @FXML
    private Label onTrack;

    private App app;
    private UUID projectColumnId;
    private ProjectTask projectTask = new ProjectTask();

    /**
     * Constructor of the task edit view
     */
    public TaskEdit() {
        projectTask.setActionItems(new LinkedList<>());
    }

    /**
     * Initialise the view
     */
    public void initialize() {
        actionItems.setCellFactory(
                CheckBoxListCell.forListView(CheckListActionItem::onProperty));

        onDueDateChange();
    }

    
    /** 
     * Sets the App instance
     * @param app
     */
    public void setApp(App app) {
        this.app = app;
    }

    
    /** 
     * Sets the project column id
     * @param projectColumnId
     */
    public void setColumn(UUID projectColumnId) {
        // Set the project column object
        this.projectColumnId = projectColumnId;
    }

    
    /** 
     * Sets the project task
     * @param projectTask
     */
    public void setProjectTask(ProjectTask projectTask) {
        if (projectTask != null) {
            this.projectTask = projectTask;

            task.setText(projectTask.getName());

            if (projectTask.getDueDate() > 0) {
                dueDate.setValue(
                        LocalDate.ofEpochDay(projectTask.getDueDate()));
            }

            onDueDateChange();

            description.setText(projectTask.getDescription());

            for (ActionItem actionItem : projectTask.getActionItems()) {
                addActionItemToList(actionItem.getActionItemId(),
                        actionItem.getDescription(), actionItem.isComplete());
            }
        }
    }

    
    /** 
     * Handles the Ok button event
     */
    @FXML
    private void ok() {
        Alert loginAlert = new Alert(AlertType.ERROR);

        // Validate we have a project name
        if (task.getLength() == 0) {
            loginAlert.setContentText("Please enter a task name");
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
            } else {
                projectTask.setDueDate(null);
            }

            projectTask.setDescription(description.getText());
            projectTask.setProjectColumnId(projectColumnId);

            // Fire the main project method
            app.projectTaskOk(projectTask);
        }
    }

    /**
     * Handles due date field changes
     */
    @FXML
    private void onDueDateChange() {
        if (dueDate.getValue() != null) {
            if (dueDate.getValue().isBefore(LocalDate.now())) {
                onTrack.setTextFill(Paint.valueOf("red"));
                onTrack.setText("Overdue");
            } else if (dueDate.getValue().minusDays(2)
                    .isBefore(LocalDate.now())) {
                onTrack.setTextFill(Paint.valueOf("darkorange"));
                onTrack.setText("Due soon");
            } else {
                onTrack.setTextFill(Paint.valueOf("lightgreen"));
                onTrack.setText("On track");
            }
        }

        onTrack.setVisible(dueDate.getValue() != null);
    }

    /**
     * Cancels editing the project item
     */
    @FXML
    private void cancel() {
        app.projectTaskCancel();
    }

    /**
     * Event to add an item to the action item list
     */
    @FXML
    private void addItem() {
        Alert loginAlert = new Alert(AlertType.ERROR);

        // Validate we have a action item description
        if (addItemText.getText().length() == 0) {
            loginAlert
                    .setContentText("Please enter an action item description");
            loginAlert.show();
        } else {
            UUID actionItemID = UUID.randomUUID();

            addActionItemToList(actionItemID, addItemText.getText(), false);

            // Add an ActionItem to the project task
            projectTask.getActionItems().add(new ActionItem(
                    actionItemID,
                    addItemText.getText(),
                    false,
                    projectTask.getProjectTaskId()));

            // Set the text to empty
            addItemText.setText("");
        }
    }

    
    /** 
     * Adds an action item to the list
     * @param id
     * @param description
     * @param checked
     */
    private void addActionItemToList(UUID id, String description,
            Boolean checked) {
        // Create a new CheckListActionItem for the list
        CheckListActionItem item = new CheckListActionItem(id, description,
                checked);

        // Add an observer to the checklist item
        item.onProperty()
                .addListener((obs, oldVal,
                        currentVal) -> {
                    // Loop through the action items
                    for (ActionItem actionItem : projectTask
                            .getActionItems()) {
                        // If we have the correct action item
                        if (actionItem.getActionItemId()
                                .compareTo(item.getId()) == 0) {
                            // Set its completion state
                            actionItem.setComplete(currentVal);

                            // Update the progress bar
                            updateCompletion();

                            // Bug out
                            break;
                        }
                    }
                });

        // Add the item to the list
        actionItems.getItems().add(item);

        // Update the progress bar
        updateCompletion();
    }

    /**
     * Updates the completion bar
     */
    private void updateCompletion() {
        // Set the completed item count to 0
        int completedItemCount = 0;

        // If we have action items
        if (!projectTask.getActionItems().isEmpty()) {
            // Loop through the action items
            for (ActionItem actionItem : projectTask.getActionItems()) {
                // If the action item is complete
                if (actionItem.isComplete()) {
                    // Add a count to the action item
                    completedItemCount++;
                }
            }

            // Update the prgress bar with the % complete
            progress.setProgress(
                    completedItemCount / Double
                            .valueOf(projectTask.getActionItems().size()));
        }
    }

}
