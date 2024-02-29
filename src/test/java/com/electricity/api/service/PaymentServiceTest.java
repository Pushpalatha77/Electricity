package com.electricity.api.service;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import com.electricity.api.data.BillRepository;

import com.electricity.api.data.PaymentRepository;
import com.electricity.api.exception.PaymentNotFoundException;
import com.electricity.api.model.Bill;

import com.electricity.api.model.Customer;

import com.electricity.api.model.Meter;

import com.electricity.api.model.Payment;

import com.electricity.api.model.User;

@ExtendWith(MockitoExtension.class)

public class PaymentServiceTest {

	@Mock

	private PaymentRepository paymentRepository;

	@Mock

	private BillRepository billRepository;

	private List<Payment> payments = new ArrayList();

	@InjectMocks
	private PaymentService service;

	private Payment payment;

	private Meter meter;

	private Bill bill;

	private Customer customer;

	private User user;

	@BeforeEach

	void setup() {

		user = new User();

		user.setId(1);

		user.setUsername("nagulu");

		user.setRole("CUSTOMER");

		user.setPassword("qwertykey");

		bill = new Bill();

		customer = new Customer();

		customer.setName("nagulu");

		customer.setId(1010);

		customer.setAddress("nandyala");

		customer.setContactNo("9090809090");

		payment = new Payment();

		bill.setId(101);

		bill.setConsumerNumber(123456);

		bill.setAmount(1980);

		bill.setBillDate(LocalDate.of(2000, 10, 20));

		bill.setDueDate(LocalDate.of(2000, 11, 05));

		bill.setBillMonth("OCT");

		bill.setConsumedUnits(120);

		payment.setCustomer(customer);

		payment.setId(23);

		payment.setBill(bill);

		payment.setPaymentDate(LocalDate.of(2022, 05, 04));

		payment.setTotalAmount(555);

		meter = new Meter();

		meter.setId(1011);

		meter.setMeterNo(1333);

		bill.setMeter(meter);

		customer.setMeter(meter);

		customer.setUser(user);

		bill.setCustomer(customer);

		payments.add(payment);

	}

	@Test

 

    void testGetAllPaymentRecords() {

 

        when(paymentRepository.findAll()).thenReturn(payments);

 

        assertEquals(payments, service.getAllPaymentRecords());

 

        assertEquals(555,payments.get(0).getTotalAmount());

 

    }
	
	@Test

	 

    void testGetPaymentById() throws PaymentNotFoundException {

 

        when(paymentRepository.findById(23)).thenReturn(Optional.of(payment));

 

        assertEquals(Optional.of(payment),service.getPaymentById(23));

 

        assertEquals(555,Optional.of(payment).get().getTotalAmount());

 

    }

 

//    @Test

//

//    void testUpdateBillById() {

//

//        when(billRepository.save(bill)).thenReturn(bill);

//

//    //    assertEquals(bill,service.updateBillById(bill));

//

//        assertEquals(1980,bills.get(0).getAmount());

//

//    }

//

//    // assertEquals(120,bill.get);

//

//    // }

 

    @Test

 

    void testGetPaymentByCustomerId() throws PaymentNotFoundException{

 

        when(paymentRepository.findAll()).thenReturn(payments);

 

        assertEquals(payments,service.getPaymentByCustomerId(1010));

 

        assertEquals(555,payments.get(0).getTotalAmount());

 

    }
	
	
    @Test
    void testLatePaidAmount() {
        double expectedFineAmount = 39.0;

        double actualFineAmount = service.latePaidAmount(payment);

        Assertions.assertEquals(expectedFineAmount, actualFineAmount);
    }

    @Test
    void testAssign() {
        service.assign(payment);

        verify(paymentRepository).save(payment);
    }

    @Test
    void testDeletePaymentById() throws PaymentNotFoundException {
        service.deletePaymentById(payment);

        verify(paymentRepository).delete(payment);
    }

//    @Test
//    void testGetAllPaymentRecords() {
//        List<Payment> expectedList = new ArrayList<>();
//
//        List<Payment> actualList = paymentService.getAllPaymentRecords();
//
//        Assertions.assertEquals(expectedList, actualList);
//        verify(paymentRepository).findAll();
//    }

    @Test
    void testUpdatePaymentById() throws PaymentNotFoundException {
        service.updatePaymentById(payment);

        verify(paymentRepository).save(payment);
    }

   
    @Test
    void testGetPaymentByCustomerId1() throws PaymentNotFoundException {
        List<Payment> expectedList = new ArrayList<>();

        List<Payment> actualList = service.getPaymentByCustomerId(101);

        Assertions.assertEquals(expectedList, actualList);
        verify(paymentRepository).findAll();
    }

}
