package com.rindus.rt6.codingTaskEBuscato.model.photoAlbum;

import lombok.Data;

@Data
public class Photo {
	
	private int id;
	private int albumId;
	private String title;
	private String url;
	private String thumbnailUrl;
	
	public String toString() {
		return title;
	}
}
