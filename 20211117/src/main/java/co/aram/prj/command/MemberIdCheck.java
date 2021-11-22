package co.aram.prj.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.aram.prj.comm.Command;
import co.aram.prj.member.service.MemberService;
import co.aram.prj.member.service.MemberVO;
import co.aram.prj.member.serviceImpl.MemberServiceImpl;

public class MemberIdCheck implements Command {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		// 아이디 중복체크(Ajax 이용)
		MemberService memberDao = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		vo.setId(request.getParameter("chkid"));
		boolean b = memberDao.memberIdCheck(vo);
		String chk = "0"; 
		if(b) { // 존재하면
			chk = "1";	
		}
	
		return "ajax:"+ chk;
	}

}
