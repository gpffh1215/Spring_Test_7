<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		$(".del").click(function(){
			var del= $(this);
			var fnum= $(this).attr("title");
			var fname=$(this).attr("id");
			var check=confirm("삭제시 복구가 불가능 합니다."); //두개 중에 하나를 주는 것(확인, 취소)
			if(check){
				$.ajax({
					url: "../file/fileDelete",//request address
					type= "GET",
					data={
						fnum:fnum, // parameter: variable
						id:fname
					}, // it include many things so that using parenthesis
					success: function(data){
						if(data.trim()==1){
							$(del).prev().remove();
							$(del).remove();
							i--;
						} // trim: delete space
					}
				}); //get and post both
			}
		});
			
		var i =${fn:length(view.files)};
	
		$("#fileButton").click(function(){
			if(i<5){
			var ex= $("#ex").html();
			$("#result").append(ex);
			i++;
			}
		});
		
		$("#result").on("click", ".X", function(){
			$(this).prev().remove();
			$(this).remove();
			i--;
		});
			
		
		
			/* 
		$("#fileButton").click(function(){
			
			$("#result").append('<input type="file" name="file"><span class="X">X</span>');
			i++;
		} else {
			alert("5개만 가능");
		}
		}); */
		
	});
</script>
<style type="text/css">
	#ex{
	display: none;
	}
	.X, .del{
	cursor: pointer;
	}
</style>
</head>
<body>
	<h1>${board}Update</h1>
	<form action="${board}Update" method="post" enctype="multipart/form-data">
	<input type="hidden" name="num" value="${view.num}">
	<p>Writer: <input type="text" value="${view.wirter}" disabled="disabled"></p>
	<p>Title: <input type="text" value="${view.title}" name="title"></p>
	<p>Contents:<textarea name="contents">${view.contents}</textarea></p>
	<p><input type="button" value="FileAdd" id="fileButton"></p>
	<div id="result">
	<c:forEach items="${view.files}" var="file">
		<p><input type="text" value="${file.oname}" readonly="readonly"><span class="del" title="${file.fnum}" id="${file.fname}">X</span></p>
	</c:forEach>
	</div>
	
	<input type="submit" value="Update">

	</form>
	<div id="ex">
			<input type="file" name="file"><span class="X">X</span>
	</div>


</body>
</html>
