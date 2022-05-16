package com.teampjt.StepUP.controller;

import java.io.File;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teampjt.StepUP.command.SearchCategoryVO;
import com.teampjt.StepUP.command.StudyGroupVO;
import com.teampjt.StepUP.group.GroupService;
import com.teampjt.StepUP.util.Criteria;

@RestController
public class AjaxController {

	// 그룹 이미지 업로드할 경로(application.properties값을 참조)
	@Value("${project.groupUpload.path}")
	private String uploadPath;
	
	@Autowired
	public GroupService groupService;
	
	//대분류 선택
	@GetMapping("/getCategory")
	public ArrayList<SearchCategoryVO> getCategory() {
		
		ArrayList<SearchCategoryVO> list = groupService.getCategory();
		
		
		return list;
	}
	
	//두 번째 카테고리
	@GetMapping("/getCategoryChild/{category_group_id}/{category_lv}/{category_detail_lv}")
	public ArrayList<SearchCategoryVO> getCategoryChild(@PathVariable("category_group_id") String category_group_id,
														@PathVariable("category_lv") int category_lv,
														@PathVariable("category_detail_lv") int category_detail_lv) {
		
		//System.out.println(category_group_id);
		//System.out.println(category_lv);
		//System.out.println(category_detail_lv);
		//System.out.println("-------------------");
		
		
		//마이바티스 전달을 위해 vo에 저장
		SearchCategoryVO vo = SearchCategoryVO.builder()
				                              .category_group_id(category_group_id)
				                              .category_lv(category_lv)
				                              .category_detail_lv(category_detail_lv)
				                              .build();
		
		ArrayList<SearchCategoryVO> list = groupService.getCategoryChild(vo);
		
		return list;
	}
	
	// 이미지 데이터 맵핑
	@GetMapping("/display")
	public byte[] display(@RequestParam("filename") String filename,
						  @RequestParam("uuid") String uuid,
						  @RequestParam("filepath") String filepath) {
		

		//System.out.println("uploadPath: " + uploadPath);
		
		File file = new File(uploadPath + "\\" + filepath + "\\" + uuid + "_" + filename);
		
		byte[] result = null;
		try {
			// 경로의 파일을 읽어서 바이트 배열형으로 파일정보를 반환
			result = FileCopyUtils.copyToByteArray(file);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
			
	
}
