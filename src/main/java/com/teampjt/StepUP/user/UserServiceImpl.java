package com.teampjt.StepUP.user;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.teampjt.StepUP.command.UserVO;
import com.teampjt.StepUP.command.userUploadVO;
import com.teampjt.StepUP.util.Criteria;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	//업로드 할 경로 (application.properties값을 참조)
	@Value("${project.upload.path}")
	private String uploadPath;

	//폴더 생성 함수
	public String makeFolder() {

		//날짜별로 폴더생성
		DateTimeFormatter datetime = DateTimeFormatter.ofPattern("yyMMdd");
		String date = LocalDateTime.now().format(datetime);

		//java.io인 것을 import (업로드 경로 \\ 폴더명)
		File file = new File(uploadPath + "\\" + date);

		if(file.exists() == false) { //폴더가 존재하면 true, 존재하지 않으면 false
			file.mkdir(); //
		}

		return date; //년, 월, 일 리턴
	}
	
	
	// multi insert작업(상품 insert -> 파일업로드 -> 업로드 테이블 insert)
	@Override
	public int regist(UserVO vo) {

		int result = userMapper.regist(vo);
		
//		for(MultipartFile f : list) {
//			//1. 파일명 추출(브라우저 별로 다를 수 있기 때문에 \\ 기준으로 파일명 추출)
//			String originName = f.getOriginalFilename();
//			String filename = originName.substring( originName.lastIndexOf("\\") + 1); //미만 절삭
//			
//			//2. 업로드 된 파일을 폴더별로 저장(파일생성)
//			String filepath = makeFolder();
//			
//			//3. 랜덤값을 이용해서 동일한 파일명을 처리
//			String uuid= UUID.randomUUID().toString();
//			
//			//4. 업로드 설정
//			String savename = uploadPath + "\\" + filepath + "\\" + uuid + "_" + filename;
//		
//			//5. 업로드 진행
//			try {
//				f.transferTo(new File(savename));
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//				return 0; //실패의 의미로 영을 영포
//			}
//			
//			//6. 빌더패턴을 이용해 업로드 테이블에 insert진행
//			
//			userUploadVO uploadVO = userUploadVO.builder()
//												.filename(filename)
//												.filepath(filepath)
//												.uuid(uuid)
//												.build();
//			
//			
//		}
		
		return result;
	}
	
	
	@Override
	public int userJoin(UserVO vo) {
		return userMapper.userJoin(vo);
	}

	@Override
	public int userDelete(int user_no) {

		return userMapper.userDelete(user_no);
	}

	@Override
	public int update(UserVO userVO) {
		return userMapper.update(userVO);
	}

	@Override
	public ArrayList<UserVO> getApplyList(Criteria cri) {

		return userMapper.getApplyList(cri);
	}

	@Override
	public int getApplyTotal() {

		return userMapper.getApplyTotal();
	}

	@Override
	public UserVO login(UserVO vo) {

		return userMapper.login(vo);
	}

}
