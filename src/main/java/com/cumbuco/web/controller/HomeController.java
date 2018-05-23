package com.cumbuco.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/inicio")
	public String index() {
		return "home/index";
	}
}
