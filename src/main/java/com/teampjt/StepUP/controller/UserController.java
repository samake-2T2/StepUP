package com.teampjt.StepUP.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.teampjt.StepUP.command.UserVO;
import com.teampjt.StepUP.user.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	

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
	
	@PostMapping("/joinForm")
	public String userJoin(UserVO vo) {
		
		userService.userJoin(vo);
		
		return  "redirect:/main";
		
	}
}
