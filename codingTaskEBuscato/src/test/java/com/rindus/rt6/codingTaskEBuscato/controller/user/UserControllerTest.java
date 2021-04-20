package com.rindus.rt6.codingTaskEBuscato.controller.user;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import com.rindus.rt6.codingTaskEBuscato.commons.TestConstants;
import com.rindus.rt6.codingTaskEBuscato.model.users.User;
import com.rindus.rt6.codingTaskEBuscato.util.convert.javatojson.JsonConverter;



class UserControllerTest {
	

	/**
	 * Verifiy that response from users matchs a JSON response
	 */
	@Test
	public void getUsersList() {
		
	    RestTemplate restTemplate = new RestTemplate();

	    String url = TestConstants.URL_REST+"/"+TestConstants.USER;
	    User[] result = restTemplate.getForObject(url , User[].class);
	    
	    String jsonString = JsonConverter.getJsonContent(result);
	    
	    assertNotNull(jsonString);
		if (jsonString!=null) {
			assertTrue(jsonString.matches(TestConstants.JSON_REGEX));		
		}
	}

}
