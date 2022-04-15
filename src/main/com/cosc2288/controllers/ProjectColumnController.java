/**
 * ProjectColumnController
 *
 * v1.0
 *
 * 2022-04-13
 *
 * © 2022 Matthew Kellock
 */
package com.cosc2288.controllers;

import com.cosc2288.models.ProjectColumn;
import java.util.List;
import java.util.UUID;
import org.hibernate.Session;

public static class ProjectColumnController {
    /**
     * Add project column
     * 
     * @param projectColumn the project column to be added
     * @param session       the ORM session
     */
    public static void addProjectColumn(
            ProjectColumn projectColumn,
            Session session) {

    }

    /**
     * Saves an edited project column
     * 
     * @param projectColumn the project column to be saved
     * @param session       the ORM session
     */
    public static void editProjectColumn(
            ProjectColumn projectColumn,
            Session session) {

    }

    /**
     * Deletes a project column
     * 
     * @param projectColumnId the project column id to be deleted
     * @param session         the ORM session
     */
    public static void deleteProjectColumn(
            UUID projectColumnId,
            Session session) {

    }

    /**
     * Loads the project columns
     * 
     * @param projectId the project id attached to the project columns
     * @param session   the ORM session
     * @return a list of project columns
     */
    public List<ProjectColumn> loadProjectColumns(
            UUID projectId,
            Session session) {

    }

    /**
     * Validates a project column
     * 
     * @param projectColumn the project column to be validated
     * @return if the project column is valid
     */
    private static boolean validate(ProjectColumn projectColumn) {

    }
}
