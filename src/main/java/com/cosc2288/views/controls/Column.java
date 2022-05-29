/**
 * Column
 *
 * v1.0
 *
 * 2022-05-29
 *
 * © 2022 Matthew Kellock
 */
package com.cosc2288.views.controls;

import com.cosc2288.IApp;
import com.cosc2288.models.IProjectColumn;
import com.cosc2288.models.IProjectTask;

import javafx.beans.binding.Bindings;
import java.util.LinkedList;
import java.util.UUID;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class Column extends AnchorPane {

    /**
     * Initilizes the project column
     * 
     * @param app
     * @param projectColumn
     */
    public Column(IApp app, IProjectColumn projectColumn) {
        // Add the column styling
        this.styleProperty().bind(Bindings.format(
                "-fx-border-insets: 1;" +
                        "-fx-padding: 2; " +
                        "-fx-border-color: Black"));
        this.setMaxWidth(250);
        this.setMinWidth(this.getMaxWidth());

        // Set up a menu button
        MenuButton menuButton = new MenuButton();
        menuButton.setText("Col. Actions");

        // Create a list of menu items
        LinkedList<MenuItem> menuItems = new LinkedList<>();

        // Add the edit title menu item
        MenuItem editTitle = new MenuItem();
        editTitle.setText("Edit title");
        editTitle.setOnAction(e -> app.columnAddEdit(true, projectColumn));
        menuItems.add(editTitle);

        // Add the add item menu item
        MenuItem addItem = new MenuItem();
        addItem.setText("Add item");
        addItem.setOnAction(e -> app.taskAddEdit(false,
                projectColumn.getProjectColumnId(), null));
        menuItems.add(addItem);

        // Add a seperator
        menuItems.add(new SeparatorMenuItem());

        // Add the delete column menu item
        MenuItem deleteColumn = new MenuItem();
        deleteColumn.setText("Delete Column");
        deleteColumn.setOnAction(e -> app.deleteProjectColumn(projectColumn));
        menuItems.add(deleteColumn);

        // Add the menu items
        menuButton.getItems().addAll(menuItems);

        // Add the menu item to the anchor
        this.getChildren().add(menuButton);

        // Anchor the button
        AnchorPane.setTopAnchor(menuButton, 0D);
        AnchorPane.setRightAnchor(menuButton, 0D);

        // Create the title label
        Label title = new Label();
        title.setText(projectColumn.getName());
        title.setTextOverrun(OverrunStyle.ELLIPSIS);

        // Add the title label to the anchor
        this.getChildren().add(title);

        // Anchor the title label
        AnchorPane.setTopAnchor(title, 5D);
        AnchorPane.setLeftAnchor(title, 0D);
        AnchorPane.setRightAnchor(title, 100D);

        // Create the scroll box
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);

        // Anchor the scroll box
        AnchorPane.setTopAnchor(scrollPane, 28D);
        AnchorPane.setLeftAnchor(scrollPane, 0D);
        AnchorPane.setRightAnchor(scrollPane, 0D);
        AnchorPane.setBottomAnchor(scrollPane, 0D);

        // Add a vbox to the scroll pane
        VBox vbox = new VBox();

        for (IProjectTask projectTask : projectColumn.getProjectTasks()) {
            Task item = new Task(app, projectColumn, projectTask);
            vbox.getChildren().add(item);
        }

        // Add the anchor pane to the tabs contents
        scrollPane.contentProperty().set(vbox);

        // Add the scroll pane to the anchor
        this.getChildren().add(scrollPane);

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
                        projectColumn);
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
