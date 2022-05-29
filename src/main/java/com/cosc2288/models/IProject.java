package com.cosc2288.models;

import java.util.List;
import java.util.UUID;

public interface IProject {

    /**
     * Returns the project ID
     * 
     * @return UUID
     */
    UUID getProjectId();

    /**
     * Sets the project ID
     * 
     * @param projectId
     */
    void setProjectId(UUID projectId);

    /**
     * Gets the project name
     * 
     * @return String
     */
    String getName();

    /**
     * Sets the project name
     * 
     * @param name
     */
    void setName(String name);

    /**
     * Returns the created date (as a long)
     * 
     * @return Long
     */
    Long getCreated();

    /**
     * Sets the created date (as a long)
     * 
     * @param created the created to set
     */
    void setCreated(Long created);

    /**
     * Returns the user ID pf the project
     * 
     * @return UUID
     */
    UUID getUserId();

    /**
     * Sets the user ID of the project
     * 
     * @param userId the userId to set
     */
    void setUserId(UUID userId);

    /**
     * Returns the project's columns
     * 
     * @return List<ProjectColumn>
     */
    List<ProjectColumn> getProjectColumns();

    /**
     * Sets the project's columns
     * 
     * @param projectColumns
     */
    void setProjectColumns(List<ProjectColumn> projectColumns);

}