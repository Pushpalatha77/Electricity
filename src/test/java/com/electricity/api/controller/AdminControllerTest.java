package com.electricity.api.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.electricity.api.model.Admin;
import com.electricity.api.service.AdminService;
@ExtendWith(MockitoExtension.class)
class AdminControllerTest {

	@Mock
	private AdminService service;
	
	@InjectMocks
	private AdminController controller;
	
	private Admin admin=new Admin();
	
	@BeforeEach
	void setup() {
		admin.setId(1);
		admin.setName("adminu");
		admin.setContactNo("9080789070");
		admin.setEmail("admin@email.com");
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testPostAdmin() {
		when(service.insertAdmin(admin)).thenReturn(admin);
		ResponseEntity<String> response = controller.postAdmin(admin);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	

}
