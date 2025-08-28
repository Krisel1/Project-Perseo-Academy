package com.project.Perseo_Academy.services;
import com.project.Perseo_Academy.dto.request.CartRequest;
import com.project.Perseo_Academy.models.Cart;
import com.project.Perseo_Academy.models.Course;
import com.project.Perseo_Academy.models.User;
import com.project.Perseo_Academy.repositories.ICartRepository;
import com.project.Perseo_Academy.repositories.ICourseRepository;
import com.project.Perseo_Academy.repositories.IUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class CartService {

    private final ICartRepository iCartRepository;
    private final IUserRepository iUserRepository;
    private final ICourseRepository iCourseRepository;

    public CartService(ICartRepository iCartRepository, IUserRepository iUserRepository, ICourseRepository iCourseRepository) {
        this.iCartRepository = iCartRepository;
        this.iUserRepository = iUserRepository;
        this.iCourseRepository = iCourseRepository;
    }

    @Transactional
    public Cart createCart(CartRequest cartRequest) {
        User user = iUserRepository.findById(cartRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Course course = iCourseRepository.findById(cartRequest.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course with ID " + cartRequest.getCourseId() + " not found"));

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setCourse(course);
        cart.setDate(LocalDate.from(LocalDateTime.now()));

        return iCartRepository.save(cart);
    }

    public List<Cart> getAllCarts() {
        return iCartRepository.findAll();
    }

    public Cart getCartById(Long id) {
        return iCartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    public void deleteCart(Long id) {
        iCartRepository.deleteById(id);
    }
}

