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