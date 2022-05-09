/**
 * ProjectTaskController
 *
 * v1.0
 *
 * 2022-04-13
 *
 * © 2022 Matthew Kellock
 */
package com.cosc2288.controllers;

import com.cosc2288.models.ProjectTask;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class ProjectTaskController extends BaseController {
    /**
     * Constructor for the class
     */
    public ProjectTaskController(String connectionString) {
        super(connectionString);
    }

    /**
     * Adds a project task
     * 
     * @param projectTask the project task to be added
     * @throws SQLException
     */
    public boolean addProjectTask(ProjectTask projectTask) throws SQLException {
        // The insert script
        String sql = "INSERT INTO project_tasks " +
                "(project_task_id, name, description, order, due_date, " +
                "completed_date, project_column_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        // Test the object's validity
        Boolean valid = validate(projectTask);

        // If we have a valid object
        if (Boolean.TRUE.equals(valid)) {
            // Run the DB insert statement
            try (Connection conn = this.newConnection();
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, projectTask.getProjectTaskId().toString());
                pstmt.setString(2, projectTask.getName());
                pstmt.setString(3, projectTask.getDescription());
                pstmt.setInt(4, projectTask.getOrder());
                pstmt.setLong(5, projectTask.getDueDate());
                pstmt.setLong(6, projectTask.getCompletedDate());
                pstmt.setString(7, projectTask.getProjectColumnId().toString());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                // Return that the operation failed
                e.printStackTrace();
                throw e;
            }

            // Object saved successfully
            return true;
        } else { // If we have an invalid object
            // Return the object's validity
            return valid;
        }
    }

    /**
     * Saves an edited project task
     * 
     * @param projectTask the project task to be saved
     * @throws SQLException
     */
    public boolean editProjectTask(ProjectTask projectTask)
            throws SQLException {
        // The update script
        String sql = "UPDATE project_tasks SET" +
                "name = ?, " +
                "description = ?, " +
                "order = ?, " +
                "due_date = ?, " +
                "completed_date = ?, " +
                "project_column_id = ?, " +
                "WHERE project_task_id = ?";

        // Test the object's validity
        Boolean valid = validate(projectTask);

        // If we have a valid object
        if (Boolean.TRUE.equals(valid)) {
            // Run the DB update statement
            try (Connection conn = this.newConnection();
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, projectTask.getName());
                pstmt.setString(2, projectTask.getDescription());
                pstmt.setInt(3, projectTask.getOrder());
                pstmt.setLong(4, projectTask.getDueDate());
                pstmt.setLong(5, projectTask.getCompletedDate());
                pstmt.setString(6, projectTask.getProjectColumnId().toString());
                pstmt.setString(7, projectTask.getProjectTaskId().toString());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                // Return that the operation failed
                e.printStackTrace();
                throw e;
            }

            // Object saved successfully
            return true;
        } else { // If we have an invalid object
            // Return the object's validity
            return valid;
        }
    }

    /**
     * Deletes a project task
     * 
     * @param projectTaskId the project task id of the project task to be
     *                      deleted
     * @throws SQLException
     */
    public boolean deleteProjectTask(UUID projectTaskId) throws SQLException {
        // The delete script
        String sql = "DELETE FROM project_tasks WHERE project_task_id = ?";

        // Run the DB delete statement
        try (Connection conn = this.newConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, projectTaskId.toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            // Return that the operation failed
            e.printStackTrace();
            throw e;
        }

        // Object deleted successfully
        return true;
    }

    /**
     * Loads a project columns tasks
     * 
     * @param projectColumnId the project column id attached to the project
     *                        tasks
     * @param sessionFactory  the ORM session factory
     * @return a list of project tasks
     * @throws SQLException
     */
    public List<ProjectTask> loadProjectTasks(UUID projectColumnId)
            throws SQLException {
        // The result we'll eventually return
        LinkedList<ProjectTask> returnVal = new LinkedList<>();

        // The select query
        String sql = "SELECT project_task_id, name, description, order, " +
                "due_date, completed_date, project_column_id " +
                "FROM action_items WHERE project_column_id = ?";

        // Run the DB select statement
        try (Connection conn = this.newConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, projectColumnId.toString());
            ResultSet queryResults = pstmt.executeQuery();

            // Assign the DB results to the result list
            while (queryResults.next()) {
                // Add a new item
                returnVal.add(new ProjectTask(
                        UUID.fromString(
                                queryResults.getString("project_task_id")),
                        queryResults.getString("name"),
                        queryResults.getString("description"),
                        queryResults.getInt("order"),
                        queryResults.getLong("due_date"),
                        queryResults.getLong("complated_date"),
                        UUID.fromString(
                                queryResults.getString("project_task_id"))));
            }

            // Return the result list
            return returnVal;
        } catch (SQLException e) {
            // Return that the operation failed
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Validates a project task
     * 
     * @param projectTask the project task to be validated
     * @return is the project task is valid
     */
    private static boolean validate(ProjectTask projectTask) {
        // Check the validity of the object
        return projectTask.getProjectTaskId() != null &&
                projectTask.getName().length() != 0 &&
                projectTask.getDescription().length() != 0;
    }

}
