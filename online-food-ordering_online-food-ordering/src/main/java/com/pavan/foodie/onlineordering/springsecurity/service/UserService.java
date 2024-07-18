package com.pavan.foodie.onlineordering.springsecurity.service;

import com.pavan.foodie.onlineordering.entity.user.UserEntity;

public interface UserService {

    public UserEntity findUserByJwtToken(String jwt) throws Exception;

    public UserEntity findUserByEmail(String email) throws Exception;
}
