package com.cosc2288.models;

import java.util.UUID;

public interface IActionItem {

    /**
     * Returns the action item ID
     * 
     * @return UUID
     */
    UUID getActionItemId();

    /**
     * Sets the action item ID
     * 
     * @param actionItemId
     */
    void setActionItemId(UUID actionItemId);

    /**
     * Returns the description
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
     * Returns id the action item is complete
     * 
     * @return boolean
     */
    boolean isComplete();

    /**
     * Sets the complete status of the action item
     * 
     * @param complete
     */
    void setComplete(boolean complete);

    /**
     * Returns the project task ID
     * 
     * @return UUID
     */
    UUID getProjectTaskId();

    /**
     * Sets the project task ID
     * 
     * @param projectTaskID
     */
    void setProjectTaskId(UUID projectTaskID);

}