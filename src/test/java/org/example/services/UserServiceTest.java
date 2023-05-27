package org.example.services;

import org.example.exceptions.NullFieldException;
import org.example.exceptions.UsernameAlreadyExistsException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.example.services.FileSystemService.getPathToFile;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserServiceTest
{
    @BeforeAll
    static void setUp()
    {
        Path applicationHomePath = FileSystemService.APPLICATION_HOME_PATH;
        if (!Files.exists(applicationHomePath))
            applicationHomePath.toFile().mkdirs();
        UserService.initTestingDatabase();
    }

    @Test
    @DisplayName("Test if user is added to database")
    void addUserTest()
    {
        assertDoesNotThrow(() -> {
            UserService.addUser("test", "test", "test", "test", "test", "test");
        });
    }

    @Test
    @DisplayName("Test if added user already exists")
    void duplicateUsernameTest()
    {
        assertThrows(UsernameAlreadyExistsException.class, () -> {
            UserService.addUser("test", "test", "test", "test", "test", "test");
        });
    }

    @Test
    @DisplayName("Test if any field is empty")
    void emptyFieldTest()
    {
        assertThrows(NullFieldException.class, () -> {
            UserService.addUser("", "test", "test", "test", "test", "test");
        });
        assertThrows(NullFieldException.class, () -> {
            UserService.addUser("test", "", "test", "test", "test", "test");
        });
        assertThrows(NullFieldException.class, () -> {
            UserService.addUser("test", "test", "", "test", "test", "test");
        });
        assertThrows(NullFieldException.class, () -> {
            UserService.addUser("test", "test", "test", "", "test", "test");
        });
        assertThrows(NullFieldException.class, () -> {
            UserService.addUser("test", "test", "test", "test", "", "test");
        });
        assertThrows(NullFieldException.class, () -> {
            UserService.addUser("test", "test", "test", "test", "test", null);
        });
    }

    @AfterAll
    static void setDown()
    {
        UserService.closeDatabase();
        try {
            getPathToFile("testUser.db").toFile().delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
