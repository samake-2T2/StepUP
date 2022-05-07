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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mysql.cj.Session;
import com.teampjt.StepUP.command.UserVO;
import com.teampjt.StepUP.command.userUploadVO;
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
			RedirectAttributes  RA,
			@RequestParam("file") MultipartFile f) {


		int result = userService.userRegist(vo);

		if(result == 1) {
			// 1. 업로드된 확장자가 이미지만 가능하도록 처리
			if(f.getContentType().contains("image") == false ) { //이미지가 아닌경우
				RA.addFlashAttribute("msg", "jpg, png, jpeg등의 이미지형식만 등록가능합니다.");
				return "redirect:/user/userJoin";
			}
		
			userService.registFile(vo, f);
			
			RA.addFlashAttribute("msg", "회원가입을 축하드립니다. ");
			

			return "user/login";
		} else {
			RA.addFlashAttribute("msg", "회원가입에 실패하였습니다. 관리자에게 문의하세요!");
			return "user/userJoin";
		}
		
	}

	@GetMapping("/userDelete")
	public String userDelete(@RequestParam("user_no") int user_no, 
			RedirectAttributes RA, HttpSession session) {
		userService.userDelete(user_no);

		int result = userService.userDelete(user_no);

		if(result == 1) { 
			RA.addFlashAttribute("msg", "탈퇴에 성공했습니다. ");
		}else { 
			RA.addFlashAttribute("msg", "탈퇴에 실패했습니다. 관리자에게 문의하세요");
		}
		return "redirect:/user/logout";
	}

	//업데이트폼
	@PostMapping("/userUpdate")
	public String userUpdate(UserVO userVO, 
			RedirectAttributes RA, Model model, HttpSession session) {


		int result = userService.update(userVO);

		//		model.addAttribute("userVO", userVO);



		if(result == 1) {
			UserVO vo = (UserVO)session.getAttribute("userVO");
			vo.setInterest(userVO.getInterest());
			vo.setUser_name(userVO.getUser_name());
			vo.setPassword(userVO.getPassword());
			session.setAttribute("userVO", vo);
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
			session.setAttribute("user_no", userVO.getUser_no());
			session.setAttribute("user_name", userVO.getUser_name());


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
			userService.userRegist(vo);
		}

		return "redirect:/main";
	}


	@GetMapping("/logout")
	public String logout(HttpSession session) {

		session.invalidate();

		return "redirect:/main";
	}
}
