package com.project.Perseo_Academy.services;
import com.project.Perseo_Academy.models.Cart;
import com.project.Perseo_Academy.models.Course;
import com.project.Perseo_Academy.models.User;
import com.project.Perseo_Academy.repositories.ICartRepository;
import com.project.Perseo_Academy.repositories.ICourseRepository;
import com.project.Perseo_Academy.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private ICartRepository iCartRepository;

    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private ICourseRepository iCourseRepository;

    public List<Cart> getAllCarts() {
        return iCartRepository.findAll();
    }

    public Optional<Cart> getCartById(Long id) {
        return iCartRepository.findById(id);
    }

    public Cart createCart(Cart cart) {
        Optional<User> user = iUserRepository.findById(cart.getUser().getId());
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        List<Course> validCourses = new ArrayList<>();
        for (Course course : cart.getCourses()) {
            Optional<Course> existingCourse = iCourseRepository.findById(course.getId());
            existingCourse.ifPresent(validCourses::add);
        }
        cart.setCourses(validCourses);

        return iCartRepository.save(cart);

    }

    public boolean deleteCart(Long id) {
        if (iCartRepository.existsById(id)) {
            iCartRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
