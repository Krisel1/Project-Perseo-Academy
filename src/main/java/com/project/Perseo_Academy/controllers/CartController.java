package com.project.Perseo_Academy.controllers;

import com.project.Perseo_Academy.models.Cart;
import com.project.Perseo_Academy.models.MyCourse;
import com.project.Perseo_Academy.services.CartService;
import com.project.Perseo_Academy.services.MyCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/carts")
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    public List<Cart> getAllCart() {
        return cartService.getAllCart();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public Optional<Cart> getCartById(@PathVariable Integer id) {
        return cartService.getCartById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public Cart createCart(@RequestBody Cart cart) {
        return cartService.createCart(cart);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public void deleteCart(@PathVariable Integer id) {
        cartService.deleteCart(id);
    }
}
