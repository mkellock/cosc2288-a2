package com.cosc2288;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import com.cosc2288.controllers.ProjectController;
import com.cosc2288.controllers.QuoteController;
import com.cosc2288.controllers.UserController;
import com.cosc2288.models.Project;
import com.cosc2288.models.User;
import com.cosc2288.views.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class App extends Application {

    private static String connectionString;
    private static User loggedInUser;
    private static Main mainView;
    private static Main main;
    private static Stage loginStage;
    private static Stage userStage;
    private static Stage projectStage;
    private static UUID projectID;

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User user) {
        loggedInUser = user;
        getMainView().setUser(user);
    }

    public static Main getMainView() {
        return mainView;
    }

    public static void setMainView(Main main) {
        mainView = main;
    }

    public static void setProject(UUID projectID) {
        App.projectID = projectID;
    }

    public static UUID getProject() {
        return projectID;
    }

    public static void main(String[] args) {

        try (
                FileInputStream settingsFile = new FileInputStream("app.properties")) {
            Properties appSettings = new Properties();

            // load config.properties file
            appSettings.load(settingsFile);

            // This is where you add your config variables:
            connectionString = (String) appSettings.get("connnection_string");
        } catch (IOException e) {
            // Trap the error (returning null if we have an exception)
            e.printStackTrace();
        }

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the log in scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/fxml/Main.fxml"));
        Scene mainScene = new Scene(loader.load());

        // Set the app instance
        main = loader.<Main>getController();
        main.setApp(this);

        // Load the quote
        App.setMainView(loader.<Main>getController());
        QuoteController quoteController = new QuoteController(connectionString);
        App.getMainView().setQuote(quoteController.randomQuote().getMessage());

        // Show the main scene
        primaryStage.setTitle("SmartBoard");
        primaryStage.setScene(mainScene);
        primaryStage.show();

        // Load and show the login modal
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/fxml/Login.fxml"));
        Scene loginScene = new Scene(fxmlLoader.load());

        // Set the app instance
        Login login = fxmlLoader.<Login>getController();
        login.setApp(this);

        loginStage = new Stage();
        loginStage.initModality(Modality.APPLICATION_MODAL);
        loginStage.setScene(loginScene);

        showLogin();
    }

    private void showLogin() {
        loginStage.showAndWait();
    }

    private void showMainScene() throws SQLException {
        loginStage.close();

        if (userStage != null) {
            userStage.close();
        }

        loadProjects();
    }

    /**
     * Log in methods
     */

    public void loginOk(String username, String password) throws SQLException {
        UserController userController = new UserController(connectionString);
        Alert addUserAlert = new Alert(AlertType.ERROR);
        User user = userController.logInUser(username, password);

        if (user == null) {
            addUserAlert.setContentText("Username and/or password is incorrect");
            addUserAlert.show();
        } else {
            setLoggedInUser(user);
            showMainScene();
        }
    }

    public void newEditUser(Boolean edit) throws IOException {
        // Load the main scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/fxml/UserEdit.fxml"));
        Scene scene = new Scene(loader.load());

        UserEdit userEdit = loader.<UserEdit>getController();
        userEdit.setApp(this);

        userStage = new Stage();

        if (Boolean.TRUE.equals(edit)) {
            userStage.setTitle("Edit profile");
            userEdit.setUser(loggedInUser);
        } else {
            loginStage.close();
            userStage.setTitle("Create a new user");
        }

        userStage.initModality(Modality.APPLICATION_MODAL);
        userStage.setScene(scene);
        userStage.show();
    }

    /**
     * New user methods
     */

    public void newUserCancel() {
        userStage.close();
        loginStage.showAndWait();
    }

    public void newEditUserOk(User user)
            throws SQLException {
        Alert addUserAlert = new Alert(AlertType.ERROR);
        UserController userController = new UserController(connectionString);

        if (App.getLoggedInUser() == null) {
            if (userController.findUser(user.getUsername()) == null) {
                userController.addUser(user);
                setLoggedInUser(user);
                showMainScene();
            } else {
                addUserAlert.setContentText("Username already exists, please enter another");
                addUserAlert.show();
            }
        } else {
            userController.editUser(App.getLoggedInUser());
            setLoggedInUser(user);
            showMainScene();
        }
    }

    /**
     * User management methods
     */
    public void logOut() {
        loginStage.showAndWait();
    }

    public void profile() throws IOException {
        newEditUser(true);
    }

    /**
     * Project management methods
     */

    public void project(Boolean edit) throws IOException {
        // Load the main scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/fxml/ProjectEdit.fxml"));
        Scene scene = new Scene(loader.load());

        ProjectEdit projectEdit = loader.<ProjectEdit>getController();
        projectEdit.setApp(this);

        projectStage = new Stage();

        if (Boolean.TRUE.equals(edit)) {
            projectStage.setTitle("Edit project");
            // projectEdit.setProject(loggedInUser);
        } else {
            projectStage.setTitle("Create a project");
        }

        projectStage.initModality(Modality.APPLICATION_MODAL);
        projectStage.setScene(scene);
        projectStage.show();
    }

    public void projectOk(String projectName) throws SQLException {
        ProjectController projectController = new ProjectController(connectionString);
        projectController.addProject(
                new Project(UUID.randomUUID(), projectName, Instant.now().toEpochMilli(),
                        getLoggedInUser().getUserId()));
        projectStage.close();
        loadProjects();
    }

    public void projectCancel() {
        projectStage.close();
    }

    public void loadProjects() throws SQLException {
        ProjectController projectController = new ProjectController(connectionString);
        List<Project> projects = projectController.loadProjects(getLoggedInUser().getUserId());
        main.loadProjects(projects);
    }

    public void deleteProject() throws SQLException {
        ProjectController projectController = new ProjectController(connectionString);
        projectController.deleteProject(getProject());
        loadProjects();
    }

}
