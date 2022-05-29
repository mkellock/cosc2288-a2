/**
 * UserTests
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

class UserTests {

    private static final UUID USER_ID = UUID
            .fromString("fe31bcf8-8893-4976-ab46-8dc83d68fbfe");
    private static final String USERNAME = "username";
    private static final String PASSWORD = "Passw0rd123!";
    private static final String FIRST_NAME = "First";
    private static final String LAST_NAME = "Last";
    private static final byte[] IMAGE = { 0x00, 0x01, 0x02 };
    private static final UUID DEFAULT_PROJECT_ID = UUID
            .fromString("a30b5aaf-957a-4914-be2f-489ee12ccea9");

    @Test
    void ShouldConstructAndGetAllProperties() {
        IUser user = new User(USER_ID, USERNAME, PASSWORD, FIRST_NAME, LAST_NAME,
                IMAGE, DEFAULT_PROJECT_ID);
        Assertions.assertEquals(USER_ID, user.getUserId());
        Assertions.assertEquals(USERNAME, user.getUsername());
        Assertions.assertEquals(PASSWORD, user.getPassword());
        Assertions.assertEquals(FIRST_NAME, user.getFirstName());
        Assertions.assertEquals(LAST_NAME, user.getLastName());
        Assertions.assertEquals(IMAGE, user.getImage());
        Assertions.assertEquals(DEFAULT_PROJECT_ID, user.getDefaultProjectId());
    }

    @Test
    void shouldSetAndGetUserId() {
        IUser user = new User();
        user.setUserId(USER_ID);
        Assertions.assertEquals(USER_ID, user.getUserId());
    }

    @Test
    void shouldSetAndGetUsername() {
        IUser user = new User();
        user.setUsername(USERNAME);
        Assertions.assertEquals(USERNAME, user.getUsername());
    }

    @Test
    void shouldSetAndGetPassword() {
        IUser user = new User();
        user.setPassword(PASSWORD);
        Assertions.assertEquals(PASSWORD, user.getPassword());
    }

    @Test
    void shouldSetAndGetFirstName() {
        IUser user = new User();
        user.setFirstName(FIRST_NAME);
        Assertions.assertEquals(FIRST_NAME, user.getFirstName());
    }

    @Test
    void shouldSetAndGetLastName() {
        IUser user = new User();
        user.setLastName(LAST_NAME);
        Assertions.assertEquals(LAST_NAME, user.getLastName());
    }

    @Test
    void shouldSetAndGetImage() {
        IUser user = new User();
        user.setImage(IMAGE);
        Assertions.assertEquals(IMAGE, user.getImage());
    }

    @Test
    void shouldSetAndGetDefaultProjectId() {
        IUser user = new User();
        user.setDefaultProjectId(DEFAULT_PROJECT_ID);
        Assertions.assertEquals(DEFAULT_PROJECT_ID, user.getDefaultProjectId());
    }

}
