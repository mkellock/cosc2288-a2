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
import javax.persistence.*;

public class User {

    @id
    @column(name = "user_id")
    private UUID userId;
    
    @column(name = "username")
    private String username;
    
    @column(name = "password")
    private String password;
    
    @column(name = "first_name")
    private String firstName;
    
    @column(name = "last_name")
    private String lastName;
    
    @column(name = "image")
    private byte[] image;
    
    @column(name = "default_project_id")
	private UUID defaultProjectId;
	
	/**
	 * @return the userId
	 */
	public UUID getUserId() {
		return userId;
	}
	
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(UUID userId) {
		this.userId = userId;
	}
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * @return the image
	 */
	public byte[] getImage() {
		return image;
	}
	
	/**
	 * @param image the image to set
	 */
	public void setImage(byte[] image) {
		this.image = image;
	}
	
	/**
	 * @return the defaultProjectId
	 */
	public UUID getDefaultProjectId() {
		return defaultProjectId;
	}
	
	/**
	 * @param defaultProjectId the defaultProjectId to set
	 */
	public void setDefaultProjectId(UUID defaultProjectId) {
		this.defaultProjectId = defaultProjectId;
	}

}
