package com.rindus.rt6.codingTaskEBuscato.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InitController {
	
	@GetMapping("/")
	public String main (Model model) {
		return "init";
	}
	
	@GetMapping("/init")
	public String mainUrl (Model model) {
		return "init";
	}

}
