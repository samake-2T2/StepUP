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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.teampjt.StepUP.command.UserVO;
import com.teampjt.StepUP.user.UserService;

import com.teampjt.StepUP.command.UserVO;
import com.teampjt.StepUP.user.UserService;

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

	@GetMapping("/userDelete")
	public String userDelete(@RequestParam("user_no") int user_no, 
			RedirectAttributes RA) {
		userService.userDelete(user_no);

		int result = userService.userDelete(user_no);
		if(result == 1) { 
			RA.addFlashAttribute("msg", "탈퇴에 성공했습니다. ");
		}else { 
			RA.addFlashAttribute("msg", "탈퇴에 실패했습니다. 관리자에게 문의하세요");
		}
		return "user/login";
	}

	//�뾽�뜲�씠�듃 �뤌
	@PostMapping("/userUpdate")
	public String userUpdate(UserVO userVO,
			RedirectAttributes RA) {

		System.out.println(userVO.toString());
		int result = userService.update(userVO);

		if(result == 1) {//�꽦怨�
			RA.addFlashAttribute("msg", "寃뚯떆臾쇱씠 �젙�긽�닔�젙�릺�뿀�뒿�땲�떎");

		}else {//�떎�뙣
			RA.addFlashAttribute("msg", "�닔�젙�떎�뙣, 愿�由ъ옄�뿉寃� 臾몄쓽�븯�꽭�슂");

		}
		return "redirect:/main";
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

	@PostMapping("/kakao-login")
	public String kakaoLogin(UserVO vo,
							 HttpSession session) {

		System.out.println(vo.toString());

		UserVO savedUser = userService.login(vo);

		// 저장된 회원정보가 없으면 전달받은 회원정보를 세션에 저장, 있으면 기존 정보 저장.
		if(savedUser != null) {
			session.setAttribute("userVO", savedUser);
		}else {
			session.setAttribute("userVO", vo);
			userService.userJoin(vo);
		}

		return "redirect:/main";
	}


	@GetMapping("/logout")
	public String logout(HttpSession session) {

		session.invalidate();

		return "/main";
	}
}
