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
import org.hibernate.Session;

import com.cosc2288.models.Project;

public static class ProjectController {

    /**
     * Adds a project
     * 
     * @param project the project to be added
     * @param session the ORM session
     */
    public static void addProject(Project project, Session session) {

    }

    /**
     * Saves an edited project
     * 
     * @param project the project to be saved
     * @param session the ORM session
     */
    public static void editProject(Project project, Session session) {

    }

    /**
     * Deletes a project
     * 
     * @param projectId the project id for the project to be deleted
     * @param session   the ORM session
     */
    public static void deleteProject(UUID projectId, Session session) {

    }

    /**
     * Loads the user's projects
     * 
     * @param userId  the user id attached to the projects
     * @param session the ORM session
     * @return a list of projects
     */
    public static List<Project> loadProjects(UUID userId, Session session) {

    }

    /**
     * Validates a project
     * 
     * @param project the project to be validate
     * @return is the project is valid
     */
    private static boolean validate(Project project) {

    }

}
