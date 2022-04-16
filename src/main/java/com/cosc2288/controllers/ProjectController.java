/**
 * ProjectController
 *
 * v1.0
 *
 * 2022-04-13
 *
 * © 2022 Matthew Kellock
 */
package com.cosc2288.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import com.cosc2288.models.Project;

public class ProjectController extends BaseController {
    /**
     * Constructor for the class
     */
    public ProjectController() {
        super();
    }

    /**
     * Adds a project
     * 
     * @param project the project to be added
     */
    public boolean addProject(Project project) {
        // The insert script
        String sql = "INSERT INTO projects " +
                "(project_id, name, created, user_id) " +
                "VALUES (?, ?, ?, ?)";

        // Test the object's validity
        Boolean valid = validate(project);

        // If we have a valid object
        if (Boolean.TRUE.equals(valid)) {
            // Run the DB insert statement
            try (Connection conn = this.getConnection();
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, project.getProjectId().toString());
                pstmt.setString(2, project.getName());
                pstmt.setLong(3, project.getCreated().toEpochMilli());
                pstmt.setString(4, project.getUserId().toString());
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
     * Saves an edited project
     * 
     * @param project the project to be saved
     */
    public boolean editProject(Project project) {
        // The update script
        String sql = "UPDATE projects SET" +
                "name = ?, " +
                "created = ?, " +
                "user_id = ? " +
                "WHERE project_id = ?";

        // Test the object's validity
        Boolean valid = validate(project);

        // If we have a valid object
        if (Boolean.TRUE.equals(valid)) {
            // Run the DB update statement
            try (Connection conn = this.getConnection();
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, project.getName());
                pstmt.setLong(2, project.getCreated().toEpochMilli());
                pstmt.setString(3, project.getUserId().toString());
                pstmt.setString(4, project.getProjectId().toString());
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
     * Deletes a project
     * 
     * @param projectId the project id for the project to be deleted
     */
    public boolean deleteProject(UUID projectId) {
        // The delete script
        String sql = "DELETE FROM projects WHERE project_id = ?";

        // Run the DB delete statement
        try (Connection conn = this.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, projectId.toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            // Return that the operation failed
            return false;
        }

        // Object deleted successfully
        return true;
    }

    /**
     * Loads the user's projects
     * 
     * @param userId the user id attached to the projects
     * @return a list of projects
     */
    public List<Project> loadProjects(UUID userId) {
        // The result we'll eventually return
        LinkedList<Project> returnVal = new LinkedList<>();

        // The select query
        String sql = "SELECT project_id, name, created, user_id" +
                "FROM projects WHERE user_id = ?";

        // Run the DB select statement
        try (Connection conn = this.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId.toString());
            ResultSet queryResults = pstmt.executeQuery();

            // Assign the DB results to the result list
            while (queryResults.next()) {
                // Add a new item
                returnVal.add(new Project(
                        UUID.fromString(
                                queryResults.getString("project_id")),
                        queryResults.getString("name"),
                        Instant.ofEpochSecond(queryResults.getLong("created")),
                        UUID.fromString(
                                queryResults.getString("user_id"))));
            }

            // Return the result list
            return returnVal;
        } catch (SQLException e) {
            // Return an empty list
            return new LinkedList<>();
        }
    }

    /**
     * Validates a project
     * 
     * @param project the project to be validate
     * @return is the project is valid
     */
    private static boolean validate(Project project) {
        // Check the validity of the object
        return project.getProjectId() != null &&
                project.getName().length() != 0 &&
                project.getCreated() != null &&
                project.getUserId() != null;
    }

}
