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
import org.hibernate.SessionFactory;

public static class ActionItemController {
    /**
     * Adds an action item
     * 
     * @param actionItem     the action item to be added
     * @param sessionFactory the ORM session factory
     */
    public static void addActionItem(
            ActionItem actionItem,
            SessionFactory sessionFactory) {

    }

    /**
     * Saves an edited action item
     * 
     * @param actionItem     the action item to be saved
     * @param sessionFactory the ORM session factory
     */
    public static void editActionItem(
            ActionItem actionItem,
            SessionFactory sessionFactory) {
    }

    /**
     * Deletes an action item
     * 
     * @param actionItemId   the aciton item id to be deleted
     * @param sessionFactory the ORM session factory
     */
    public static void deleteActionItem(
            UUID actionItemId,
            SessionFactory sessionFactory) {

    }

    /**
     * Loads a project tasks action items
     * 
     * @param projectTaskId  the project task id attached to the action items
     * @param sessionFactory the ORM session factory
     * @return a list of action items
     */
    public static List<ActionItem> loadActionItems(
            UUID projectTaskId,
            SessionFactory sessionFactory) {

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
