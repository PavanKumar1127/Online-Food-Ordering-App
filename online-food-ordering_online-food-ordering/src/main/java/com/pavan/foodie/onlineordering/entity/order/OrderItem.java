package com.pavan.foodie.onlineordering.entity.order;

import java.util.List;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
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
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderItemId; 
	
	@ManyToOne
	private Food food;
	
	private List<String> ingredients;
	
	private int quantity;
	
	private double price;

	// Additional fields and methods if necessary
}
