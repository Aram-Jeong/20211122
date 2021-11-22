package co.aram.prj.command;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.aram.prj.comm.Command;

public class AjaxFileDownload implements Command { // 파일 다운로드

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		String fileName = request.getParameter("fileName");
		String pfileName = request.getParameter("pfileName");
		// 파일 다운로드 로직 작성
		InputStream in = null;
		OutputStream out = null;
		File file = null;
		try {
			file = new File(pfileName);
			in = new FileInputStream(file);
			fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
			response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName+"\"");
			out = response.getOutputStream(); // response 객체로 초기화
			byte b[] = new byte[(int)file.length()]; // 메모리에 파일을 담음
			int leng = 0;
			while((leng = in.read(b)) > 0) { // 실제 다운로드 함
				out.write(b,0,leng);
			}
			response.reset();
			in.close(); // 반드시 닫아준다!
			out.close();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "ajax:" + "OK"; 
	}

}
