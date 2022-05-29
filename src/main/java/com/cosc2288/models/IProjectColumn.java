package com.cosc2288.models;

import java.util.List;
import java.util.UUID;

public interface IProjectColumn {

    /**
     * Returns the project column ID
     * 
     * @return UUID
     */
    UUID getProjectColumnId();

    /**
     * Sets the project column ID
     * 
     * @param projectColumnId
     */
    void setProjectColumnId(UUID projectColumnId);

    /**
     * Returns the name
     * 
     * @return String
     */
    String getName();

    /**
     * Sets the name
     * 
     * @param name
     */
    void setName(String name);

    /**
     * Returns the order no.
     * 
     * @return int
     */
    int getOrder();

    /**
     * Sets the order no.
     * 
     * @param order
     */
    void setOrder(int order);

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
     * Returns the project tasks
     * 
     * @return List<ProjectTask>
     */
    List<ProjectTask> getProjectTasks();

    /**
     * Sets the project tasks
     * 
     * @param projectTasks
     */
    void setProjectTasks(List<ProjectTask> projectTasks);

}