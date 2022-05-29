package com.cosc2288.controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import com.cosc2288.models.IProject;
import com.cosc2288.models.Project;

public interface IProjectController {

    /**
     * Adds a project
     * 
     * @param project the project to be added
     * @throws SQLException
     */
    boolean addProject(IProject project) throws SQLException;

    /**
     * Saves an edited project
     * 
     * @param project the project to be saved
     * @throws SQLException
     */
    boolean editProject(IProject project) throws SQLException;

    /**
     * Deletes a project
     * 
     * @param projectId the project id for the project to be deleted
     * @throws SQLException
     */
    boolean deleteProject(UUID projectId) throws SQLException;

    /**
     * Loads the user's projects
     * 
     * @param userId the user id attached to the projects
     * @return a list of projects
     * @throws SQLException
     */
    List<Project> loadProjects(UUID userId) throws SQLException;

}