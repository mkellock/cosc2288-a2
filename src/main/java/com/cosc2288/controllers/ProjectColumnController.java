/**
 * ProjectColumnController
 *
 * v1.0
 *
 * 2022-04-13
 *
 * © 2022 Matthew Kellock
 */
package com.cosc2288.controllers;

import com.cosc2288.models.ProjectColumn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class ProjectColumnController extends BaseController {
    /**
     * Constructor for the class
     */
    public ProjectColumnController(String connectionString) {
        super(connectionString);
    }

    /**
     * Add project column
     * 
     * @param projectColumn the project column to be added
     * @throws SQLException
     */
    public boolean addProjectColumn(ProjectColumn projectColumn)
            throws SQLException {
        // Test the object's validity
        Boolean valid = validate(projectColumn);

        // If we have a valid object
        if (Boolean.TRUE.equals(valid)) {
            // Get the highest order number script
            String sql = "SELECT MAX(\"order\") AS \"order\" "
                    + "FROM project_columns WHERE project_id = ?; ";

            // Get the max column order from the DB
            try (Connection conn = this.newConnection();
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {
                projectColumn.setOrder(0);
                pstmt.setString(1, projectColumn.getProjectId().toString());
                ResultSet queryResults = pstmt.executeQuery();

                while (queryResults.next()) {
                    // Set the max order + 1 if there are other columns
                    projectColumn.setOrder(queryResults.getInt("order") + 1);
                }
            }

            // The insert script
            sql = "INSERT INTO project_columns "
                    + "(project_column_id, name, \"order\", project_id) "
                    + "VALUES (?, ?, ?, ?)";

            // Run the DB insert statement
            try (Connection conn = this.newConnection();
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1,
                        projectColumn.getProjectColumnId().toString());
                pstmt.setString(2, projectColumn.getName());
                pstmt.setInt(3, projectColumn.getOrder());
                pstmt.setString(4, projectColumn.getProjectId().toString());
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
     * Saves an edited project column
     * 
     * @param projectColumn the project column to be saved
     * @throws SQLException
     */
    public boolean editProjectColumn(ProjectColumn projectColumn)
            throws SQLException {
        // The update script
        String sql = "UPDATE project_columns SET \"order\" = ?, name = ?, "
                + "project_id = ? WHERE project_column_id = ?";

        // Test the object's validity
        Boolean valid = validate(projectColumn);

        // If we have a valid object
        if (Boolean.TRUE.equals(valid)) {
            // Run the DB update statement
            try (Connection conn = this.newConnection();
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, projectColumn.getOrder());
                pstmt.setString(2, projectColumn.getName());
                pstmt.setString(3, projectColumn.getProjectId().toString());
                pstmt.setString(4,
                        projectColumn.getProjectColumnId().toString());
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
     * Deletes a project column
     * 
     * @param projectColumnId the project column id to be deleted
     * @throws SQLException
     */
    public boolean deleteProjectColumn(UUID projectColumnId)
            throws SQLException {
        // The delete script
        String sql = "DELETE FROM project_columns WHERE project_column_id = ?";

        // Run the DB delete statement
        try (Connection conn = this.newConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, projectColumnId.toString());
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
     * Loads the project columns
     * 
     * @param projectId the project id attached to the project columns
     * @return a list of project columns
     * @throws SQLException
     */
    public List<ProjectColumn> loadProjectColumns(UUID projectId)
            throws SQLException {
        // The result we'll eventually return
        LinkedList<ProjectColumn> returnVal = new LinkedList<>();

        // The select query
        String sql = "SELECT project_column_id, name, \"order\", project_id "
                + "FROM project_columns WHERE project_id = ? "
                + "ORDER BY \"order\" ASC";

        // Run the DB select statement
        try (Connection conn = this.newConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, projectId.toString());
            ResultSet queryResults = pstmt.executeQuery();

            // Assign the DB results to the result list
            while (queryResults.next()) {
                // Add a new item
                returnVal.add(new ProjectColumn(
                        UUID.fromString(
                                queryResults.getString("project_column_id")),
                        queryResults.getString("name"),
                        queryResults.getInt("order"),
                        UUID.fromString(queryResults.getString("project_id"))));
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
     * Validates a project column
     * 
     * @param projectColumn the project column to be validated
     * @return if the project column is valid
     */
    private static boolean validate(ProjectColumn projectColumn) {
        // Check the validity of the object
        return projectColumn.getProjectColumnId() != null
                && projectColumn.getName().length() != 0
                && projectColumn.getProjectId() != null;
    }
}
