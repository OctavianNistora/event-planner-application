package org.example.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class ViewEventController
{
    @FXML
    private TextField searchField;

    @FXML
    private ListView<String> eventList;

    private ObservableList<String> events = FXCollections.observableArrayList("Event 1", "Event 2", "Event 3");

    @FXML
    public void initialize()
    {
        eventList.setItems(events);
    }

    @FXML
    public void handleUserSpecificAction()
    {
        System.out.println("User specific action");
    }

    public void onEnter()
    {
        ArrayList<String> filteredEvents = new ArrayList<String>();
        filteredEvents.addAll(events);
        System.out.println(filteredEvents);
        for (String event : events)
        {
            if (!event.toLowerCase().contains(searchField.getText().toLowerCase()))
            {
                filteredEvents.remove(event);
            }
        }
        System.out.println(filteredEvents);
        eventList.setItems(FXCollections.observableArrayList(filteredEvents));
    }
}
