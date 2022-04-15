/**
 * ProjectTaskController
 *
 * v1.0
 *
 * 2022-04-13
 *
 * © 2022 Matthew Kellock
 */
package com.cosc2288.controllers;

import java.util.UUID;
import com.cosc2288.models.ProjectTask;
import org.hibernate.Session;

public static class ProjectTaskController {

    /**
     * Adds a project task
     * 
     * @param projectTask the project task to be added
     * @param session     the ORM session
     */
    private static void addProjectTask(
            ProjectTask projectTask,
            Session session) {

    }

    /**
     * Saves an edited project task
     * 
     * @param projectTask the project task to be saved
     * @param session     the ORM session
     */
    private static void editProjectTask(
            ProjectTask projectTask,
            Session session) {

    }

    /**
     * Deletes a project task
     * 
     * @param projectTaskId the project task id of the project task to be
     *                      deleted
     * @param session       the ORM session
     */
    private static void deleteProjectTask(
            UUID projectTaskId,
            Session session) {

    }

    /**
     * Loads a project columns tasks
     * 
     * @param projectColumnId the project column id attached to the project
     *                        tasks
     * @param session         the ORM session
     * @return a list of project tasks
     */
    public static List<ProjectTask> loadProjectTasks(
            UUID projectColumnId,
            Session session) {

    }

    /**
     * Validates a project task
     * 
     * @param projectTask the project task to be validated
     * @return is the project task is valid
     */
    private static boolean validate(ProjectTask projectTask) {

    }

}
