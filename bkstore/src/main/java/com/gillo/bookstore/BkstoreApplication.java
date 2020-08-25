package com.gillo.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
@RequestMapping("/")
public class BkstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BkstoreApplication.class, args);
	}
	
	@GetMapping
	String greeting(Model model) {
		model.addAttribute("greeting", "G'day mate!");
		return "home";
	}

}
