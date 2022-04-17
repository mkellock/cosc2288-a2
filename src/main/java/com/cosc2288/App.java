package com.cosc2288;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    private static String connectionString;

    public static void main(String[] args) {

        try (
                FileInputStream settingsFile =
                    new FileInputStream("app.properties")) {
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

    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(event -> System.out.println("Hello World!"));

        StackPane root = new StackPane();
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("123 Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
