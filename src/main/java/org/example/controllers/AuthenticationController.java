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
        try
        {
            Stage currentStage = (Stage) registrationMessage.getScene().getWindow();
            Stage newStage = new Stage();
            currentStage.close();
            Parent loginRoot = FXMLLoader.load(getClass().getClassLoader().getResource("view-events.fxml"));
            newStage.setScene(new Scene(loginRoot, 600, 400));
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
