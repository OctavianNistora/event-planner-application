package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import org.example.model.Event;

public class EventDetailsController {
    @FXML
    private TextArea nameField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private TextArea tagsField;

    public void initEvent(Event event)
    {
        nameField.setText(event.getName());
        descriptionField.setText(event.getDescription());
        String tags = "";
        for (String tag : event.getTags())
        {
            tags += tag + ", ";
        }
        tags = tags.substring(0, tags.length() - 2);
        tagsField.setText(tags);
    }

}
