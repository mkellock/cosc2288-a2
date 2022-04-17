package com.cosc2288.controllers;

import java.nio.file.Files;
import java.util.UUID;
import java.io.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

abstract class BaseTests {

    private static String testConnString;

    private final static String JDBC_CONN_PREXIX = "jdbc:sqlite:";

    protected static String getTestConnString() {
        return testConnString;
    }

    @BeforeAll
    protected static void ProvisionDB() throws IOException {
        // Generate a unique DB name
        testConnString = UUID.randomUUID() + ".db";

        // Copy the test DB
        File src = new File("cosc2288-2-test.db");
        File dest = new File(testConnString);
        Files.copy(src.toPath(), dest.toPath());

        // Add the connection string info
        testConnString = JDBC_CONN_PREXIX + testConnString;
    }

    @AfterAll
    protected static void DeprovisionDB() throws IOException {
        File dest = new File(testConnString.replace(JDBC_CONN_PREXIX, ""));
        Files.delete(dest.toPath());
    }
}
