/**
 * ActionItemController
 *
 * v1.0
 *
 * 2022-04-13
 *
 * © 2022 Matthew Kellock
 */
package com.cosc2288.controllers;

import com.cosc2288.models.ActionItem;
import java.util.List;
import java.util.UUID;

public static class ActionItemController {
    /**
     * Adds an action item
     * 
     * @param actionItem the action item to be added
     */
    public static void addActionItem(ActionItem actionItem) {

    }

    /**
     * Saves an edited action item
     * 
     * @param actionItem the action item to be saved
     */
    public static void editActionItem(ActionItem actionItem) {
    }

    /**
     * Deletes an action item
     * 
     * @param actionItemId the aciton item id to be deleted
     */
    public static void deleteActionItem(UUID actionItemId) {

    }

    /**
     * Loads a project tasks action items
     * 
     * @param projectTaskId the project task id attached to the action items
     * @return a list of action items
     */
    public static List<ActionItem> loadActionItems(UUID projectTaskId) {

    }

    /**
     * Property to set the discount percentage when a discount is applied
     * 
     * @param actionItem the action item to be validates
     * @return if the action item is valid
     */
    private static boolean validate(ActionItem actionItem) {

    }
}
