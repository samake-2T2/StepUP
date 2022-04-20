package com.teampjt.StepUP.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/group")
public class GroupController {

	@GetMapping("/groupApplication")
	public String groupApplication() {
		return "group/groupApplication";
	}
	
	@GetMapping("/groupMain")
	public String groupMain() {
		return "group/groupMain";
	}
	
	@GetMapping("/groupRegist")
	public String groupRegist() {
		return "group/groupRegist";
	}
	
	@GetMapping("/groupRegList")
	public String groupRegList() {
		return "group/groupRegList";
	}
	
	@GetMapping("/groupDetail")
	public String groupDetail() {
		return "group/groupDetail";
	}
	
	@GetMapping("/groupNotice")
	public String groupNotice() {
		return "group/groupNotice";
	}
}
