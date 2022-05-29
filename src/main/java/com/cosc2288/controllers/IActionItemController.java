package com.cosc2288.controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import com.cosc2288.models.ActionItem;
import com.cosc2288.models.IActionItem;

public interface IActionItemController {

    /**
     * Adds an action item
     * 
     * @param actionItem the action item to be added
     * @throws SQLException
     */
    boolean addActionItem(IActionItem actionItem) throws SQLException;

    /**
     * Saves an edited action item
     * 
     * @param actionItem the action item to be saved
     * @throws SQLException
     */
    boolean editActionItem(IActionItem actionItem) throws SQLException;

    /**
     * Deletes action items based on project task ID
     * 
     * @param projectTaskId the project task id to be deleted
     * @throws SQLException
     */
    boolean deleteActionItems(UUID projectTaskId) throws SQLException;

    /**
     * Loads a project tasks action items
     * 
     * @param projectTaskId the project task id attached to the action items
     * @return a list of action items
     * @throws SQLException
     */
    List<ActionItem> loadActionItems(UUID projectTaskId) throws SQLException;

    /**
     * Loads a project tasks action items
     * 
     * @param projectTaskId the project task id attached to the action items
     * @return a list of action items
     * @throws SQLException
     */
    IActionItem loadActionItem(UUID actionItemId) throws SQLException;

}