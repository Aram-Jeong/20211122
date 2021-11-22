/* 
 * 파일업로드
 * 서블릿 3.0 이상에서 사용가능
 * 서블릿 객체가 제공하는 Part Class를 사용한다.
 */
package co.aram.prj.command;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import co.aram.prj.comm.Command;
import co.aram.prj.notice.service.NoticeService;
import co.aram.prj.notice.service.NoticeVO;
import co.aram.prj.notice.serviceImpl.NoticeServiceImpl;
//@MultipartConfig(
//         location="C:\\FileTest",   // 파일 임시 저장소
//         maxFileSize = -1,         // maxFileSize = -1 : Upload의 파일 Size를 제한하지 않겠다.
//         maxRequestSize = -1,      // 전체 multipart 사이즈에 제한두지 않겠다. multipart사이즈 = Home에서 넘어오는 데이터의 Size.
//         fileSizeThreshold = 1024   
//      )

public class ServletApiUpload implements Command {
   private String attachDir = "C:\\FileTest";   // 실제 저장할 장소
   private String fileName;
   
   @Override
   public String run(HttpServletRequest request, HttpServletResponse response) {
	   // 공지사항 
	   NoticeService noticeDao = new NoticeServiceImpl();
	   NoticeVO vo = new NoticeVO();
	   HttpSession session = request.getSession();
	   vo.setId((String) session.getAttribute("id"));
	   vo.setName((String) session.getAttribute("name"));
	   
      String contentType = request.getContentType();         // ContentType을 가져온다.
      if(contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
         try {
            Collection<Part> parts = request.getParts();   // 폼에서 넘어온 데이터를 각각 Pat 객체에 담는다.
            for(Part part : parts) {
               if(part.getHeader("Content-Disposition").contains("filename=")) {
                  String partHeader = part.getHeader("Content-Disposition");
                  for(String str : partHeader.split(";")) {
                     if(str.trim().startsWith("filename")) {   // 파일명 찾음
                        fileName = str.substring(str.indexOf("=") + 1).trim().replace("\"", "");
                        int index = fileName.indexOf(File.separator);
                        fileName = fileName.substring(index + 1);
                        System.out.println(fileName);
                     }
                  }
                  
                  if(part.getSize() > 0) {
                     part.write(attachDir + File.separator + fileName);   // 파일저장
                     part.delete();         // 임시 저장된 파일 삭제.
                  }
               }
            }
         } catch(IOException | ServletException e) {
            e.printStackTrace();
         }
      } // 파일 업로드
      		vo.setFileName(fileName);
      		vo.setPfileName(attachDir + File.separator + fileName); // 물리파일명
      		vo.setWdate(Date.valueOf(request.getParameter("wdate")));
      		vo.setTitle(request.getParameter("title"));
      		vo.setSubject(request.getParameter("subject"));
      		noticeDao.noticeInsert(vo);
      	
      // 이곳에 데이터 처리 request.getParameter("id") 형식으로 vo 객체에 담아서 처리
      return "noticeList.do";
   }

}