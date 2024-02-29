package com.electricity.api.model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class PaymentTest {

    @Test
    public void testPaymentConstructor() {
        int id = 1;
        double totalAmount = 100.0;
        LocalDate paymentDate = LocalDate.now();
        String transactionId = "ABC123";
        Customer customer = new Customer();
        Bill bill = new Bill();

        Payment payment = new Payment(id, totalAmount, paymentDate, transactionId, customer, bill);

        assertEquals(id, payment.getId());
        assertEquals(totalAmount, payment.getTotalAmount());
        assertEquals(paymentDate, payment.getPaymentDate());
        assertEquals(transactionId, payment.getTransactionId());
        assertEquals(customer, payment.getCustomer());
        assertEquals(bill, payment.getBill());
    }

    @Test
    public void testPaymentGettersAndSetters() {
        Payment payment = new Payment();

        int id = 1;
        double totalAmount = 100.0;
        LocalDate paymentDate = LocalDate.now();
        String transactionId = "ABC123";
        Customer customer = new Customer();
        Bill bill = new Bill();

        payment.setId(id);
        payment.setTotalAmount(totalAmount);
        payment.setPaymentDate(paymentDate);
        payment.setTransactionId(transactionId);
        payment.setCustomer(customer);
        payment.setBill(bill);

        assertEquals(id, payment.getId());
        assertEquals(totalAmount, payment.getTotalAmount());
        assertEquals(paymentDate, payment.getPaymentDate());
        assertEquals(transactionId, payment.getTransactionId());
        assertEquals(customer, payment.getCustomer());
        assertEquals(bill, payment.getBill());
    }
    @Test
    void testEqualsAndCanEqual() {
        // Create mock Customer and Bill objects
        Customer customer1 = Mockito.mock(Customer.class);
        Customer customer2 = Mockito.mock(Customer.class);
        Bill bill1 = Mockito.mock(Bill.class);
        Bill bill2 = Mockito.mock(Bill.class);

        // Create Payment objects with the same values
        Payment payment1 = new Payment(1, 100.0, LocalDate.of(2023, 1, 1), "123456", customer1, bill1);
        Payment payment2 = new Payment(1, 100.0, LocalDate.of(2023, 1, 1), "123456", customer2, bill2);

        // Test equals(Object) method
        Assertions.assertTrue(payment1.equals(payment2));

        // Test canEqual(Object) method
        Assertions.assertTrue(payment1.canEqual(payment2));
    }

    @Test
    void testHashCode() {
        // Create mock Customer and Bill objects
        Customer customer = Mockito.mock(Customer.class);
        Bill bill = Mockito.mock(Bill.class);

        // Create Payment object
        Payment payment = new Payment(1, 100.0, LocalDate.of(2023, 1, 1), "123456", customer, bill);

        // Test hashCode() method
        Assertions.assertEquals(50397409, payment.hashCode());
    }

    @Test
    void testToString() {
        // Create mock Customer and Bill objects
        Customer customer = Mockito.mock(Customer.class);
        Bill bill = Mockito.mock(Bill.class);

        // Create Payment object
        Payment payment = new Payment(1, 100.0, LocalDate.of(2023, 1, 1), "123456", customer, bill);

        // Test toString() method
        String expected = "Payment(id=1, totalAmount=100.0, paymentDate=2023-01-01, transactionId=123456, " +
                "customer=" + customer + ", bill=" + bill + ")";
        Assertions.assertEquals(expected, payment.toString());
    }
}
