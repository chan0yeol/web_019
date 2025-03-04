package com.min.edu.dto;


//import com.util.edu.Utility;

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
public class UserDto {
	private String id,
	name,
	password,
	email,
	auth,
	enable,
	joindate;
	
}
