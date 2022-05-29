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
     */
    public ActionItem(UUID actionItemId, String description, boolean complete,
            UUID projectTaskID) {
        this.actionItemId = actionItemId;
        this.description = description;
        this.complete = complete;
        this.projectTaskID = projectTaskID;
    }

    /**
     * @return the actionItemId
     */
    public UUID getActionItemId() {
        return actionItemId;
    }

    /**
     * @param actionItemId the actionItemId to set
     */
    public void setActionItemId(UUID actionItemId) {
        this.actionItemId = actionItemId;
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
     * @return the complete
     */
    public boolean isComplete() {
        return complete;
    }

    /**
     * @param complete the complete to set
     */
    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    /**
     * @return the projectTaskID
     */
    public UUID getProjectTaskId() {
        return projectTaskID;
    }

    /**
     * @param projectTaskID the actionItemId to set
     */
    public void setProjectTaskId(UUID projectTaskID) {
        this.projectTaskID = projectTaskID;
    }

}
