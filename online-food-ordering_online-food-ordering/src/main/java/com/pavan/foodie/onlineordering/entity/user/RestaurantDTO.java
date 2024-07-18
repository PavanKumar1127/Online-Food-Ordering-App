package com.pavan.foodie.onlineordering.entity.user;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pavan.foodie.onlineordering.entity.order.Food;
import com.pavan.foodie.onlineordering.entity.order.Order;

import java.time.LocalDateTime;
import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDTO {

	// DTOs (Data Transfer Objects): DTO classes that are used to transfer data
	// between different layers can benefit from @Data
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long restaurantDTOID;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner_id")
	private UserEntity owner;

	@Column(nullable = false)
	private String name;

	@Column(length = 1000)
	private String description;

	@Column(nullable = false)
	private String cuisineType;

	@OneToOne
	private Address address;

	@Embedded
	private ContactInformation contactInformation;

	@Column
	private String workingHours;

	@OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Order> orders = new ArrayList<>();

	@ElementCollection(fetch = FetchType.LAZY)
	@Column(length = 1000)
	private List<String> images = new ArrayList<>();

	@Column(nullable = false)
	private String registrationDate;

	@Column(nullable = false)
	private boolean open;

	@JsonIgnore
	@OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Food> foods = new ArrayList<>();

	public Long getRestaurantDTOID() {
		return restaurantDTOID;
	}

	public void setRestaurantDTOID(Long restaurantDTOID) {
		this.restaurantDTOID = restaurantDTOID;
	}

	public UserEntity getOwner() {
		return owner;
	}

	public void setOwner(UserEntity owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCuisineType() {
		return cuisineType;
	}

	public void setCuisineType(String cuisineType) {
		this.cuisineType = cuisineType;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public ContactInformation getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(ContactInformation contactInformation) {
		this.contactInformation = contactInformation;
	}

	public String getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate.toString();
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public List<Food> getFoods() {
		return foods;
	}

	public void setFoods(List<Food> foods) {
		this.foods = foods;
	}

	// Additional fields and methods if necessary
	
}
