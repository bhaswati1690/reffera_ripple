package com.referal.referal_ripple.repository;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.referal.referal_ripple.entity.UserRegistration;

public interface UserRepo extends JpaRepository<UserRegistration,Long> {

	static void save(User user) {
		// TODO Auto-generated method stub
		
	}

}
