package co.aram.prj.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.aram.prj.comm.Command;

public class Logout implements Command {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		// 로그아웃은 세션 리무브시키면 됨
		HttpSession session = request.getSession();
		session.invalidate(); // 세션 자체 삭제
		return "home.do";
	}

}
