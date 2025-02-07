package com.project.Perseo_Academy.services;

import com.project.Perseo_Academy.models.User;
import com.project.Perseo_Academy.repositories.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.project.Perseo_Academy.models.ERole.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserService userService;
    private IUserRepository iUserRepository;

    @BeforeEach
    public void setUp() {
        iUserRepository = mock(IUserRepository.class);
        userService = new UserService(iUserRepository);
    }

    @Test
    public void test_Get_All_Users() {
        List<User> mockProjects = new ArrayList<>();
        mockProjects.add(new User(1L, "Sofi", "user1@example.com","password1", USER));
        mockProjects.add(new User(2L, "Mari", "user2@example.com","password2", USER));
        when(iUserRepository.findAll()).thenReturn(mockProjects);

        ArrayList<User> result = userService.getAllUsers();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Sofi", result.get(0).getUsername());
        assertEquals("Mari", result.get(1).getUsername());

        verify(iUserRepository, times(1)).findAll();
    }

    @Test
    public void test_Get_User_By_Id() {
        User mockProject = new User(1L, "Sofi", "user1@example.com","password1", USER);
        Long userId = 1L;
        when(iUserRepository.findById(userId)).thenReturn(Optional.of(mockProject));
        Optional<User> result = userService.getUserById(userId);

        assertNotNull(result);
        assertEquals("Sofi", result.get().getUsername());
        verify(iUserRepository, times(1)).findById(userId);
    }


    @Test
    public void test_Create_User() {
        User newUser = new User(1L, "Sofi", "user1@example.com","password1", USER);
        when(iUserRepository.save(newUser)).thenReturn(newUser);
        User result = userService.createUser(newUser);

        assertNotNull(result);
        assertEquals("Sofi", result.getUsername());
        verify(iUserRepository, times(1)).save(newUser);
    }

    @Test
    public void test_Create_Manager() {
        User newUser = new User(1L, "Lucia", "user1@example.com","password1", MANAGER);
        when(iUserRepository.save(newUser)).thenReturn(newUser);
        User result = userService.createUser(newUser);

        assertNotNull(result);
        assertEquals("Lucia", result.getUsername());
        verify(iUserRepository, times(1)).save(newUser);
    }

    @Test
    public void test_Create_Admin() {
        User newUser = new User(1L, "Eugenia", "user1@example.com","password1", ADMIN);
        when(iUserRepository.save(newUser)).thenReturn(newUser);
        User result = userService.createUser(newUser);

        assertNotNull(result);
        assertEquals("Eugenia", result.getUsername());
        verify(iUserRepository, times(1)).save(newUser);
    }

    @Test
    public void test_Update_User() {
        User user = new User(1L, "Sofi", "user1@example.com","password1", USER);
        userService.updateUser(user);

        verify(iUserRepository, times(1)).save(user);
    }

    @Test
    public void test_Delete_User_Success() {
        Long userId = 1L;
        String result = userService.deleteUser(userId);

        verify(iUserRepository, times(1)).deleteById(userId);
        assertEquals("User has been deleted", result);
    }

    @Test
    public void test_Delete_if_Users_Not_Found() {
        Long userId = 1L;
        doThrow(new RuntimeException("User not found")).when(iUserRepository).deleteById(userId);
        String result = userService.deleteUser(userId);

        verify(iUserRepository, times(1)).deleteById(userId);
        assertEquals("User not found", result);
    }

}