package com.aman.ppmapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.aman.ppmapp.domain.User;
import com.aman.ppmapp.exceptions.UserNameAlreadyExistsException;
import com.aman.ppmapp.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public User saveUser(User newUser)
	{
		try {
			
			newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
			newUser.setUsername(newUser.getUsername());
			newUser.setConfirmPassword("");
			return userRepository.save(newUser);
		}
		catch(Exception e)
		{
		throw new UserNameAlreadyExistsException("Username "+newUser.getUsername()+" already exists");
		}	
	}
}














