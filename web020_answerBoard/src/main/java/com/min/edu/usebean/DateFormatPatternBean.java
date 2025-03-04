package com.min.edu.usebean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// bean
// 형태는 은닉화 코드와 작성방법이 유사하다.
// tag를 통해서 호출(getter) 및 입력(setter)
// 따라서 작성되는 형태는 getter/setter
// public T getXXX() { return T;}
// public void setXXX(T t) {this.t = t;}

public class DateFormatPatternBean {

	private String oldDate;
	
	public void setOldDate(String oldDate) {
		this.oldDate = oldDate;
	}
	// declaraion 영억 : 자바의 메소드를 JSP에 직접 작성할 수 이는 영역
	// Bean : 
		
	// DTO에 있는 문자열의 Date를 입력 받아서 java.util.Date로 변경하고, SimpleDateFormat을 통해서 형태를 변경한다.
	public String getDateFormatPattern() {
		SimpleDateFormat sdf2 = null;
		Date nowDate = null;
		Date cDate = null;
		try {
			//입력받은 문자열 Date를 java.util.Date로 변경하는 pattern format 지정
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			// 출력할 문자열의 형태 ex) 2024년 12월 30일
			sdf2 = new SimpleDateFormat("yyyy년 MM월 dd일");
			
			nowDate = new Date();
			cDate = sdf.parse(oldDate); 
					
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return sdf2.format(cDate).compareTo(sdf2.format(nowDate)) == 0?"최신글":sdf2.format(cDate);
	}
}
