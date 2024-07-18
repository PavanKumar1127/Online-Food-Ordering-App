package com.pavan.foodie.onlineordering.springsecurity.controller;

import com.pavan.foodie.onlineordering.entity.user.RestaurantDTO;
import com.pavan.foodie.onlineordering.entity.user.UserEntity;
import com.pavan.foodie.onlineordering.springsecurity.service.RestaurantService;
import com.pavan.foodie.onlineordering.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<RestaurantDTO>> searchRestaurant(
            @RequestHeader("Authorization") String jwt,
            @RequestParam String keyword
    ) throws Exception {
        UserEntity user = userService.findUserByJwtToken(jwt);
        List<RestaurantDTO> restaurants = restaurantService.searchRestaurant(keyword);
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurant(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
    	UserEntity user = userService.findUserByJwtToken(jwt);
        List<RestaurantDTO> restaurants = restaurantService.getAllRestaurant();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDTO> findRestaurantById(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception {
    	UserEntity user = userService.findUserByJwtToken(jwt);
        RestaurantDTO restaurant = restaurantService.findRestaurantById(id);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @PostMapping("/favorites/{id}")
    public ResponseEntity<Void> addToFavorite(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception {
    	UserEntity user = userService.findUserByJwtToken(jwt);
        restaurantService.addToFavorites(id, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
