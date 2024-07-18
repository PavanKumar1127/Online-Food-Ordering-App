package com.pavan.foodie.onlineordering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pavan.foodie.onlineordering.entity.user.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

	public UserEntity findByEmail(String userName) ;
	
}
