package com.test.edu;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.min.edu.dto.UserDto;
import com.min.edu.model.IUserDao;
import com.min.edu.model.UserDaoImpl;

public class login_JUnit {

	@Test
	public void test() {
		IUserDao dao = new UserDaoImpl();
		Map<String, Object> map = new HashMap<>();
		map.put("id", "A001");
		map.put("password", "A001");
		UserDto loginDto = dao.login(map);
		assertNotNull(loginDto);
	}

}
