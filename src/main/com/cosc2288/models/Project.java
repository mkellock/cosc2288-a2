/**
 * 
 */
package com.cosc2288.models;

import java.util.Date;
import java.util.UUID;

/**
 * @author mattkellock
 *
 */
public class Project {

	private UUID projectId;
	private String name;
	private Date created;
	private UUID userId;
	
	/**
	 * @return the projectId
	 */
	public UUID getProjectId() {
		return projectId;
	}
	
	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(UUID projectId) {
		this.projectId = projectId;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}
	
	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

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
	
}
