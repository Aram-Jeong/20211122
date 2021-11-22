package co.aram.prj.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.aram.prj.comm.Command;
import co.aram.prj.member.service.MemberService;
import co.aram.prj.member.service.MemberVO;
import co.aram.prj.member.serviceImpl.MemberServiceImpl;

public class MemberInfo implements Command {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		// 회원정보 보기
		HttpSession session = request.getSession();
		MemberService memberDao = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		vo.setId((String) session.getAttribute("id"));
		vo = memberDao.memberSelect(vo);
		request.setAttribute("member", vo);
		return "member/memberInfo";
	}

}
