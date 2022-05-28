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

import java.util.List;
import java.util.UUID;

public class ProjectTask {

    private UUID projectTaskId;
    private String name;
    private String description;
    private int order;
    private Long dueDate;
    private Long completedDate;
    private UUID projectColumnId;
    private List<ActionItem> actionItems;

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
     * @param actionItems
     */
    public ProjectTask(UUID projectTaskId, String name, String description,
            int order, Long dueDate, Long completedDate, UUID projectColumnId,
            List<ActionItem> actionItems) {
        this.projectTaskId = projectTaskId;
        this.name = name;
        this.description = description;
        this.order = order;
        this.dueDate = dueDate;
        this.completedDate = completedDate;
        this.projectColumnId = projectColumnId;
        this.actionItems = actionItems;
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
    public Long getDueDate() {
        return dueDate;
    }

    /**
     * @param dueDate the dueDate to set
     */
    public void setDueDate(Long dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * @return the completedDate
     */
    public Long getCompletedDate() {
        return completedDate;
    }

    /**
     * @param completedDate the completedDate to set
     */
    public void setCompletedDate(Long completedDate) {
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

    public List<ActionItem> getActionItems() {
        return actionItems;
    }

    public void setActionItems(List<ActionItem> actionItems) {
        this.actionItems = actionItems;
    }

}
