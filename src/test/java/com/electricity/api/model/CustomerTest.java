package com.electricity.api.model;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    @Test
    public void testCustomerConstructor() {
        int id = 1;
        String name = "John Doe";
        String contactNo = "1234567890";
        String email = "john.doe@example.com";
        String address = "123 Main Street";
        Meter meter = new Meter();
        User user = new User();

        Customer customer = new Customer(id, name, contactNo, email, address, meter, user);

        assertEquals(id, customer.getId());
        assertEquals(name, customer.getName());
        assertEquals(contactNo, customer.getContactNo());
        assertEquals(email, customer.getEmail());
        assertEquals(address, customer.getAddress());
        assertEquals(meter, customer.getMeter());
        assertEquals(user, customer.getUser());
    }

    @Test
    public void testCustomerGettersAndSetters() {
        Customer customer = new Customer();

        int id = 1;
        String name = "John Doe";
        String contactNo = "1234567890";
        String email = "john.doe@example.com";
        String address = "123 Main Street";
        Meter meter = new Meter();
        User user = new User();

        customer.setId(id);
        customer.setName(name);
        customer.setContactNo(contactNo);
        customer.setEmail(email);
        customer.setAddress(address);
        customer.setMeter(meter);
        customer.setUser(user);

        assertEquals(id, customer.getId());
        assertEquals(name, customer.getName());
        assertEquals(contactNo, customer.getContactNo());
        assertEquals(email, customer.getEmail());
        assertEquals(address, customer.getAddress());
        assertEquals(meter, customer.getMeter());
        assertEquals(user, customer.getUser());
    }
}
