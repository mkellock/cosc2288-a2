/**
 * Task
 *
 * v1.0
 *
 * 2022-05-29
 *
 * © 2022 Matthew Kellock
 */
package com.cosc2288.views.controls;

import com.cosc2288.App;
import com.cosc2288.models.ActionItem;
import com.cosc2288.models.ProjectColumn;
import com.cosc2288.models.ProjectTask;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.UUID;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class Task extends AnchorPane {

    /**
     * Initializes a project task
     * @param app
     * @param projectColumn
     * @param projectTask
     */
    public Task(App app, ProjectColumn projectColumn, ProjectTask projectTask) {
        this.styleProperty().bind(Bindings.format(
                "-fx-border-insets: 1;" +
                        "-fx-padding: 2; " +
                        "-fx-border-color: Black"));
        this.setMinHeight(150);
        this.setMaxHeight(this.getMinHeight());

        // Create a delete menu button
        Button deleteButton = new Button();
        deleteButton.setText("Delete");
        deleteButton.setOnAction(e -> app.deleteProjectTask(projectTask));

        // Add the menu item to the anchor
        this.getChildren().add(deleteButton);

        // Anchor the delete button
        AnchorPane.setTopAnchor(deleteButton, 0D);
        AnchorPane.setRightAnchor(deleteButton, 0D);

        // Create an edit menu button
        Button editButton = new Button();
        editButton.setText("Edit");
        editButton.setOnAction(e -> app.taskAddEdit(true,
                projectColumn.getProjectColumnId(), projectTask));

        // Add the menu item to the anchor
        this.getChildren().add(editButton);

        // Anchor the delete button
        AnchorPane.setTopAnchor(editButton, 0D);
        AnchorPane.setRightAnchor(editButton, 55D);

        // Create a label for the task
        Label taskLabel = new Label();
        taskLabel.setText(projectTask.getName());
        taskLabel.setTextOverrun(OverrunStyle.ELLIPSIS);
        taskLabel.setWrapText(true);
        taskLabel.setAlignment(Pos.TOP_LEFT);

        // Add the title label to the anchor
        this.getChildren().add(taskLabel);

        // Anchor the title label
        AnchorPane.setTopAnchor(taskLabel, 30D);
        AnchorPane.setLeftAnchor(taskLabel, 0D);
        AnchorPane.setRightAnchor(taskLabel, 0D);
        AnchorPane.setBottomAnchor(taskLabel, 0D);

        // Create a small font
        Font smallFont = new Font(9);

        // If we have a due date set, create a label for the due date
        if (projectTask.getDueDate() > 0) {
            Label dueLabel = new Label();
            LocalDate dueDate = LocalDate.ofEpochDay(projectTask.getDueDate());

            dueLabel.setText("Due: " + dueDate.format(
                    DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
            dueLabel.setFont(smallFont);

            if (dueDate.isBefore(LocalDate.now())) {
                dueLabel.styleProperty()
                        .bind(Bindings.format("-fx-background-color: red"));
            } else if (dueDate.minusDays(2).isBefore(LocalDate.now())) {
                dueLabel.styleProperty().bind(
                        Bindings.format("-fx-background-color: darkorange"));
            }

            // Add the due label to the anchor
            this.getChildren().add(dueLabel);

            // Anchor the title label
            AnchorPane.setTopAnchor(dueLabel, 0D);
            AnchorPane.setLeftAnchor(dueLabel, 0D);
        }

        // If we have action items
        if (!projectTask.getActionItems().isEmpty()) {
            // Create a label for the due date
            Label subTaskLabel = new Label();

            // Set the completed item count to 0
            int completedItemCount = 0;

            // Loop through the action items
            for (ActionItem actionItem : projectTask.getActionItems()) {
                // If the action item is complete
                if (actionItem.isComplete()) {
                    // Add a count to the action item
                    completedItemCount++;
                }
            }

            subTaskLabel.setText("Actions: " + completedItemCount + "/"
                    + projectTask.getActionItems().size());
            subTaskLabel.setFont(smallFont);

            if (completedItemCount == projectTask.getActionItems().size()) {
                subTaskLabel.styleProperty()
                        .bind(Bindings
                                .format("-fx-background-color: lightgreen"));
            }

            // Add the due label to the anchor
            this.getChildren().add(subTaskLabel);

            // Anchor the title label
            AnchorPane.setTopAnchor(subTaskLabel, 15D);
            AnchorPane.setLeftAnchor(subTaskLabel, 0D);
        }

        this.setOnDragDetected(event -> {
            /* drag was detected, start a drag-and-drop gesture */
            /* allow any transfer mode */
            Dragboard db = this.startDragAndDrop(TransferMode.COPY_OR_MOVE);

            /* Put a string on a dragboard */
            ClipboardContent content = new ClipboardContent();
            content.putString(projectTask.getProjectTaskId().toString());
            db.setContent(content);

            event.consume();
        });

        // Accept drag events
        this.setOnDragOver(event -> {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            event.consume();
        });

        // Trap a task drop event
        this.setOnDragDropped(event -> {
            /* data dropped */
            /* if there is a string data on dragboard, read it and use it */
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                success = true;
                app.dragProjectTask(UUID.fromString(db.getString()),
                        projectTask);
            }
            /*
             * let the source know whether the string was successfully
             * transferred and used
             */
            event.setDropCompleted(success);

            event.consume();
        });
    }
}
