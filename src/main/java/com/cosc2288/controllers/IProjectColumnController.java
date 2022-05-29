package com.cosc2288.controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import com.cosc2288.models.IProjectColumn;
import com.cosc2288.models.ProjectColumn;

public interface IProjectColumnController {

    /**
     * Add project column
     * 
     * @param projectColumn the project column to be added
     * @throws SQLException
     */
    boolean addProjectColumn(IProjectColumn projectColumn) throws SQLException;

    /**
     * Saves an edited project column
     * 
     * @param projectColumn the project column to be saved
     * @throws SQLException
     */
    boolean editProjectColumn(IProjectColumn projectColumn) throws SQLException;

    /**
     * Deletes a project column
     * 
     * @param projectColumnId the project column id to be deleted
     * @throws SQLException
     */
    boolean deleteProjectColumn(UUID projectColumnId) throws SQLException;

    /**
     * Loads the project columns
     * 
     * @param projectId the project id attached to the project columns
     * @return a list of project columns
     * @throws SQLException
     */
    List<ProjectColumn> loadProjectColumns(UUID projectId) throws SQLException;

}