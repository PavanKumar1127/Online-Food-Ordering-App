package com.pavan.foodie.onlineordering.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pavan.foodie.onlineordering.entity.user.RestaurantDTO;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantDTO, Long> {

	@Query("SELECT r FROM Restaurant r WHERE lower(r.name) LIKE lower(concat('%', :query, '%')) OR lower(r.cuisineType) LIKE lower(concat('%', :query, '%'))")
	List<RestaurantDTO> findBySearchQuery(String query);

	RestaurantDTO findByOwnerId(Long ownerId);
}
