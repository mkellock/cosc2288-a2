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
import com.cosc2288.views.controls.ProjectTab;

import javafx.beans.binding.Bindings;
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
    private MenuItem addColumnMenuItem;

    @FXML
    private MenuItem renameMenuItem;

    @FXML
    private MenuItem setAsDefaultMenuItem;

    @FXML
    private MenuItem unsetAsDefaultMenuItem;

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
        if (user == null) {
            userFullName.setText(null);
            userImage.setImage(null);
        } else {
            userFullName.setText(user.getFirstName() + " " + user.getLastName());
            userImage.setImage(new Image(new ByteArrayInputStream(user.getImage())));
        }
    }

    @FXML
    private void profile() throws IOException {
        // Show the profile dialog
        app.profile();
    }

    @FXML
    private void logOut() throws SQLException {
        // Log out the user
        app.logOut();
    }

    @FXML
    private void newProject() throws IOException {
        // Open the project edit dialog in add mode
        app.projectAddEdit(false);
    }

    private void setDefaultMenuOptions() {
        // Check if we have a user set
        if (App.getLoggedInUser() != null) {
            // If we are not on a default tab, or one has not been set
            if (App.getLoggedInUser().getDefaultProjectId() == null || App.getLoggedInUser().getDefaultProjectId()
                    .compareTo(App.getSelectedProject().getProjectId()) != 0) {
                // Enable setting
                setAsDefaultMenuItem.setDisable(false);
                unsetAsDefaultMenuItem.setDisable(true);
            } else {
                // Else enable unsetting
                setAsDefaultMenuItem.setDisable(true);
                unsetAsDefaultMenuItem.setDisable(false);
            }
        }
    }

    public void loadProjects(List<Project> projects, UUID selectedProjectId, UUID defaultProjectId) {
        // Clear the tabs
        workArea.getTabs().clear();

        // If we have projects
        if (projects != null) {
            // Create an eventhandler for selecting a project
            EventHandler<Event> event = e -> {
                // Enable the project menu items
                setProjectMenuItemsDisabled(false);

                // Set the currently selected project
                App.setSelectedProjectById(UUID.fromString(workArea.getSelectionModel().getSelectedItem().getId()));

                // Change the menu options
                setDefaultMenuOptions();
            };

            // Loop through the projects
            for (Project project : projects) {
                // Create a new tab instance
                ProjectTab projectTab = new ProjectTab(this.app, project);

                // The the tabs id, text, and project tabs
                projectTab.setId(project.getProjectId().toString());
                projectTab.setText(project.getName());

                // Add the handler for the selection change
                projectTab.setOnSelectionChanged(event);

                // Add the tab
                workArea.getTabs().add(projectTab);
            }

            // If the selected project ID isn't null, set the selected tab to the specified
            // project
            if (selectedProjectId != null) {
                selectTabByProjectId(selectedProjectId);
            }

            // If the default project ID isn't null, set the default project tab to yellow
            if (defaultProjectId != null) {
                colourDefaultTab(defaultProjectId);
            }
        } else {
            // Disable the project menu items
            setProjectMenuItemsDisabled(true);

            // Set the currently selected project to null
            App.setSelectedProject(null);
        }
    }

    private void selectTabByProjectId(UUID selectedProjectId) {
        // Loop through the tabs
        for (Tab projectTab : workArea.getTabs()) {
            // If the tab's Id equals the default tab
            if (projectTab.getId().equals(selectedProjectId.toString())) {
                // Select the tab
                workArea.getSelectionModel().select(projectTab);

                // Exit the loop
                break;
            }
        }
    }

    private void colourDefaultTab(UUID defaultProjectId) {
        // Loop through the tabs
        for (Tab projectTab : workArea.getTabs()) {
            // If the tab's Id equals the default tab
            if (defaultProjectId != null && projectTab.getId().equals(defaultProjectId.toString())) {
                // Select the tab
                projectTab.styleProperty().bind(Bindings.format("-fx-background-color: yellow"));
            } else {
                // Remove the background styling
                projectTab.styleProperty().bind(Bindings.format("-fx-background-color: -fx-color"));
            }
        }
    }

    @FXML
    private void delete() throws SQLException {
        // Delete the selected project
        app.deleteProject();

        // If we have no tabs left
        if (workArea.getTabs().isEmpty()) {
            // Disable the project menu items
            setProjectMenuItemsDisabled(true);
        }
    }

    private void setProjectMenuItemsDisabled(boolean state) {
        addColumnMenuItem.setDisable(state);
        renameMenuItem.setDisable(state);
        setAsDefaultMenuItem.setDisable(state);
        unsetAsDefaultMenuItem.setDisable(state);
        deleteMenuItem.setDisable(state);
    }

    @FXML
    private void setDefault() throws SQLException {
        // Set the default tab colour
        colourDefaultTab(App.getSelectedProject().getProjectId());

        // Sets the current project as default
        app.setDefaultProject();

        // Change the menu options
        setDefaultMenuOptions();
    }

    @FXML
    private void unsetDefault() throws SQLException {
        // Unset the default tab colour
        colourDefaultTab(null);

        // Sets the current project as default
        app.unsetDefaultProject();

        // Change the menu options
        setDefaultMenuOptions();
    }

    @FXML
    private void rename() throws IOException {
        // Open the project edit dialog in edit mode
        app.projectAddEdit(true);
    }

    @FXML
    private void addColumn() throws IOException {
        // Open the column edit dialog in add mode
        app.columnAddEdit(false, null);
    }
}
