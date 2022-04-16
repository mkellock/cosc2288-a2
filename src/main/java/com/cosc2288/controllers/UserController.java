/**
 * UserController
 *
 * v1.0
 *
 * 2022-04-13
 *
 * © 2022 Matthew Kellock
 */
package com.cosc2288.controllers;

import com.cosc2288.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserController extends BaseController {
    /**
     * Constructor for the class
     */
    public UserController() {
        super();
    }

    /**
     * Adds a user
     * 
     * @param user the user to add
     */
    public boolean addUser(User user) {
        // The insert script
        String sql = "INSERT INTO users " +
                "(user_id, username, password, first_name, last_name, image, " +
                "default_project_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        // Test the object's validity
        Boolean valid = validate(user);

        // If we have a valid object
        if (Boolean.TRUE.equals(valid)) {
            // Run the DB insert statement
            try (Connection conn = this.getConnection();
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, user.getUserId().toString());
                pstmt.setString(2, user.getUsername());
                pstmt.setString(3, user.getPassword());
                pstmt.setString(4, user.getFirstName());
                pstmt.setString(5, user.getLastName());
                pstmt.setBytes(6, user.getImage());
                pstmt.setString(7, user.getDefaultProjectId().toString());
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
     * Saves an edited user
     * 
     * @param user the user to save
     */
    public boolean editUser(User user) {
        // The update script
        String sql = "UPDATE users SET" +
                "username = ?, " +
                "password = ?, " +
                "first_name = ?, " +
                "last_name = ?, " +
                "image = ?, " +
                "default_project_id = ? " +
                "WHERE user_id = ?";

        // Test the object's validity
        Boolean valid = validate(user);

        // If we have a valid object
        if (Boolean.TRUE.equals(valid)) {
            // Run the DB update statement
            try (Connection conn = this.getConnection();
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, user.getUsername());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getFirstName());
                pstmt.setString(4, user.getLastName());
                pstmt.setBytes(5, user.getImage());
                pstmt.setString(6, user.getDefaultProjectId().toString());
                pstmt.setString(7, user.getUserId().toString());
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
     * Validates a user login
     * 
     * @param username the user's username
     * @param password the user's password
     * @return a user
     */
    public User logInUser(String username, String password) {
        // The select query
        String sql = "SELECT user_id, username, password, first_name, " +
                "last_name, image, default_project_id FROM users " +
                "WHERE username = ? AND password = ?";

        // Run the DB select statement
        try (Connection conn = this.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet queryResults = pstmt.executeQuery();

            // Assign the DB results to the result list
            queryResults.next();

            return new User(
                    UUID.fromString(
                            queryResults.getString("user_id")),
                    queryResults.getString("username"),
                    queryResults.getString("password"),
                    queryResults.getString("first_name"),
                    queryResults.getString("last_name"),
                    queryResults.getBytes("image"),
                    UUID.fromString(
                            queryResults.getString("default_project_id")));
        } catch (SQLException e) {
            // Return an empty list
            return null;
        }
    }

    /**
     * Validates a user
     * 
     * @param user the user to be validated
     * @return if the user is valid
     */
    private static boolean validate(User user) {
        return user.getUserId() != null &&
                user.getUsername().length() > 0 &&
                user.getPassword().length() > 0 &&
                user.getFirstName().length() > 0 &&
                user.getLastName().length() > 0 &&
                user.getImage() != null;
    }

}
