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
import javax.persistence.*;

public class ActionItem {

    @id
    @column(name = "action_item_id")
    private UUID actionItemId;

    @column(name = "description")
    private String description;

    @column(name = "complete")
    private boolean complete;

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

}
