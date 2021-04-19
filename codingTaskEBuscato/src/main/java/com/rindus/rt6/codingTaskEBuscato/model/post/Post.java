package com.rindus.rt6.codingTaskEBuscato.model.post;

import lombok.Data;

@Data
public class Post {
	
	private int id;
	private int userId;
	private String title;
	private String body;
	
	public String toString() {
		return title;
	}

}
