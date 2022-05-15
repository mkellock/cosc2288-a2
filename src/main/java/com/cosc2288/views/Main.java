/**
 * ActionItem
 *
 * v1.0
 *
 * 2022-04-12
 *
 * © 2022 Matthew Kellock
 */
package com.cosc2288.views;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import com.cosc2288.App;
import com.cosc2288.models.Project;
import com.cosc2288.models.User;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class Main {
    @FXML
    private Text quote;

    @FXML
    private Text userFullName;

    @FXML
    private ImageView userImage;

    @FXML
    private Button logOutButton;

    @FXML
    private Button profileButton;

    @FXML
    private MenuItem newProjectMenuItem;

    @FXML
    private MenuItem deleteMenuItem;

    @FXML
    private TabPane workArea;

    private App app;

    public void setApp(App app) {
        this.app = app;
    }

    public void setQuote(String quoteMessage) {
        quote.setText(quoteMessage);
    }

    public void setUser(User user) {
        userFullName.setText(user.getFirstName() + " " + user.getLastName());
        userImage.setImage(new Image(new ByteArrayInputStream(user.getImage())));
    }

    @FXML
    private void profile() throws IOException {
        app.profile();
    }

    @FXML
    private void logOut() {
        app.logOut();
    }

    @FXML
    private void newProject() throws IOException {
        app.project(false);
    }

    public void loadProjects(List<Project> projects) {
        workArea.getTabs().clear();

        EventHandler<Event> event = e -> {
            deleteMenuItem.setDisable(false);
            App.setProject(UUID.fromString(workArea.getSelectionModel().getSelectedItem().getId()));
        };

        for (Project project : projects) {
            Tab projectTab = new Tab();

            projectTab.setId(project.getProjectId().toString());
            projectTab.setText(project.getName());

            projectTab.setOnSelectionChanged(event);

            workArea.getTabs().add(projectTab);
        }
    }

    @FXML
    private void delete() throws SQLException {
        app.deleteProject();
    }
}
