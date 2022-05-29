/**
 * User
 *
 * v1.0
 *
 * 2022-04-12
 *
 * © 2022 Matthew Kellock
 */
package com.cosc2288.models;

import java.util.UUID;

public class User implements IUser {

    private UUID userId;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private byte[] image;
    private UUID defaultProjectId;

    /**
     * Constructs a user
     */
    public User() {
    }

    /**
     * Constructs a user
     * 
     * @param userId
     * @param username
     * @param password
     * @param firstName
     * @param lastName
     * @param image
     * @param defaultProjectId
     */
    public User(UUID userId, String username, String password, String firstName,
            String lastName, byte[] image, UUID defaultProjectId) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
        this.defaultProjectId = defaultProjectId;
    }

    /**
     * Gets the user ID
     * 
     * @return UUID
     */
    @Override
    public UUID getUserId() {
        return userId;
    }

    /**
     * Sets the user ID
     * 
     * @param userId
     */
    @Override
    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    /**
     * Gets the username
     * 
     * @return String
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username
     * 
     * @param username
     */
    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password
     * 
     * @return String
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password
     * 
     * @param password
     */
    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the first name
     * 
     * @return String
     */
    @Override
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name
     * 
     * @param firstName
     */
    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name
     * 
     * @return String
     */
    @Override
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name
     * 
     * @param lastName
     */
    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the profile image
     * 
     * @return byte[]
     */
    @Override
    public byte[] getImage() {
        return image;
    }

    /**
     * Sets the profile image
     * 
     * @param image
     */
    @Override
    public void setImage(byte[] image) {
        this.image = image;
    }

    /**
     * Gets the default project ID
     * 
     * @return UUID
     */
    @Override
    public UUID getDefaultProjectId() {
        return defaultProjectId;
    }

    /**
     * Sets the default project ID
     * 
     * @param defaultProjectId
     */
    @Override
    public void setDefaultProjectId(UUID defaultProjectId) {
        this.defaultProjectId = defaultProjectId;
    }

}
