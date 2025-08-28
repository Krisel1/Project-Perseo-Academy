package com.project.Perseo_Academy.controllers;

import com.project.Perseo_Academy.dto.request.CartRequest;
import com.project.Perseo_Academy.models.Cart;
import com.project.Perseo_Academy.services.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) { this.cartService = cartService; }


    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> carts = cartService.getAllCarts();
        return ResponseEntity.ok(carts);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Cart> getCartById(@PathVariable Long id) {
        Cart cart = cartService.getCartById(id);
        return cart != null ? ResponseEntity.ok(cart) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Cart> createCart(@RequestBody CartRequest cartRequest) {
        Cart createdCart = cartService.createCart(cartRequest);

        if (createdCart != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCart);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public void deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
    }

}


