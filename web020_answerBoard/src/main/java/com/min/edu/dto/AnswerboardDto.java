package com.min.edu.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AnswerboardDto implements Serializable {

	private static final long serialVersionUID = 4929768572262021453L;
	
	private int seq, ref, step, depth;
	private String id, name, title, content, regdate, delflag;
}
