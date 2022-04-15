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
import org.hibernate.SessionFactory;

public static class UserController {

    /**
     * Adds a user
     * 
     * @param user           the user to add
     * @param sessionFactory the ORM session factory
     */
    public static void addUser(User user, SessionFactory sessionFactory) {

    }

    /**
     * Saves an edited user
     * 
     * @param user           the user to save
     * @param sessionFactory the ORM session factory
     */
    public static void editUser(User user, SessionFactory sessionFactory) {

    }

    /**
     * Validates a user login
     * 
     * @param username       the user's username
     * @param password       the user's password
     * @param sessionFactory the ORM session factory
     * @return a user
     */
    public static User logInUser(
            String username,
            String password,
            SessionFactory sessionFactory) {

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
