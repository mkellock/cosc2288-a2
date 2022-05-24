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
import javafx.stage.StageStyle;

public class App extends Application {

    private static String connectionString;
    private static User loggedInUser;
    private static Main mainView;
    private static Main main;
    private static Stage loginStage;
    private static Stage userStage;
    private static Stage projectStage;
    private static Project selectedProject;
    private static List<Project> projects;

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User user) {
        // Set the user object
        loggedInUser = user;

        // Set a project with the default project
        if (user != null) {
            Project tempProj = new Project();
            tempProj.setProjectId(user.getDefaultProjectId());
            setSelectedProject(tempProj);
        }

        // Set the user on the main view
        getMainView().setUser(user);
    }

    public static Main getMain() {
        return main;
    }

    public static void setMain(Main main) {
        App.main = main;
    }

    public static Main getMainView() {
        return mainView;
    }

    public static void setMainView(Main main) {
        mainView = main;
    }

    public static Stage getLoginStage() {
        return loginStage;
    }

    public static void setLoginStage(Stage loginStage) {
        App.loginStage = loginStage;
    }

    public static Stage getUserStage() {
        return userStage;
    }

    public static void setUserStage(Stage userStage) {
        App.userStage = userStage;
    }

    public static Stage getProjectStage() {
        return projectStage;
    }

    public static void setProjectStage(Stage projectStage) {
        App.projectStage = projectStage;
    }

    public static void setSelectedProject(Project project) {
        App.selectedProject = project;
    }

    public static void setSelectedProjectById(UUID projectId) {
        for (Project project : getProjects()) {
            if (projectId.compareTo(project.getProjectId()) == 0) {
                setSelectedProject(project);
            }
        }
    }

    public static Project getSelectedProject() {
        return selectedProject;
    }

    public static void setProjects(List<Project> projects) {
        App.projects = projects;
    }

    public static List<Project> getProjects() {
        return projects;
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
        setMain(loader.<Main>getController());
        getMain().setApp(this);

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

        setLoginStage(new Stage());
        getLoginStage().initStyle(StageStyle.UNDECORATED);
        getLoginStage().initModality(Modality.APPLICATION_MODAL);
        getLoginStage().setScene(loginScene);

        showLogin();
    }

    private void showLogin() {
        getLoginStage().showAndWait();
    }

    private void showMainScene() throws SQLException {
        getLoginStage().close();

        if (getUserStage() != null) {
            getUserStage().close();
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

        setUserStage(new Stage());

        if (Boolean.TRUE.equals(edit)) {
            getUserStage().setTitle("Edit profile");
            userEdit.setUser(loggedInUser);
        } else {
            getLoginStage().close();
            getUserStage().setTitle("Create a new user");
        }

        getUserStage().initStyle(StageStyle.UTILITY);
        getUserStage().initModality(Modality.APPLICATION_MODAL);
        getUserStage().setScene(scene);
        getUserStage().show();
    }

    /**
     * New user methods
     */

    public void newUserCancel() {
        getUserStage().close();
        getLoginStage().showAndWait();
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
     * 
     * @throws SQLException
     */
    public void logOut() throws SQLException {
        setLoggedInUser(null);
        setSelectedProject(null);
        loadProjects();
        getLoginStage().showAndWait();
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

        setProjectStage(new Stage());

        if (Boolean.TRUE.equals(edit)) {
            getProjectStage().setTitle("Edit project");
            projectEdit.setProject(selectedProject);
        } else {
            getProjectStage().setTitle("Create a project");
            projectEdit.setProject(null);
        }

        getProjectStage().initStyle(StageStyle.UTILITY);
        getProjectStage().initModality(Modality.APPLICATION_MODAL);
        getProjectStage().setScene(scene);
        getProjectStage().show();
    }

    public void projectOk(Project project) throws SQLException {
        ProjectController projectController = new ProjectController(connectionString);

        if (project.getProjectId() == null) {
            projectController.addProject(
                    new Project(UUID.randomUUID(), project.getName(), Instant.now().toEpochMilli(),
                            getLoggedInUser().getUserId()));
        } else {
            projectController.editProject(project);
        }

        getProjectStage().close();
        loadProjects();
    }

    public void projectCancel() {
        getProjectStage().close();
    }

    public void loadProjects() throws SQLException {
        if (getLoggedInUser() == null) {
            main.loadProjects(null, null, null);
        } else {
            ProjectController projectController = new ProjectController(connectionString);
            setProjects(projectController.loadProjects(getLoggedInUser().getUserId()));
            main.loadProjects(projects, selectedProject.getProjectId(), getLoggedInUser().getDefaultProjectId());
        }
    }

    public void deleteProject() throws SQLException {
        ProjectController projectController = new ProjectController(connectionString);
        projectController.deleteProject(getSelectedProject().getProjectId());
        loadProjects();
    }

    public void setDefaultProject() throws SQLException {
        // Set the default project
        getLoggedInUser().setDefaultProjectId(getSelectedProject().getProjectId());

        // Save the user
        saveUser();
    }

    public void unsetDefaultProject() throws SQLException {
        // Set the default project to null
        getLoggedInUser().setDefaultProjectId(null);

        // Save the user
        saveUser();
    }

    private void saveUser() throws SQLException {
        // Save the current user
        UserController userController = new UserController(connectionString);
        userController.editUser(getLoggedInUser());
    }
}
