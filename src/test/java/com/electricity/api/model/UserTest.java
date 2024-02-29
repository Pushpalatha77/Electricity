package com.electricity.api.model;


import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;

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
    @Test
    public void testEquals() {
        User user1 = new User(1, "john", "password", "admin");
        User user2 = new User(1, "john", "password", "admin");
        User user3 = new User(2, "jane", "password", "user");

        // Test equality of users with the same ID, username, password, and role
        Assertions.assertEquals(user1, user2);

        // Test inequality of users with different ID, username, password, or role
        Assertions.assertNotEquals(user1, user3);
        Assertions.assertNotEquals(user2, user3);
    }

    @Test
    public void testEqualsNull() {
        User user = new User(1, "john", "password", "admin");

        // Test equality with null
        Assertions.assertNotEquals(user, null);
    }

    @Test
    public void testEqualsDifferentClass() {
        User user = new User(1, "john", "password", "admin");
        String str = "not a user";

        // Test equality with a different class
        Assertions.assertNotEquals(user, str);
    }

    @Test
    public void testHashCode() {
        User user1 = new User(1, "john", "password", "admin");
        User user2 = new User(1, "john", "password", "admin");
        User user3 = new User(2, "jane", "password", "user");

        // Test hash code equality of users with the same ID, username, password, and role
        Assertions.assertEquals(user1.hashCode(), user2.hashCode());

        // Test hash code inequality of users with different ID, username, password, or role
        Assertions.assertNotEquals(user1.hashCode(), user3.hashCode());
        Assertions.assertNotEquals(user2.hashCode(), user3.hashCode());
    }

    @Test
    public void testToString() {
        User user = new User(1, "john", "password", "admin");

        // Test the string representation of the user object
        String expectedString = "User(id=1, username=john, password=password, role=admin)";
        Assertions.assertEquals(expectedString, user.toString());
    }
}
