/**
 * ActionItemController
 *
 * v1.0
 *
 * 2022-04-13
 *
 * © 2022 Matthew Kellock
 */
package com.cosc2288.controllers;

import com.cosc2288.models.ActionItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class ActionItemController extends BaseController {
    /**
     * Constructor for the class
     */
    public ActionItemController(String connectionString) {
        super(connectionString);
    }

    /**
     * Adds an action item
     * 
     * @param actionItem the action item to be added
     * @throws SQLException
     */
    public boolean addActionItem(ActionItem actionItem) throws SQLException {
        // The insert script
        String sql = "INSERT INTO action_items "
                + "(action_item_id, description, complete) "
                + "VALUES (?, ?, ?)";

        // Test the object's validity
        Boolean valid = validate(actionItem);

        // If we have a valid object
        if (Boolean.TRUE.equals(valid)) {
            // Run the DB insert statement
            try (Connection conn = this.newConnection();
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, actionItem.getActionItemId().toString());
                pstmt.setString(2, actionItem.getDescription());
                pstmt.setBoolean(3, actionItem.isComplete());
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
     * Saves an edited action item
     * 
     * @param actionItem the action item to be saved
     * @throws SQLException
     */
    public boolean editActionItem(ActionItem actionItem) throws SQLException {
        // The update script
        String sql = "UPDATE action_items SET description = ?, "
                + "complete = ? WHERE action_item_id = ?";

        // Test the object's validity
        Boolean valid = validate(actionItem);

        // If we have a valid object
        if (Boolean.TRUE.equals(valid)) {
            // Run the DB update statement
            try (Connection conn = this.newConnection();
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, actionItem.getDescription());
                pstmt.setBoolean(2, actionItem.isComplete());
                pstmt.setString(3, actionItem.getActionItemId().toString());
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
     * Deletes an action item
     * 
     * @param actionItemId the aciton item id to be deleted
     * @throws SQLException
     */
    public boolean deleteActionItem(UUID actionItemId) throws SQLException {
        // The delete script
        String sql = "DELETE FROM action_items WHERE action_item_id = ?";

        // Run the DB delete statement
        try (Connection conn = this.newConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, actionItemId.toString());
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
     * Loads a project tasks action items
     * 
     * @param projectTaskId the project task id attached to the action items
     * @return a list of action items
     * @throws SQLException
     */
    public List<ActionItem> loadActionItems(UUID projectTaskId)
            throws SQLException {
        // The result we'll eventually return
        LinkedList<ActionItem> returnVal = new LinkedList<>();

        // The select query
        String sql = "SELECT action_item_id, description, complete "
                + "FROM action_items WHERE project_task_id = ?";

        // Run the DB select statement
        try (Connection conn = this.newConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, projectTaskId.toString());
            ResultSet queryResults = pstmt.executeQuery();

            // Assign the DB results to the result list
            while (queryResults.next()) {
                // Add a new item
                returnVal.add(new ActionItem(
                        UUID.fromString(
                                queryResults.getString("action_item_id")),
                        queryResults.getString("description"),
                        queryResults.getBoolean("complete")));
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
     * Property to set the discount percentage when a discount is applied
     * 
     * @param actionItem the action item to be validates
     * @return if the action item is valid
     */
    private static boolean validate(ActionItem actionItem) {
        // Check the validity of the object
        return actionItem.getActionItemId() != null
                && actionItem.getDescription().length() != 0;
    }
}
