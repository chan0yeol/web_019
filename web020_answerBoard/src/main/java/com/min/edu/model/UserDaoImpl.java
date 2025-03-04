package com.min.edu.model;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.min.edu.database.SqlSessionFactoryManager;
import com.min.edu.dto.UserDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserDaoImpl implements IUserDao {
	
	private SqlSessionFactory manager = SqlSessionFactoryManager.getFactory();
	private final String NS = "com.min.edu.model.UserDaoImpl.";
	
	@Override
	public UserDto login(Map<String, Object> map) {
		log.info("UserDaoImpl 로그인 : {}", map);
		SqlSession s = manager.openSession();
		return s.selectOne(NS+"login",map);
	}

	
	
}
