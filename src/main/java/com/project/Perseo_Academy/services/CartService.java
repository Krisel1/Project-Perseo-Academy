package com.project.Perseo_Academy.services;
import com.project.Perseo_Academy.models.Cart;
import com.project.Perseo_Academy.repositories.ICartRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {


    private final ICartRepository iCartRepository;

    public CartService(ICartRepository iCartRepository) {
        this.iCartRepository = iCartRepository;
    }

    public List<Cart> getAllCarts() { return iCartRepository.findAll(); }

    public Optional<Cart> getCartById(Long id) { return iCartRepository.findById(id); }

    public Cart createCart(Cart cart) { return iCartRepository.save(cart); }

    public void deleteCart(Long id) { iCartRepository.deleteById(id); }
}
