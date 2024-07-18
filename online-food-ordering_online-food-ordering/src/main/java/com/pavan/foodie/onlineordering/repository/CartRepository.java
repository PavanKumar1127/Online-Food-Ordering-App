package com.pavan.foodie.onlineordering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pavan.foodie.onlineordering.entity.cart.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

}
