package com.electricity.api.model;


import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;

public class MeterTest {

    @Test
    public void testMeterConstructor() {
        int id = 1;
        int meterNo = 12345;

        Meter meter = new Meter(id, meterNo);

        assertEquals(id, meter.getId());
        assertEquals(meterNo, meter.getMeterNo());
    }

    @Test
    public void testMeterGettersAndSetters() {
        Meter meter = new Meter();

        int id = 1;
        int meterNo = 12345;

        meter.setId(id);
        meter.setMeterNo(meterNo);

        assertEquals(id, meter.getId());
        assertEquals(meterNo, meter.getMeterNo());
    }
    @Test
    public void testEquals() {
        Meter meter1 = new Meter(1, 123456);
        Meter meter2 = new Meter(1, 123456);
        Meter meter3 = new Meter(2, 789012);

        // Test equality of meters with the same ID and meter number
        Assertions.assertEquals(meter1, meter2);

        // Test inequality of meters with different ID or meter number
        Assertions.assertNotEquals(meter1, meter3);
        Assertions.assertNotEquals(meter2, meter3);
    }

    @Test
    public void testEqualsNull() {
        Meter meter = new Meter(1, 123456);

        // Test equality with null
        Assertions.assertNotEquals(meter, null);
    }

    @Test
    public void testEqualsDifferentClass() {
        Meter meter = new Meter(1, 123456);
        String str = "not a meter";

        // Test equality with a different class
        Assertions.assertNotEquals(meter, str);
    }

    @Test
    public void testHashCode() {
        Meter meter1 = new Meter(1, 123456);
        Meter meter2 = new Meter(1, 123456);
        Meter meter3 = new Meter(2, 789012);

        // Test hash code equality of meters with the same ID and meter number
        Assertions.assertEquals(meter1.hashCode(), meter2.hashCode());

        // Test hash code inequality of meters with different ID or meter number
        Assertions.assertNotEquals(meter1.hashCode(), meter3.hashCode());
        Assertions.assertNotEquals(meter2.hashCode(), meter3.hashCode());
    }

    @Test
    public void testToString() {
        Meter meter = new Meter(1, 123456);

        // Test the string representation of the meter object
        String expectedString = "Meter(id=1, meterNo=123456)";
        Assertions.assertEquals(expectedString, meter.toString());
    }

}
