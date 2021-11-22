package co.aram.prj.command;

import java.io.File;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import co.aram.prj.comm.Command;
import co.aram.prj.notice.service.NoticeService;
import co.aram.prj.notice.service.NoticeVO;
import co.aram.prj.notice.serviceImpl.NoticeServiceImpl;

public class NoticeResister implements Command {
	private String filePath="c:\\FileTest"; // 파일이 저장되는 절대 경로
	private int fileSize = 1024 * 1024 * 100; // 파일 최대 사이즈(100메가)
	
	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		// 공지사항 저장
		NoticeService noticeDao = new NoticeServiceImpl();
		NoticeVO vo = new NoticeVO();
		HttpSession session = request.getSession();
		vo.setId((String) session.getAttribute("id")); // 세션에 저장된 아이디 값
		vo.setName((String) session.getAttribute("name"));
		
		try {
			MultipartRequest multi =  // 객체 생성 시 파일이 전송됨
					new MultipartRequest(request, filePath, fileSize, "utf-8", new DefaultFileRenamePolicy()); // 마지막 메소드-중복된 파일이 오면 index 붙여주는 것
			// fileName은 중복이름이 들어올 경우 자동으로 index가 있는 물리 파일명
			String fileName = multi.getFilesystemName("fileName");
			// index되기 전의 원본명
			String original = multi.getOriginalFileName("fileName"); // 원본 파일명
			fileName = filePath + File.separator + fileName; // separator는 \이것이다! 저장경로를 포함해서 만듦 c:\FileTest\fileName
			if(original != null) { // 첨부파일이 존재할 때
				vo.setFileName(original); // 원본
				vo.setPfileName(fileName); // 물리파일명
				
			}else { // 첨부파일 존재 엑스
				vo.setFileName("");
				vo.setPfileName("");
			}
			vo.setWdate(Date.valueOf(multi.getParameter("wdate"))); // 문자를 날짜로 변경
			vo.setTitle(multi.getParameter("title"));
			vo.setSubject(multi.getParameter("subject"));
			
			noticeDao.noticeInsert(vo);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "noticeList.do";
	}

}
