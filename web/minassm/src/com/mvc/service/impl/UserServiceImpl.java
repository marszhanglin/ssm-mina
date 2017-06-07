package com.mvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.dao.UserMapper;
import com.mvc.model.User;
import com.mvc.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public void save(User user) {
		
	}

	@Override
	public boolean loginValidate(String name, String password) {
		User user=userMapper.selectByName(name);
		if(null!=user&&null!=password){
			if(password.equals(user.getPassword())){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isUserExit(String username) {
		User user=userMapper.selectByName(username);
		return null!=user;
	}

	@Override
	public List<User> getUsers() {
		
		
		return userMapper.selectAll();
	}

}
