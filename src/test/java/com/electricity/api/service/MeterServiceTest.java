package com.electricity.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.electricity.api.data.MeterRepository;
import com.electricity.api.model.Meter;

@ExtendWith(MockitoExtension.class)
class MeterServiceTest {

    @Mock
    private MeterRepository meterRepository;

    @InjectMocks
    private MeterService meterService;

    private Meter meter;

    @BeforeEach
    void setup() {
        meter = new Meter();
        meter.setId(1);
        meter.setMeterNo(123456);
    }

    @Test
    void testInsertMeter() {
        // Call the method to be tested
        meterService.insertMeter(meter);

        // Verify that the meterRepository method was called
        verify(meterRepository).save(meter);
    }

    @Test
    void testGetAllMeterNumbers() {
        List<Meter> meters = new ArrayList<>();
        meters.add(meter);

        // Mocking the meterRepository method
        when(meterRepository.findAll()).thenReturn(meters);

        // Call the method to be tested
        List<Meter> result = meterService.getAllMeterNumbers();

        // Verify that the meterRepository method was called
        verify(meterRepository).findAll();

        // Assert the expected result
        assertEquals(meters, result);
    }

    @Test
    void testGetMeterById() {
        Optional<Meter> optional = Optional.of(meter);

        // Mocking the meterRepository method
        when(meterRepository.findById(1)).thenReturn(optional);

        // Call the method to be tested
        Optional<Meter> result = meterService.getMeterById(1);

        // Verify that the meterRepository method was called
        verify(meterRepository).findById(1);

        // Assert the expected result
        assertEquals(optional, result);
    }
}
