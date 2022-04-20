package com.teampjt.StepUP.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	//업데이트 폼
	@PostMapping("/userUpdate")
	public String userUpdate(UserVO userVO,
							 RedirectAttributes RA) {
		
		System.out.println(userVO.toString());
		int result = userService.update(userVO);
		
		if(result == 1) {//성공
			RA.addFlashAttribute("msg", "게시물이 정상수정되었습니다");
			
		}else {//실패
			RA.addFlashAttribute("msg", "수정실패, 관리자에게 문의하세요");
			
		}
		return "redirect:/main";
	}
}
