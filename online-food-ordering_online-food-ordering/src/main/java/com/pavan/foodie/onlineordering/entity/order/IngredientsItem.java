package com.pavan.foodie.onlineordering.entity.order;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pavan.foodie.onlineordering.entity.user.RestaurantDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientsItem {
    
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String ingredientName;

    @JoinColumn(nullable = false)
    @ManyToOne
    private IngredientCategory category;

    @JsonIgnore
    @ManyToOne
    private RestaurantDTO restaurant;

    @Column(nullable = false)
    private boolean inStock;

    private double quantity;

    // Additional fields and methods if necessary
}
