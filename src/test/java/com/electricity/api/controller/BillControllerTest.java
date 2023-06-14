//package com.electricity.api.controller;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.time.LocalDate;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import com.electricity.api.model.Bill;
//import com.electricity.api.model.Customer;
//import com.electricity.api.model.Meter;
//import com.electricity.api.model.User;
//import com.electricity.api.service.BillService;
//import com.electricity.api.service.CustomerService;
//
//@ExtendWith(MockitoExtension.class)
//public class BillControllerTest {
//	@Mock
//	private BillService service;
//	
//	@InjectMocks
//	private BillController controller;
//	
//	@Mock
//	private CustomerService customerService;
//	
//	
//	private Bill bill = new Bill();
//	private Customer customer = new Customer();
//	private Meter meter = new Meter();
//	private User user = new User();
//	
//	@BeforeEach
//	void setup() {
//		bill.setId(101);
//		bill.setConsumerNumber(123456);
//		bill.setAmount(1980);
//		bill.setBillDate(LocalDate.of(2000, 10, 20));
//		bill.setDueDate(LocalDate.of(2000, 11, 05));
//		bill.setBillMonth("OCT");
//		bill.setConsumedUnits(120);
//		bill.setCustomer(customer);
//		customer.setName("nagulu");
//		customer.setId(1010);
//		customer.setAddress("nandyala");
//		customer.setContactNo("9090809090");
//		bill.setMeter(meter);
//		meter.setId(1011);
//		meter.setMeterNo(1333);
//		customer.setUser(user);
//		user.setId(1);
//		user.setUsername("nagulu");
//		user.setRole("CUSTOMER");
//		user.setPassword("qwertykey");
//		MockitoAnnotations.openMocks(this);
//		
//	}
////	@Test
////	void testPostBill() {
////		 Mockito.doNothing().when(service.insertBill(bill)).thenReturn(bill);
////		ResponseEntity<String> response = controller.postBill(1010, 1011, bill);
////		assertEquals(HttpStatus.OK, response.getStatusCode());
////	}
//	@Test
//	void testPostBill() {
//	    // Call the method to be tested
//	    ResponseEntity<String> response = controller.postBill(1010, 1011, bill);
//
//	    // Verify that the service method was called with the correct arguments
//	    verify(service).insertBill(bill);
//
//	    // Assert the expected response status
//	    assertEquals(HttpStatus.OK, response.getStatusCode());
//	}
//}
package com.electricity.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.electricity.api.exception.BillNotFoundException;
import com.electricity.api.model.Bill;
import com.electricity.api.model.Customer;
import com.electricity.api.model.Meter;
import com.electricity.api.service.BillService;
import com.electricity.api.service.CustomerService;
import com.electricity.api.service.MeterService;
import com.electricity.api.util.LoggerUtil;

@ExtendWith(MockitoExtension.class)
public class BillControllerTest {
    @Mock
    private BillService billService;

    @Mock
    private CustomerService customerService;

    @Mock
    private MeterService meterService;

    @InjectMocks
    private BillController controller;

    private Bill bill;
    private Customer customer;
    private Meter meter;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        bill = new Bill();
        bill.setId(101);
        bill.setConsumerNumber(123456);
        bill.setAmount(1980);
        bill.setBillDate(LocalDate.of(2000, 10, 20));
        bill.setDueDate(LocalDate.of(2000, 11, 05));
        bill.setBillMonth("OCT");
        bill.setConsumedUnits(120);

        customer = new Customer();
        customer.setName("nagulu");
        customer.setId(1010);
        customer.setAddress("nandyala");
        customer.setContactNo("9090809090");

        meter = new Meter();
        meter.setId(1011);
        meter.setMeterNo(1333);
    }

    @Test
    void testPostBill() {
        // Mocking service methods
        when(customerService.getCustomerById(1010)).thenReturn(Optional.of(customer));
        when(meterService.getMeterById(1011)).thenReturn(Optional.of(meter));
       // when(billService.insertBill(bill)).thenReturn(bill);

        // Call the method to be tested
        ResponseEntity<String> response = controller.postBill(1010, 1011, bill);

        // Verify that the service methods were called with the correct arguments
        verify(customerService).getCustomerById(1010);
        verify(meterService).getMeterById(1011);
        verify(billService).insertBill(bill);

        // Assert the expected response status and message
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Bill added in DB", response.getBody());
    }

    @Test
    void testGetAllBill() {
        // Mocking service method
        List<Bill> billList = new ArrayList<>();
        billList.add(bill);
        when(billService.getAllBill()).thenReturn(billList);

        // Call the method to be tested
        List<Bill> result = controller.getAllBill();

        // Verify that the service method was called
        verify(billService).getAllBill();

        // Assert the expected result
        assertEquals(billList, result);
    }

    @Test
    void testGetBillById() throws BillNotFoundException {
        // Mocking service method
        when(billService.getBillById(101)).thenReturn(Optional.of(bill));

        // Call the method to be tested
        ResponseEntity<Object> response = controller.getBillById(101);

        // Verify that the service method was called with the correct argument
        verify(billService).getBillById(101);

        // Assert the expected response status and body
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bill, response.getBody());
    }

    @Test
    void testUpdateBillById() throws BillNotFoundException {
        // Call the method to be tested
        ResponseEntity<String> response = controller.updateBillById(101, bill);

        // Verify that the service method was called with the correct argument
        verify(billService).updateBillById(bill);

        // Assert the expected response status and body
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Bill is updated", response.getBody());
    }

    @Test
    void testDeleteBillById() throws BillNotFoundException{
        // Call the method to be tested
        ResponseEntity<String> response = controller.deleteBillById(101, bill);

        // Verify that the service method was called with the correct argument
        verify(billService).deleteBillById(bill);

        // Assert the expected response status and body
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Bill is Deleted", response.getBody());
    }

    @Test
    void testGetBillByCustomerId() {
        // Mocking service method
        List<Bill> billList = new ArrayList<>();
        billList.add(bill);
        when(billService.getBillByCustomerId(1010)).thenReturn(billList);

        // Call the method to be tested
        List<Bill> result = controller.getBillByCustomerId(1010);

        // Verify that the service method was called with the correct argument
        verify(billService).getBillByCustomerId(1010);

        // Assert the expected result
        assertEquals(billList, result);
    }

    @Test
    void testGetBillByMeterId() {
        // Mocking service method
        List<Bill> billList = new ArrayList<>();
        billList.add(bill);
        when(billService.getBillByMeterId(1011)).thenReturn(billList);

        // Call the method to be tested
        List<Bill> result = controller.getBillByMeterId(1011);

        // Verify that the service method was called with the correct argument
        verify(billService).getBillByMeterId(1011);

        // Assert the expected result
        assertEquals(billList, result);
    }
}
