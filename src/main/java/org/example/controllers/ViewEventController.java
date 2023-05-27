package org.example.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.model.Event;
import org.example.model.User;
import org.example.services.EventService;

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

    public void refresh()
    {
        events = FXCollections.observableArrayList(EventService.getEvents());
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

    public void handleOnKeyTyped()
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

    public void handleOnClick(MouseEvent mouseEvent)
    {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY) && mouseEvent.getClickCount() == 2) {
            Event event = eventList.getSelectionModel().getSelectedItem();
            try {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("event-details.fxml"));
                stage.setScene(new Scene(loader.load(), 600, 400));
                EventDetailsController controller = loader.getController();
                controller.initEvent(event);
                stage.setTitle(event + " | Event details");
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(eventList.getScene().getWindow());
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void handleAddEvent()
    {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("add-event.fxml"));
            stage.setScene(new Scene(loader.load(), 600, 400));
            AddEventController controller = loader.getController();
            controller.setParentController(this);
            stage.setTitle("Add event");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(eventList.getScene().getWindow());
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
