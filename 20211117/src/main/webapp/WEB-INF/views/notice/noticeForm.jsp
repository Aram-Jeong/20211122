<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		color: Sienna;
	}
	td{
		color: gray;
	}
	input[type=submit]{
		border: 1px solid Sienna;
		background-color: white;
		color: Sienna;
	}
	input[type=reset]{
		border: 1px solid Sienna;
		background-color: white;
		color: Sienna;
	}
	button{
	
		border: 1px solid Sienna;
		background-color: white;
		color: Sienna;
	}
	
</style>
</head>
<body>
<jsp:include page="../home/header.jsp"/>
<div align="center">
	<div><p>🐰공지사항 등록🥕</p></div>
	<div>
		<form id="frm" action="noticeResister.do" method="post" enctype="multipart/form-data">
		<div>
			<table>
				<tr>
					<th width="150">작성자</th>
					<td width="150">${name }</td> <!-- 세션값에서 가져온다 -->
					<th width="150">작성일자</th>
					<td width="150">
						<input type="date" id="wdate" name="wdate">
					</td>
				</tr>
				<tr>
					<th>제 목</th>
					<td colspan="3">
						<input type="text" id="title" name="title" size="98">
					</td>
				</tr>
				<tr>
					<th>내 용</th>
					<td colspan="3">
						<textarea rows="6" cols="100" id="subject" name="subject"></textarea>
					</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td colspan="3">
						<input type="file" id="fileName" name="fileName">
					</td>
				</tr>
			</table><br>
		</div>
			<input type="submit" value="저 장"> &nbsp;&nbsp;&nbsp;
			<input type="reset" value="취 소">
		</form>
	</div>
	<script type="text/javascript"> <!-- 오늘 날짜로 자동 설정-->
		document.getElementById('wdate').value = new Date().toISOString().substring(0, 10);
</script>
	
</div>
</body>
</html>