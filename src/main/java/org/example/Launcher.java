package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.services.EventService;
import org.example.services.FileSystemService;
import org.example.services.UserService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Launcher extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception
    {
        initDirectory();
        UserService.initDatabase();
        EventService.initDatabase();
        try {
            EventService.addEvent("Event 1", "Description 1", new ArrayList<String>(List.of("action", "adventure")), LocalDate.of(2020, 1, 1));
            EventService.addEvent("Event 2", "Description 2", new ArrayList<String>(List.of("drama")), LocalDate.of(2010, 1, 1));
            EventService.addEvent("Event 3", "Description 3", new ArrayList<String>(List.of("comics", "sci-fi")), LocalDate.of(2016, 5, 1));
        } catch (Exception e) {
            // until Create Event is implemented
        }
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("authentication.fxml"));
        primaryStage.setTitle("Authentication");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    private void initDirectory() {
        Path applicationHomePath = FileSystemService.APPLICATION_HOME_PATH;
        if (!Files.exists(applicationHomePath))
            applicationHomePath.toFile().mkdirs();
    }
}