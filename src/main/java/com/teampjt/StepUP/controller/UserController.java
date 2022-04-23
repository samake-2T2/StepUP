package com.teampjt.StepUP.controller;

import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.multipart.MultipartFile;
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

	//회원가입 폼
	@PostMapping("/joinForm")
	public String joinForm(UserVO vo,
						   RedirectAttributes  RA) {
		
//		for(MultipartFile f : list) {
//			System.out.println(f.isEmpty()); //비어있다면 true
//			System.out.println(f.getContentType()); //파일의 타입
//		}
//		
//	
//		// 1. 빈 형태로 넘어오는 이미지를 제거
//		list = list.stream().filter( (f) -> f.isEmpty() == false).collect( Collectors.toList());
//		
//		// 2. 업로드 된 확장자가 이미지만 가능하도록 처리
//		for(MultipartFile f : list) {
//			if(f.getContentType().contains("image") == false) {
//				RA.addFlashAttribute("msg", "jpg, png, jepg 이미지 형식만 등록가능합니다.");
//				
//				return "redirect:/main";
//			}
//		}
		//vo를 등록
		int result = userService.userJoin(vo);
		
		if(result == 1) {
			RA.addFlashAttribute("msg", vo.getUser_no() + "이 정상 등록되었습니다.");
		} else {
			RA.addFlashAttribute("msg", "등록실패, 관리자에게 문의하세요.");
		}

		return "redirect:/main";

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

	//업데이트폼
	@PostMapping("/userUpdate")
	public String userUpdate(UserVO userVO,
							 RedirectAttributes RA) {

		System.out.println(userVO.toString());
		int result = userService.update(userVO);

		if(result == 1) {
			RA.addFlashAttribute("msg", "정보수정이 완료되었습니다");

		}else {
			RA.addFlashAttribute("msg", "정보수정에 실패하였습니다");

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
