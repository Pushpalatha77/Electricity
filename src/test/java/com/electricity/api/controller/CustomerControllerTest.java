package com.electricity.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.electricity.api.data.UserRepository;
import com.electricity.api.dto.Message;
import com.electricity.api.model.Customer;
import com.electricity.api.model.Meter;
import com.electricity.api.model.User;
import com.electricity.api.service.CustomerService;
import com.electricity.api.service.MeterService;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {
    @Mock
    private CustomerService customerService;

    @Mock
    private MeterService meterService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CustomerController controller;

    private Customer customer;
    private Meter meter;
    private User user;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        customer = new Customer();
        customer.setId(6);
        customer.setName("Raju");
        customer.setEmail("raju@gmail.com");
        customer.setContactNo("7330739188");
        customer.setAddress("PUNE");

        meter = new Meter();
        meter.setId(101);
        meter.setMeterNo(234566);

        customer.setMeter(meter);

        user = new User();
      //  user.setUserName("raju_user");
        user.setPassword("password123");
        user.setRole("CUSTOMER");

        customer.setUser(user);
    }

    @Test
    void testPostCustomer() {
        // Mocking service methods
      //  when(customerService.insertCustomer(customer)).thenReturn(customer);
        when(meterService.getMeterById(101)).thenReturn(Optional.of(meter));
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");
        when(userRepository.save(user)).thenReturn(user);

        // Call the method to be tested
        ResponseEntity<Object> response = controller.postCustomer(101, customer);

        // Verify that the service methods were called with the correct arguments
        verify(customerService).insertCustomer(customer);
        verify(meterService).getMeterById(101);
        verify(passwordEncoder).encode("password123");
        verify(userRepository).save(user);

        // Assert the expected response status and body
        Message message = new Message();
        message.setMsg("Customer Registration Is Done");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(message, response.getBody());
    }

    @Test
    void testGetAllCustomer() {
        // Mocking service method
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        when(customerService.getAllCustomer()).thenReturn(customerList);

        // Call the method to be tested
        List<Customer> result = controller.getAllCustomer();

        // Verify that the service method was called
        verify(customerService).getAllCustomer();

        // Assert the expected result
        assertEquals(customerList, result);
    }

    @Test
    void testGetCustomerById() {
        // Mocking service method
        when(customerService.getCustomerById(6)).thenReturn(Optional.of(customer));

        // Call the method to be tested
        ResponseEntity<Object> response = controller.getCustomerById(6);

        // Verify that the service method was called with the correct argument
        verify(customerService).getCustomerById(6);

        // Assert the expected response status and body
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customer, response.getBody());
    }

    @Test
    void testUpdateCustomerById() {
        // Call the method to be tested
        ResponseEntity<String> response = controller.updateCustomerById(6, customer);

        // Verify that the service method was called with the correct argument
        verify(customerService).updateCustomerById(customer);

        // Assert the expected response status and body
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Customer is updated", response.getBody());
    }

    @Test
    void testDeleteCustomerById() {
        // Call the method to be tested
        ResponseEntity<String> response = controller.deleteCustomerById(6, customer);

        // Verify that the service method was called with the correct argument
        verify(customerService).deleteCustomerById(customer);

        // Assert the expected response status and body
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Customer is Deleted", response.getBody());
    }

    @Test
    void testGetCustomerByMeterId() {
        // Mocking service method
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        when(customerService.getCustomerByMeterId(101)).thenReturn(customerList);

        // Call the method to be tested
        List<Customer> result = controller.getCustomerByMeterId(101);

        // Verify that the service method was called with the correct argument
        verify(customerService).getCustomerByMeterId(101);

        // Assert the expected result
        assertEquals(customerList, result);
    }
}
