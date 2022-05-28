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

import java.sql.SQLException;
import java.time.Instant;
import java.util.UUID;

import com.cosc2288.models.Project;
import com.google.gson.Gson;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProjectControllerTests extends BaseTests {

    private UUID PROJECT_ID = UUID
            .fromString("d1692273-c891-4950-be04-9620604c2cfe");
    private String NAME = "Project Name";
    private Long CREATED = Instant.now().toEpochMilli();
    private UUID USER_ID = UUID
            .fromString("b45542c8-f159-4b3d-a268-dcabfaa7443a");

    @Test
    void shouldAddProject() throws SQLException {
        // Set up test variables
        final UUID projectId = UUID.randomUUID();
        final Project project = new Project(projectId, NAME, CREATED, USER_ID);

        // Add a project to the database
        final ProjectController projectController = new ProjectController(
                getTestConnString());
        projectController.addProject(project);

        // Grab the project from the list
        Project projectLoaded = null;

        for (Project tempProject : projectController.loadProjects(USER_ID)) {
            if (tempProject.getProjectId().equals(projectId)) {
                projectLoaded = tempProject;
            }
        }

        // Assert that the elements are identical
        Assertions.assertEquals(new Gson().toJson(project),
                new Gson().toJson(projectLoaded));
    }

    @Test
    void shouldEditProject() throws SQLException {
        // Set up test variables
        final Project project = new Project(PROJECT_ID, NAME, CREATED, USER_ID);

        // Update the project
        project.setName("Project Name New");

        // Save the project
        final ProjectController projectController = new ProjectController(
                getTestConnString());
        projectController.editProject(project);

        // Grab the project from the list
        Project projectLoaded = null;

        for (Project tempProject : projectController.loadProjects(USER_ID)) {
            if (tempProject.getProjectId().equals(PROJECT_ID)) {
                projectLoaded = tempProject;
            }
        }

        // Assert that the elements are identical
        Assertions.assertEquals(new Gson().toJson(project),
                new Gson().toJson(projectLoaded));
    }

    @Test
    void shouldDeleteProject() throws SQLException {
        final UUID projectId = UUID
                .fromString("530a76ec-25ae-4a69-ba7b-b47f307f7b85");
        boolean result = false;

        // Delete the project
        ProjectController projectController = new ProjectController(
                getTestConnString());
        projectController.deleteProject(projectId);

        // Check that the project no longer exists
        for (Project tempProject : projectController.loadProjects(USER_ID)) {
            if (tempProject.getProjectId().equals(projectId)) {
                result = true;
            }
        }

        // Assert the project no longer exists
        Assertions.assertFalse(result);
    }

    @Test
    void shouldLoadProjects() throws SQLException {
        // Check that we have elements
        ProjectController projectController = new ProjectController(
                getTestConnString());
        Assertions
                .assertTrue(projectController.loadProjects(USER_ID).size() > 0);
    }

}
