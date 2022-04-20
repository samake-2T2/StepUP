package com.teampjt.StepUP.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
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
	
	@PostMapping("/login")
	public String loginForm(@Valid UserVO vo, Errors errors,
							Model model,
							HttpSession session) {
		
		
		if(errors.hasErrors()) {
			
			List<FieldError> list = errors.getFieldErrors();
			
			for(FieldError err : list) {
				model.addAttribute("valid_" + err.getField(), err.getDefaultMessage());
			}
			
			return "user/login";
			
		}
		
		UserVO userVO = userService.login(vo);
		
		if(userVO == null) {
			return "user/login";
		} else {			
			session.setAttribute("userVO", userVO);
			
			return "redirect:/main";
		}
		
	}
	
	@GetMapping("kakaoLogin")
	public String kakaoLogin() {
		return "/main";
	}
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "/main";
	}
}
