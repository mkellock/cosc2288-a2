/**
 * ProjectColumn
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

public class ProjectColumn {

    @id
    @column(name = "project_column_id")
    private UUID projectColumnId;
    
    @column(name = "name")
    private String name;
    
    @column(name = "order")
    private int order;
    
    @column(name = "project_id")
	private UUID projectId;
	
	/**
	 * @return the projectColumnId
	 */
	public UUID getProjectColumnId() {
		return projectColumnId;
	}
	
	/**
	 * @param projectColumnId the projectColumnId to set
	 */
	public void setProjectColumnId(UUID projectColumnId) {
		this.projectColumnId = projectColumnId;
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
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}

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
	
}
