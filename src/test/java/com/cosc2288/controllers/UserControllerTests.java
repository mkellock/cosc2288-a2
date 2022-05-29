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

import com.cosc2288.models.IUser;
import com.cosc2288.models.User;
import com.google.gson.Gson;

import java.sql.SQLException;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserControllerTests extends BaseTests {

    private static final UUID USER_ID = UUID
            .fromString("abf54e21-91ba-4592-bcd0-d16d63723f9a");
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String FIRST_NAME = "First";
    private static final String LAST_NAME = "Last";
    private static final byte[] IMAGE = { 0x00, 0x01, 0x02 };
    private static final UUID DEFAULT_PROJECT_ID = UUID.randomUUID();

    
    /** 
     * @throws SQLException
     */
    @Test
    void ShouldAddUser() throws SQLException {
        // Set up test variables
        UUID userId = UUID.randomUUID();
        String username = "Username1234";
        String password = "Passw0rd!";
        IUser user = new User(userId, username, password, FIRST_NAME, LAST_NAME,
                IMAGE, DEFAULT_PROJECT_ID);

        // Add a user to the database
        IUserController userController = new UserController(getTestConnString());
        userController.addUser(user);

        // Check that the user exists in the database (compare vals and not obj)
        Assertions.assertEquals(new Gson().toJson(user), new Gson()
                .toJson(userController.logInUser(username, password)));
    }

    
    /** 
     * @throws SQLException
     */
    @Test
    void ShouldEditUser() throws SQLException {
        // Set the user
        IUser user = new User(USER_ID, USERNAME, PASSWORD, FIRST_NAME, LAST_NAME,
                IMAGE, DEFAULT_PROJECT_ID);

        // Update the user
        user.setFirstName("NewFirst");

        // Save the user
        IUserController userController = new UserController(getTestConnString());
        userController.editUser(user);

        // Check that the details are correct
        IUser userLoad = userController.logInUser(USERNAME, PASSWORD);
        Assertions.assertEquals(user.getFirstName(), userLoad.getFirstName());
    }

    
    /** 
     * @throws SQLException
     */
    @Test
    void ShouldLogInUser() throws SQLException {
        // Load the user
        IUserController userController = new UserController(getTestConnString());
        IUser user = userController.logInUser(USERNAME, PASSWORD);

        // Assert that the User ID is corrrect
        Assertions.assertEquals(USER_ID, user.getUserId());
    }

    
    /** 
     * @throws SQLException
     */
    @Test
    void ShouldFindUser() throws SQLException {
        // Load the user
        IUserController userController = new UserController(getTestConnString());
        IUser user = userController.findUser(USERNAME);

        // Assert that the User ID is corrrect
        Assertions.assertEquals(USER_ID, user.getUserId());
    }

}
