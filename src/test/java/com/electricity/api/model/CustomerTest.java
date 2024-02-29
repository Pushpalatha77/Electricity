package com.electricity.api.model;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;



import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;

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
    @Test
    public void testHashCode() {
        Customer customer1 = new Customer(1, "John Doe", "1234567890", "john.doe@example.com", "123 Main St", null, null);
        Customer customer2 = new Customer(1, "John Doe", "1234567890", "john.doe@example.com", "123 Main St", null, null);

        Assertions.assertEquals(customer1.hashCode(), customer2.hashCode());
    }

    @Test
    public void testToString() {
        Customer customer = new Customer(1, "John Doe", "1234567890", "john.doe@example.com", "123 Main St", null, null);

        String expectedToString = "Customer(id=1, name=John Doe, contactNo=1234567890, email=john.doe@example.com, address=123 Main St, meter=null, user=null)";
        Assertions.assertEquals(expectedToString, customer.toString());
    }
    @Test
    public void testEqualsAndCanEqual() {
        // Create a mock Meter and User objects
        Meter meter1 = Mockito.mock(Meter.class);
        Meter meter2 = Mockito.mock(Meter.class);
        User user1 = Mockito.mock(User.class);
        User user2 = Mockito.mock(User.class);

        // Create Customer objects with the same values
        Customer customer1 = new Customer(1, "John Doe", "1234567890", "john.doe@example.com", "123 Main St", meter1, user1);
        Customer customer2 = new Customer(1, "John Doe", "1234567890", "john.doe@example.com", "123 Main St", meter2, user2);

        // Test equals(Object) method
        Assertions.assertTrue(customer1.equals(customer2));

        // Test canEqual(Object) method
        Assertions.assertTrue(customer1.canEqual(customer2));
    }
}
