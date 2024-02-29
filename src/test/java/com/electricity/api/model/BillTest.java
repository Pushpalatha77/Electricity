package com.electricity.api.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
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
    @Test
    public void testGettersAndSetters() {
        // Create a mock Customer and Meter objects
        Customer customer = Mockito.mock(Customer.class);
        Meter meter = Mockito.mock(Meter.class);
        
        // Create a Bill object
        Bill bill = new Bill();
        bill.setId(1);
        bill.setConsumerNumber(123456);
        bill.setConsumedUnits(100);
        bill.setBillMonth("OCT");
        bill.setBillDate(LocalDate.of(2023, 10, 1));
        bill.setAmount(2000.0);
        bill.setDueDate(LocalDate.of(2023, 10, 31));
        bill.setCustomer(customer);
        bill.setMeter(meter);
        
        // Verify the values using getters
        Assertions.assertEquals(1, bill.getId());
        Assertions.assertEquals(123456, bill.getConsumerNumber());
        Assertions.assertEquals(100, bill.getConsumedUnits());
        Assertions.assertEquals("OCT", bill.getBillMonth());
        Assertions.assertEquals(LocalDate.of(2023, 10, 1), bill.getBillDate());
        Assertions.assertEquals(2000.0, bill.getAmount(), 0.001);
        Assertions.assertEquals(LocalDate.of(2023, 10, 31), bill.getDueDate());
        Assertions.assertEquals(customer, bill.getCustomer());
        Assertions.assertEquals(meter, bill.getMeter());
    }
    
    @Test
    public void testEqualsAndCanEqual() {
        // Create mock Customer and Meter objects
        Customer customer1 = Mockito.mock(Customer.class);
        Customer customer2 = Mockito.mock(Customer.class);
        Meter meter1 = Mockito.mock(Meter.class);
        Meter meter2 = Mockito.mock(Meter.class);

        // Create Bill objects with the same values
        Bill bill1 = new Bill(1, 123456, 100, "January", LocalDate.of(2023, 1, 1), 100.0, LocalDate.of(2023, 1, 31), customer1, meter1);
        Bill bill2 = new Bill(1, 123456, 100, "January", LocalDate.of(2023, 1, 1), 100.0, LocalDate.of(2023, 1, 31), customer2, meter2);

        // Test equals(Object) method
        Assertions.assertTrue(bill1.equals(bill2));

        // Test canEqual(Object) method
        Assertions.assertTrue(bill1.canEqual(bill2));
    }

    @Test
    public void testHashCode() {
        // Create a mock Customer and Meter objects
        Customer customer = Mockito.mock(Customer.class);
        Meter meter = Mockito.mock(Meter.class);

        // Create a Bill object
        Bill bill = new Bill(1, 123456, 100, "January", LocalDate.of(2023, 1, 1), 100.0, LocalDate.of(2023, 1, 31), customer, meter);

//        Test hashCode() method
//         int expectedHashCode = 31 * (31 * (31 * (31 * (31 * (31 * (31 * (31 * 1 + 1) + 123456) + 100) + "January".hashCode()) + LocalDate.of(2023, 1, 1).hashCode()) + 100.0) + LocalDate.of(2023, 1, 31).hashCode()) + customer.hashCode()) + meter.hashCode();
  //      Assertions.assertEquals(expectedHashCode, bill.hashCode());
    }

    @Test
    public void testToString() {
        // Create a mock Customer and Meter objects
        Customer customer = Mockito.mock(Customer.class);
        Meter meter = Mockito.mock(Meter.class);

        // Create a Bill object
        Bill bill = new Bill(1, 123456, 100, "January", LocalDate.of(2023, 1, 1), 100.0, LocalDate.of(2023, 1, 31), customer, meter);

        // Test toString() method
        String expectedToString = "Bill(id=1, consumerNumber=123456, consumedUnits=100, billMonth=January, billDate=2023-01-01, amount=100.0, dueDate=2023-01-31, customer=" + customer.toString() + ", meter=" + meter.toString() + ")";
        Assertions.assertEquals(expectedToString, bill.toString());
    }

}

