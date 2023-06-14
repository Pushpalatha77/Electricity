package com.electricity.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.electricity.api.model.Admin;
import com.electricity.api.service.AdminService;
import com.electricity.api.util.LoggerUtil;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class AdminController {

	@Autowired
	private AdminService adminService;

	// ADMIN Post API
	@PostMapping("api/admin/add")
	public ResponseEntity<String> postAdmin(@RequestBody Admin admin) {
		adminService.insertAdmin(admin);
		LoggerUtil.logInfo("Admin details are posted");
		return ResponseEntity.status(HttpStatus.OK).body("Admin added in the DB");
	}

	// Get Api
	@GetMapping("api/getall/admins")
	public List<Admin> getAllAdmins() {
		List<Admin> list = adminService.getAllAdmins();
		return list;
	}

	// Put ApI
	@PutMapping("/api/admin/one/{id}")
	public ResponseEntity<String> updateAdminById(@PathVariable("id") int id, @RequestBody Admin admin) {
		adminService.updateAdminById(admin);
		return ResponseEntity.status(HttpStatus.OK).body("Admin is Updated");

	}

	// Delete Api
	@DeleteMapping("/api/admin/{id}")
	public ResponseEntity<String> deleteAdminById(@PathVariable("id") int id, @RequestBody Admin admin) {
		adminService.deleteAdminById(admin);
		return ResponseEntity.status(HttpStatus.OK).body("Admin is Deleted");
	}

}
