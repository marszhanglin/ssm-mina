package com.mvc.dao;

import java.util.List;

import com.mvc.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User selectByName(String name);
    
    List<User> selectAll();
    
	List<User> findPageByPage(Integer limit, Integer row);
}