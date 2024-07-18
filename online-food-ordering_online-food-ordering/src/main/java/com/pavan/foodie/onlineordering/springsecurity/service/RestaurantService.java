package com.pavan.foodie.onlineordering.springsecurity.service;

import com.pavan.foodie.onlineordering.request.CreateRestaurantRequest;

import java.util.List;

import com.pavan.foodie.onlineordering.entity.user.RestaurantDTO;
import com.pavan.foodie.onlineordering.entity.user.UserEntity;

public interface RestaurantService {

	public RestaurantDTO createRestaurant(CreateRestaurantRequest req, UserEntity user);

	public RestaurantDTO updateRestaurant(Long restaurantId, CreateRestaurantRequest updateResturant) throws Exception;

	public void deleteRestaurant(Long restaurantId) throws Exception;

	public List<RestaurantDTO> getAllRestaurant();

	public List<RestaurantDTO> searchRestaurant(String keyword);

	public RestaurantDTO findRestaurantById(Long restaurantId) throws Exception;

	public RestaurantDTO getRestaurantById(Long userId) throws Exception;

	public RestaurantDTO addToFavorites(Long restaurantId, UserEntity user) throws Exception;

	public RestaurantDTO updateRestaurantStatus(Long restaurantId) throws Exception;

}
