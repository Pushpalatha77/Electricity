package com.electricity.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.electricity.api.model.Meter;
import com.electricity.api.service.MeterService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class MeterController {

	@Autowired
	private MeterService meterService;

	// Meter POST API

	@PostMapping("api")
	public ResponseEntity<String> postMeter(@RequestBody Meter meter) {
		meterService.insertMeter(meter);
		return ResponseEntity.status(HttpStatus.OK).body("Meter Number added in DB");
	}

	// GetAll API
	@GetMapping("/api/getall")
	public List<Meter> getAllMeterNumbers() {
		List<Meter> list = meterService.getAllMeterNumbers();
		return list;
	}

	// GetById API

	@GetMapping("/api/meter/{id}")

	public ResponseEntity<Object> getMeterById(@PathVariable("id") int id) {

		Optional<Meter> optional = meterService.getMeterById(id);

		if (optional == null)

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid ID Given");

		Meter meter = optional.get();

		return ResponseEntity.status(HttpStatus.OK).body(meter);

	}
}
