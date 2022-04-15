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

import java.util.List;
import java.util.UUID;
import com.cosc2288.models.ProjectTask;
import org.hibernate.SessionFactory;

public class ProjectTaskController {

    /**
     * Adds a project task
     * 
     * @param projectTask    the project task to be added
     * @param sessionFactory the ORM session factory
     */
    private static void addProjectTask(
            ProjectTask projectTask,
            SessionFactory sessionFactory) {

    }

    /**
     * Saves an edited project task
     * 
     * @param projectTask    the project task to be saved
     * @param sessionFactory the ORM session factory
     */
    private static void editProjectTask(
            ProjectTask projectTask,
            SessionFactory sessionFactory) {

    }

    /**
     * Deletes a project task
     * 
     * @param projectTaskId  the project task id of the project task to be
     *                       deleted
     * @param sessionFactory the ORM session factory
     */
    private static void deleteProjectTask(
            UUID projectTaskId,
            SessionFactory sessionFactory) {

    }

    /**
     * Loads a project columns tasks
     * 
     * @param projectColumnId the project column id attached to the project
     *                        tasks
     * @param sessionFactory  the ORM session factory
     * @return a list of project tasks
     */
    public static List<ProjectTask> loadProjectTasks(
            UUID projectColumnId,
            SessionFactory sessionFactory) {
        return null;
    }

    /**
     * Validates a project task
     * 
     * @param projectTask the project task to be validated
     * @return is the project task is valid
     */
    private static boolean validate(ProjectTask projectTask) {
        return true;
    }

}
