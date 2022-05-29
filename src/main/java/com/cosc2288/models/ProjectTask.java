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

public class ProjectTask implements IProjectTask {

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
     * Gets the project task ID
     * 
     * @return UUID
     */
    @Override
    public UUID getProjectTaskId() {
        return projectTaskId;
    }

    /**
     * Sets the project task ID
     * 
     * @param projectTaskId
     */
    @Override
    public void setProjectTaskId(UUID projectTaskId) {
        this.projectTaskId = projectTaskId;
    }

    /**
     * Gets the project task name
     * 
     * @return String
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Sets the project task name
     * 
     * @param name
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description
     * 
     * @return String
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description
     * 
     * @param description
     */
    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the project task order
     * 
     * @return int
     */
    @Override
    public int getOrder() {
        return order;
    }

    /**
     * Sets the project task order
     * 
     * @param order
     */
    @Override
    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * Gets the due date
     * 
     * @return Long
     */
    @Override
    public Long getDueDate() {
        return dueDate;
    }

    /**
     * Sets the due date
     * 
     * @param dueDate
     */
    @Override
    public void setDueDate(Long dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Gets the completed date
     * 
     * @return Long
     */
    @Override
    public Long getCompletedDate() {
        return completedDate;
    }

    /**
     * Sets the completed date
     * 
     * @param completedDate
     */
    @Override
    public void setCompletedDate(Long completedDate) {
        this.completedDate = completedDate;
    }

    /**
     * Returns the project column ID
     * 
     * @return UUID
     */
    @Override
    public UUID getProjectColumnId() {
        return projectColumnId;
    }

    /**
     * Sets the project column ID
     * 
     * @param projectColumnId
     */
    @Override
    public void setProjectColumnId(UUID projectColumnId) {
        this.projectColumnId = projectColumnId;
    }

    /**
     * Returns the action items
     * 
     * @return List<ActionItem>
     */
    @Override
    public List<ActionItem> getActionItems() {
        return actionItems;
    }

    /**
     * Sets the action items
     * 
     * @param actionItems
     */
    @Override
    public void setActionItems(List<ActionItem> actionItems) {
        this.actionItems = actionItems;
    }

}
