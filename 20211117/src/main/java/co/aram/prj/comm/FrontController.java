package co.aram.prj.comm;

import java.io.IOException;

import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.aram.prj.command.AjaxAuthorUpdate;
import co.aram.prj.command.CommonFileUpload;
import co.aram.prj.command.AjaxFileDownload;
import co.aram.prj.command.HomeCommand;
import co.aram.prj.command.Logout;
import co.aram.prj.command.MemberDelete;
import co.aram.prj.command.MemberEditSave;
import co.aram.prj.command.MemberIdCheck;
import co.aram.prj.command.MemberInfo;
import co.aram.prj.command.MemberJoin;
import co.aram.prj.command.MemberJoinForm;
import co.aram.prj.command.MemberList;
import co.aram.prj.command.MemberLogin;
import co.aram.prj.command.MemberLoginForm;
import co.aram.prj.command.MemberUpdate;
import co.aram.prj.command.NoticeForm;
import co.aram.prj.command.NoticeList;
import co.aram.prj.command.NoticeRead;
import co.aram.prj.command.NoticeResister;
import co.aram.prj.command.ServletApiUpload;


@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Command> map = new HashMap<String, Command>();
       
   
    public FrontController() {
        super();
      
    }

	
	public void init(ServletConfig config) throws ServletException {
		// Command들을 요청에 따라 처리할 수 있도록 메모리에 구성한다.
		map.put("/home.do", new HomeCommand()); // 홈 페이지 <스트링, 인터페이스>
		map.put("/memberLoginForm.do", new MemberLoginForm()); // 로그인 폼 호출
		map.put("/memberLogin.do", new MemberLogin());
		map.put("/logout.do", new Logout()); 
		map.put("/memberList.do", new MemberList());
		map.put("/memberJoinForm.do", new MemberJoinForm()); 
		map.put("/ajaxIdCheck.do", new MemberIdCheck());
		map.put("/memberJoin.do", new MemberJoin());
		map.put("/memberInfo.do", new MemberInfo());
		map.put("/memberUpdate.do", new MemberUpdate()); // 회원정보 수정
		map.put("/memberEditSave.do", new MemberEditSave());
		map.put("/memberDelete.do", new MemberDelete());
		map.put("/ajaxAuthorUpdate.do", new AjaxAuthorUpdate()); // 회원권한 변경
		map.put("/noticeForm.do", new NoticeForm()); // 공지사항 폼 호출
		map.put("/noticeList.do", new NoticeList());
//		map.put("/noticeResister.do", new ServletApiUpload());
//		map.put("/noticeResister.do", new NoticeResister());
		map.put("/noticeResister.do", new CommonFileUpload()); // 공지사항 저장
		map.put("/noticeRead.do", new NoticeRead()); // 공지사항 상세 보기
		map.put("/ajaxFileDownload.do", new AjaxFileDownload());
	}


	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// 서비스 요청 들어오면 리퀘스트, 리스폰스 객체를 생성해준다.
		// 실제 요청을 수행할 커맨드 찾기
		// 커맨드에 리퀘스트와 리스폰스를 매개변수로 전달 - 서버 이용, 값 추가 가능
		// 리퀘스트, 리스폰스 객체를 싣고 뷰페이지 
		
		// 요청을 분석 실행할 명령을 찾아 수행하고 결과를 돌려주는 메소드
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String page = uri.substring(contextPath.length());
		
		Command command = map.get(page); // new HomeCommand();
		String viewPage = command.run(request, response); // 수행한 뒤 보여줄 결과 페이지
		
		if(!viewPage.endsWith(".do")) { // .do로 끝나지 않으면
			if(viewPage.startsWith("ajax:")) { // ajax 처리
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().append(viewPage.substring(5)); //ajax: 를 자른다.
				return;
			}else {
				viewPage = "WEB-INF/views/" + viewPage + ".jsp"; // 이걸 더해서 넘겨라
			}
			
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
