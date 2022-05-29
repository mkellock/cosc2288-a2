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

import com.cosc2288.models.ActionItem;
import com.cosc2288.models.ProjectColumn;
import com.cosc2288.models.ProjectTask;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class ProjectTaskController extends BaseController {

    private final String connectionString;

    /**
     * Constructor for the class
     */
    public ProjectTaskController(String connectionString) {
        super(connectionString);

        this.connectionString = connectionString;
    }

    
    /** 
     * @param projectColumnId
     * @return int
     * @throws SQLException
     */
    private int maxColumnOrder(UUID projectColumnId) throws SQLException {
        // Default return val
        int returnVal = 0;

        // Get the highest order number script
        String sql = "SELECT MAX(\"order\") AS \"order\" "
                + "FROM project_tasks WHERE project_column_id = ?; ";

        // Get the max column order from the DB
        try (Connection conn = this.newConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, projectColumnId.toString());
            ResultSet queryResults = pstmt.executeQuery();

            while (queryResults.next()) {
                // Set the max order + 1 if there are other columns
                returnVal = queryResults.getInt("order");
            }
        }

        // Return the return val
        return returnVal;
    }

    /**
     * Adds a project task
     * 
     * @param projectTask the project task to be added
     * @throws SQLException
     */
    public boolean addProjectTask(ProjectTask projectTask) throws SQLException {
        // Create an Action Items controller
        ActionItemController actionItemController = new ActionItemController(
                this.connectionString);

        // Set the order of the project task
        projectTask.setOrder(
                maxColumnOrder(projectTask.getProjectColumnId()) + 1);

        // The insert script
        String sql = "INSERT INTO project_tasks "
                + "(project_task_id, name, description, \"order\", due_date, "
                + "completed_date, project_column_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

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

                if (projectTask.getDueDate() != null) {
                    pstmt.setLong(5, projectTask.getDueDate());
                } else {
                    pstmt.setNull(5, java.sql.Types.NULL);
                }

                if (projectTask.getCompletedDate() != null) {
                    pstmt.setLong(6, projectTask.getCompletedDate());
                } else {
                    pstmt.setNull(6, java.sql.Types.NULL);
                }

                pstmt.setString(7, projectTask.getProjectColumnId().toString());
                pstmt.executeUpdate();

                // Insert the action items
                for (ActionItem actionItem : projectTask.getActionItems()) {
                    actionItemController.addActionItem(actionItem);
                }
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
        // Create an Action Items controller
        ActionItemController actionItemController = new ActionItemController(
                this.connectionString);

        // The update script
        String sql = "UPDATE project_tasks SET name = ?, "
                + "description = ?, \"order\" = ?, due_date = ?, "
                + "completed_date = ?, project_column_id = ? "
                + "WHERE project_task_id = ?";

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

                if (projectTask.getDueDate() != null) {
                    pstmt.setLong(4, projectTask.getDueDate());
                } else {
                    pstmt.setNull(4, java.sql.Types.NULL);
                }

                if (projectTask.getCompletedDate() != null) {
                    pstmt.setLong(5, projectTask.getCompletedDate());
                } else {
                    pstmt.setNull(5, java.sql.Types.NULL);
                }

                pstmt.setString(6, projectTask.getProjectColumnId().toString());
                pstmt.setString(7, projectTask.getProjectTaskId().toString());
                pstmt.executeUpdate();

                // Insert/update the action items
                for (ActionItem actionItem : projectTask.getActionItems()) {
                    if (actionItemController.loadActionItem(
                            actionItem.getActionItemId()) == null) {
                        actionItemController.addActionItem(actionItem);
                    } else {
                        actionItemController.editActionItem(actionItem);
                    }
                }
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
        // Create an Action Items controller
        ActionItemController actionItemController = new ActionItemController(
                this.connectionString);

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

        // Delete the associated action items
        actionItemController.deleteActionItems(projectTaskId);

        // Object deleted successfully
        return true;
    }

    /**
     * Loads a project columns tasks
     * 
     * @param projectColumnId the project column id attached to the project
     *                        tasks
     * @return a list of project tasks
     * @throws SQLException
     */
    public List<ProjectTask> loadProjectTasks(UUID projectColumnId)
            throws SQLException {
        // Create an Action Items controller
        ActionItemController actionItemController = new ActionItemController(
                this.connectionString);

        // The result we'll eventually return
        LinkedList<ProjectTask> returnVal = new LinkedList<>();

        // The select query
        String sql = "SELECT project_task_id, name, description, \"order\", "
                + "due_date, completed_date, project_column_id "
                + "FROM project_tasks WHERE project_column_id = ? "
                + "ORDER BY \"order\"";

        // Run the DB select statement
        try (Connection conn = this.newConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, projectColumnId.toString());
            ResultSet queryResults = pstmt.executeQuery();

            // Assign the DB results to the result list
            while (queryResults.next()) {
                // Load the action items
                List<ActionItem> actionItems = actionItemController
                        .loadActionItems(UUID.fromString(
                                queryResults.getString("project_task_id")));

                // Add a new item
                returnVal.add(new ProjectTask(
                        UUID.fromString(
                                queryResults.getString("project_task_id")),
                        queryResults.getString("name"),
                        queryResults.getString("description"),
                        queryResults.getInt("order"),
                        queryResults.getLong("due_date"),
                        queryResults.getLong("completed_date"),
                        UUID.fromString(
                                queryResults.getString("project_column_id")),
                        actionItems));
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
     * @param draggedProjectTaskId
     * @param projectTask
     * @return boolean
     * @throws SQLException
     */
    public boolean moveTaskToPosition(UUID draggedProjectTaskId,
            ProjectTask projectTask) throws SQLException {
        // Update script to set all tickets with a higher order to +1
        String sql = "UPDATE project_tasks SET \"order\" = \"order\" + 1 "
                + "WHERE project_column_id = ? AND \"order\" >= ?";

        // Run the DB update statement
        try (Connection conn = this.newConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, projectTask.getProjectColumnId().toString());
            pstmt.setInt(2, projectTask.getOrder());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            // Return that the operation failed
            e.printStackTrace();
            throw e;
        }

        // Set the source ticket to the destination ticket's order +1 and set
        // the source tickets project column to the same project column as the
        // destination ticket
        sql = "UPDATE project_tasks SET \"order\" = ?, "
                + "project_column_id = ? WHERE project_task_id = ?";

        // Run the DB update statement
        try (Connection conn = this.newConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, projectTask.getOrder());
            pstmt.setString(2, projectTask.getProjectColumnId().toString());
            pstmt.setString(3, draggedProjectTaskId.toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            // Return that the operation failed
            e.printStackTrace();
            throw e;
        }

        // Object saved successfully
        return true;
    }

    
    /** 
     * @param draggedProjectTaskId
     * @param projectColumn
     * @return boolean
     * @throws SQLException
     */
    public boolean moveTaskToColumn(UUID draggedProjectTaskId,
            ProjectColumn projectColumn) throws SQLException {
        int columnPosition = maxColumnOrder(projectColumn.getProjectColumnId())
                + 1;

        // The update script
        String sql = "UPDATE project_tasks SET \"order\" = ?, "
                + "project_column_id = ? WHERE project_task_id = ?";

        // Run the DB update statement
        try (Connection conn = this.newConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, columnPosition);
            pstmt.setString(2, projectColumn.getProjectColumnId().toString());
            pstmt.setString(3, draggedProjectTaskId.toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            // Return that the operation failed
            e.printStackTrace();
            throw e;
        }

        // Object saved successfully
        return true;
    }

    /**
     * Validates a project task
     * 
     * @param projectTask the project task to be validated
     * @return is the project task is valid
     */
    private static boolean validate(ProjectTask projectTask) {
        // Check the validity of the object
        return projectTask.getProjectTaskId() != null
                && projectTask.getName().length() != 0;
    }

}
