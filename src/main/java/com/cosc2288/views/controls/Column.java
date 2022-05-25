package com.cosc2288.views.controls;

import java.util.LinkedList;

import javafx.beans.binding.Bindings;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Column extends AnchorPane {
    public Column() {
        // Add the column styling
        this.styleProperty().bind(Bindings.format("-fx-border-insets: 1; -fx-padding: 2; -fx-border-color: Black"));
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
        menuItems.add(editTitle);

        // Add the add item menu item
        MenuItem addItem = new MenuItem();
        addItem.setText("Add item");
        menuItems.add(addItem);

        // Add a seperator
        menuItems.add(new SeparatorMenuItem());

        // Add the delete column menu item
        MenuItem deleteColumn = new MenuItem();
        deleteColumn.setText("Delete Column");
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
        title.setText(
                "This is some sample text, this is some sample text, this is some sample text, this is some sample text...");
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

        VBox vbox = new VBox();

        for (int i = 0; i < 10; i++) {
            Pane item = new Pane();
            item.setMinHeight(150);

            item.setOnDragDetected(
                    event -> {
                        /* drag was detected, start a drag-and-drop gesture */
                        /* allow any transfer mode */
                        Dragboard db = item.startDragAndDrop(TransferMode.COPY_OR_MOVE);

                        /* Put a string on a dragboard */
                        ClipboardContent content = new ClipboardContent();
                        content.putString("Well, hello there!");
                        db.setContent(content);

                        event.consume();
                    });

            item.setOnDragOver(event -> {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                event.consume();
            });

            item.setOnDragDropped(
                    event -> {
                        /* data dropped */
                        /* if there is a string data on dragboard, read it and use it */
                        Dragboard db = event.getDragboard();
                        boolean success = false;
                        if (db.hasString()) {
                            System.out.println(db.getString());
                            success = true;
                        }
                        /*
                         * let the source know whether the string was successfully
                         * transferred and used
                         */
                        event.setDropCompleted(success);

                        event.consume();
                    });

            if (i % 2 == 0) {
                item.styleProperty().bind(Bindings.format("-fx-background-color: Black"));
            }

            vbox.getChildren().add(item);
        }

        // Add the anchor pane to the tabs contents
        scrollPane.contentProperty().set(vbox);

        // Add the title label to the anchor
        this.getChildren().add(scrollPane);
    }
}
