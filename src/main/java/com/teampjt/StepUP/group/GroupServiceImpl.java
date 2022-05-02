package com.teampjt.StepUP.group;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.teampjt.StepUP.command.GroupDetailCommentVO;
import com.teampjt.StepUP.command.GroupNoticeVO;
import com.teampjt.StepUP.command.SearchCategoryVO;
import com.teampjt.StepUP.command.StudyGroupVO;
import com.teampjt.StepUP.util.Criteria;

@Service("groupService")
public class GroupServiceImpl implements GroupService {

	@Autowired
	public GroupMapper groupMapper;

	// 그룹 이미지 업로드할 경로(application.properties값을 참조)
	@Value("${project.groupUpload.path}")
	private String uploadPath;

	//폴더생성함수
	public String makeFolder(String folderName) {

		File file = new File(uploadPath + "/" + folderName ); //java.io (업로드 경로 \\ 폴더명 )
		if(file.exists() == false ) { //폴더가 존재하면 true, 존재하지 않으면 false
			file.mkdir(); //폴더가 생성
		}

		return folderName; //폴더명 리턴
	}





	@Override
	public ArrayList<StudyGroupVO> getGroupList(Criteria cri) {

		return groupMapper.getGroupList(cri);
	}

	@Override
	public int getGroupTotal(Criteria cri) {

		return groupMapper.getGroupTotal(cri);
	}

	@Override
	public ArrayList<SearchCategoryVO> getCategory() {

		return groupMapper.getCategory();
	}

	@Override
	public ArrayList<SearchCategoryVO> getCategoryChild(SearchCategoryVO vo) {

		return groupMapper.getCategoryChild(vo);
	}

	@Override
	public int noticeRegist(GroupNoticeVO gnVO) {
		return groupMapper.noticeRegist(gnVO);
	}

	@Override
	public ArrayList<GroupNoticeVO> getNoticeList(Criteria cri) {
		return groupMapper.getNoticeList(cri);
	}

	@Override
	public GroupNoticeVO getNoticeDetail(int groupnotice_no) {
		return groupMapper.getNoticeDetail(groupnotice_no);
	}

	@Override
	public int updatecount(int groupnotice_no) {
		return groupMapper.updatecount(groupnotice_no);
	}

	@Override
	public GroupNoticeVO getNoticeModify(int groupnotice_no) {
		return groupMapper.getNoticeModify(groupnotice_no);
	}

	@Override
	public int noticeUpdate(GroupNoticeVO gnVO) {
		return groupMapper.noticeUpdate(gnVO);
	}

	@Override
	public int noticeDelete(int groupnotice_no) {
		return groupMapper.noticeDelete(groupnotice_no);
	}

	@Override
	public int getTotal() {
		return groupMapper.getTotal();
	}		

	public ArrayList<GroupDetailCommentVO> getComment() {

		return groupMapper.getComment();
	}

	@Override
	public int commentRegist(GroupDetailCommentVO gdcVO) {

		return groupMapper.commentRegist(gdcVO);
	}

	@Override
	public ArrayList<GroupDetailCommentVO> getGroupCommentList() {

		return groupMapper.getGroupCommentList();
	}

	@Override
	public GroupNoticeVO movePage(int groupnotice_no) {
		return groupMapper.movePage(groupnotice_no);
	}

	@Override
	public int commentUpdate(GroupDetailCommentVO gdcVO) {

		return groupMapper.commentUpdate(gdcVO);
	}

	@Override
	public int groupRegist(StudyGroupVO vo, MultipartFile f) {

			// 1. 파일명추출(브라우저별로 다를 수 있기 때문에 \\ 기준으로 파일명 추출)
			String originName = f.getOriginalFilename();
			String filename = originName.substring(originName.lastIndexOf("/") + 1);

			// 2. 업로드 된 파일을 폴더별로 저장(파일생성)
			String filepath = makeFolder(vo.getGroup_name());

			// 3. 랜덤값을 이용해서 동일한 파일명의 처리
			String uuid = UUID.randomUUID().toString();

			// 최종경로
			String savename = uploadPath + "/" + filepath + "/" + uuid + "_" + filename;

			// 업로드 진행
			try {
				f.transferTo(new File(savename));
			} catch (Exception e) {
				e.printStackTrace();
				return 0; // 실패의 의미 0
			}

			// 테이블에 insert진행
			StudyGroupVO groupVO = StudyGroupVO.builder()
					.group_name(vo.getGroup_name())
					.group_content(vo.getGroup_content())
					.group_participant_amount(vo.getGroup_participant_amount())
					.group_leader_no(vo.getGroup_leader_no())
					.group_leader_name(vo.getGroup_leader_name())
					.group_filename(filename)
					.group_filepath(filepath)
					.group_fileuuid(uuid)
					.category_parent_name(vo.getCategory_parent_name())
					.category_child_name(vo.getCategory_child_name())
					.build();

			System.out.println(groupVO.toString());

			int result = groupMapper.groupRegist(groupVO);
			return result;
		}


	@Override
	public int nameChk(StudyGroupVO vo) {

		return groupMapper.nameChk(vo);
	}



	@Override
	public int commentDelete(int comment_no) {

		return groupMapper.commentDelete(comment_no);
	}
}
