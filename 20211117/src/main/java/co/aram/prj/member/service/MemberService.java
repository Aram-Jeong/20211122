package co.aram.prj.member.service;

import java.util.List;

public interface MemberService {
	List<MemberVO> memberSelectList(); // 전체
	MemberVO memberSelect(MemberVO vo); // 한 명
	int memberInsert(MemberVO vo);
	int memberDelete(MemberVO vo);
	int memberUpdate(MemberVO vo);
	boolean memberIdCheck(MemberVO vo); // id 중복체크
	int memberAuthorUpdate(MemberVO vo);  // 권한 변경
}
