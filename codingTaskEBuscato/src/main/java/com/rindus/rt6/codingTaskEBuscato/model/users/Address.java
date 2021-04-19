package com.rindus.rt6.codingTaskEBuscato.model.users;

import lombok.Data;

@Data
public class Address {
	
	private String street;
	private String suite;
	private String city;
	private String zipcode;
	private Geo geo;
	
	public String toString() {
		return street + ",  "+ suite+ " (" + city + ")";
	}
	

}
