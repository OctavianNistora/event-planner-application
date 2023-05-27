package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.example.exceptions.UsernameAlreadyExistsException;
import org.example.services.UserService;

public class RegistrationController
{
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ChoiceBox gender;
    @FXML
    private Text registrationMessage;

    @FXML
    public void initialize()
    {
        gender.getItems().addAll("male", "female", "other");
    }

    public void handleRegisterAction()
    {
        try {
            UserService.addUser(firstNameField.getText(), lastNameField.getText(), emailField.getText(), usernameField.getText(), passwordField.getText(), ((String) gender.getValue()));
            registrationMessage.getScene().getWindow().hide();
        } catch (Exception e) {
            registrationMessage.setText(e.getMessage());
        }
    }
}
