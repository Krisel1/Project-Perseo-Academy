package com.project.Perseo_Academy.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.Perseo_Academy.models.ERole;
import com.project.Perseo_Academy.models.User;
import com.project.Perseo_Academy.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockController;

    private User user;
    private User userAdmin;
    private User userSuperAdmin;

    private List<User> userList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockController = MockMvcBuilders.standaloneSetup(userController).build();

        user = new User();
        user.setId(1);
        user.setUsername("Valen");
        user.setPassword("1234");
        user.setRole(ERole.USER);

        userAdmin = new User();
        userAdmin.setId(2);
        userAdmin.setUsername("Ana");
        userAdmin.setPassword("1234");
        userAdmin.setRole(ERole.ADMIN);

        userSuperAdmin = new User();
        userSuperAdmin.setId(3);
        userSuperAdmin.setUsername("Kris");
        userSuperAdmin.setPassword("1234");
        userSuperAdmin.setRole(ERole.SUPER_ADMIN);

        userList.add(user);
        userList.add(userAdmin);
        userList.add(userSuperAdmin);

    }

    @Test
    void test_create_User() throws Exception {
        when(userService.createUser(any(User.class))).thenReturn(user);

        String userJson = new ObjectMapper().writeValueAsString(user);


        mockController
                .perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andExpect(content().json(userJson));

    }

    @Test
    void test_create_Admin() throws Exception {
        when(userService.createUser(any(User.class))).thenReturn(userAdmin);

        String adminJson =
                "{\"id\": 1,\n"
                        + "\"username\": \"Ana\",\n"
                        + "\"password\": \"1234\",\n"
                        + "\"role\": \"ADMIN\"}";

        mockController
                .perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(adminJson))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\"id\": 1,\n"
                                + "\"username\": \"Ana\",\n"
                                + "\"password\": \"1234\",\n"
                                + "\"role\": \"ADMIN\"}"));
    }

    @Test
    void test_create_Super_Admin() throws Exception {
        when(userService.createUser(any(User.class))).thenReturn(userSuperAdmin);

        String superAdminJson =
                "{\"id\": 2,\n"
                        + "\"username\": \"Kris\",\n"
                        + "\"password\": \"1234\",\n"
                        + "\"role\": \"SUPER_ADMIN\"}";

        mockController
                .perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(superAdminJson))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\"id\": 2,\n"
                                + "\"username\": \"Kris\",\n"
                                + "\"password\": \"1234\",\n"
                                + "\"role\": \"SUPER_ADMIN\"}"));
    }

    @Test
    void test_update_user() throws Exception {
        when(userService.updateUser(any(User.class), any(Integer.class))).thenReturn(user);

        String updateUserJson =
                "{\"id\": 3,\n"
                        + "\"username\": \"Valen\",\n"
                        + "\"password\": \"1234\",\n"
                        + "\"role\": \"USER\"}";

        mockController
                .perform(put("/api/users/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateUserJson))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\"id\": 3,\n"
                                + "\"username\": \"Valen\",\n"
                                + "\"password\": \"1234\",\n"
                                + "\"role\": \"USER\"}"));

        verify(userService).updateUser(any(User.class), any(Integer.class));
    }

    @Test
    void test_update_Admin() throws Exception {
        when(userService.updateUser(any(User.class), any(Integer.class))).thenReturn(userAdmin);

        String updateAdminJson =
                "{\"id\": 1,\n"
                        + "\"username\": \"Ana\",\n"
                        + "\"password\": \"1234\",\n"
                        + "\"role\": \"ADMIN\"}";

        mockController
                .perform(put("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateAdminJson))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\"id\": 1,\n"
                                + "\"username\": \"Ana\",\n"
                                + "\"password\": \"1234\",\n"
                                + "\"role\": \"ADMIN\"}"));

        verify(userService).updateUser(any(User.class), any(Integer.class));
    }

    @Test
    void test_update_Super_Admin() throws Exception {
        when(userService.updateUser(any(User.class), any(Integer.class))).thenReturn(userSuperAdmin);

        String updateSuperAdminJson =
                "{\"id\": 2,\n"
                        + "\"username\": \"Kris\",\n"
                        + "\"password\": \"1234\",\n"
                        + "\"role\": \"SUPER_ADMIN\"}";

        mockController
                .perform(put("/api/users/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateSuperAdminJson))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\"id\": 2,\n"
                                + "\"username\": \"Kris\",\n"
                                + "\"password\": \"1234\",\n"
                                + "\"role\": \"SUPER_ADMIN\"}"));

        verify(userService).updateUser(any(User.class), any(Integer.class));
    }



}