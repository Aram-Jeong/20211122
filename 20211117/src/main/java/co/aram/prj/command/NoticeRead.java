package co.aram.prj.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.aram.prj.comm.Command;
import co.aram.prj.notice.service.NoticeService;
import co.aram.prj.notice.service.NoticeVO;
import co.aram.prj.notice.serviceImpl.NoticeServiceImpl;

public class NoticeRead implements Command {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		NoticeService noticeDao = new NoticeServiceImpl();
		NoticeVO vo = new NoticeVO();
		vo.setNo(Integer.valueOf(request.getParameter("no")));
		request.setAttribute("notice", noticeDao.noticeSelect(vo));
		return "notice/noticeRead";
	}

}
