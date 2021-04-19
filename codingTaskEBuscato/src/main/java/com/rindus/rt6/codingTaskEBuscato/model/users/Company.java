package com.rindus.rt6.codingTaskEBuscato.model.users;

import lombok.Data;

@Data
public class Company {
	
	private String name;
	private String catchPhrase;
	private String bs;
	
	public String toString() {
		return name;
	}
}
