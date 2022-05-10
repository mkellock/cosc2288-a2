package com.cosc2288;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import com.cosc2288.controllers.QuoteController;
import com.cosc2288.controllers.UserController;
import com.cosc2288.models.User;
import com.cosc2288.views.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class App extends Application {

    private static String connectionString;
    private static User loggedInUser;
    private static Main mainView;

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User user) {
        loggedInUser = user;
        getMainView().setUser(user);
    }

    public static Main getMainView() {
        return mainView;
    }

    public static void setMainView(Main main) {
        mainView = main;
    }

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

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the log in scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/fxml/Login.fxml"));
        Parent logIn = loader.load();
        Scene logInScene = new Scene(logIn);

        primaryStage.setTitle("Log in to Smart Board");
        primaryStage.setScene(logInScene);
        primaryStage.show();

        Login login = loader.<Login>getController();
        login.setApp(this);
        login.setStage(primaryStage);
    }

    private void showMainScene(Stage primaryStage) throws SQLException, IOException {
        // Load the main scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/fxml/Main.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        // Load the quote
        App.setMainView(loader.<Main>getController());
        QuoteController quoteController = new QuoteController(connectionString);
        App.getMainView().setQuote(quoteController.randomQuote().getMessage());

        // Set the main and scene
        Main main = loader.<Main>getController();
        main.setApp(this);
        main.setStage(primaryStage);

        primaryStage.setTitle("SmartBoard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Log in methods
     */

    public void loginOk(String username, String password, Stage primaryStage) throws IOException, SQLException {
        UserController userController = new UserController(connectionString);
        Alert addUserAlert = new Alert(AlertType.ERROR);
        User user = userController.logInUser(username, password);

        if (user == null) {
            addUserAlert.setContentText("Username and/or password is incorrect");
            addUserAlert.show();
        } else {
            showMainScene(primaryStage);
            App.setLoggedInUser(user);
        }
    }

    public void newEditUser(Stage primaryStage, Boolean edit) throws IOException {
        // Load the main scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/fxml/UserEdit.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        UserEdit userEdit = loader.<UserEdit>getController();
        userEdit.setApp(this);
        userEdit.setStage(primaryStage);

        if (Boolean.TRUE.equals(edit)) {
            primaryStage.setTitle("Edit profile");

            userEdit.setUser(loggedInUser);
        } else {
            primaryStage.setTitle("Create a new user");
        }

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * New user methods
     */

    public void newUserCancel(Stage primaryStage) throws Exception {
        start(primaryStage);
    }

    public void newEditUserOk(User user, Stage primaryStage)
            throws SQLException, IOException {
        Alert addUserAlert = new Alert(AlertType.ERROR);
        UserController userController = new UserController(connectionString);

        if (App.getLoggedInUser() == null) {
            if (userController.findUser(user.getUsername()) == null) {
                userController.addUser(user);
                showMainScene(primaryStage);
                App.setLoggedInUser(user);
            } else {
                addUserAlert.setContentText("Username already exists, please enter another");
                addUserAlert.show();
            }
        } else {
            userController.editUser(App.getLoggedInUser());
            showMainScene(primaryStage);
            App.setLoggedInUser(user);
        }
    }

    /**
     * User management methods
     * 
     * @throws Exception
     */
    public void logOut(Stage stage) throws Exception {
        start(stage);
    }

    public void profile(Stage stage) throws IOException {
        newEditUser(stage, true);
    }

}
