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

import com.electricity.api.data.AdminRepository;
import com.electricity.api.model.Admin;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private AdminService adminService;

    private Admin admin;

    @BeforeEach
    void setup() {
        admin = new Admin();
        admin.setId(1);
        admin.setName("John Doe");
        admin.setEmail("johndoe@example.com");
    }

    @Test
    void testInsertAdmin() {
        // Mocking the adminRepository method
        when(adminRepository.save(admin)).thenReturn(admin);

        // Call the method to be tested
        Admin insertedAdmin = adminService.insertAdmin(admin);

        // Verify that the adminRepository method was called
        verify(adminRepository).save(admin);

        // Assert the expected result
        assertEquals(admin, insertedAdmin);
    }

    @Test
    void testGetAllAdmins() {
        List<Admin> admins = new ArrayList<>();
        admins.add(admin);

        // Mocking the adminRepository method
        when(adminRepository.findAll()).thenReturn(admins);

        // Call the method to be tested
        List<Admin> result = adminService.getAllAdmins();

        // Verify that the adminRepository method was called
        verify(adminRepository).findAll();

        // Assert the expected result
        assertEquals(admins, result);
    }

    @Test
    void testUpdateAdminById() {
        // Mocking the adminRepository method
        when(adminRepository.save(admin)).thenReturn(admin);

        // Call the method to be tested
        adminService.updateAdminById(admin);

        // Verify that the adminRepository method was called
        verify(adminRepository).save(admin);
    }

    @Test
    void testDeleteAdminById() {
        // Call the method to be tested
        adminService.deleteAdminById(admin);

        // Verify that the adminRepository method was called
        verify(adminRepository).delete(admin);
    }
}
