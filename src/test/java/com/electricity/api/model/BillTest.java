package com.electricity.api.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

class BillTest {

    @Mock
    private Customer customer;

    @Mock
    private Meter meter;

    private Bill bill;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bill = new Bill();
    }

    @Test
    void testGetId() {
        bill.setId(1);
        assertEquals(1, bill.getId());
    }

    @Test
    void testGetConsumerNumber() {
        bill.setConsumerNumber(123);
        assertEquals(123, bill.getConsumerNumber());
    }

    @Test
    void testGetConsumedUnits() {
        bill.setConsumedUnits(50);
        assertEquals(50, bill.getConsumedUnits());
    }

    @Test
    void testGetBillMonth() {
        bill.setBillMonth("June");
        assertEquals("June", bill.getBillMonth());
    }

    @Test
    void testGetBillDate() {
        LocalDate date = LocalDate.now();
        bill.setBillDate(date);
        assertEquals(date, bill.getBillDate());
    }

    @Test
    void testGetAmount() {
        bill.setAmount(100.50);
        assertEquals(100.50, bill.getAmount());
    }

    @Test
    void testGetDueDate() {
        LocalDate dueDate = LocalDate.now().plusDays(7);
        bill.setDueDate(dueDate);
        assertEquals(dueDate, bill.getDueDate());
    }

    @Test
    void testGetCustomer() {
        bill.setCustomer(customer);
        assertEquals(customer, bill.getCustomer());
    }

    @Test
    void testGetMeter() {
        bill.setMeter(meter);
        assertEquals(meter, bill.getMeter());
    }
}

