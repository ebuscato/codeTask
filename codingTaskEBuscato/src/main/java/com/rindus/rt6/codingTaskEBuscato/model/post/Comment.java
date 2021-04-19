package com.rindus.rt6.codingTaskEBuscato.model.post;

import lombok.Data;

@Data
public class Comment {
	
	private int id;
	private int postId;
	private String name;
	private String email;
	private String body;
	
	public String toString() {
		return name;
	}

}
