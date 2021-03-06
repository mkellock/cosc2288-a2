/**
 * ProjectTaskTests
 *
 * v1.0
 *
 * 2022-04-12
 *
 * © 2022 Matthew Kellock
 */
package com.cosc2288.models;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProjectTaskTests {

    private static final UUID PROJECT_TASK_ID = UUID
            .fromString("182335a1-cfa1-4251-b2a1-ba63df10cd7e");
    private static final String NAME = "Name";
    private static final String DESCRIPTION = "Description";
    private static final int ORDER = 1;
    private static final Long DUE_DATE = Instant
            .parse("2022-04-14T00:00:00.00Z").toEpochMilli();
    private static final Long COMPLETED_DATE = Instant
            .parse("2022-04-13T00:00:00.00Z").toEpochMilli();
    private static final UUID PROJECT_COLUMN_ID = UUID
            .fromString("174a11d4-8d95-4acd-a914-c296428a0053");
    private static final List<ActionItem> ACTION_ITEMS = 
            new ArrayList<ActionItem>();

    @Test
    void ShouldConstructAndGetAllProperties() {
        IProjectTask projectTask = new ProjectTask(PROJECT_TASK_ID, NAME,
                DESCRIPTION, ORDER, DUE_DATE, COMPLETED_DATE, PROJECT_COLUMN_ID,
                ACTION_ITEMS);
        Assertions.assertEquals(PROJECT_TASK_ID,
                projectTask.getProjectTaskId());
        Assertions.assertEquals(NAME, projectTask.getName());
        Assertions.assertEquals(DESCRIPTION, projectTask.getDescription());
        Assertions.assertEquals(ORDER, projectTask.getOrder());
        Assertions.assertEquals(DUE_DATE, projectTask.getDueDate());
        Assertions.assertEquals(COMPLETED_DATE, projectTask.getCompletedDate());
        Assertions.assertEquals(PROJECT_COLUMN_ID,
                projectTask.getProjectColumnId());
    }

    @Test
    void shouldSetAndGetProjectTaskId() {
        IProjectTask projectTask = new ProjectTask();
        projectTask.setProjectTaskId(PROJECT_TASK_ID);
        Assertions.assertEquals(PROJECT_TASK_ID,
                projectTask.getProjectTaskId());
    }

    @Test
    void shouldSetAndGetName() {
        IProjectTask projectTask = new ProjectTask();
        projectTask.setName(NAME);
        Assertions.assertEquals(NAME, projectTask.getName());
    }

    @Test
    void shouldSetAndGetDescription() {
        IProjectTask projectTask = new ProjectTask();
        projectTask.setDescription(DESCRIPTION);
        Assertions.assertEquals(DESCRIPTION, projectTask.getDescription());
    }

    @Test
    void shouldSetAndGetOrder() {
        IProjectTask projectTask = new ProjectTask();
        projectTask.setOrder(ORDER);
        Assertions.assertEquals(ORDER, projectTask.getOrder());
    }

    @Test
    void shouldSetAndGetDueDate() {
        IProjectTask projectTask = new ProjectTask();
        projectTask.setDueDate(DUE_DATE);
        Assertions.assertEquals(DUE_DATE, projectTask.getDueDate());
    }

    @Test
    void shouldSetAndGetCompletedDate() {
        IProjectTask projectTask = new ProjectTask();
        projectTask.setCompletedDate(COMPLETED_DATE);
        Assertions.assertEquals(COMPLETED_DATE, projectTask.getCompletedDate());
    }

    @Test
    void shouldSetAndGetProjectColumnId() {
        IProjectTask projectTask = new ProjectTask();
        projectTask.setProjectColumnId(PROJECT_COLUMN_ID);
        Assertions.assertEquals(PROJECT_COLUMN_ID,
                projectTask.getProjectColumnId());
    }
}
