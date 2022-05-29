package com.cosc2288.models;

import java.util.UUID;

public interface IUser {

	/**
	 * Gets the user ID
	 * @return UUID
	 */
	UUID getUserId();

	/**
	 * Sets the user ID
	 * @param userId
	 */
	void setUserId(UUID userId);

	/**
	 * Gets the username
	 * @return String
	 */
	String getUsername();

	/**
	 * Sets the username
	 * @param username
	 */
	void setUsername(String username);

	/**
	 * Gets the password
	 * @return String
	 */
	String getPassword();

	/**
	 * Sets the password
	 * @param password
	 */
	void setPassword(String password);

	/**
	 * Returns the first name
	 * @return String
	 */
	String getFirstName();

	/**
	 * Sets the first name
	 * @param firstName
	 */
	void setFirstName(String firstName);

	/**
	 * Returns the last name
	 * @return String
	 */
	String getLastName();

	/**
	 * Sets the last name
	 * @param lastName
	 */
	void setLastName(String lastName);

	/**
	 * Returns the profile image
	 * @return byte[]
	 */
	byte[] getImage();

	/**
	 * Sets the profile image
	 * @param image
	 */
	void setImage(byte[] image);

	/**
	 * Gets the default project ID
	 * @return UUID
	 */
	UUID getDefaultProjectId();

	/**
	 * Sets the default project ID
	 * @param defaultProjectId
	 */
	void setDefaultProjectId(UUID defaultProjectId);

}