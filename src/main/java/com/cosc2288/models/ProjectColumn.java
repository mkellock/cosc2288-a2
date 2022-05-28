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
    public ProjectColumn(
            UUID projectColumnId,
            String name,
            int order,
            UUID projectId) {
        this.projectColumnId = projectColumnId;
        this.name = name;
        this.order = order;
        this.projectId = projectId;
    }

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

    public List<ProjectTask> getProjectTasks() {
        return projectTasks;
    }

    public void setProjectTasks(List<ProjectTask> projectTasks) {
        this.projectTasks = projectTasks;
    }

}
