package com.rindus.rt6.codingTaskEBuscato.controller.album;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import com.rindus.rt6.codingTaskEBuscato.commons.TestConstants;
import com.rindus.rt6.codingTaskEBuscato.model.photoAlbum.Album;
import com.rindus.rt6.codingTaskEBuscato.model.photoAlbum.Photo;
import com.rindus.rt6.codingTaskEBuscato.util.convert.javatojson.JsonConverter;
import com.rindus.rt6.codingTaskEBuscato.util.services.CommunicationServices;

class AlbumControllerTest {

	
	/**
	 * Test the behaviour of the request to Albums list
	 */
	@Test
	void getAlbumsList() {	
	
		String jsonString = "";
		Album[] albumsList;
		
		// assert we look for a known and existing value
		albumsList= testAlbumList( 1);		
		assertNotNull(albumsList);
		if (albumsList!=null) {
			 jsonString = JsonConverter.getJsonContent(albumsList);		
			 assertTrue(jsonString.matches(TestConstants.JSON_REGEX));	
		}
		
		
		// assert we look for a known and unexisting value 
		albumsList = testAlbumList(0);
		assertNotNull(albumsList);
		if (albumsList!=null) {
			assertEquals(albumsList.length, 0 );
			//the return must be a void list [] , accepted as a valid JSON
			jsonString = JsonConverter.getJsonContent(albumsList);
			assertTrue(jsonString.matches(TestConstants.JSON_REGEX));
			assertEquals(jsonString.trim().length(), 2 );
			assertEquals(jsonString, "[]");	
		}
	}
		
	/**
	 * Auxilairy method. 
	 * Allow to manage serious test id values for testing albums list
	 * @param testValue
	 * @return
	 */
	private Album[] testAlbumList( int testValue) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		String url = 
				TestConstants.URL_REST
				+"/"
				+TestConstants.USER
				+"/"
				+"%d"
				+"/"
				+TestConstants.ALBUMS;
		
		Album[] albumsList;
		
		String urlTest = String.format(url, testValue);	
		albumsList = restTemplate.getForObject(urlTest, Album[].class);
		return albumsList;
	}
	
	
	/**
	 * Test the behaviour to get a single value of photos
	 */
	@Test
	public void getPhotoData() {
		RestTemplate restTemplate = new RestTemplate();
		
		int testValue = 1;
		
		String url = TestConstants.URL_REST
				+"/"
				+TestConstants.PHOTOS
				+"/"
				+"1";			
		
		Photo photo = restTemplate.getForObject(url, Photo.class);
		assertNotNull(photo);
		if (photo!=null) {
			String jsonString = JsonConverter.getJsonContent(photo);
			assertTrue(jsonString.matches(TestConstants.JSON_REGEX));
			assertEquals(photo.getId(), testValue);	
		}
	
		
	}
	
	

}
