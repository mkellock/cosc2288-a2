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
    public ProjectColumnController() {
        super();
    }

    /**
     * Add project column
     * 
     * @param projectColumn the project column to be added
     */
    public boolean addProjectColumn(ProjectColumn projectColumn) {
        // The insert script
        String sql = "INSERT INTO project_columns " +
                "(project_column_id, name, order, project_id) " +
                "VALUES (?, ?, ?, ?)";

        // Test the object's validity
        Boolean valid = validate(projectColumn);

        // If we have a valid object
        if (Boolean.TRUE.equals(valid)) {
            // Run the DB insert statement
            try (Connection conn = this.getConnection();
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1,
                        projectColumn.getProjectColumnId().toString());
                pstmt.setString(2, projectColumn.getName());
                pstmt.setInt(3, projectColumn.getOrder());
                pstmt.setString(4, projectColumn.getProjectId().toString());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                // Return that the operation failed
                return false;
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
     */
    public boolean editProjectColumn(ProjectColumn projectColumn) {
        // The update script
        String sql = "UPDATE project_columns SET" +
                "order = ?, " +
                "name = ?, " +
                "project_id = ? " +
                "WHERE project_column_id = ?";

        // Test the object's validity
        Boolean valid = validate(projectColumn);

        // If we have a valid object
        if (Boolean.TRUE.equals(valid)) {
            // Run the DB update statement
            try (Connection conn = this.getConnection();
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, projectColumn.getName());
                pstmt.setInt(2, projectColumn.getOrder());
                pstmt.setString(3, projectColumn.getProjectId().toString());
                pstmt.setString(4,
                        projectColumn.getProjectColumnId().toString());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                // Return that the operation failed
                return false;
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
     * @param sessionFactory  the ORM session factory
     */
    public boolean deleteProjectColumn(UUID projectColumnId) {
        // The delete script
        String sql = "DELETE FROM project_columns WHERE project_column_id = ?";

        // Run the DB delete statement
        try (Connection conn = this.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, projectColumnId.toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            // Return that the operation failed
            return false;
        }

        // Object deleted successfully
        return true;
    }

    /**
     * Loads the project columns
     * 
     * @param projectId the project id attached to the project columns
     * @return a list of project columns
     */
    public List<ProjectColumn> loadProjectColumns(UUID projectId) {
        // The result we'll eventually return
        LinkedList<ProjectColumn> returnVal = new LinkedList<>();

        // The select query
        String sql = "SELECT project_column_id, name, order, project_id " +
                "FROM project_columns WHERE project_id = ?";

        // Run the DB select statement
        try (Connection conn = this.getConnection();
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
                        UUID.fromString(
                                queryResults.getString("project_id"))));
            }

            // Return the result list
            return returnVal;
        } catch (SQLException e) {
            // Return an empty list
            return new LinkedList<>();
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
        return projectColumn.getProjectColumnId() != null &&
                projectColumn.getName().length() != 0 &&
                projectColumn.getProjectId() != null;
    }
}
