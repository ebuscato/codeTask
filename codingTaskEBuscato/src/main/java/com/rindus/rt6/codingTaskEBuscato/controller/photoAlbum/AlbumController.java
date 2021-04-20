package com.rindus.rt6.codingTaskEBuscato.controller.photoAlbum;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.rindus.rt6.codingTaskEBuscato.model.photoAlbum.Album;
import com.rindus.rt6.codingTaskEBuscato.model.photoAlbum.Photo;
import com.rindus.rt6.codingTaskEBuscato.util.convert.javatojson.JsonConverter;
import com.rindus.rt6.codingTaskEBuscato.util.file.FileUtil;
import com.rindus.rt6.codingTaskEBuscato.util.services.CommunicationServices;

@Controller
public class AlbumController {
	
	
	@Autowired
	private RestTemplate restTemplate;
	

	@Value("${rest.api.url}")
	private String rest_api_url;
	
	@Value("${rest.api.users}")
	private String rest_api_users;

	
	@Value("${rest.api.albums}")
	private String rest_api_albums;
	
	@Value("${rest.api.photos}")
	private String rest_api_photos;
	
	@Value("${export.json.filename}")
	private String export_json_filename;


	/**
	 * Get a list of albums belonging to the user
	 *  identified by the id provided
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/albumsList/{id}")
	public String getAlbumsList(Model model,  @PathVariable int id) {
					
		Album[] albumsList = getAlbumsList(id);
		
		model.addAttribute("albumsList", albumsList);
		model.addAttribute("userId", id);
		
		return "photo/albumsList";		
		
	}


	/**
	 * Exports to JSON the  list of albums belonging to the user
	 *  identified by the id provided
	 * @param response
	 * @param id
	 * @return
	 */
	@GetMapping("/exportAlbumToJson/{id}")
	public String exportAlbumsListToJson(HttpServletResponse response,  @PathVariable int id) {
		
		
		Album[] albumsList = getAlbumsList(id);			
		
		String json = JsonConverter.getJsonContent(albumsList);
		
		
		FileUtil.saveFile(response, rest_api_albums+export_json_filename, json);
			
	
		return "index";
	
	}
	
	/**
	 * Exports to JSON the  list of photos belonging to the album
	 *  identified by the id provided
	 * @param response
	 * @param id
	 * @return
	 */
	@GetMapping("/exportPhotosToJson/{id}")
	public String exportPhotosListToJson(HttpServletResponse response,  @PathVariable int id) {
		
		
		Photo[] photosList = getPhotosList(id);			
		
		String json = JsonConverter.getJsonContent(photosList);
		
		
		FileUtil.saveFile(response,rest_api_photos+export_json_filename, json);
			
	
		return "index";
	
	}
	


	/**
	 * Get a list of photos belonging to the album
	 *  identified by the id provided
	 *  
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/photosList/{id}")
	public String getPhotosList(Model model,  @PathVariable int id) {
					
		Photo[] photosList = getPhotosList(id);
		
		model.addAttribute("photosList", photosList);
		model.addAttribute("albumId", id);
		model.addAttribute("userId", id);
		
		return "photo/photosList";		
		
	}



	/**
	 * Get a single photo, identified by the id provided
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/photoData/{id}")
	public String getPhotoData(Model model,  @PathVariable int id) {
					
		Photo photo = null;
		
		String url = rest_api_url
				+"/"
				+rest_api_photos
				+"/"
				+id;			
		
		photo = restTemplate.getForObject(url, Photo.class);
		
		model.addAttribute("photo", photo);
		
		return "photo/photoData";	
		
	}
	
	/**
	 * Update the datas of the photo in the service
	 * 
	 * @param photo
	 * @return
	 */
	 @PostMapping("/photoUpdate")
    public String photoSubmit(@ModelAttribute Photo photo) {  
		
		String url = rest_api_url
				+"/"
				+rest_api_photos
				+"/"
				+photo.getId();	
		
		HttpHeaders headers = CommunicationServices.getHeaders();		
	
	    HttpEntity<Photo> entity = new HttpEntity<>(photo, headers);
		
		restTemplate.put(url, entity, photo.getId());	
			
		return "photo/photoData";		
		
	}


	
	/**
	 * Consumes an albums list from the service.
	 * 
	 * @param id
	 * @return
	 */
	private Album[] getAlbumsList(int id) {
		Album[] albumsList;
		String url = rest_api_url
				+"/"
				+rest_api_users
				+"/"
				+id
				+"/"
				+rest_api_albums;	
		
		albumsList = restTemplate.getForObject(url, Album[].class);
		return albumsList;
	}
	

	/**
	 * Consumes a list of photos from the service
	 * @param id
	 * @return
	 */
	private Photo[] getPhotosList(int id) {
		Photo[] photosList = null;
		
		String url = rest_api_url
				+"/"
				+rest_api_albums
				+"/"
				+id
				+"/"
				+rest_api_photos;
		
		photosList = restTemplate.getForObject(url, Photo[].class);
		return photosList;
	}

}
