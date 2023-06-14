package com.electricity.api.model;


import org.junit.jupiter.api.Test;
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
}
