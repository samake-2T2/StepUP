package com.teampjt.StepUP.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	@GetMapping("/login")
	public String login() {
		return "user/login";
	}
	
	@GetMapping("/userJoin")
	public String userJoin() {
		return "user/userJoin";
	}
	
	@GetMapping("/userMypage")
	public String userMypage() {
		return "user/userMypage";
	}
}
