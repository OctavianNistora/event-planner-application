package org.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.exceptions.InvalidCredentialsException;
import org.example.model.User;
import org.example.services.UserService;

public class AuthenticationController
{
    @FXML
    private Text registrationMessage;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    public void handleRegisterAction()
    {
        try {
            Stage stage = new Stage();
            Parent registerRoot = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(new Scene(registerRoot, 600, 400));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleLoginAction()
    {
        try {
            User user;
            String nameUser;
            if (usernameField.getText().equals("admin") && passwordField.getText().equals("admin")) {
                user = null;
                nameUser = "ADMIN";
            }
            else {
                user = UserService.findUser(usernameField.getText(), passwordField.getText());
                nameUser = user.getFirstName() + " " + user.getLastName();
            }
            Stage currentStage = (Stage) registrationMessage.getScene().getWindow();
            Stage newStage = new Stage();
            currentStage.close();
            //Parent loginRoot = FXMLLoader.load
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view-events.fxml"));
            newStage.setScene(new Scene(loader.load(), 600, 400));
            ViewEventController controller = loader.getController();
            controller.initUser(user);
            newStage.setTitle(nameUser + " | View Events");
            newStage.show();
        } catch (InvalidCredentialsException e) {
            registrationMessage.setText(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
