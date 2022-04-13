/**
 * Project
 *
 * v1.0
 *
 * 2022-04-12
 *
 * © 2022 Matthew Kellock
 */
package com.cosc2288.models;

import java.time.Instant;
import java.util.UUID;
import javax.persistence.*;

public class Project {

    @id
    @column(name = "project_id")
    private UUID projectId;
    
    @column(name = "name")
    private String name;
    
    @column(name = "created")
    private Instant created;
    
    @column(name = "user_id")
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
	public Instant getCreated() {
		return created;
	}
	
	/**
	 * @param created the created to set
	 */
	public void setCreated(Instant created) {
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
