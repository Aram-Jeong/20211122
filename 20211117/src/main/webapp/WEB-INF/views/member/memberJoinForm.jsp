<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	h2{
		color: Sienna;
	}
	table{
		
		height: 300px;
		color: Sienna;
	}
	th{
		background-color: wheat;
		text-align: left;
		padding-left: 15px;	
	}
	td{
		border: 1px solid wheat;
	}
	input[type=text]{
		border: none;
	}
	input[type=email]{
		border: none;
	}
	input[type=password]{
		border: none;
	}
	input[type=button]{
		background-color: wheat;
		color: Sienna;
		border: 1px solid wheat;
		border-radius: 5px;
		width: 70px;
		height: 30px;
		
	}
	input[type=reset]{
		background-color: wheat;
		color: Sienna;
		border: 1px solid wheat;
		border-radius: 5px;
		width: 70px;
		height: 30px;
	}
	#idchk{
		background-color: wheat;
		color: Sienna;
		border: none;
		width: 70px;
		height: 30px;
		border-radius: 5px;
	}
	
	

</style>
<script src="js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	function CheckEmail(str){ 		<!--이메일 체크 정규식-->
		var reg_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
		if(!reg_email.test(str)){
			return false;
		}else{
			return true;
		}
	}
		function idCheck(){ 		<!--아이디 중복체크-->
			var id = $("#id").val();
			if(!CheckEmail(id)){
				alert("이메일을 입력하세요.");
				$("#id").val("");
				$("#id").focus();
				return;
			}
			$.ajax({
				url : "ajaxIdCheck.do",
				type : "post",
				data : {chkid : id},
				dataType : "text",
				success : function(data){
					 if(data == '0'){
						 alert(id + "는사용할 수 있는 아이디입니다.");
						 $("#idchk").val("Yes")
						 $("#password").focus();
					 }else{
						 alert("이미 존재하는 아이디입니다.");
						 $("#id").val("");
						 $("#id").focus();
			 }	
			}
			
		});
		
	}
		function formCheck(){
			if($("#idchk").val() != 'Yes'){
				alert("아이디 중복체크를 해주세요.");
				return false;
			}
			if($("#password").val() != $("#passwordChk").val()){
				alert("패스워드가일치하지 않습니다.");
				$("#password").val("");
				$("#passwordChk").val("");
				$("#password").focus();
				return false;
			}
			$("#frm").submit();
			
		}
</script>
</head>
<body>
<jsp:include page="../home/header.jsp" />
   <div align ="center">
   <div><h2>회원가입</h2></div>
   <div>
	   <form id="frm" action="memberJoin.do" method="post">
	   <div>
	      <table>
	         <tr>
	            <th width="130">* 아 이 디</th>
	            <td width="350"><input type="email" id="id" name="id" required="required" placeholder="이메일 입력">
	            &nbsp;&nbsp;<button type="button" id="idchk" onclick="idCheck()" value="No">중복체크</button></td>
	         </tr>
	         <tr>
	            <th>* 비밀번호</th>
	            <td><input type="password" id="password" name="password" required="required" placeholder="비밀번호 입력"></td>
	         </tr>
	         <tr>
	            <th>* 비밀번호 확인</th>
	            <td><input type="password" id="passwordChk" name="passwordChk" required="required" placeholder="비밀번호 입력"></td>
	         </tr>
	         <tr>
	            <th>* 이     름</th><td><input type="text" id="name" name="name" placeholder="이름 입력"></td>
	         </tr>
	         <tr>
	            <th>전화번호</th>
	            <td><input type="text" id="tel" name="tel" placeholder="연락처 입력"></td>
	         </tr>
	         <tr>
	            <th>주     소</th>
	            <td><input type="text" id="address" name="address" size="68" placeholder="주소 입력"></td>
	         </tr>
	      </table>
	   </div><br>
	      <input type="button" onclick="formCheck()" value="회원가입">&nbsp;&nbsp;&nbsp;
	      <input type="reset" value="취  소">
	   </form>   
	</div>
</div>
</body>
</html>