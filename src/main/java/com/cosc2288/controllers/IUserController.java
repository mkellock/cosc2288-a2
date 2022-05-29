package com.cosc2288.controllers;

import java.sql.SQLException;

import com.cosc2288.models.IUser;

public interface IUserController {

    /**
     * Adds a user
     * 
     * @param user the user to add
     */
    boolean addUser(IUser user) throws SQLException;

    /**
     * Saves an edited user
     * 
     * @param user the user to save
     */
    boolean editUser(IUser user) throws SQLException;

    /**
     * Validates a user login
     * 
     * @param username the user's username
     * @param password the user's password
     * @return a user
     */
    IUser logInUser(String username, String password) throws SQLException;

    /**
     * Validates a user login
     * 
     * @param username the user's username
     * @return a user
     */
    IUser findUser(String username) throws SQLException;

}