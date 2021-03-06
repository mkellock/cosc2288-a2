/**
 * Project
 *
 * v1.0
 *
 * 2022-04-12
 *
 * © 2022 Matthew Kellock
 */
package com.cosc2288.models;

import java.util.List;
import java.util.UUID;

public class Project implements IProject {

    private UUID projectId;
    private String name;
    private Long created;
    private UUID userId;
    private List<ProjectColumn> projectColumns;

    /**
     * Constructs a project
     */
    public Project() {
        // Empty constructor
    }

    /**
     * Constructs a project
     * 
     * @param projectId
     * @param name
     * @param created
     * @param userId
     */
    public Project(UUID projectId, String name, Long created, UUID userId) {
        this.projectId = projectId;
        this.name = name;
        this.created = created;
        this.userId = userId;
    }

    /**
     * Returns the project ID
     * 
     * @return UUID
     */
    @Override
    public UUID getProjectId() {
        return projectId;
    }

    /**
     * Sets the project ID
     * 
     * @param projectId
     */
    @Override
    public void setProjectId(UUID projectId) {
        this.projectId = projectId;
    }

    /**
     * Gets the project name
     * 
     * @return String
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Sets the project name
     * 
     * @param name
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the created date (as a long)
     * 
     * @return Long
     */
    @Override
    public Long getCreated() {
        return created;
    }

    /**
     * Sets the created date (as a long)
     * 
     * @param created the created to set
     */
    @Override
    public void setCreated(Long created) {
        this.created = created;
    }

    /**
     * Returns the user ID pf the project
     * 
     * @return UUID
     */
    @Override
    public UUID getUserId() {
        return userId;
    }

    /**
     * Sets the user ID of the project
     * 
     * @param userId the userId to set
     */
    @Override
    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    /**
     * Returns the project's columns
     * 
     * @return List<ProjectColumn>
     */
    @Override
    public List<ProjectColumn> getProjectColumns() {
        return projectColumns;
    }

    /**
     * Sets the project's columns
     * 
     * @param projectColumns
     */
    @Override
    public void setProjectColumns(List<ProjectColumn> projectColumns) {
        this.projectColumns = projectColumns;
    }

}
