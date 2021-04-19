package com.rindus.rt6.codingTaskEBuscato.model.users;

import lombok.Data;

@Data
public class Geo {
	
	private String lat;
	private String lng;
	
	public String toString() {
		return "("+lng+", "+ lat+")";
	}

}
