/**
 * ProjectController
 *
 * v1.0
 *
 * 2022-04-13
 *
 * © 2022 Matthew Kellock
 */
package com.cosc2288.controllers;

import java.util.List;
import java.util.UUID;
import org.hibernate.SessionFactory;

import com.cosc2288.models.Project;

public class ProjectController {

    /**
     * Adds a project
     * 
     * @param project        the project to be added
     * @param sessionFactory the ORM session factory
     */
    public static void addProject(
            Project project,
            SessionFactory sessionFactory) {

    }

    /**
     * Saves an edited project
     * 
     * @param project        the project to be saved
     * @param sessionFactory the ORM session factory
     */
    public static void editProject(
            Project project,
            SessionFactory sessionFactory) {

    }

    /**
     * Deletes a project
     * 
     * @param projectId      the project id for the project to be deleted
     * @param sessionFactory the ORM session factory
     */
    public static void deleteProject(
            UUID projectId,
            SessionFactory sessionFactory) {

    }

    /**
     * Loads the user's projects
     * 
     * @param userId         the user id attached to the projects
     * @param sessionFactory the ORM session factory
     * @return a list of projects
     */
    public static List<Project> loadProjects(
            UUID userId,
            SessionFactory sessionFactory) {
        return null;
    }

    /**
     * Validates a project
     * 
     * @param project the project to be validate
     * @return is the project is valid
     */
    private static boolean validate(Project project) {
        return false;
    }

}
