<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	h1{
		color: Sienna;
	}
	table{
		
	}
	th{
		color: Sienna;
		
		background-color: wheat;
		
	}
	td{
		color: Sienna;
		padding-left: 15px;
		border: 1px solid wheat;
		
	}
	button{
		border: 1px solid Sienna;
		background-color: white;
		color: Sienna;
	}
</style>
<script src="js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

/*
	function CallProcess(n){
		if(n == 1){
			frm.action = "memberUpdate.do";
		}else{
			var str = confirm("나갈 거니...? \n 들어올 땐 마음대로지만 나갈 땐 아니란다.")
			if(str)
				frm.action = "memberDelete.do";
			else
				return false;
		}
		frm.submit();
	}
	*/
	
	$(function(){
		$("#btn1").click(function(){
			frm.action = "memberUpdate.do";
			frm.submit();
		});
		$("#btn2").click(function(){
			var str = confirm("탈퇴...? \n 들어올 땐 마음대로지만 나갈 땐 아니란다.")
			if(str)
				frm.action = "memberDelete.do";
			else
				return false;
			frm.submit();
		});
	});
</script>
</head>
<body>
<jsp:include page="../home/header.jsp"/>
<div align="center">
	<div><h1>마이페이지</h1></div>
	<div>
		<table>
			<tr>
				<th width="150">아이디</th>
				<td width="150">${member.id }</td>
				<th width="150">이 름</th>
				<td width="150">${member.name }</td>
			</tr>
			<tr>
				<th>패스워드</th>
				<td>****</td>
				<th>권 한</th>
				<td>${member.author }</td>
			</tr>
			<tr>
				<th>연락처</th>
				<td colspan="3">${member.tel }</td>
			</tr>
			<tr>
				<th>주 소</th>
				<td colspan="3">${member.address }</td>
			</tr>
		</table>
	</div><br>
	<div>
		<button type="button" id="btn1">수 정</button>&nbsp;&nbsp;&nbsp;
		<button type="button" id="btn2">회원탈퇴</button>
	</div>
	<div>
		<form id="frm" method="post">
			<input type="hidden" id="id" name="id" value="${member.id }">
		</form>
	</div>
</div>
</body>
</html>