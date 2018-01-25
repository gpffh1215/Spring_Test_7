<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		("#del").click(function(){
			var id = $(this).attr("title");
			$("#"+id).append('Photo: <input type="file" name="file" id="f">')
		});
		
		$("#btn").click(function(){
			var f = $("#f").val();
			if(f != ''){
				alert("OK");
				$("#frm").submit;
			}
			
		});
		
	});
</script>
</head>
<body>
	<h1>Update Form</h1>
	<form id="frm" action="./memberUpdate" method="post" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${member.id}">
		<p>Pw : <input type="password" name="pw" value="${member.pw}"> </p>
		<p>Name : <input type="text" name="name" value="${member.name}"></p>
		<p>Email : <input type="text" name="email" value="${member.email}"></p>
		<p>Phone : <input type="text" name="phone" value="${member.phone}"></p>
		<p>Age : <input type="text" name="age" value="${member.age}"> </p>
		<p>Job : <input type="text" name="job" value="${member.job}"> </p>
		<p id="photo">Photo : <span id="del" title="photo">${member.oname} X</span></p>
		<input type="button" value="update" id="btn">Join
	</form>
</body>
</html>