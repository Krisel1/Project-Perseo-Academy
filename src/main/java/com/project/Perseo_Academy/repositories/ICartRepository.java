package com.project.Perseo_Academy.repositories;

import com.project.Perseo_Academy.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartRepository extends JpaRepository<Cart, Integer> {
}
