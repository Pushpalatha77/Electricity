package com.electricity.api.controller;

import com.electricity.api.exception.PaymentNotFoundException;
import com.electricity.api.model.Bill;
import com.electricity.api.model.Customer;
import com.electricity.api.model.Payment;
import com.electricity.api.service.BillService;
import com.electricity.api.service.CustomerService;
import com.electricity.api.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PaymentControllerTest {

    @Mock
    private PaymentService paymentService;

    @Mock
    private BillService billService;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private PaymentController paymentController;

    private Payment payment;
    private Bill bill;
    private Customer customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        payment = new Payment();
        payment.setId(23);
        payment.setPaymentDate(LocalDate.of(2022, 05, 04));
        payment.setTotalAmount(555);

        bill = new Bill();
        bill.setId(101);
        bill.setConsumerNumber(123456);
        bill.setAmount(1980);
        bill.setBillDate(LocalDate.of(2000, 10, 20));
        bill.setDueDate(LocalDate.of(2000, 11, 05));
        bill.setBillMonth("OCT");
        bill.setConsumedUnits(120);

        customer = new Customer();
        customer.setId(1010);
        customer.setName("nagulu");
        customer.setAddress("nandyala");
        customer.setContactNo("9090809090");

        bill.setCustomer(customer);
        payment.setBill(bill);
        payment.setCustomer(customer);
    }

    @Test
    void testBillPayment_ValidBillAndCustomer_ReturnsSuccessMessage() throws Exception {
        int billId = 101;
        int custId = 1010;

        when(billService.getBillById(billId)).thenReturn(Optional.of(bill));
        when(customerService.getCustomerById(custId)).thenReturn(Optional.of(customer));
      //  when(paymentService.assign(payment)).thenReturn(payment);

        ResponseEntity<String> response = paymentController.BillPayment(payment, billId, custId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Payment Succesfull!!!", response.getBody());

        verify(billService, times(1)).getBillById(billId);
        verify(customerService, times(1)).getCustomerById(custId);
        verify(paymentService, times(1)).assign(payment);
    }

    @Test
    void testBillPayment_InvalidBill_ReturnsBadRequest() throws Exception {
        int billId = 101;
        int custId = 1010;

        when(billService.getBillById(billId)).thenReturn(Optional.empty());

        ResponseEntity<String> response = paymentController.BillPayment(payment, billId, custId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("entered invalid billId", response.getBody());

        verify(billService, times(1)).getBillById(billId);
        verify(customerService, never()).getCustomerById(anyInt());
        verify(paymentService, never()).assign(any(Payment.class));
    }

    @Test
    void testBillPayment_InvalidCustomer_ReturnsBadRequest() throws Exception {
        int billId = 101;
        int custId = 1010;

        when(billService.getBillById(billId)).thenReturn(Optional.of(bill));
        when(customerService.getCustomerById(custId)).thenReturn(Optional.empty());

        ResponseEntity<String> response = paymentController.BillPayment(payment, billId, custId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("entered invalid custId", response.getBody());

        verify(billService, times(1)).getBillById(billId);
        verify(customerService, times(1)).getCustomerById(custId);
        verify(paymentService, never()).assign(any(Payment.class));
    }

    @Test
    void testDeletePaymentById_ValidPaymentId_ReturnsSuccessMessage() throws PaymentNotFoundException {
        int paymentId = 23;

        ResponseEntity<String> response = paymentController.deletePaymentById(paymentId, payment);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Payment Record is Deleted", response.getBody());

        verify(paymentService, times(1)).deletePaymentById(payment);
    }

    @Test
    void testGetAllPaymentRecords_ReturnsListOfPayments() {
        List<Payment> expectedPayments = new ArrayList<>();
        expectedPayments.add(payment);

        when(paymentService.getAllPaymentRecords()).thenReturn(expectedPayments);

        List<Payment> result = paymentController.getAllPaymentRecords();

        assertEquals(expectedPayments, result);

        verify(paymentService, times(1)).getAllPaymentRecords();
    }

    @Test
    void testUpdatePaymentById_ValidPaymentId_ReturnsSuccessMessage() throws PaymentNotFoundException {
        int paymentId = 23;

        ResponseEntity<String> response = paymentController.updatePaymentById(paymentId, payment);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Payment Record is updated", response.getBody());

        verify(paymentService, times(1)).updatePaymentById(payment);
    }
}
