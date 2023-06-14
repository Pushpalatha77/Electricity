package com.electricity.api.model;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
}
