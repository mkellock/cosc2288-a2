package com.cosc2288.views.controls;

import javafx.beans.binding.Bindings;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class ProjectTab extends Tab {
    private HBox hbox;

    public ProjectTab() {
        // Create a new anchor pane
        AnchorPane anchorPane = new AnchorPane();

        // Create a new scrollpane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.fitToHeightProperty().set(true);

        // Add the scrollpane to the anchorpane
        anchorPane.getChildren().addAll(scrollPane);

        // Set the anchors for the scrollpane
        AnchorPane.setBottomAnchor(scrollPane, 0D);
        AnchorPane.setTopAnchor(scrollPane, 0D);
        AnchorPane.setLeftAnchor(scrollPane, 0D);
        AnchorPane.setRightAnchor(scrollPane, 0D);

        this.hbox = new HBox();
        scrollPane.setContent(hbox);

        for (int i = 0; i < 10; i++) {
            Pane pane = new Pane();
            pane.setMinWidth(200);

            if (i % 2 == 0) {
                pane.styleProperty().bind(Bindings.format("-fx-background-color: black"));
            }

            hbox.getChildren().add(pane);
        }

        // Add the anchor pane to the tabs contents
        this.contentProperty().set(anchorPane);
    }

    public HBox getHBox() {
        return hbox;
    }
}
