/**
 * ProjectTests
 *
 * v1.0
 *
 * 2022-04-12
 *
 * © 2022 Matthew Kellock
 */
package com.cosc2288.models;

import java.time.Instant;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProjectTests {

    private static final UUID PROJECT_ID =
        UUID.fromString("035b7b0d-9895-422d-a514-73463bbe4534");
    private static final String NAME = "Name";
    private static final Instant CREATED =
        Instant.parse("2022-04-12T00:00:00.00Z");
    private static final UUID USER_ID =
        UUID.fromString("b4a55bb8-4cb9-4de2-8489-224bcfb993f6");

    @Test
    void ShouldConstructAndGetAllProperties() {
        Project project = new Project(
                PROJECT_ID,
                NAME,
                CREATED,
                USER_ID);
        Assertions.assertEquals(PROJECT_ID, project.getProjectId());
        Assertions.assertEquals(NAME, project.getName());
        Assertions.assertEquals(CREATED, project.getCreated());
        Assertions.assertEquals(USER_ID, project.getUserId());
    }

    @Test
    void shouldSetAndGetProjectId() {
        Project project = new Project();
        project.setProjectId(PROJECT_ID);
        Assertions.assertEquals(PROJECT_ID, project.getProjectId());
    }

    @Test
    void shouldSetAndGetName() {
        Project project = new Project();
        project.setName(NAME);
        Assertions.assertEquals(NAME, project.getName());
    }

    @Test
    void shouldSetAndGetCreated() {
        Project project = new Project();
        project.setCreated(CREATED);
        Assertions.assertEquals(CREATED, project.getCreated());
    }

    @Test
    void shouldSetAndGetUserId() {
        Project project = new Project();
        project.setUserId(USER_ID);
        Assertions.assertEquals(USER_ID, project.getUserId());
    }
}
