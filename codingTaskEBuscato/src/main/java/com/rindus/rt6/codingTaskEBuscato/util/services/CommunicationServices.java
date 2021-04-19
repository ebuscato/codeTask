package com.rindus.rt6.codingTaskEBuscato.util.services;

import java.util.Collections;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class CommunicationServices {
	
	 /**
	  * Get the headers used in the creation of entities
	  * @return
	  */
	public static HttpHeaders getHeaders() {
	
	    HttpHeaders headers = new HttpHeaders();
	
	    headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		
		return headers;
	}

}
