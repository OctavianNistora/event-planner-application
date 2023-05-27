package org.example.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.example.exceptions.InvalidCredentialsException;
import org.example.exceptions.NullFieldException;
import org.example.exceptions.UsernameAlreadyExistsException;
import org.example.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import static org.example.services.FileSystemService.getPathToFile;

public class UserService
{
    private static ObjectRepository<User> userRepository;
    private static Nitrite database;

    public static void initDatabase() {
        database = Nitrite.builder()
                .filePath(getPathToFile("user.db").toFile())
                .openOrCreate("test", "test");

        userRepository = database.getRepository(User.class);
    }

    public static void initTestingDatabase() {
        database = Nitrite.builder()
                .filePath(getPathToFile("testUser.db").toFile())
                .openOrCreate("test", "test");

        userRepository = database.getRepository(User.class);
    }

    public static void closeDatabase() {
        database.close();
        userRepository = null;
    }

    public static void addUser(String firstName, String lastName, String email, String username, String password, String gender) throws UsernameAlreadyExistsException, NullFieldException {
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty() || gender == null)
        {
            throw new NullFieldException();
        }
        checkUserDoesNotAlreadyExist(username);
        userRepository.insert(new User(firstName, lastName, email, username, encodePassword(username, password), gender));
    }

    public static User findUser(String username, String password) throws InvalidCredentialsException
    {
        for (User user : userRepository.find())
        {
            if (Objects.equals(username, user.getUsername()))
            {
                if (Objects.equals(encodePassword(username, password), user.getPassword()))
                {
                    return user;
                }
                else
                {
                    throw new InvalidCredentialsException();
                }
            }
        }
        throw new InvalidCredentialsException();
    }

    private static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        if (username.equals("admin"))
            throw new UsernameAlreadyExistsException(username);
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }

    private static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }
}
