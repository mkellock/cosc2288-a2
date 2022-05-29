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

import java.util.List;
import java.util.UUID;

public class ProjectColumn {

    private UUID projectColumnId;
    private String name;
    private int order;
    private UUID projectId;
    private List<ProjectTask> projectTasks;

    /**
     * Constructs a project column
     */
    public ProjectColumn() {
        // Empty constructor
    }

    /**
     * Constructs a project column
     * 
     * @param projectColumnId
     * @param name
     * @param order
     * @param projectId
     */
    public ProjectColumn(UUID projectColumnId, String name, int order,
            UUID projectId) {
        this.projectColumnId = projectColumnId;
        this.name = name;
        this.order = order;
        this.projectId = projectId;
    }

    /**
     * Returns the project column ID
     * @return UUID
     */
    public UUID getProjectColumnId() {
        return projectColumnId;
    }

    /**
     * Sets the project column ID
     * @param projectColumnId
     */
    public void setProjectColumnId(UUID projectColumnId) {
        this.projectColumnId = projectColumnId;
    }

    /**
     * Returns the name
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the order no.
     * @return int
     */
    public int getOrder() {
        return order;
    }

    /**
     * Sets the order no.
     * @param order
     */
    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * Returns the project ID
     * @return UUID
     */
    public UUID getProjectId() {
        return projectId;
    }

    /**
     * Sets the project ID
     * @param projectId
     */
    public void setProjectId(UUID projectId) {
        this.projectId = projectId;
    }

    
    /** 
     * Returns the project tasks
     * @return List<ProjectTask>
     */
    public List<ProjectTask> getProjectTasks() {
        return projectTasks;
    }

    
    /** 
     * Sets the project tasks
     * @param projectTasks
     */
    public void setProjectTasks(List<ProjectTask> projectTasks) {
        this.projectTasks = projectTasks;
    }

}
