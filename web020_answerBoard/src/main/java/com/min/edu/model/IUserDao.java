package com.min.edu.model;

import java.util.Map;

import com.min.edu.dto.UserDto;

public interface IUserDao {
	UserDto login(Map<String, Object> map);
	
}
