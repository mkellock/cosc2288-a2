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
import javax.persistence.*;

@Entity
@Table(name = "project_tasks")
public class ProjectTask {

    @id
    @column(name = "project_task_id")
    private UUID projectTaskId;

    @column(name = "name")
    private String name;

    @column(name = "description")
    private String description;

    @column(name = "order")
    private int order;

    @column(name = "due_date")
    private Instant dueDate;

    @column(name = "completed_date")
    private Instant completedDate;

    @column(name = "project_column_id")
    private UUID projectColumnId;

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
