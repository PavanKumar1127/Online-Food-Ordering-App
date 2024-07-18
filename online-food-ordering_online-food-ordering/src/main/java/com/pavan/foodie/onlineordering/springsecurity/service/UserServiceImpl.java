package com.pavan.foodie.onlineordering.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pavan.foodie.onlineordering.entity.user.UserEntity;
import com.pavan.foodie.onlineordering.repository.UserRepository;
import com.pavan.foodie.onlineordering.springsecurity.configuration.JwtProvider;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public UserEntity findUserByJwtToken(String jwt) throws Exception {
        
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        UserEntity user = findUserByEmail(email);
        return user;
    }
    
    

    @Override
    public UserEntity findUserByEmail(String email) throws Exception {
        UserEntity user = userRepository.findByEmail(email);
        if (user==null) {
            throw new Exception("User not found with email: " + email);
        }
        return user;
    }
}
