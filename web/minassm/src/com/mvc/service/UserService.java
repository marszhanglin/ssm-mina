package com.mvc.service;


import java.util.List;

import com.mvc.model.User;

public interface UserService {
	
	public void save(User user);
	
	public boolean loginValidate(String name,String password); 
	
	public boolean isUserExit(String username);
	
	public List<User> getUsers();
	
	public List<User> findPageByPage(int page,int row);
	
	public int count();
	
	
	public User findUserByName(String name);
}
