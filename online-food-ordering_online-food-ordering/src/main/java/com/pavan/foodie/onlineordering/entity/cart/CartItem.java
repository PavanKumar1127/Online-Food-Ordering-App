package com.pavan.foodie.onlineordering.entity.cart;

import java.util.List;

import com.pavan.foodie.onlineordering.entity.order.Food;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    private Food food;

    @Column(nullable = false)
    private int quantity;

    @ElementCollection
    private List<String> ingredients;

    @Column(nullable = false)
    private double totalPrice;
}
