package com.electricity.api.controller;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.electricity.api.model.Meter;
import com.electricity.api.service.MeterService;

@ExtendWith(MockitoExtension.class)
class MeterControllerTest {

    @Mock
    private MeterService meterService;

    @InjectMocks
    private MeterController meterController;

    private Meter meter;

    @BeforeEach
    void setup() {
        meter = new Meter();
        meter.setId(1);
        meter.setMeterNo(123456);
    }

    @Test
    void testPostMeter() {
        // Call the method to be tested
        ResponseEntity<String> responseEntity = meterController.postMeter(meter);

        // Verify that the meterService method was called
        verify(meterService).insertMeter(meter);

        // Assert the expected result
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Meter Number added in DB", responseEntity.getBody());
    }

    @Test
    void testGetAllMeterNumbers() {
        List<Meter> meters = new ArrayList<>();
        meters.add(meter);

        // Mocking the meterService method
        when(meterService.getAllMeterNumbers()).thenReturn(meters);

        // Call the method to be tested
        List<Meter> result = meterController.getAllMeterNumbers();

        // Verify that the meterService method was called
        verify(meterService).getAllMeterNumbers();

        // Assert the expected result
        assertEquals(meters, result);
    }

    @Test
    void testGetMeterByIdWithValidId() {
        Optional<Meter> optional = Optional.of(meter);

        // Mocking the meterService method
        when(meterService.getMeterById(1)).thenReturn(optional);

        // Call the method to be tested
        ResponseEntity<Object> responseEntity = meterController.getMeterById(1);

        // Verify that the meterService method was called
        verify(meterService).getMeterById(1);

        // Assert the expected result
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(meter, responseEntity.getBody());
    }

   
    @Test
    void testGetMeterByIdWithInvalidId() {
        // Create an empty Optional
        Optional<Meter> optional = Optional.empty();

        // Mock the meterService method to return the empty Optional
        when(meterService.getMeterById(2)).thenReturn(optional);

        // Call the method to be tested
        ResponseEntity<Object> responseEntity = meterController.getMeterById(2);

        // Verify that the meterService method was called
        verify(meterService).getMeterById(2);

        // Assert the expected result
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Invalid ID Given", responseEntity.getBody().toString());
    }


}
