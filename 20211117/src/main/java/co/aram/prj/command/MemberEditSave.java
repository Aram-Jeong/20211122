package co.aram.prj.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.aram.prj.comm.Command;
import co.aram.prj.member.service.MemberService;
import co.aram.prj.member.service.MemberVO;
import co.aram.prj.member.serviceImpl.MemberServiceImpl;

public class MemberEditSave implements Command {
	private String message;
	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		// 회원정보 변경 저장
		MemberService memberDao = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		vo.setId(request.getParameter("id"));
		vo.setPassword(request.getParameter("password"));
		vo.setName(request.getParameter("name"));
		vo.setTel(request.getParameter("tel"));
		vo.setAddress(request.getParameter("address"));
		vo.setAuthor(request.getParameter("author"));
		int n = memberDao.memberUpdate(vo);
		if(n != 0) {
			message = "수정되었습니다.";
		}
		request.setAttribute("message", message);
		return "memberInfo.do";
	}

}
