package com.cosc2288;

import java.io.Console;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.cosc2288.controllers.QuoteController;
import com.cosc2288.models.Quote;
import com.cosc2288.views.Login;
import com.cosc2288.views.Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    private static String connectionString;

    public static void main(String[] args) {

        try (
                FileInputStream settingsFile = new FileInputStream("app.properties")) {
            Properties appSettings = new Properties();

            // load config.properties file
            appSettings.load(settingsFile);

            // This is where you add your config variables:
            connectionString = (String) appSettings.get("connnection_string");
        } catch (IOException e) {
            // Trap the error (returning null if we have an exception)
            e.printStackTrace();
        }

        launch(args);
    }

    public static void ok(String username, String password) {
        System.out.println(username);
    }

    public static void newUser() {
        System.out.println("New user");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the log in scene
        FXMLLoader logInLoader = new FXMLLoader(getClass().getResource("views/fxml/Login.fxml"));
        Parent logIn = logInLoader.load();
        Scene logInScene = new Scene(logIn);

        primaryStage.setTitle("Log in to Smart Board");
        primaryStage.setScene(logInScene);
        primaryStage.show();

        // Load the user
        Login login = logInLoader.<Login>getController();

        // // Load the main scene
        // FXMLLoader mainLoader = new
        // FXMLLoader(getClass().getResource("views/fxml/Main.fxml"));
        // Parent root = mainLoader.load();
        // Scene scene = new Scene(root);

        // // Load the quote
        // Main main = mainLoader.<Main>getController();
        // QuoteController quoteController = new QuoteController(connectionString);
        // main.setQuote(quoteController.randomQuote().getMessage());

        // primaryStage.setTitle("123 Hello World!");
        // primaryStage.setScene(scene);
        // primaryStage.show();
    }

}
