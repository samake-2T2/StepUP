package com.teampjt.StepUP.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@GetMapping("/userDelete")
	public String userDelete(@RequestParam("user_no") int user_no, 
							 RedirectAttributes RA) {
		userService.userDelete(user_no);
		
		int result = userService.userDelete(user_no);
		if(result == 1) { // 성공
			RA.addFlashAttribute("msg", "탈퇴에 성공하였습니다.");
		}else { // 실패
			RA.addFlashAttribute("msg", "탈퇴에 실패하였습니다. 관리자에게 문의하세요.");
		}
		return "user/login";
	}
}
