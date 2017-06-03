package com.mvc.service;

import com.mvc.model.User;

public interface UserService {
	
	public void save(User user);
	
	public boolean loginValidate(String name,String password); 
	
	public boolean isUserExit(String username);
}
