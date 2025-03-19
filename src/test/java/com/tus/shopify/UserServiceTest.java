package com.tus.shopify;


import com.tus.shopify.controller.UserController;
import com.tus.shopify.model.User;
import com.tus.shopify.repository.UserRepository;
import com.tus.shopify.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john@example.com");
    }

    @Test
    void testGetAllUsers() {
        userService.getAllUsers();
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testCreateUser() {
        when(userRepository.save(user)).thenReturn(user);
        User createdUser = userService.createUser(user);
        assertEquals("John Doe", createdUser.getName());
    }

    @Test
    void testGetUser() {
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));
        User foundUser = userService.getUser(1L);
        assertEquals("John Doe", foundUser.getName());
    }
}
