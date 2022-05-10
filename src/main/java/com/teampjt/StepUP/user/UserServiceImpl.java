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

import com.teampjt.StepUP.board.BoardService;
import com.teampjt.StepUP.command.GroupNoticeVO;
import com.teampjt.StepUP.command.RequestVO;
import com.teampjt.StepUP.command.UserVO;
import com.teampjt.StepUP.command.userUploadVO;
import com.teampjt.StepUP.group.GroupMapper;
import com.teampjt.StepUP.group.GroupService;
import com.teampjt.StepUP.group.GroupServiceImpl;
import com.teampjt.StepUP.util.Criteria;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private BoardService boardService;

	//업로드 할 경로 (application.properties값을 참조)
	@Value("${project.upload.path}")
	private String useruploadPath;

	//폴더생성함수
	public String makeFolder(String folderName) {

		File file = new File(useruploadPath + "\\" + folderName);
		if(file.exists() == false ) { //폴도가 존재하면 true, 존재하지 않으면 false
			file.mkdir(); //폴더가 생성
		}

		return folderName; //폴더명 리턴
	}


	//회원가입
	@Override
	public int userRegist(UserVO vo) {

		return userMapper.userRegist(vo);

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
	public ArrayList<RequestVO> getApplyList(int group_no) {

		return userMapper.getApplyList(group_no);
	}
	
	@Override
	public UserVO login(UserVO vo) {

		return userMapper.login(vo);
	}


	@Override
	public int registFile(UserVO vo, MultipartFile f) {

		//1. 파일명 추출
		String originName = f.getOriginalFilename();
		String filename = originName.substring(originName.lastIndexOf("\\") + 1);


		//2. 업로드 된 파일을 폴더별로 저장(파일생성)
		String filepath = makeFolder(vo.getEmail());

		//3. 랜덤값을 이용해서 동일한 파일명의 처리
		String uuid = UUID.randomUUID().toString();

		// 최종경로
		String email = useruploadPath + "\\" + filepath + "\\" + uuid + "_" + filename;


		try {
			f.transferTo(new File(email));
		} catch (Exception e) {
			e.printStackTrace();
			return 0; // 실패의 의미 0
		}

		//
		userUploadVO upvo = userUploadVO.builder()
				.filename(filename)
				.filepath(filepath)
				.uuid(uuid)
				.user_no(vo.getUser_no())
				.build();


		System.out.println(upvo.toString());

		int result = userMapper.registFile(upvo);

		return result;




	}


	@Override
	public UserVO emailserch(UserVO vo) {
		
		return userMapper.emailserch(vo);
	}



	


	




}
