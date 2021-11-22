<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	p{
		font-size: 32px;
		color: Sienna;
	}
	table{
		border: 1px dashed Sienna;
	}
	th{
		border-bottom: 1px dashed Sienna;
		color: Sienna;
	}
	tr{
		color: gray;
	}
	button{
		border: 1px solid Sienna;
		background-color: white;
		color: Sienna;
	}
</style>
<script type="text/javascript">
	function noticeRead(str){
		frm.no.value=str;
		frm.submit();
	}
</script>
</head>
<body>
<jsp:include page="../home/header.jsp"/>
<div align="center">
	<div><p>🦊공지사항🍇</p></div>
	<div>
		<table>
			<tr>
				<th width="70">글번호</th>
				<th width="100">작성자</th>
				<th width="300">제 목</th>
				<th width="100">작성일자</th>
				<th width="100">첨부파일</th>
			</tr>
			<c:forEach items="${notices }" var="notice">
			  <tr onmouseover="this.style.background='wheat';"
                      onmouseleave="this.style.background='#FFFFFF';"
                      onclick="noticeRead('${notice.no}')">
				<td align="center">${notice.no}</td>
				<td align="center">${notice.name}</td>
				<td align="center">${notice.title}</td>
				<td align="center">${notice.wdate}</td>
				<td align="center">
					<c:if test="${not empty notice.fileName }">
						<img alt="첨부파일" src="img/file2.png"width="20", height="20">
					</c:if>
				</td>
			</tr>
			</c:forEach>
		</table>
	</div><br>
	<div>
		<c:if test="${not empty id }">
			<button type="button" onclick="location.href='noticeForm.do'">글쓰기</button>
		</c:if>
	</div>
	<div>
		<form id="frm" action="noticeRead.do" method="post">
			<input type="hidden" id="no" name="no">
		</form>
	</div>
</div>
</body>
</html>