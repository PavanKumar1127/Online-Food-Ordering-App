package com.pavan.foodie.onlineordering.entity.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pavan.foodie.onlineordering.entity.user.Address;
import com.pavan.foodie.onlineordering.entity.user.RestaurantDTO;
import com.pavan.foodie.onlineordering.entity.user.UserEntity;

import java.util.HashSet;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private UserEntity customer;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private RestaurantDTO restaurant;

    @Column(nullable = false)
    private double totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus orderStatus;

    @Column(nullable = false)
    private Date createdate;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address deliveryAddress;

    @OneToMany(fetch = FetchType.LAZY)
    private List<OrderItem> items = new ArrayList();

//    @Embedded
//    private Payment payment;

    @Column(nullable = false)
    private int totalItem;
    
    @Column(nullable = false)
    private int totalPrice;
}
