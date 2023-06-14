package com.electricity.api.model;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testUserConstructor() {
        int id = 1;
        String username = "john.doe";
        String password = "password123";
        String role = "admin";

        User user = new User(id, username, password, role);

        assertEquals(id, user.getId());
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(role, user.getRole());
    }

    @Test
    public void testUserGettersAndSetters() {
        User user = new User();

        int id = 1;
        String username = "john.doe";
        String password = "password123";
        String role = "admin";

        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);

        assertEquals(id, user.getId());
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(role, user.getRole());
    }
}
