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

public class Project {

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
     * @return the projectId
     */
    public UUID getProjectId() {
        return projectId;
    }

    /**
     * @param projectId the projectId to set
     */
    public void setProjectId(UUID projectId) {
        this.projectId = projectId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the created
     */
    public Long getCreated() {
        return created;
    }

    /**
     * @param created the created to set
     */
    public void setCreated(Long created) {
        this.created = created;
    }

    /**
     * @return the userId
     */
    public UUID getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public List<ProjectColumn> getProjectColumns() {
        return projectColumns;
    }

    public void setProjectColumns(List<ProjectColumn> projectColumns) {
        this.projectColumns = projectColumns;
    }

}
