package com.pavan.foodie.onlineordering.springsecurity.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.pavan.foodie.onlineordering.entity.user.Address;
import com.pavan.foodie.onlineordering.entity.user.RestaurantDTO;
import com.pavan.foodie.onlineordering.entity.user.UserEntity;
import com.pavan.foodie.onlineordering.repository.AddressRepository;
import com.pavan.foodie.onlineordering.repository.RestaurantRepository;
import com.pavan.foodie.onlineordering.repository.UserRepository;
import com.pavan.foodie.onlineordering.request.CreateRestaurantRequest;

public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Override
	public RestaurantDTO createRestaurant(CreateRestaurantRequest req, UserEntity user) {

		Address address = addressRepository.save(req.getAddress());

		RestaurantDTO restaurant = new RestaurantDTO();

		restaurant.setAddress(address);

		restaurant.setContactInformation(req.getContactInformation());

		restaurant.setCuisineType(req.getCuisineType());

		restaurant.setDescription(req.getDescription());

		restaurant.setImages(req.getImages());

		restaurant.setName(req.getName());

		restaurant.setWorkingHours(req.getOpeningHours());

		restaurant.setRegistrationDate(LocalDateTime.now());

		restaurant.setOwner(user);

		return restaurantRepository.save(restaurant);
	}

	@Override
	public RestaurantDTO updateRestaurant(Long restaurantId, CreateRestaurantRequest updateResturant) throws Exception {

		RestaurantDTO updatedRestaurant = findRestaurantById(restaurantId);

		if (updatedRestaurant.getCuisineType() != null) {
			updatedRestaurant.setCuisineType(updatedRestaurant.getCuisineType());
		}

		if (updatedRestaurant.getDescription() != null) {
			updatedRestaurant.setDescription(updatedRestaurant.getDescription());
		}

		if (updatedRestaurant.getName() != null) {
			updatedRestaurant.setName(updatedRestaurant.getName());
		}

		// Assuming you have other fields to update such as address, contact
		// information, etc.
		// Add similar checks and updates as needed.

		return restaurantRepository.save(updatedRestaurant);
	}

	@Override
	public void deleteRestaurant(Long restaurantId) throws Exception {

		RestaurantDTO restaurant = findRestaurantById(restaurantId);
		restaurantRepository.delete(restaurant);
	}

	@Override
	public List<RestaurantDTO> getAllRestaurant() {
		// TODO Auto-generated method stub
		return restaurantRepository.findAll();
	}

	@Override
	public List<RestaurantDTO> searchRestaurant(String keyword) {
		return restaurantRepository.findBySearchQuery(keyword);
	}

	@Override
	public RestaurantDTO findRestaurantById(Long restaurantId) throws Exception {

		Optional<RestaurantDTO> opt = restaurantRepository.findById(restaurantId);

		if (opt.isEmpty()) {
			throw new Exception("Restaurant not found with Id: " + restaurantId);
		}

		return opt.get();
	}

	@Override
	public RestaurantDTO getRestaurantById(Long ownerId) throws Exception {
		RestaurantDTO restaurant = restaurantRepository.findByOwnerId(ownerId);
		if (restaurant == null) {
			throw new Exception("Restaurant not found with owner id" + ownerId);
		}
		return restaurant;
	}

	@Override
	public RestaurantDTO addToFavorites(Long restaurantId, UserEntity user) throws Exception {
	    
		RestaurantDTO restaurant = findRestaurantById(restaurantId);

	    // Create a new DTO instance to represent the restaurant
	    RestaurantDTO dto = new RestaurantDTO();
	    dto.setDescription(restaurant.getDescription());
	    dto.setImages(restaurant.getImages());
	    dto.setName(restaurant.getName());
	    dto.setRestaurantDTOID(restaurantId);

	    // Check if the user's favorites already contain this restaurant
	    if (user.getFavorites().contains(dto)) {
	        user.getFavorites().remove(dto); // Remove from favorites if already present
	    } else {
	        user.getFavorites().add(dto); // Add to favorites if not already present
	    }

		// Save or update the user entity (assuming this is necessary)
	    userRepository.save(user);

	    return dto; // Return the updated restaurant DTO or handle return as per logic
	}


	@Override
	public RestaurantDTO updateRestaurantStatus(Long restaurantId) throws Exception {

		RestaurantDTO restaurantDTO = findRestaurantById(restaurantId);
		restaurantDTO.setOpen(!restaurantDTO.isOpen());
		return restaurantRepository.save(restaurantDTO);
	}

}
