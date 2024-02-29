package com.electricity.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.Collection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.electricity.api.data.UserRepository;
import com.electricity.api.model.User;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService service;

    private User user;

    @BeforeEach
    void setup() {
        user = new User();
        user.setId(1);
        user.setUsername("nagulu");
        user.setPassword("$2a$10$GPc4tZ00T0NRxPy36Ex4Y.IWTXvlbsT8LdQ0BunN4IPOpFdeJWf0K");
        user.setRole("ROLE_USER");
    }

    @Test
    void testLoadUserByUsername() {
        // Mocking the userRepository method
        when(userRepository.getUserByUsername(user.getUsername())).thenReturn(user);

        // Call the method to be tested
        UserDetails userDetails = service.loadUserByUsername(user.getUsername());

        // Verify that the userRepository method was called
        verify(userRepository).getUserByUsername(user.getUsername());

        // Assert the expected result
        assertEquals(user.getUsername(), userDetails.getUsername());
        assertEquals(user.getPassword(), userDetails.getPassword());

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        assertEquals(1, authorities.size());
        GrantedAuthority authority = authorities.iterator().next();
        assertEquals(user.getRole(), authority.getAuthority());
    }

    @Test
    void testLoadUserByUsernameUserNotFound() {
        // Mocking the userRepository method to return null
        when(userRepository.getUserByUsername(user.getUsername())).thenReturn(null);

        // Call the method to be tested
        assertThrows(UsernameNotFoundException.class, () -> {
            service.loadUserByUsername(user.getUsername());
        });

        // Verify that the userRepository method was called
        verify(userRepository).getUserByUsername(user.getUsername());

        // Fix: Create a user object and set the required properties
        User mockUser = new User();
        mockUser.setUsername(user.getUsername());
      //  mockUser.setRole(USER);

        // Update the userRepository mock to return the mockUser object
        when(userRepository.getUserByUsername(user.getUsername())).thenReturn(mockUser);

        // Call the method again
        UserDetails userDetails = service.loadUserByUsername(user.getUsername());

        // Verify that the userRepository method was called again
        verify(userRepository, times(2)).getUserByUsername(user.getUsername());

        // Additional assertions can be added to verify the returned UserDetails object
        assertNotNull(userDetails);
        assertEquals(mockUser.getUsername(), userDetails.getUsername());
        // Add more assertions as needed
    }

}
