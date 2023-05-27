package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.example.services.EventService;

import java.util.ArrayList;

public class AddEventController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField tagsField;
    @FXML
    private Text registrationMessage;

    private ViewEventController parentController;
    public void handleRegisterAction()
    {
        ArrayList<String> tags = new ArrayList<>();
        String[] tagsArray = tagsField.getText().split("[,; ]");
        for (String tag : tagsArray) {
            if (!tag.isEmpty()) {
                tags.add(tag);
            }
        }
        try {
            EventService.addEvent(nameField.getText(), descriptionField.getText(), tags);
            registrationMessage.setText("Event added successfully!");
            // update the list of events
            parentController.refresh();
            registrationMessage.getScene().getWindow().hide();
        } catch (Exception e) {
            registrationMessage.setText(e.getMessage());
        }
    }

    public void setParentController(ViewEventController parentController)
    {
        this.parentController = parentController;
    }
}
