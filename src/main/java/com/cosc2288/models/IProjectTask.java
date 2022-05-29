package com.cosc2288.models;

import java.util.List;
import java.util.UUID;

public interface IProjectTask {

    /**
     * Gets the project task ID
     * 
     * @return UUID
     */
    UUID getProjectTaskId();

    /**
     * Sets the project task ID
     * 
     * @param projectTaskId
     */
    void setProjectTaskId(UUID projectTaskId);

    /**
     * Gets the project task name
     * 
     * @return String
     */
    String getName();

    /**
     * Sets the project task name
     * 
     * @param name
     */
    void setName(String name);

    /**
     * Gets the description
     * 
     * @return String
     */
    String getDescription();

    /**
     * Sets the description
     * 
     * @param description
     */
    void setDescription(String description);

    /**
     * Returns the project task order
     * 
     * @return int
     */
    int getOrder();

    /**
     * Sets the project task order
     * 
     * @param order
     */
    void setOrder(int order);

    /**
     * Gets the due date
     * 
     * @return Long
     */
    Long getDueDate();

    /**
     * Sets the due date
     * 
     * @param dueDate
     */
    void setDueDate(Long dueDate);

    /**
     * Gets the completed date
     * 
     * @return Long
     */
    Long getCompletedDate();

    /**
     * Sets the completed date
     * 
     * @param completedDate
     */
    void setCompletedDate(Long completedDate);

    /**
     * Returns the project column ID
     * 
     * @return UUID
     */
    UUID getProjectColumnId();

    /**
     * Sets the project column ID
     * 
     * @param projectColumnId
     */
    void setProjectColumnId(UUID projectColumnId);

    /**
     * Returns the action items
     * 
     * @return List<ActionItem>
     */
    List<ActionItem> getActionItems();

    /**
     * Sets the action items
     * 
     * @param actionItems
     */
    void setActionItems(List<ActionItem> actionItems);

}