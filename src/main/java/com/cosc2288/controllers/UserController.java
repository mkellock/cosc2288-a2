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
    public UserController(String connectionString) {
        super(connectionString);
    }

    /**
     * Adds a user
     * 
     * @param user the user to add
     */
    public boolean addUser(User user) throws SQLException {
        // The insert script
        String sql = "INSERT INTO users "
                + "(user_id, username, password, first_name, last_name, image, "
                + "default_project_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        // Test the object's validity
        Boolean valid = validate(user);

        // If we have a valid object
        if (Boolean.TRUE.equals(valid)) {
            // Run the DB insert statement
            try (Connection conn = this.newConnection();
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, user.getUserId().toString());
                pstmt.setString(2, user.getUsername());
                pstmt.setString(3, user.getPassword());
                pstmt.setString(4, user.getFirstName());
                pstmt.setString(5, user.getLastName());
                pstmt.setBytes(6, user.getImage());

                if (user.getDefaultProjectId() != null) {
                    pstmt.setString(7, user.getDefaultProjectId().toString());
                } else {
                    pstmt.setString(7, null);
                }

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
     * Saves an edited user
     * 
     * @param user the user to save
     */
    public boolean editUser(User user) throws SQLException {
        // The update script
        String sql = "UPDATE users SET username = ?, password = ?, "
                + "first_name = ?, last_name = ?, image = ?, "
                + "default_project_id = ? WHERE user_id = ?";

        // Test the object's validity
        Boolean valid = validate(user);

        // If we have a valid object
        if (Boolean.TRUE.equals(valid)) {
            // Run the DB update statement
            try (Connection conn = this.newConnection();
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, user.getUsername());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getFirstName());
                pstmt.setString(4, user.getLastName());
                pstmt.setBytes(5, user.getImage());

                if (user.getDefaultProjectId() != null) {
                    pstmt.setString(6, user.getDefaultProjectId().toString());
                } else {
                    pstmt.setString(6, null);
                }

                pstmt.setString(7, user.getUserId().toString());
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
     * Validates a user login
     * 
     * @param username the user's username
     * @param password the user's password
     * @return a user
     */
    public User logInUser(String username, String password)
            throws SQLException {
        // The select query
        String sql = "SELECT user_id, username, password, first_name, "
                + "last_name, image, default_project_id FROM users "
                + "WHERE username = ? AND password = ?";

        // Run the DB select statement
        try (Connection conn = this.newConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Find the user
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet queryResults = pstmt.executeQuery();

            // If the user is valid
            if (queryResults.next()) {
                // Assign the DB results to a user object
                return new User(
                        UUID.fromString(queryResults.getString("user_id")),
                        queryResults.getString("username"),
                        queryResults.getString("password"),
                        queryResults.getString("first_name"),
                        queryResults.getString("last_name"),
                        queryResults.getBytes("image"),
                        queryResults.getString("default_project_id") == null
                                ? null
                                : UUID.fromString(queryResults
                                        .getString("default_project_id")));
            } else {
                // If the user is invalid
                return null;
            }
        } catch (SQLException e) {
            // Return that the operation failed
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Validates a user login
     * 
     * @param username the user's username
     * @return a user
     */
    public User findUser(String username) throws SQLException {
        // The select query
        String sql = "SELECT user_id, username, password, first_name, "
                + "last_name, image, default_project_id FROM users "
                + "WHERE username = ?";

        // Run the DB select statement
        try (Connection conn = this.newConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Find the user
            pstmt.setString(1, username);
            ResultSet queryResults = pstmt.executeQuery();

            // If the user is valid
            if (queryResults.next()) {
                // Assign the DB results to a user object
                return new User(
                        UUID.fromString(queryResults.getString("user_id")),
                        queryResults.getString("username"),
                        queryResults.getString("password"),
                        queryResults.getString("first_name"),
                        queryResults.getString("last_name"),
                        queryResults.getBytes("image"),
                        queryResults.getString("default_project_id") == null
                                ? null
                                : UUID.fromString(queryResults
                                        .getString("default_project_id")));
            } else {
                // If the user is invalid
                return null;
            }
        } catch (SQLException e) {
            // Return that the operation failed
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Validates a user
     * 
     * @param user the user to be validated
     * @return if the user is valid
     */
    private static boolean validate(User user) {
        boolean valid = false;

        valid = user.getUserId() != null && user.getImage() != null
                && (user.getUsername() + user.getPassword()
                        + user.getFirstName() + user.getLastName())
                        .length() > 0;

        return valid;
    }

}
