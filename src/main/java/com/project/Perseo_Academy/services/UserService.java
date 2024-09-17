package com.project.Perseo_Academy.services;

import com.project.Perseo_Academy.models.User;
import com.project.Perseo_Academy.repositories.IUserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    IUserRepository iUserRepository;

    public List<User> getAllUser() {
        return iUserRepository.findAll();
    }

    public Optional<User> getUserById(Integer id) {
        return iUserRepository.findById(id);
    }

    public User createUser(User newUser) {
        return iUserRepository.save(newUser);
    }

    public User updateUser(User user, Integer id){
        user.setId(id);
        return iUserRepository.save(user);
    }

    public void deleteUser(Integer id) {
        iUserRepository.deleteById(id);
    }
}
