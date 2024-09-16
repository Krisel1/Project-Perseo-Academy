package com.project.Perseo_Academy.services;

import com.project.Perseo_Academy.models.Cart;
import com.project.Perseo_Academy.models.MyCourse;
import com.project.Perseo_Academy.repositories.ICartRepository;
import com.project.Perseo_Academy.repositories.IMyCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    ICartRepository iCartRepository;

    public List<Cart> getAllCart() {
        return iCartRepository.findAll();
    }

    public Optional<Cart> getCartById(Integer id) {
        return iCartRepository.findById(id);
    }

    public Cart createCart(Cart cart) {
        return iCartRepository.save(cart);
    }

    public void deleteCart(Integer id) {
        iCartRepository.deleteById(id);
    }
}
