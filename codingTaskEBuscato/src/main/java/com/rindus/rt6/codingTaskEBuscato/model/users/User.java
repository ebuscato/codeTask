package com.rindus.rt6.codingTaskEBuscato.model.users;

import lombok.Data;

@Data
public class User {
	
	private int id;
	private String name;
	private String username;
	private String email;
	private Address address;
	private String phone;
	private String website;
	private Company company;
	
	public String toString() {
		return username;		
	}
	
	

}
