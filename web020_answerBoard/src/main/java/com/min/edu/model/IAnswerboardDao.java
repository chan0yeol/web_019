package com.min.edu.model;

import java.util.List;
import java.util.Map;

import com.min.edu.dto.AnswerboardDto;

public interface IAnswerboardDao {
//	전체글조회         selectAllBoard
	List<AnswerboardDto> selectAllBoard();
//	상세글조회         selectDetailBoard
	AnswerboardDto selectDetailBoard(String seq);
//	수정               modifyBoard
	boolean modifyBoard(Map<String, Object> map);
//	삭제               multiDeleteBoard
	boolean multiDeleteBoard(List<String> list);
//	입력					insertBoard
	boolean insertBoard(AnswerboardDto answerboardDto);
//	삭제					deleteBoard
	boolean deleteBoard(String seq);
//	답글업데이트 			replyUpdate
	boolean replyUpdate(AnswerboardDto answerboardDto);
//	답글 입력 			replyInsert
	boolean replyInsert(AnswerboardDto answerboardDto);
	
	//Transaction 답글 처리
	boolean reply(AnswerboardDto answerboardDto);
}
