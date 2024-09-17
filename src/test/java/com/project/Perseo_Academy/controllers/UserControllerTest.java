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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockController;

    private User user;
    private User userAdmin;
    private User userManager;

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

        userManager = new User();
        userManager.setId(3);
        userManager.setUsername("Kris");
        userManager.setPassword("1234");
        userManager.setRole(ERole.MANAGER);

        userList.add(user);
        userList.add(userAdmin);
        userList.add(userManager);

    }

    @Test
    void test_Create_Admin() throws Exception {
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
    void test_Create_Manager() throws Exception {
        when(userService.createUser(any(User.class))).thenReturn(userManager);

        String managerJson =
                "{\"id\": 2,\n"
                        + "\"username\": \"Kris\",\n"
                        + "\"password\": \"1234\",\n"
                        + "\"role\": \"MANAGER\"}";

        mockController
                .perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(managerJson))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\"id\": 2,\n"
                                + "\"username\": \"Kris\",\n"
                                + "\"password\": \"1234\",\n"
                                + "\"role\": \"MANAGER\"}"));
    }

    @Test
    void test_create_User() throws Exception {
        when(userService.createUser(any(User.class))).thenReturn(user);

        String userJson =
                "{\"id\": 3,\n"
                        + "\"username\": \"Valen\",\n"
                        + "\"password\": \"1234\",\n"
                        + "\"role\": \"USER\"}";

        mockController
                .perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\"id\": 3,\n"
                                + "\"username\": \"Valen\",\n"
                                + "\"password\": \"1234\",\n"
                                + "\"role\": \"USER\"}"));
    }

    @Test
    void deleteUser() throws Exception {
        doNothing().when(userService).deleteUser(1);

        mockController.perform(MockMvcRequestBuilders.delete("/api/users/1")).andExpect(status().isOk());
    }
    @Test
    void test_update_admin() throws Exception {
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
    void test_update_manager() throws Exception {
        when(userService.updateUser(any(User.class), any(Integer.class))).thenReturn(userManager);

        String updateManagerJson =
                "{\"id\": 2,\n"
                        + "\"username\": \"Kris\",\n"
                        + "\"password\": \"1234\",\n"
                        + "\"role\": \"MANAGER\"}";

        mockController
                .perform(put("/api/users/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateManagerJson))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\"id\": 2,\n"
                                + "\"username\": \"Kris\",\n"
                                + "\"password\": \"1234\",\n"
                                + "\"role\": \"MANAGER\"}"));

        verify(userService).updateUser(any(User.class), any(Integer.class));
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
//    @Test
//    void test_GetAllUsers() throws Exception {
//        when(userService.getAllUsers()).thenReturn(userList);
//
//        mockController
//                .perform(get("/api/users"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(3)))
//                .andExpect(content().json(
//                        "[{\"id\": 1, \"username\": \"Ana\", \"password\": \"1234\", \"role\": \"ADMIN\"}, " +
//                                "{\"id\": 2, \"username\": \"Kris\", \"password\": \"1234\", \"role\": \"MANAGER\"}, " +
//                                "{\"id\": 3, \"username\": \"Valen\", \"password\": \"1234\", \"role\": \"USER\"}]"));
//    }

}