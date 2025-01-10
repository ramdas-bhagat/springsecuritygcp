package com.test.springsecuritygcp.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.test.springsecuritygcp.model.SecurityUser;
import com.test.springsecuritygcp.repository.SecurityUserRepository;

import java.util.Optional;
import java.util.Set;

public class SecurityUserDetailsServiceTest {

    @Mock
    private SecurityUserRepository securityUserRepository;

    @InjectMocks
    private SecurityUserDetailsService securityUserDetailsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoadUserByUsername_Success() {
        // Arrange
        String username = "testuser";
        SecurityUser mockUser = SecurityUser.builder()
                .username(username)
                .password("encodedPassword")
                .roles(Set.of("ROLE_USER", "ROLE_ADMIN"))
                .build();
        when(securityUserRepository.findByUsername(username)).thenReturn(Optional.of(mockUser));

        // Act
        UserDetails userDetails = securityUserDetailsService.loadUserByUsername(username);

        // Assert
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        assertEquals("encodedPassword", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_USER")));
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN")));
        verify(securityUserRepository, times(1)).findByUsername(username);
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        // Arrange
        String username = "unknownuser";
        when(securityUserRepository.findByUsername(username)).thenReturn(Optional.empty());

        // Act & Assert
        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () ->
                securityUserDetailsService.loadUserByUsername(username));
        assertEquals(String.format("Username: %s not found.", username), exception.getMessage());
        verify(securityUserRepository, times(1)).findByUsername(username);
    }
}
