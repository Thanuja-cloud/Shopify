package com.tus.shopify;

import com.tus.shopify.controller.UserController;
import com.tus.shopify.model.User;
import com.tus.shopify.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void testGetAllUsers() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk());
    }

    /*@Test
    void testCreateUser() throws Exception {
        User user = new User();
        user.setName("Jane Doe");
        user.setEmail("jane@example.com");

        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        mockMvc.perform(post("/users")
                        .contentType("application/json")
                        .content("{\"name\":\"Jane Doe\",\"email\":\"jane@example.com\"}"))
                .andExpect(status().isCreated());
    }*/
}
