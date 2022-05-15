package com.teampjt.StepUP.group;

import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

import com.teampjt.StepUP.command.GroupBoardVO;
import com.teampjt.StepUP.command.GroupCommentsVO;
import com.teampjt.StepUP.command.GroupDetailCommentVO;
import com.teampjt.StepUP.command.GroupMemberVO;
import com.teampjt.StepUP.command.GroupNoticeVO;
import com.teampjt.StepUP.command.LikeCountVO;
import com.teampjt.StepUP.command.MainCommentsVO;
import com.teampjt.StepUP.command.RequestVO;
import com.teampjt.StepUP.command.SearchCategoryVO;
import com.teampjt.StepUP.command.StudyGroupVO;
import com.teampjt.StepUP.command.ToDoListVO;
import com.teampjt.StepUP.util.Criteria;

public interface GroupService {
	
	//메인화면 스터디그룹 조회메서드
	public ArrayList<StudyGroupVO> getGroupList(Criteria cri); 
	
	//전체 그룹 조회메서드
	public int getGroupTotal(Criteria cri);
	
	//카테고리 조회
	public ArrayList<SearchCategoryVO> getCategory();
	
	//두 번째 카테고리 조회
	public ArrayList<SearchCategoryVO> getCategoryChild(SearchCategoryVO vo);

	public int noticeRegist(GroupNoticeVO gnVO); //공지등록
	
	public ArrayList<GroupNoticeVO> getNoticeList(Criteria cri); //공지목록
	
	// 그룹 공지 미리보기 - 그룹 메인화면에서 보이는 공지 상위 5개
	public ArrayList<GroupNoticeVO> getNoticeView(int group_no);
	
	public GroupNoticeVO getNoticeDetail(int groupnotice_no); //공지상세보기
	
	public int updatecount(int groupnotice_no); //조회수
	
	public GroupNoticeVO getNoticeModify(int groupnotice_no); //수정페이지
	
	public int noticeUpdate(GroupNoticeVO gnVO);//수정
	
	public int noticeDelete(int groupnotice_no); //공지삭제
	
	public int GNgetTotal(); //전체게시글
	
	public GroupNoticeVO movePage(int groupnotice_no); //이전글,다음글
	
	public int commentRegist(GroupDetailCommentVO gdcVO); //글 등록
	
	public ArrayList<GroupDetailCommentVO> getGroupCommentList(); //댓글 목록
	
	public ArrayList<GroupDetailCommentVO> getComment(); // 댓글 등록
	
	public int commentUpdate(GroupDetailCommentVO gdcVO); //댓글 수정
	
	public int commentDelete(int comment_no); //댓글 삭제
	
	public int groupRegist(StudyGroupVO vo, MultipartFile f); // 그룹 생성 메서드
	
	public int nameChk(String group_name); // 그룹명 중복체크
	
	public int groupApplicationReg(RequestVO reqVO); //그룹신청
	
	public StudyGroupVO getStudyGroupDetail(int group_no); //그룹 상세보기
	
	// 이미 해당 그룹에 가입되어 있는지 체크
	public int requestChk(GroupMemberVO vo);
	
	//신청 수락(insert)
	public int requestOk(GroupMemberVO vo);
	
	// 현재 그룹에 가입중인 회원 수 카운트
	public int getMemberTotal(int group_no);
	
	// 그룹 참여신청 카운트(같은 그룹 같은사람이 여러번 참여신청하는것 방지)
	public int getReqChk(RequestVO reqVO);
	
	// 내가 가입한 그룹번호 리스트 가져오기
	public ArrayList<Integer> getMyGroupNoList1(int user_no);
	
	// 내가 그룹장인 그룹번호 리스트 가져오기
	public ArrayList<Integer> getMyGroupNoList2(int user_no);
	
	// 그룹 일정 등록
	public int toDoListReg(ToDoListVO vo);
	
	// 그룹 일정 조회
	public ArrayList<ToDoListVO> getToDoList(ToDoListVO vo);
	
	// 그룹 일정 삭제
	public int toDoDelete(int todo_no);
	
	// 그룹 일정 수정
	public int todoUpdate(ToDoListVO vo);
	
	// 해당유저의 좋아요 여부 확인 메서드
	public int like_count_chk(LikeCountVO vo);
	
	// 좋아요 등록 메서드
	public int like_count_reg(LikeCountVO vo);
	
	// 좋아요 갯수 카운트 메서드
	public int like_count(LikeCountVO vo);
	
	// 좋아요 취소 메서드
	public int like_delete(LikeCountVO vo);
	
	// 신청목록 삭제 메서드(수락 및 거절 후 목록에서 지워줌)
	public int req_delete(RequestVO vo);
	
	// 그룹멤버 탈퇴
	public int groupSecession(GroupMemberVO vo);
	
	
	
	
	// 그룹 메인게시판
	
	//글 등록
	public int gb_regist(GroupBoardVO vo);
	
	//글 목록 불러오기
	public ArrayList<GroupBoardVO> gb_getList(Criteria cri);
	
	//글 게시글 수 불러오기 (total)
	public int gb_getTotal(Criteria cri);
	
	//글 내용 불러오기
	public GroupBoardVO gb_getUpdateList(int group_board_no);
	
	//글 내용 수정하기
	public int gb_update(GroupBoardVO vo);

	//글 삭제
	public int gb_delete(GroupBoardVO vo);
	
	//댓글 등록
	public int gc_regist(GroupCommentsVO vo);
	
	//댓글 목록 불러오기
	public ArrayList<GroupCommentsVO> gc_getList(GroupCommentsVO vo);
	
	//댓글 삭제
	public int gc_delete(GroupCommentsVO vo);
	
	//댓글 업데이트
	public int gc_UpdateContents(GroupCommentsVO vo);
	
	// 전용 게시판 글 미리보기
	public ArrayList<GroupBoardVO> gb_getListview();
	
}

