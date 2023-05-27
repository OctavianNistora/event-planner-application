package org.example.services;

import org.example.exceptions.IdenticalEventExistsException;
import org.example.exceptions.NullFieldException;
import org.example.exceptions.UsernameAlreadyExistsException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.example.services.FileSystemService.getPathToFile;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventServiceTest {
    @BeforeAll
    static void setUp()
    {
        Path applicationHomePath = FileSystemService.APPLICATION_HOME_PATH;
        if (!Files.exists(applicationHomePath))
            applicationHomePath.toFile().mkdirs();
        EventService.initTestingDatabase();
    }

    @Test
    @DisplayName("Test if event is added to database")
    void addUserTest()
    {
        assertDoesNotThrow(() -> {
            EventService.addEvent("test", "test", new ArrayList<String>(List.of("test")));
        });
    }

    @Test
    @DisplayName("Test if added event already exists")
    void duplicateUsernameTest()
    {
        assertThrows(IdenticalEventExistsException.class, () -> {
            EventService.addEvent("test", "test", new ArrayList<String>(List.of("test")));
        });
    }

    @Test
    @DisplayName("Test if any field is empty")
    void emptyFieldTest()
    {
        assertThrows(NullFieldException.class, () -> {
            EventService.addEvent("", "test", new ArrayList<String>(List.of("test")));
        });
        assertThrows(NullFieldException.class, () -> {
            EventService.addEvent("test", "", new ArrayList<String>(List.of("test")));
        });
        assertThrows(NullFieldException.class, () -> {
            EventService.addEvent("test", "test", new ArrayList<String>());
        });
    }

    @AfterAll
    static void setDown()
    {
        EventService.closeDatabase();
        try {
            getPathToFile("testEvent.db").toFile().delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
