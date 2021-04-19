package com.rindus.rt6.codingTaskEBuscato.util.convert.javatojson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter {
	
	
	/**
	 * Converts a list of beans in a  JSON object
	 * @param elementsList
	 * @param content
	 * @return
	 */
	public static String getJsonContent(Object[] elementsList, String content) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			content = mapper.writeValueAsString(elementsList);	   
		   
	
		} catch (JsonProcessingException e) {
		   e.printStackTrace();
		}
		return content;
	}
}
