/**
 * ActionItem
 *
 * v1.0
 *
 * 2022-04-12
 *
 * © 2022 Matthew Kellock
 */
package com.cosc2288.views;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import com.cosc2288.App;
import com.cosc2288.models.User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main {
    @FXML
    private Text quote;

    @FXML
    private Text userFullName;

    @FXML
    private ImageView userImage;

    @FXML
    private Button logOutButton;

    @FXML
    private Button profileButton;

    private App app;
    private Stage stage;

    public void setApp(App app) {
        this.app = app;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setQuote(String quoteMessage) {
        quote.setText(quoteMessage);
    }

    public void setUser(User user) {
        userFullName.setText(user.getFirstName() + " " + user.getLastName());
        userImage.setImage(new Image(new ByteArrayInputStream(user.getImage())));
    }

    @FXML
    private void profile() throws IOException {
        app.profile(stage);
    }

    @FXML
    private void logOut() throws Exception {
        app.logOut(stage);
    }
}
