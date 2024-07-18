package com.pavan.foodie.onlineordering.entity.order;

import java.util.Date;
import java.util.List;

import com.pavan.foodie.onlineordering.entity.user.RestaurantDTO;

import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Food {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private double price;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "category_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Category foodCategory;

	@ElementCollection(fetch = FetchType.LAZY)
	@Column(length = 1000)
    private List<String> image;

    @Column(nullable = false)
    private boolean available;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private RestaurantDTO restaurant;

    @Column(nullable = false)
    private boolean isVegetarian;

    @Column(nullable = false)
    private boolean isSeasonable;

    @OneToMany
    private List<IngredientsItem> ingredients = new ArrayList<>();

    @Column(nullable = false)
    private Date date;
}