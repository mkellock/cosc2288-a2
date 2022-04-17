/**
 * ProjectColumnTests
 *
 * v1.0
 *
 * 2022-04-12
 *
 * © 2022 Matthew Kellock
 */
package com.cosc2288.models;

import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProjectColumnTests {

    private static final UUID PROJECT_COLUMN_ID =
        UUID.fromString("6b602110-ffcc-45d3-85c6-3a6fd2c32945");
    private static final String NAME = "Name";
    private static final int ORDER = 1;
    private static final UUID PROJECT_ID =
        UUID.fromString("1f74708e-917a-4b21-b637-77f857e2f4e4");

    @Test
    void ShouldConstructAndGetAllProperties() {
        ProjectColumn projectColumn = new ProjectColumn(
                PROJECT_COLUMN_ID,
                NAME,
                ORDER,
                PROJECT_ID);
        Assertions.assertEquals(
                PROJECT_COLUMN_ID,
                projectColumn.getProjectColumnId());
        Assertions.assertEquals(NAME, projectColumn.getName());
        Assertions.assertEquals(ORDER, projectColumn.getOrder());
        Assertions.assertEquals(PROJECT_ID, projectColumn.getProjectId());

    }

    @Test
    void shouldSetAndGetProjectColumnId() {
        ProjectColumn projectColumn = new ProjectColumn();
        projectColumn.setProjectColumnId(PROJECT_COLUMN_ID);
        Assertions.assertEquals(
                PROJECT_COLUMN_ID,
                projectColumn.getProjectColumnId());
    }

    @Test
    void shouldSetAndGetName() {
        ProjectColumn projectColumn = new ProjectColumn();
        projectColumn.setName(NAME);
        Assertions.assertEquals(NAME, projectColumn.getName());
    }

    @Test
    void shouldSetAndGetOrder() {
        ProjectColumn projectColumn = new ProjectColumn();
        projectColumn.setOrder(ORDER);
        Assertions.assertEquals(ORDER, projectColumn.getOrder());
    }

    @Test
    void shouldSetAndGetProjectId() {
        ProjectColumn projectColumn = new ProjectColumn();
        projectColumn.setProjectId(PROJECT_ID);
        Assertions.assertEquals(PROJECT_ID, projectColumn.getProjectId());
    }

}
