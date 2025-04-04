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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
        List<User> users = Arrays.asList(user);
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getName());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testCreateUser() {
        when(userRepository.save(user)).thenReturn(user);

        User createdUser = userService.createUser(user);

        assertNotNull(createdUser);
        assertEquals("John Doe", createdUser.getName());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testGetUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User foundUser = userService.getUser(1L);

        assertNotNull(foundUser);
        assertEquals("John Doe", foundUser.getName());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testGetUser_NotFound() {
        when(userRepository.findById(2L)).thenReturn(Optional.empty());

        User foundUser = userService.getUser(2L);

        assertNull(foundUser);
        verify(userRepository, times(1)).findById(2L);
    }
}

