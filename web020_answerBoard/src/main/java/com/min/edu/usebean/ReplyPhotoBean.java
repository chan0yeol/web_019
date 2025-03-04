package com.min.edu.usebean;

import lombok.Setter;

@Setter
public class ReplyPhotoBean {

	private int depth;
	
	public String getReplyPhoto() {
		String replyStr = "";
		String img = "<img alt=\"답글\" src=\"./img/reply.png\">";
		String blank = "&nbsp;&nbsp;&nbsp;&nbsp;";
		
		for (int i = 0; i < depth; i++) {
			replyStr += blank;
			
		}
		
		return depth==0?replyStr:replyStr+img;
	}
	
}
