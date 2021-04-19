package com.rindus.rt6.codingTaskEBuscato.model.photoAlbum;

import lombok.Data;

@Data
public class Album {
	
	private int id;
	private int userId;
	private String title;
	
	public String toString() {
		return title;		
	}

}
