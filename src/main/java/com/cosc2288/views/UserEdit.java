/**
 * UserEdit
 *
 * v1.0
 *
 * 2022-05-29
 *
 * © 2022 Matthew Kellock
 */
package com.cosc2288.views;

import com.cosc2288.IApp;
import com.cosc2288.models.IUser;
import com.cosc2288.models.User;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class UserEdit {
    @FXML
    private TextField username;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField password;

    @FXML
    private ImageView userImage;

    private IApp app;
    private Stage stage;
    private byte[] image;
    private IUser user;

    public UserEdit() {
        user = new User();
        user.setUserId(UUID.randomUUID());
    }

    /**
     * Sets the App instance
     * 
     * @param app
     */
    public void setApp(IApp app) {
        this.app = app;
    }

    /**
     * Sets the current user
     * 
     * @param user
     */
    public void setUser(IUser user) {
        this.user = user;

        username.setText(user.getUsername());
        username.setDisable(true);

        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        password.setText(user.getPassword());
        image = user.getImage();

        userImage
                .setImage(new Image(new ByteArrayInputStream(user.getImage())));
    }

    /**
     * Gets the default user image
     * 
     * @throws IOException
     */
    @FXML
    protected void initialize() throws IOException {
        loadImage(getClass().getResource("/com/cosc2288/views/profile.jpg")
                .getPath());
    }

    /**
     * Loads an image and applies it to the user profile
     * 
     * @param imagePath
     * @throws IOException
     */
    private void loadImage(String imagePath) throws IOException {
        // Load the image
        File imageFile = new File(imagePath);
        try (FileInputStream imageStream = new FileInputStream(imageFile)) {
            // Set the image into the private variable
            image = new byte[(int) imageFile.length()];

            int count = 0;
            while (count >= 0) {
                count = imageStream.read(image);
            }

            // Set the profile image
            Image profileImage = new Image("file:" + imagePath);
            userImage.setImage(profileImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the Cancel button event
     */
    @FXML
    private void cancel() {
        app.newUserCancel();
    }

    /**
     * Handles the Ok button event
     */
    @FXML
    private void ok() {
        Alert addUserAlert = new Alert(AlertType.ERROR);

        if (username.getLength() == 0) {
            addUserAlert.setContentText("Please enter a username");
            addUserAlert.show();
        } else if (firstName.getLength() == 0) {
            addUserAlert.setContentText("Please enter a first name");
            addUserAlert.show();
        } else if (lastName.getLength() == 0) {
            addUserAlert.setContentText("Please enter a last name");
            addUserAlert.show();
        } else if (password.getLength() == 0) {
            addUserAlert.setContentText("Please enter a password");
            addUserAlert.show();
        } else {
            user.setUsername(username.getText());
            user.setFirstName(firstName.getText());
            user.setLastName(lastName.getText());
            user.setPassword(password.getText());
            user.setImage(image);

            app.newEditUserOk(user);
        }
    }

    /**
     * Handles the image click event, opening a file dialog and loading the
     * specified image
     */
    @FXML
    private void onImageClick() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Profile Image");
        fileChooser.getExtensionFilters()
                .addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg",
                        "*.gif", "*.bmp"),
                        new ExtensionFilter("All Files", "*.*"));

        File profileImageFile = fileChooser.showOpenDialog(stage);

        if (profileImageFile != null) {
            loadImage(profileImageFile.toString());
        }
    }

}
