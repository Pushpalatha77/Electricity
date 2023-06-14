package com.electricity.api.model;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
}
