package com.pavan.foodie.onlineordering.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pavan.foodie.onlineordering.entity.user.RestaurantDTO;
import com.pavan.foodie.onlineordering.entity.user.UserEntity;
import com.pavan.foodie.onlineordering.request.CreateRestaurantRequest;
import com.pavan.foodie.onlineordering.response.MessageResponse;
import com.pavan.foodie.onlineordering.springsecurity.service.RestaurantService;
import com.pavan.foodie.onlineordering.springsecurity.service.UserService;

@RestController
@RequestMapping("/api/admin/restaurants")
public class AdminRestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<RestaurantDTO> createRestaurant(
            @RequestBody CreateRestaurantRequest req,
            @RequestHeader("Authorization") String jwt) throws Exception {

        UserEntity user = userService.findUserByJwtToken(jwt);
        RestaurantDTO restaurant = restaurantService.createRestaurant(req, user);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDTO> updateRestaurant(
            @RequestBody CreateRestaurantRequest req,
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id) throws Exception {

        UserEntity user = userService.findUserByJwtToken(jwt);
        RestaurantDTO restaurant = restaurantService.updateRestaurant(id, req);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteRestaurant(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id) throws Exception {

        UserEntity user = userService.findUserByJwtToken(jwt);
        restaurantService.deleteRestaurant(id);

        MessageResponse response = new MessageResponse();
        response.setMessage("Restaurant Deleted Successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<RestaurantDTO> updateRestaurantStatus(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id) throws Exception {

        UserEntity user = userService.findUserByJwtToken(jwt);
        RestaurantDTO restaurant = restaurantService.updateRestaurantStatus(id);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<RestaurantDTO> findRestaurantByUserId(
            @RequestHeader("Authorization") String jwt) throws Exception {

        UserEntity user = userService.findUserByJwtToken(jwt);
        RestaurantDTO restaurant = restaurantService.getRestaurantById(user.getId());

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }
}
