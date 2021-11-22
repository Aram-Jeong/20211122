package co.aram.prj.command;

import java.io.File;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import co.aram.prj.comm.Command;
import co.aram.prj.notice.service.NoticeService;
import co.aram.prj.notice.service.NoticeVO;
import co.aram.prj.notice.serviceImpl.NoticeServiceImpl;

public class CommonFileUpload implements Command {
	// Apache Common-fileupload 라이브러리 사용
	private String fileSave = "c:\\FileTest";
//	private String fileSave = "fileUpload";  // 운영서버에 실제 동작환경을 꾸밀 때
	
	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		   HashMap<String, String> map = new HashMap<String, String>(); // 데이터 저장할 공간
		   NoticeService noticeDao = new NoticeServiceImpl();
		   NoticeVO vo = new NoticeVO();
		   HttpSession session = request.getSession();
		   vo.setId((String) session.getAttribute("id"));
		   vo.setName((String) session.getAttribute("name"));
		   String fileName = null;
		   String pfileName = null;
		
		   DiskFileItemFactory fileItemFactory = new DiskFileItemFactory(); // 파일저장소 관련 정보
		   ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory); // request 객체 parse
		
		try {
			List<FileItem> items = fileUpload.parseRequest(request);
			// FileItem 객체는 폼에서 넘어온 멀티파트 객체 형식을 다루는 객체
			for(FileItem item : items) {
				if(item.isFormField()){
					map.put(item.getFieldName(), item.getString("UTF-8")); //  필드명과 데이터 저장
				}else if(!item.isFormField() && item.getSize() > 0){
					int index = item.getName().lastIndexOf(File.separator);
					fileName = item.getName().substring(index +1); // 실 파일명만 추출
					String extension = fileName.substring(fileName.lastIndexOf("."),fileName.length()); // 파일확장자 찾기
					UUID uuid = UUID.randomUUID(); // 고유한 UUID 생성
					String newFileName =uuid.toString() + extension; // UUID를 통해 새로운 파일명으로 변환
					pfileName = fileSave + File.separator + newFileName; //c:\\FileTest\파일명
					File uploadFile = new File(pfileName); // 파일을 열어서 읽고
					item.write(uploadFile); // 파일 업로드가 일어남
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// 이곳에 DB 처리할 값을 넣어주는 곳
		
		vo.setFileName(fileName); // 원본
  		vo.setPfileName(pfileName); // 물리파일명
  		vo.setWdate(Date.valueOf(map.get("wdate")));
  		vo.setTitle(map.get("title"));
  		vo.setSubject(map.get("subject"));
  		noticeDao.noticeInsert(vo);
		return "noticeList.do";
	}

}
