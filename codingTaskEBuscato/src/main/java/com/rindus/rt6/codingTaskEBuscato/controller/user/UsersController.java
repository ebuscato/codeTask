package com.rindus.rt6.codingTaskEBuscato.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.rindus.rt6.codingTaskEBuscato.model.users.User;

@Controller
public class UsersController {
	

	
	@Autowired
	private RestTemplate restTemplate;

	@Value("${rest.api.url}")
	private String rest_api_url;
	
	@Value("${rest.api.users}")
	private String rest_api_users;


	/**
	 * Return a list with all the users
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/usersList")
	public String getUsersList(Model model) {
		
		User[] usersList = null;
		
		String url = rest_api_url
						+ "/"
						+rest_api_users;
		
		usersList = restTemplate.getForObject(url , User[].class);
		
		model.addAttribute("usersList", usersList);
		 
		return "/users/userslist";
	}
	

}
