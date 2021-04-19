package com.rindus.rt6.codingTaskEBuscato.controller.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.rindus.rt6.codingTaskEBuscato.model.post.Post;

@Controller
public class PostController {
	

	@Autowired
	private RestTemplate restTemplate;

	@Value("${rest.api.url}")
	private String rest_api_url;
	
	@Value("${rest.api.users}")
	private String rest_api_users;

	
	@Value("${rest.api.posts}")
	private String rest_api_posts;
	
	
	/**
	 * Get a list with the Posts belongint to user
	 * identified by the provided id
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/postsList/{id}")
	public String getPostsList(Model model, @PathVariable int id) {
					
		Post[] postsList = null;
		
		String url = rest_api_url
				+"/"
				+rest_api_users
				+"/"
				+id
				+"/"
				+rest_api_posts;
		
		postsList = restTemplate.getForObject(url, Post[].class);
		
		model.addAttribute("postsList", postsList);
		
		return "post/postsList";		
		
	}

}
