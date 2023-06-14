package com.electricity.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.security.Principal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.electricity.api.data.UserRepository;
import com.electricity.api.model.User;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserController controller;

    private User user;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setUsername("john");
        user.setPassword("password123");
    }

    @Test
    void testSignUp() {
        // Mocking userRepository method
        when(userRepository.getUserByUsername("john")).thenReturn(null);
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");

        // Call the method to be tested
        ResponseEntity<String> response = controller.signUp(user);

        // Verify that the userRepository method was called
        verify(userRepository).getUserByUsername("john");

        // Assert the expected response status and body
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Signup Success", response.getBody());
    }

    @Test
    void testSignUpUsernameConflict() {
        // Mocking userRepository method
        when(userRepository.getUserByUsername("john")).thenReturn(user);

        // Call the method to be tested
        ResponseEntity<String> response = controller.signUp(user);

        // Verify that the userRepository method was called
        verify(userRepository).getUserByUsername("john");

        // Assert the expected response status and body
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Username already present", response.getBody());
    }

    @Test
    void testLogin() {
        // Mocking userRepository method
        when(userRepository.getUserByUsername("john")).thenReturn(user);

        // Call the method to be tested
        User result = controller.login(principalMock("john"));

        // Verify that the userRepository method was called
        verify(userRepository).getUserByUsername("john");

        // Assert the expected result
        assertEquals(user, result);
    }

    @Test
    void testGetHello() {
        // Call the method to be tested
        String result = controller.getHello();

        // Assert the expected result
        assertEquals("Hello", result);
    }

    @Test
    void testGetAuthHello() {
        // Call the method to be tested
        String result = controller.getAuthHello();

        // Assert the expected result
        assertEquals("Auth Hello", result);
    }

    @Test
    void testGetPrivateRoleHello() {
        // Call the method to be tested
        String result = controller.getPrivateRoleHello();

        // Assert the expected result
        assertEquals("Private Role Hello", result);
    }

    // Helper method to mock Principal
    private Principal principalMock(String username) {
        return new Principal() {
            @Override
            public String getName() {
                return username;
            }
        };
    }
}
