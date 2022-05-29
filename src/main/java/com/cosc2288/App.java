package com.cosc2288;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import com.cosc2288.controllers.ProjectColumnController;
import com.cosc2288.controllers.ProjectController;
import com.cosc2288.controllers.ProjectTaskController;
import com.cosc2288.controllers.QuoteController;
import com.cosc2288.controllers.UserController;
import com.cosc2288.models.ActionItem;
import com.cosc2288.models.Project;
import com.cosc2288.models.ProjectColumn;
import com.cosc2288.models.ProjectTask;
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
    private static Stage columnStage;
    private static Stage taskStage;
    private static Project selectedProject;
    private static List<Project> projects;
    private static List<ProjectColumn> projectColumns;

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

    public static Stage getTaskStage() {
        return taskStage;
    }

    public static void setTaskStage(Stage taskStage) {
        App.taskStage = taskStage;
    }

    public static Stage getColumnStage() {
        return columnStage;
    }

    public static void setColumnStage(Stage columnStage) {
        App.columnStage = columnStage;
    }

    public static void setSelectedProjectById(UUID projectId) {
        for (Project project : getProjects()) {
            if (projectId.compareTo(project.getProjectId()) == 0) {
                setSelectedProject(project);
            }
        }
    }

    public static void setSelectedProject(Project project) {
        App.selectedProject = project;
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

    public static void setProjectColumns(List<ProjectColumn> projectColumns) {
        App.projectColumns = projectColumns;
    }

    public static List<ProjectColumn> getProjectColumns() {
        return projectColumns;
    }

    public static void main(String[] args) {

        try (FileInputStream settingsFile = new FileInputStream(
                "app.properties")) {
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
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("views/fxml/Main.fxml"));
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
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("views/fxml/Login.fxml"));
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
            addUserAlert
                    .setContentText("Username and/or password is incorrect");
            addUserAlert.show();
        } else {
            setLoggedInUser(user);
            showMainScene();
        }
    }

    public void newEditUser(Boolean edit) throws IOException {
        // Load the main scene
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("views/fxml/UserEdit.fxml"));
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

    public void newEditUserOk(User user) throws SQLException {
        Alert addUserAlert = new Alert(AlertType.ERROR);
        UserController userController = new UserController(connectionString);

        if (App.getLoggedInUser() == null) {
            if (userController.findUser(user.getUsername()) == null) {
                userController.addUser(user);
                setLoggedInUser(user);
                showMainScene();
            } else {
                addUserAlert.setContentText(
                        "Username already exists, please enter another");
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

    public void projectAddEdit(Boolean edit) throws IOException {
        // Load the main scene
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("views/fxml/ProjectEdit.fxml"));
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

        // Set the modal parameters
        getProjectStage().initStyle(StageStyle.UTILITY);
        getProjectStage().initModality(Modality.APPLICATION_MODAL);
        getProjectStage().setScene(scene);
        getProjectStage().show();
    }

    public void projectOk(Project project) throws SQLException {
        ProjectController projectController = new ProjectController(
                connectionString);

        if (project.getProjectId() == null) {
            projectController.addProject(new Project(UUID.randomUUID(),
                    project.getName(), Instant.now().toEpochMilli(),
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

    public void projectTaskOk(ProjectTask projectTask) throws SQLException {
        ProjectTaskController projectController = new ProjectTaskController(
                connectionString);

        if (projectTask.getProjectTaskId() == null) {
            projectTask.setProjectTaskId(UUID.randomUUID());

            for (ActionItem actionItem : projectTask.getActionItems()) {
                actionItem.setProjectTaskId(projectTask.getProjectTaskId());
            }

            projectController.addProjectTask(projectTask);
        } else {
            projectController.editProjectTask(projectTask);
        }

        getTaskStage().close();
        loadProjects();
    }

    public void projectTaskCancel() {
        getTaskStage().close();
    }

    public void projectColumnOk(ProjectColumn projectColumn)
            throws SQLException {
        ProjectColumnController projectColumnController = new ProjectColumnController(
                connectionString);

        if (projectColumn.getProjectColumnId() == null) {
            projectColumnController.addProjectColumn(new ProjectColumn(
                    UUID.randomUUID(), projectColumn.getName(), 0,
                    projectColumn.getProjectId()));
        } else {
            projectColumnController.editProjectColumn(projectColumn);
        }

        getColumnStage().close();
        loadProjects();
    }

    public void projectColumnCancel() {
        getColumnStage().close();
    }

    public void loadProjects() throws SQLException {
        if (getLoggedInUser() == null) {
            main.loadProjects(null, null, null);
        } else {
            ProjectController projectController = new ProjectController(
                    connectionString);
            setProjects(projectController
                    .loadProjects(getLoggedInUser().getUserId()));

            ProjectColumnController projectColumnController = new ProjectColumnController(
                    connectionString);
            ProjectTaskController projectTaskController = new ProjectTaskController(
                    connectionString);

            for (Project project : getProjects()) {
                project.setProjectColumns(projectColumnController
                        .loadProjectColumns(project.getProjectId()));

                if (getSelectedProject() == null
                        && getLoggedInUser().getDefaultProjectId() != null
                        && getLoggedInUser().getDefaultProjectId()
                                .compareTo(project.getProjectId()) == 0) {
                    setSelectedProject(project);
                }

                for (ProjectColumn projectColumn : project
                        .getProjectColumns()) {
                    projectColumn.setProjectTasks(
                            projectTaskController.loadProjectTasks(
                                    projectColumn.getProjectColumnId()));
                }
            }

            if (getSelectedProject() == null && !getProjects().isEmpty()) {
                setSelectedProject(getProjects().get(0));
            }

            main.loadProjects(getProjects(),
                    selectedProject != null ? selectedProject.getProjectId()
                            : null,
                    getLoggedInUser().getDefaultProjectId());
        }
    }

    public void deleteProject() throws SQLException {
        ProjectColumnController projectColumnController = new ProjectColumnController(
                connectionString);

        for (ProjectColumn projectColumn : getSelectedProject()
                .getProjectColumns()) {
            projectColumnController
                    .deleteProjectColumn(projectColumn.getProjectColumnId());
        }

        ProjectController projectController = new ProjectController(
                connectionString);
        projectController.deleteProject(getSelectedProject().getProjectId());

        loadProjects();
    }

    public void setDefaultProject() throws SQLException {
        // Set the default project
        getLoggedInUser()
                .setDefaultProjectId(getSelectedProject().getProjectId());

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

    public void columnAddEdit(Boolean edit, ProjectColumn selectedProjectColumn)
            throws IOException {
        // Load the main scene
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("views/fxml/ColumnEdit.fxml"));
        Scene scene = new Scene(loader.load());

        ColumnEdit columnEdit = loader.<ColumnEdit>getController();
        columnEdit.setApp(this);

        setColumnStage(new Stage());

        if (Boolean.TRUE.equals(edit)) {
            getColumnStage().setTitle("Edit column");
        } else {
            getColumnStage().setTitle("Add column");
        }

        // Set the project info
        columnEdit.setProject(selectedProject);
        columnEdit.setColumn(selectedProjectColumn);

        // Set the modal parameters
        getColumnStage().initStyle(StageStyle.UTILITY);
        getColumnStage().initModality(Modality.APPLICATION_MODAL);
        getColumnStage().setScene(scene);
        getColumnStage().show();
    }

    public void taskAddEdit(Boolean edit, UUID projectColumnId,
            ProjectTask projectTask) throws IOException {
        // Load the main scene
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("views/fxml/TaskEdit.fxml"));
        Scene scene = new Scene(loader.load());

        TaskEdit taskEdit = loader.<TaskEdit>getController();
        taskEdit.setApp(this);

        setTaskStage(new Stage());

        if (Boolean.TRUE.equals(edit)) {
            getTaskStage().setTitle("Edit task");
        } else {
            getTaskStage().setTitle("Add task");
        }

        // Set the project info
        taskEdit.setColumn(projectColumnId);
        taskEdit.setProjectTask(projectTask);

        // Set the modal parameters
        getTaskStage().initStyle(StageStyle.UTILITY);
        getTaskStage().initModality(Modality.APPLICATION_MODAL);
        getTaskStage().setScene(scene);
        getTaskStage().show();
    }

    public void deleteProjectColumn(ProjectColumn selectedProjectColumn)
            throws SQLException {
        ProjectColumnController projectColumnController = new ProjectColumnController(
                connectionString);
        projectColumnController.deleteProjectColumn(
                selectedProjectColumn.getProjectColumnId());
        loadProjects();
    }

    public void deleteProjectTask(ProjectTask projectTask) throws SQLException {
        ProjectTaskController projectTaskController = new ProjectTaskController(
                connectionString);
        projectTaskController.deleteProjectTask(projectTask.getProjectTaskId());
        loadProjects();
    }

    public void dragProjectTask(UUID draggedProjectTaskId,
            ProjectTask projectTask) throws SQLException {
        if (draggedProjectTaskId
                .compareTo(projectTask.getProjectTaskId()) != 0) {
            ProjectTaskController projectTaskController = new ProjectTaskController(
                    connectionString);
            projectTaskController.moveTaskToPosition(draggedProjectTaskId,
                    projectTask);
            loadProjects();
        }
    }

    public void dragProjectTask(UUID draggedProjectTaskId,
            ProjectColumn projectColumn) throws SQLException {
        ProjectTaskController projectTaskController = new ProjectTaskController(
                connectionString);
        projectTaskController.moveTaskToColumn(draggedProjectTaskId,
                projectColumn);
        loadProjects();
    }
}
