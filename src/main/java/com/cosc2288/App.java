/**
 * App
 *
 * v1.0
 *
 * 2022-04-12
 *
 * © 2022 Matthew Kellock
 */
package com.cosc2288;

import com.cosc2288.controllers.IProjectColumnController;
import com.cosc2288.controllers.IProjectController;
import com.cosc2288.controllers.IQuoteController;
import com.cosc2288.controllers.IUserController;
import com.cosc2288.controllers.ProjectColumnController;
import com.cosc2288.controllers.ProjectController;
import com.cosc2288.controllers.ProjectTaskController;
import com.cosc2288.controllers.QuoteController;
import com.cosc2288.controllers.UserController;
import com.cosc2288.models.IActionItem;
import com.cosc2288.models.IProject;
import com.cosc2288.models.IProjectColumn;
import com.cosc2288.models.IProjectTask;
import com.cosc2288.models.IUser;
import com.cosc2288.models.Project;
import com.cosc2288.models.ProjectColumn;
import com.cosc2288.views.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application implements IApp {

    private static String connectionString;
    private static IUser loggedInUser;
    private static Main mainView;
    private static Main main;
    private static Stage loginStage;
    private static Stage userStage;
    private static Stage projectStage;
    private static Stage columnStage;
    private static Stage taskStage;
    private static IProject selectedProject;
    private static List<Project> projects;
    private static List<ProjectColumn> projectColumns;

    /**
     * Gets the currently logged in user
     * 
     * @return User
     */
    public static IUser getLoggedInUser() {
        return loggedInUser;
    }

    /**
     * Sets the currently loged in user
     * 
     * @param user
     */
    public static void setLoggedInUser(IUser user) {
        // Set the user object
        loggedInUser = user;

        // Set a project with the default project
        if (user != null) {
            IProject tempProj = new Project();
            tempProj.setProjectId(user.getDefaultProjectId());
            setSelectedProject(tempProj);
        }

        // Set the user on the main view
        getMainView().setUser(user);
    }

    /**
     * Returns the Main view controller
     * 
     * @return Main
     */
    public static Main getMain() {
        return main;
    }

    /**
     * Sets the main view controller
     * 
     * @param main
     */
    public static void setMain(Main main) {
        App.main = main;
    }

    /**
     * 
     * @return Main view
     */
    public static Main getMainView() {
        return mainView;
    }

    /**
     * Sets the main view
     * 
     * @param main
     */
    public static void setMainView(Main main) {
        mainView = main;
    }

    /**
     * Returns the login stage
     * 
     * @return Stage
     */
    public static Stage getLoginStage() {
        return loginStage;
    }

    /**
     * Sets the login stage
     * 
     * @param loginStage
     */
    public static void setLoginStage(Stage loginStage) {
        App.loginStage = loginStage;
    }

    /**
     * Gets the user stage
     * 
     * @return Stage
     */
    public static Stage getUserStage() {
        return userStage;
    }

    /**
     * Sets the user stage
     * 
     * @param userStage
     */
    public static void setUserStage(Stage userStage) {
        App.userStage = userStage;
    }

    /**
     * Gets the project stage
     * 
     * @return Stage
     */
    public static Stage getProjectStage() {
        return projectStage;
    }

    /**
     * Sets the project stage
     * 
     * @param projectStage
     */
    public static void setProjectStage(Stage projectStage) {
        App.projectStage = projectStage;
    }

    /**
     * Gets the task stage
     * 
     * @return Stage
     */
    public static Stage getTaskStage() {
        return taskStage;
    }

    /**
     * Sets the task stage
     * 
     * @param taskStage
     */
    public static void setTaskStage(Stage taskStage) {
        App.taskStage = taskStage;
    }

    /**
     * Gets the column stage
     * 
     * @return Stage
     */
    public static Stage getColumnStage() {
        return columnStage;
    }

    /**
     * Sets the column stage
     * 
     * @param columnStage
     */
    public static void setColumnStage(Stage columnStage) {
        App.columnStage = columnStage;
    }

    /**
     * Sets the selected project by ID
     * 
     * @param projectId
     */
    public static void setSelectedProjectById(UUID projectId) {
        for (IProject project : getProjects()) {
            if (projectId.compareTo(project.getProjectId()) == 0) {
                setSelectedProject(project);
            }
        }
    }

    /**
     * Sets the selected project by project object
     * 
     * @param project
     */
    public static void setSelectedProject(IProject project) {
        App.selectedProject = project;
    }

    /**
     * Gets the selected project
     * 
     * @return Project
     */
    public static IProject getSelectedProject() {
        return selectedProject;
    }

    /**
     * Sets the list of the user's projects
     * 
     * @param projects
     */
    public static void setProjects(List<Project> projects) {
        App.projects = projects;
    }

    /**
     * Returns a list of the user's projects
     * 
     * @return List<Project>
     */
    public static List<Project> getProjects() {
        return projects;
    }

    /**
     * Sets the active project columns
     * 
     * @param projectColumns
     */
    public static void setProjectColumns(List<ProjectColumn> projectColumns) {
        App.projectColumns = projectColumns;
    }

    /**
     * Returns the active projects columns
     * 
     * @return List<ProjectColumn>
     */
    public static List<ProjectColumn> getProjectColumns() {
        return projectColumns;
    }

    /**
     * Main method that runs the application
     * 
     * @param args
     */
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

    /**
     * JavaFX start method to run the UI
     * 
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // Load the log in scene
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("views/fxml/Main.fxml"));
            Scene mainScene = new Scene(loader.load());

            // Set the app instance
            setMain(loader.<Main>getController());
            getMain().setApp(this);

            // Load the quote
            App.setMainView(loader.<Main>getController());
            IQuoteController quoteController = new QuoteController(
                    connectionString);
            App.getMainView()
                    .setQuote(quoteController.randomQuote().getMessage());

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
        } catch (Exception e) {
            // Pass the application exception to the handler
            handleException();
        }
    }

    /**
     * Shows the login dialog
     */
    private void showLogin() {
        getLoginStage().showAndWait();
    }

    /**
     * Loads the main scene
     */
    private void showMainScene() {
        getLoginStage().close();

        if (getUserStage() != null) {
            getUserStage().close();
        }

        loadProjects();
    }

    /**
     * Logs in the user
     * 
     * @param username
     * @param password
     */
    @Override
    public void loginOk(String username, String password) {
        try {
            IUserController userController = new UserController(
                    connectionString);
            Alert addUserAlert = new Alert(AlertType.ERROR);
            IUser user = userController.logInUser(username, password);

            if (user == null) {
                addUserAlert
                        .setContentText(
                                "Username and/or password is incorrect");
                addUserAlert.show();
            } else {
                setLoggedInUser(user);
                showMainScene();
            }
        } catch (SQLException e) {
            // Pass the application exception to the handler
            handleException();
        }
    }

    /**
     * Shows the new/edit user dialog
     * 
     * @param edit
     */
    @Override
    public void newEditUser(Boolean edit) {
        try {
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
        } catch (IOException e) {
            // Pass the application exception to the handler
            handleException();
        }
    }

    /**
     * Cancels the new user dialog
     * New user methods
     */

    @Override
    public void newUserCancel() {
        getUserStage().close();
        getLoginStage().showAndWait();
    }

    /**
     * Commits a new/edited user to the database and closes the dialog
     * 
     * @param user
     */
    @Override
    public void newEditUserOk(IUser user) {
        try {
            Alert addUserAlert = new Alert(AlertType.ERROR);
            IUserController userController = new UserController(
                    connectionString);

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
        } catch (SQLException e) {
            // Pass the application exception to the handler
            handleException();
        }
    }

    /**
     * Logs out the current user
     */
    @Override
    public void logOut() {
        setLoggedInUser(null);
        setSelectedProject(null);
        loadProjects();
        getLoginStage().showAndWait();
    }

    /**
     * Edits the current user's profile
     */
    @Override
    public void profile() {
        newEditUser(true);
    }

    /**
     * Shows the new/edit project dialog
     * 
     * @param edit
     */
    @Override
    public void projectAddEdit(Boolean edit) {
        try {
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
        } catch (IOException e) {
            // Pass the application exception to the handler
            handleException();
        }
    }

    /**
     * Commits a new/edited project to the database and closes the dialog
     * 
     * @param project
     */
    @Override
    public void projectOk(IProject project) {
        try {
            IProjectController projectController = new ProjectController(
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
        } catch (SQLException e) {
            // Pass the application exception to the handler
            handleException();
        }
    }

    /**
     * Cancels the project dialog
     */
    @Override
    public void projectCancel() {
        getProjectStage().close();
    }

    /**
     * Commits a new/edited project task to the datbase and closes the dialog
     * 
     * @param projectTask
     */
    @Override
    public void projectTaskOk(IProjectTask projectTask) {
        try {
            ProjectTaskController projectController = new ProjectTaskController(
                    connectionString);

            if (projectTask.getProjectTaskId() == null) {
                projectTask.setProjectTaskId(UUID.randomUUID());

                for (IActionItem actionItem : projectTask.getActionItems()) {
                    actionItem.setProjectTaskId(projectTask.getProjectTaskId());
                }

                projectController.addProjectTask(projectTask);
            } else {
                projectController.editProjectTask(projectTask);
            }

            getTaskStage().close();
            loadProjects();
        } catch (SQLException e) {
            // Pass the application exception to the handler
            handleException();
        }
    }

    /**
     * Cancels the project task dialog
     */
    @Override
    public void projectTaskCancel() {
        getTaskStage().close();
    }

    /**
     * Commits the project column to the database and closes the dialog
     * 
     * @param projectColumn
     */
    @Override
    public void projectColumnOk(IProjectColumn projectColumn) {
        try {
            IProjectColumnController projectColumnController = new ProjectColumnController(
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
        } catch (SQLException e) {
            // Pass the application exception to the handler
            handleException();
        }
    }

    /**
     * Closes the project column dialog
     */
    @Override
    public void projectColumnCancel() {
        getColumnStage().close();
    }

    /**
     * Loads the user's projects
     */
    @Override
    public void loadProjects() {
        try {
            if (getLoggedInUser() == null) {
                main.loadProjects(null, null, null);
            } else {
                IProjectController projectController = new ProjectController(
                        connectionString);
                setProjects(projectController
                        .loadProjects(getLoggedInUser().getUserId()));

                IProjectColumnController projectColumnController = new ProjectColumnController(
                        connectionString);
                ProjectTaskController projectTaskController = new ProjectTaskController(
                        connectionString);

                for (IProject project : getProjects()) {
                    project.setProjectColumns(projectColumnController
                            .loadProjectColumns(project.getProjectId()));

                    if (getSelectedProject() == null
                            && getLoggedInUser().getDefaultProjectId() != null
                            && getLoggedInUser().getDefaultProjectId()
                                    .compareTo(project.getProjectId()) == 0) {
                        setSelectedProject(project);
                    }

                    for (IProjectColumn projectColumn : project
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
        } catch (SQLException e) {
            // Pass the application exception to the handler
            handleException();
        }
    }

    /**
     * Deletes the active project
     */
    @Override
    public void deleteProject() {
        try {
            IProjectColumnController projectColumnController = new ProjectColumnController(
                    connectionString);

            for (IProjectColumn projectColumn : getSelectedProject()
                    .getProjectColumns()) {
                projectColumnController
                        .deleteProjectColumn(
                                projectColumn.getProjectColumnId());
            }

            IProjectController projectController = new ProjectController(
                    connectionString);
            projectController
                    .deleteProject(getSelectedProject().getProjectId());

            loadProjects();
        } catch (SQLException e) {
            // Pass the application exception to the handler
            handleException();
        }
    }

    /**
     * Sets the active project as default
     */
    @Override
    public void setDefaultProject() {
        // Set the default project
        getLoggedInUser()
                .setDefaultProjectId(getSelectedProject().getProjectId());

        // Save the user
        saveUser();
    }

    /**
     * Unsets the default project
     */
    @Override
    public void unsetDefaultProject() {
        // Set the default project to null
        getLoggedInUser().setDefaultProjectId(null);

        // Save the user
        saveUser();
    }

    /**
     * Saves the current user
     */
    private void saveUser() {
        try {
            // Save the current user
            IUserController userController = new UserController(
                    connectionString);
            userController.editUser(getLoggedInUser());
        } catch (SQLException e) {
            // Pass the application exception to the handler
            handleException();
        }
    }

    /**
     * Shows the new/edit column dialog
     * 
     * @param edit
     * @param selectedProjectColumn
     */
    @Override
    public void columnAddEdit(Boolean edit,
            IProjectColumn selectedProjectColumn) {
        try {
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
        } catch (IOException e) {
            // Pass the application exception to the handler
            handleException();
        }
    }

    /**
     * Shows the task add/edit dialog
     * 
     * @param edit
     * @param projectColumnId
     * @param projectTask
     */
    @Override
    public void taskAddEdit(Boolean edit, UUID projectColumnId,
            IProjectTask projectTask) {
        try {
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
        } catch (IOException e) {
            // Pass the application exception to the handler
            handleException();
        }
    }

    /**
     * Deletes a project column
     * 
     * @param selectedProjectColumn
     */
    @Override
    public void deleteProjectColumn(IProjectColumn selectedProjectColumn) {
        try {
            IProjectColumnController projectColumnController = new ProjectColumnController(
                    connectionString);
            projectColumnController.deleteProjectColumn(
                    selectedProjectColumn.getProjectColumnId());
            loadProjects();
        } catch (SQLException e) {
            // Pass the application exception to the handler
            handleException();
        }
    }

    /**
     * Deletes a project task
     * 
     * @param projectTask
     */
    @Override
    public void deleteProjectTask(IProjectTask projectTask) {
        try {
            ProjectTaskController projectTaskController = new ProjectTaskController(
                    connectionString);
            projectTaskController
                    .deleteProjectTask(projectTask.getProjectTaskId());
            loadProjects();
        } catch (SQLException e) {
            // Pass the application exception to the handler
            handleException();
        }
    }

    /**
     * Handles a drag event of a project task to another project task
     * 
     * @param draggedProjectTaskId
     * @param projectTask
     */
    @Override
    public void dragProjectTask(UUID draggedProjectTaskId,
            IProjectTask projectTask) {
        try {
            if (draggedProjectTaskId
                    .compareTo(projectTask.getProjectTaskId()) != 0) {
                ProjectTaskController projectTaskController = new ProjectTaskController(
                        connectionString);
                projectTaskController.moveTaskToPosition(draggedProjectTaskId,
                        projectTask);
                loadProjects();
            }
        } catch (SQLException e) {
            // Pass the application exception to the handler
            handleException();
        }
    }

    /**
     * Handles a drag event of a project task to another column
     * 
     * @param draggedProjectTaskId
     * @param projectColumn
     */
    @Override
    public void dragProjectTask(UUID draggedProjectTaskId,
            IProjectColumn projectColumn) {
        try {
            ProjectTaskController projectTaskController = new ProjectTaskController(
                    connectionString);
            projectTaskController.moveTaskToColumn(draggedProjectTaskId,
                    projectColumn);
            loadProjects();
        } catch (SQLException e) {
            // Pass the application exception to the handler
            handleException();
        }
    }

    /**
     * Handles exceptions
     * 
     * @param ex
     */
    private void handleException() {
        System.out.println("---------");
        System.out.println("There was an exception in the application, " +
                "it is safer for the app to close than continue!");
        System.out.println("---------");
        System.exit(-1);
    }
}
