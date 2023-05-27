package org.example.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.example.model.User;

import java.util.ArrayList;

public class ViewEventController
{
    private User user=null;
    @FXML
    private TextField searchField;

    @FXML
    private ListView<String> eventList;

    @FXML
    private Button addEventButton;

    private ObservableList<String> events = FXCollections.observableArrayList("Event 1", "Event 2", "Event 3");

    @FXML
    public void initialize()
    {
        eventList.setItems(events);
    }

    public void initUser(User user)
    {
        this.user = user;
        if (user == null)
        {
            addEventButton.setDisable(false);
        }
    }

    @FXML
    public void handleUserSpecificAction()
    {
        System.out.println("User specific action");
    }

    public void handleOnEnter()
    {
        ArrayList<String> filteredEvents = new ArrayList<String>();
        filteredEvents.addAll(events);
        for (String event : events)
        {
            if (!event.toLowerCase().contains(searchField.getText().toLowerCase()))
            {
                filteredEvents.remove(event);
            }
        }
        eventList.setItems(FXCollections.observableArrayList(filteredEvents));
    }

    public void handleAddEvent()
    {
        System.out.println("Add event");
    }
}
