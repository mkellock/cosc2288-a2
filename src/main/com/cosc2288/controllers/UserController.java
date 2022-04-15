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
import org.hibernate.Session;

public static class UserController {

    /**
     * Adds a user
     * 
     * @param user    the user to add
     * @param session the ORM session
     */
    public static void addUser(User user, Session session) {

    }

    /**
     * Saves an edited user
     * 
     * @param user    the user to save
     * @param session the ORM session
     */
    public static void editUser(User user, Session session) {

    }

    /**
     * Validates a user login
     * 
     * @param username the user's username
     * @param password the user's password
     * @param session  the ORM session
     * @return a user
     */
    public static User logInUser(
            String username,
            String password,
            Session session) {

    }

    /**
     * Validates a user
     * 
     * @param user the user to be validated
     * @return if the user is valid
     */
    private static boolean validate(User user) {

    }

}
