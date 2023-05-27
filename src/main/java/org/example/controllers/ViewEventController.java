package org.example.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import org.example.model.Event;
import org.example.model.User;
import org.example.services.EventService;

import java.time.LocalDate;
import java.util.ArrayList;

public class ViewEventController
{
    private User user=null;
    @FXML
    private TextField searchField;

    @FXML
    private ListView<Event> eventList;

    @FXML
    private Button addEventButton;

    private ObservableList<Event> events = FXCollections.observableArrayList(EventService.getEvents());

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
        ArrayList<Event> filteredEvents = new ArrayList<Event>(events);
        for (Event event : events)
        {
            if (    !event.getName().toLowerCase().contains(searchField.getText().toLowerCase()) &&
                    !event.getDescription().toLowerCase().contains(searchField.getText().toLowerCase()) &&
                    !event.getTags().toString().toLowerCase().contains(searchField.getText().toLowerCase()))
            {
                filteredEvents.remove(event);
            }
        }
        eventList.setItems(FXCollections.observableArrayList(filteredEvents));
    }

    public void handleOnClick(MouseEvent event)
    {
        if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
            System.out.println(eventList.getSelectionModel().getSelectedItem());
        }
    }

    public void handleAddEvent()
    {
        System.out.println("Add event");
    }
}
