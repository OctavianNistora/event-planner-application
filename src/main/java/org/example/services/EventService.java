package org.example.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.example.exceptions.IdenticalEventExistsException;
import org.example.exceptions.NullFieldException;
import org.example.model.Event;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.example.services.FileSystemService.getPathToFile;

public class EventService {
    private static ObjectRepository<Event> eventRepository;
    private static Nitrite database;

    public static void initDatabase() {
        database = Nitrite.builder()
                .filePath(getPathToFile("event.db").toFile())
                .openOrCreate("test", "test");

        eventRepository = database.getRepository(Event.class);
    }

    public static void initTestingDatabase() {
        database = Nitrite.builder()
                .filePath(getPathToFile("testEvent.db").toFile())
                .openOrCreate("test", "test");

        eventRepository = database.getRepository(Event.class);
    }

    public static void closeDatabase() {
        database.close();
        eventRepository = null;
    }

    public static void addEvent(String name, String description, ArrayList<String> tags) throws IdenticalEventExistsException, NullFieldException {
        if (name.isEmpty() || description.isEmpty() || tags.isEmpty())
        {
            throw new NullFieldException();
        }
        Event event = new Event(name, description, tags);
        checkIdenticalEventExists(event);
        eventRepository.insert(event);
    }

    public static ArrayList<Event> getEvents() {
        ArrayList<Event> events = new ArrayList<Event>();
        for (Event event : eventRepository.find()) {
            events.add(event);
        }
        return events;
    }

    private static void checkIdenticalEventExists(Event event) throws IdenticalEventExistsException {
        for (Event e : eventRepository.find()) {
            if (e.equals(event)) {
                throw new IdenticalEventExistsException();
            }
        }
    }
}
