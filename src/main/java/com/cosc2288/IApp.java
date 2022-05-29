package com.cosc2288;

import java.util.UUID;

import com.cosc2288.models.IProject;
import com.cosc2288.models.IProjectColumn;
import com.cosc2288.models.IProjectTask;
import com.cosc2288.models.IUser;

import javafx.stage.Stage;

public interface IApp {

    /**
     * JavaFX start method to run the UI
     * 
     * @param primaryStage
     * @throws Exception
     */
    void start(Stage primaryStage) throws Exception;

    /**
     * Logs in the user
     * 
     * @param username
     * @param password
     */
    void loginOk(String username, String password);

    /**
     * Shows the new/edit user dialog
     * 
     * @param edit
     */
    void newEditUser(Boolean edit);

    /**
     * Cancels the new user dialog
     * New user methods
     */

    void newUserCancel();

    /**
     * Commits a new/edited user to the database and closes the dialog
     * 
     * @param user
     */
    void newEditUserOk(IUser user);

    /**
     * Logs out the current user
     */
    void logOut();

    /**
     * Edits the current user's profile
     */
    void profile();

    /**
     * Shows the new/edit project dialog
     * 
     * @param edit
     */
    void projectAddEdit(Boolean edit);

    /**
     * Commits a new/edited project to the database and closes the dialog
     * 
     * @param project
     */
    void projectOk(IProject project);

    /**
     * Cancels the project dialog
     */
    void projectCancel();

    /**
     * Commits a new/edited project task to the datbase and closes the dialog
     * 
     * @param projectTask
     */
    void projectTaskOk(IProjectTask projectTask);

    /**
     * Cancels the project task dialog
     */
    void projectTaskCancel();

    /**
     * Commits the project column to the database and closes the dialog
     * 
     * @param projectColumn
     */
    void projectColumnOk(IProjectColumn projectColumn);

    /**
     * Closes the project column dialog
     */
    void projectColumnCancel();

    /**
     * Loads the user's projects
     */
    void loadProjects();

    /**
     * Deletes the active project
     */
    void deleteProject();

    /**
     * Sets the active project as default
     */
    void setDefaultProject();

    /**
     * Unsets the default project
     */
    void unsetDefaultProject();

    /**
     * Shows the new/edit column dialog
     * 
     * @param edit
     * @param selectedProjectColumn
     */
    void columnAddEdit(Boolean edit, IProjectColumn selectedProjectColumn);

    /**
     * Shows the task add/edit dialog
     * 
     * @param edit
     * @param projectColumnId
     * @param projectTask
     */
    void taskAddEdit(Boolean edit, UUID projectColumnId,
            IProjectTask projectTask);

    /**
     * Deletes a project column
     * 
     * @param selectedProjectColumn
     */
    void deleteProjectColumn(IProjectColumn selectedProjectColumn);

    /**
     * Deletes a project task
     * 
     * @param projectTask
     */
    void deleteProjectTask(IProjectTask projectTask);

    /**
     * Handles a drag event of a project task to another project task
     * 
     * @param draggedProjectTaskId
     * @param projectTask
     */
    void dragProjectTask(UUID draggedProjectTaskId, IProjectTask projectTask);

    /**
     * Handles a drag event of a project task to another column
     * 
     * @param draggedProjectTaskId
     * @param projectColumn
     */
    void dragProjectTask(UUID draggedProjectTaskId,
            IProjectColumn projectColumn);

}