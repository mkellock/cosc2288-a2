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
import org.hibernate.SessionFactory;

public static class ProjectColumnController {
    /**
     * Add project column
     * 
     * @param projectColumn  the project column to be added
     * @param sessionFactory the ORM session factory
     */
    public static void addProjectColumn(
            ProjectColumn projectColumn,
            SessionFactory sessionFactory) {

    }

    /**
     * Saves an edited project column
     * 
     * @param projectColumn  the project column to be saved
     * @param sessionFactory the ORM session factory
     */
    public static void editProjectColumn(
            ProjectColumn projectColumn,
            SessionFactory sessionFactory) {

    }

    /**
     * Deletes a project column
     * 
     * @param projectColumnId the project column id to be deleted
     * @param sessionFactory  the ORM session factory
     */
    public static void deleteProjectColumn(
            UUID projectColumnId,
            SessionFactory sessionFactory) {

    }

    /**
     * Loads the project columns
     * 
     * @param projectId      the project id attached to the project columns
     * @param sessionFactory the ORM session factory
     * @return a list of project columns
     */
    public List<ProjectColumn> loadProjectColumns(
            UUID projectId,
            SessionFactory sessionFactory) {

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
