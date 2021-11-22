<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	a{
		color: Sienna ;
		text-align: center;
		text-decoration: none;
	}
	p{
		font-size: 32px;
		color: Sienna;
	}
	th{
		color: Sienna;
		background-color: wheat;
		
		
		
	}
	td{
		color: gray;
		border: 1px solid wheat;
	}
	

	button{
		background-color: white;
		border: 1px solid Sienna ;
		color: Sienna;
	}
	table{
		
	
	}
	textarea{
		border: none;
	}
	

</style>
<script src="js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	function fileDown(){
		var ofile = $("#fileName").val();
		var pfile = $("#pfileName").val();
		$.ajax({
			url:"ajaxFileDownload.do",
			type:"post",
			data: {fileName:ofile, pfileName:pfile},
			dataType: "text",
			success: function(data){
				if(data == 'OK'){
					alert(ofile + "을 성공적으로 다운로드하였습니다.");
				}
				
			},
			error: function(){
				alert("파일 다운로드 실패");
			}
		});
	}
</script>
</head>
<body>
<jsp:include page="../home/header.jsp"/>
<div align="center">
	<div><p>📋 공지사항</p></div>
	<div>
		<table>
			<tr>
				<th width="150">작성자</th>
				<td width="150">${notice.name }</td>
				<th width="150">작성일자</th>
				<td width="150">${notice.wdate }</td>
			</tr>
			<tr>
				<th>제 목</th>
				<td colspan="3">${notice.title }</td>
			</tr>
			<tr>
				<th>내 용</th>
				<td colspan="3">
					<textarea rows="6" cols="100" readonly="readonly">
						${notice.subject }
					</textarea>
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td colspan="3">
					<c:if test="${not empty notice.fileName }">
						<span><img src="img/file2.png" width="20" height="20">
						<a href="javascript:void(0);" onclick="fileDown(); return false;">${notice.fileName }</a></span>
					</c:if>
				</td>
			</tr>
		</table>
	</div>
	<div>
		<form> <!-- Ajax로 처리함 -->
			<input type="hidden" id="fileName" name="fileName" value="${notice.fileName }">
			<input type="hidden" id="pfileName" name="pfileName" value="${notice.pfileName }">
			<br><button type="button" onclick="history.back()">목록</button>
		</form>
	</div>
</div>
</body>
</html>