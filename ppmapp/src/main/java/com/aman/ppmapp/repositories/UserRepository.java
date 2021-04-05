package com.aman.ppmapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.aman.ppmapp.domain.User;

public interface UserRepository extends CrudRepository<User,Long> {

	 User findByUsername(String username);
	 User getById(Long id);
	
}
