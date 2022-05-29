/**
 * ActionItem
 *
 * v1.0
 *
 * 2022-04-12
 *
 * © 2022 Matthew Kellock
 */
package com.cosc2288.models;

import java.util.UUID;

public class ActionItem {

    private UUID actionItemId;
    private String description;
    private boolean complete;
    private UUID projectTaskID;

    /**
     * Constructs an action item
     */
    public ActionItem() {
        // Empty constructor
    }

    /**
     * Constructs an action item
     * 
     * @param actionItemId
     * @param description
     * @param complete
     * @param projectTaskID
     */
    public ActionItem(UUID actionItemId, String description, boolean complete,
            UUID projectTaskID) {
        this.actionItemId = actionItemId;
        this.description = description;
        this.complete = complete;
        this.projectTaskID = projectTaskID;
    }

    /**
     * Returns the action item ID
     * @return UUID
     */
    public UUID getActionItemId() {
        return actionItemId;
    }

    /**
     * Sets the action item ID
     * 
     * @param actionItemId
     */
    public void setActionItemId(UUID actionItemId) {
        this.actionItemId = actionItemId;
    }

    /**
     * Returns the description
     * 
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns id the action item is complete
     * @return boolean
     */
    public boolean isComplete() {
        return complete;
    }

    /**
     * Sets the complete status of the action item
     * @param complete
     */
    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    /**
     * Returns the project task ID
     * @return UUID
     */
    public UUID getProjectTaskId() {
        return projectTaskID;
    }

    /**
     * Sets the project task ID
     * @param projectTaskID
     */
    public void setProjectTaskId(UUID projectTaskID) {
        this.projectTaskID = projectTaskID;
    }

}
