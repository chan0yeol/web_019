package com.min.edu.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.min.edu.database.SqlSessionFactoryManager;
import com.min.edu.dto.AnswerboardDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AnswerboardDaoImpl implements IAnswerboardDao {

	private SqlSessionFactory manager = SqlSessionFactoryManager.getFactory();
	private final String NS = "com.min.edu.model.AnswerboardDaoImpl."; 
	
	@Override
	public List<AnswerboardDto> selectAllBoard() {
		log.info("selectAllBoard");
		SqlSession s = manager.openSession();
		return s.selectList(NS+"selectAllBoard");
	}

	@Override
	public AnswerboardDto selectDetailBoard(String seq) {
		log.info("selectDetailBoard : {}" , seq);
		SqlSession s = manager.openSession();
		return s.selectOne(NS+"selectDetailBoard",seq);
	}

	@Override
	public boolean modifyBoard(Map<String, Object> map) {
		log.info("modifyBoard : {}", map);
		SqlSession s = manager.openSession(true);
		int n = s.update(NS+"modifyBoard",map);
		return n>0?true:false;
	}

	@Override
	public boolean multiDeleteBoard(List<String> list) {
		log.info("multiDeleteBoard : {} ", list);
		SqlSession s = manager.openSession(true);
		int n = s.delete(NS+"multiDeleteBoard",list);
		return n>0?true:false;
	}

	@Override
	public boolean insertBoard(AnswerboardDto dto) {
		log.info("insertBoard : {}", dto);
		SqlSession s = manager.openSession(true);
		int n = s.insert(NS+"insertBoard",dto);
		return n>0?true:false;
	}

	@Override
	public boolean deleteBoard(String seq) {
		log.info("deleteBoard : {}" , seq);
		SqlSession s = manager.openSession(true);
		int n = s.delete(NS+"deleteBoard",seq);
		return n>0?true:false;
	}

	@Override
	public boolean replyUpdate(AnswerboardDto dto) {
		log.info("replyUpdate");
		return false;
	}

	@Override
	public boolean replyInsert(AnswerboardDto dto) {
		log.info("");
		return false;
	}

	@Override
	public boolean reply(AnswerboardDto dto) {
		log.info("reply Transaction : {} ", dto);
		SqlSession s = manager.openSession();
		int cnt = 0;
		try {
			cnt += s.update(NS+"replyUpdate",dto);
			cnt += s.insert(NS+"replyInsert",dto);
			s.commit();
		} catch (Exception e) {
			s.rollback();
			e.printStackTrace();
		}
		
		return cnt>0?true:false;
	}


}
