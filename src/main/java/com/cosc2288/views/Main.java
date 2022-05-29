/**
 * Main
 *
 * v1.0
 *
 * 2022-04-12
 *
 * © 2022 Matthew Kellock
 */
package com.cosc2288.views;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.UUID;
import com.cosc2288.App;
import com.cosc2288.IApp;
import com.cosc2288.models.IProject;
import com.cosc2288.models.IUser;
import com.cosc2288.models.Project;
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

    private IApp app;

    /**
     * Sets the app instance
     * 
     * @param app
     */
    public void setApp(IApp app) {
        this.app = app;
    }

    /**
     * Sets the quote message
     * 
     * @param quoteMessage
     */
    public void setQuote(String quoteMessage) {
        quote.setText(quoteMessage);
    }

    /**
     * Sets the user instance
     * 
     * @param user
     */
    public void setUser(IUser user) {
        if (user == null) {
            userFullName.setText(null);
            userImage.setImage(null);
        } else {
            userFullName
                    .setText(user.getFirstName() + " " + user.getLastName());
            userImage.setImage(
                    new Image(new ByteArrayInputStream(user.getImage())));
        }
    }

    /**
     * Handles the Profile button event
     */
    @FXML
    private void profile() {
        // Show the profile dialog
        app.profile();
    }

    /**
     * Handles the Log Out button event
     */
    @FXML
    private void logOut() {
        // Log out the user
        app.logOut();
    }

    /**
     * Handles the New Project menu item
     */
    @FXML
    private void newProject() {
        // Open the project edit dialog in add mode
        app.projectAddEdit(false);
    }

    /**
     * Handles enabling/disabling the default project menu items
     */
    private void setDefaultMenuOptions() {
        // Check if we have a user set
        if (App.getLoggedInUser() != null) {
            // If we are not on a default tab, or one has not been set
            if (App.getLoggedInUser().getDefaultProjectId() == null
                    || App.getLoggedInUser().getDefaultProjectId().compareTo(
                            App.getSelectedProject().getProjectId()) != 0) {
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

    /**
     * Loads the projects into the view
     * 
     * @param projects
     * @param selectedProjectId
     * @param defaultProjectId
     */
    public void loadProjects(List<Project> projects, UUID selectedProjectId,
            UUID defaultProjectId) {
        // Clear the tabs
        workArea.getTabs().clear();

        // If we have projects
        if (projects != null && !projects.isEmpty()) {
            // Create an eventhandler for selecting a project
            EventHandler<Event> event = e -> {
                // Enable the project menu items
                setProjectMenuItemsDisabled(false);

                // Set the currently selected project
                App.setSelectedProjectById(UUID.fromString(workArea
                        .getSelectionModel().getSelectedItem().getId()));

                // Change the menu options
                setDefaultMenuOptions();
            };

            // Loop through the projects
            for (IProject project : projects) {
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

            // If the selected project ID isn't null, set the selected tab to
            // the specified project
            if (selectedProjectId != null) {
                selectTabByProjectId(selectedProjectId);
            }

            // If the default project ID isn't null, set the default project tab
            // to yellow
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

    /**
     * Selects a project by the project ID
     * 
     * @param selectedProjectId
     */
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

    /**
     * Colours the default project tab
     * 
     * @param defaultProjectId
     */
    private void colourDefaultTab(UUID defaultProjectId) {
        // Loop through the tabs
        for (Tab projectTab : workArea.getTabs()) {
            // If the tab's Id equals the default tab
            if (defaultProjectId != null
                    && projectTab.getId().equals(defaultProjectId.toString())) {
                // Select the tab
                projectTab.styleProperty()
                        .bind(Bindings.format("-fx-background-color: yellow"));
            } else {
                // Remove the background styling
                projectTab.styleProperty().bind(
                        Bindings.format("-fx-background-color: -fx-color"));
            }
        }
    }

    /**
     * Handles the project delete menu item
     */
    @FXML
    private void delete() {
        // Delete the selected project
        app.deleteProject();

        // If we have no tabs left
        if (workArea.getTabs().isEmpty()) {
            // Disable the project menu items
            setProjectMenuItemsDisabled(true);
        }
    }

    /**
     * Sets the project menu item state
     * 
     * @param state
     */
    private void setProjectMenuItemsDisabled(boolean state) {
        addColumnMenuItem.setDisable(state);
        renameMenuItem.setDisable(state);
        setAsDefaultMenuItem.setDisable(state);
        unsetAsDefaultMenuItem.setDisable(state);
        deleteMenuItem.setDisable(state);
    }

    /**
     * Hanbles the Set Default menu item
     */
    @FXML
    private void setDefault() {
        // Set the default tab colour
        colourDefaultTab(App.getSelectedProject().getProjectId());

        // Sets the current project as default
        app.setDefaultProject();

        // Change the menu options
        setDefaultMenuOptions();
    }

    /**
     * Handles the Unset Default menu item
     */
    @FXML
    private void unsetDefault() {
        // Unset the default tab colour
        colourDefaultTab(null);

        // Sets the current project as default
        app.unsetDefaultProject();

        // Change the menu options
        setDefaultMenuOptions();
    }

    /**
     * Handles the Rename menu item
     */
    @FXML
    private void rename() {
        // Open the project edit dialog in edit mode
        app.projectAddEdit(true);
    }

    /**
     * Handles the Add Column menu item
     */
    @FXML
    private void addColumn() {
        // Open the column edit dialog in add mode
        app.columnAddEdit(false, null);
    }
}
