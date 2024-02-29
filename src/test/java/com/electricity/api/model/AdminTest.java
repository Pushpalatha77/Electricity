package com.electricity.api.model;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;

public class AdminTest {

    @Test
    public void testAdminConstructor() {
        int id = 1;
        String name = "John Doe";
        String contactNo = "1234567890";
        String email = "john.doe@example.com";

        Admin admin = new Admin(id, name, contactNo, email);

        assertEquals(id, admin.getId());
        assertEquals(name, admin.getName());
        assertEquals(contactNo, admin.getContactNo());
        assertEquals(email, admin.getEmail());
    }

    @Test
    public void testAdminGettersAndSetters() {
        Admin admin = new Admin();

        int id = 1;
        String name = "John Doe";
        String contactNo = "1234567890";
        String email = "john.doe@example.com";

        admin.setId(id);
        admin.setName(name);
        admin.setContactNo(contactNo);
        admin.setEmail(email);

        assertEquals(id, admin.getId());
        assertEquals(name, admin.getName());
        assertEquals(contactNo, admin.getContactNo());
        assertEquals(email, admin.getEmail());
    }
    @Test
    void testEqualsAndCanEqual() {
        // Create two Admin objects with the same values
        Admin admin1 = new Admin(1, "John Doe", "1234567890", "john@example.com");
        Admin admin2 = new Admin(1, "John Doe", "1234567890", "john@example.com");

        // Test equals(Object) method
        Assertions.assertTrue(admin1.equals(admin2));

        // Test canEqual(Object) method
        Assertions.assertTrue(admin1.canEqual(admin2));
    }

    @Test
    void testHashCode() {
        // Create an Admin object
        Admin admin = new Admin(1, "John Doe", "1234567890", "john@example.com");

        // Test hashCode() method
        Assertions.assertEquals(30817, admin.hashCode());
    }

    @Test
    void testToString() {
        // Create an Admin object
        Admin admin = new Admin(1, "John Doe", "1234567890", "john@example.com");

        // Test toString() method
        String expected = "Admin(id=1, name=John Doe, contactNo=1234567890, email=john@example.com)";
        Assertions.assertEquals(expected, admin.toString());
    }
}
