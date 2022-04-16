/**
 * ProjectTask
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

public class ProjectTask {

    private UUID projectTaskId;
    private String name;
    private String description;
    private int order;
    private Instant dueDate;
    private Instant completedDate;
    private UUID projectColumnId;

    /**
     * Constucts a project task
     */
    public ProjectTask() {
        // Empty constructor
    }

    /**
     * Constucts a project task
     * 
     * @param projectTaskId
     * @param name
     * @param description
     * @param order
     * @param dueDate
     * @param completedDate
     * @param projectColumnId
     */
    public ProjectTask(
            UUID projectTaskId,
            String name,
            String description,
            int order,
            Instant dueDate,
            Instant completedDate,
            UUID projectColumnId) {
        this.projectTaskId = projectTaskId;
        this.name = name;
        this.description = description;
        this.order = order;
        this.dueDate = dueDate;
        this.completedDate = completedDate;
        this.projectColumnId = projectColumnId;
    }

    /**
     * @return the projectTaskId
     */
    public UUID getProjectTaskId() {
        return projectTaskId;
    }

    /**
     * @param projectTaskId the projectTaskId to set
     */
    public void setProjectTaskId(UUID projectTaskId) {
        this.projectTaskId = projectTaskId;
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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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
     * @return the dueDate
     */
    public Instant getDueDate() {
        return dueDate;
    }

    /**
     * @param dueDate the dueDate to set
     */
    public void setDueDate(Instant dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * @return the completedDate
     */
    public Instant getCompletedDate() {
        return completedDate;
    }

    /**
     * @param completedDate the completedDate to set
     */
    public void setCompletedDate(Instant completedDate) {
        this.completedDate = completedDate;
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

}
