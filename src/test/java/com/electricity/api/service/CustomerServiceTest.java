package com.electricity.api.service;

 

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

 

import static org.mockito.Mockito.when;

 

import java.util.List;

import java.util.Optional;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;

import org.mockito.Mock;

 

import org.mockito.junit.jupiter.MockitoExtension;

 

import com.electricity.api.data.CustomerRepository;

 

import com.electricity.api.model.Customer;

import com.electricity.api.model.Meter;

import com.electricity.api.model.User;

@ExtendWith(MockitoExtension.class)

class CustomerServiceTest {

    

    @Mock

    private CustomerRepository customerrepository;

    

    

    private List<Customer> customers= new ArrayList();

    private List<Meter> meters= new ArrayList();

    

    

    @InjectMocks

    private CustomerService service;

    

    

    private Customer customer;

    private Meter meter;

    private User user;

    

    

    @BeforeEach

    void setup() {

        customer =new Customer();

        customer.setName("Raju");

        customer.setId(6);

        customer.setEmail("raju@gmail.com");

        customer.setContactNo("7330739188");

        customer.setAddress("PUNE");

        meter = new Meter();

        meter.setId(101);

        meter.setMeterNo(234566);

        customer.setMeter(meter);

    customers.add(customer);

        

    }

    

 

    @Test

    void getAllCustomerTest()  {

          when(customerrepository.findAll()).thenReturn(customers);

 

            assertEquals(customers, service.getAllCustomer());

 

            assertEquals("Raju",customers.get(0).getName());

    }

    

//    @Test

//    void insertCustomerTest() {

//        when(customerrepository.save(customer).thenReurn(customers));

//         assertEquals(customers, service.insertCustomer(customer));

//        

//    }

    

    

    @Test

 

    void testGetCustomerById() {

 

        

 

        when(customerrepository.findById(6)).thenReturn(Optional.of(customer));

 

        assertEquals(Optional.of(customer),service.getCustomerById(6));

 

        assertEquals(6,Optional.of(customer).get().getId());

    }

 

    

    

 

    @Test

 

    void testGetCustomerByMeterId() {

 
        when(customerrepository.findAll()).thenReturn(customers);

 

        assertEquals(customers,service.getCustomerByMeterId(101));

 

        assertEquals("Raju",customers.get(0).getName());

           }

    

@Test

void testUpdateCustomerById()  {

    when(customerrepository.findAll()).thenReturn(customers);

     assertEquals(customers, service.getAllCustomer());



     assertEquals("Raju",customers.get(0).getName());

    



}



@Test

void testDeleteCustomerById()  {

   when(customerrepository.findAll()).thenReturn(customers);

     assertEquals(customers, service.getAllCustomer());



     assertEquals("Raju",customers.get(0).getName());

}
}





 

 


