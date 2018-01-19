<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		var message='${message}';
		if(message != ''){
			alert(message);
		}
		
		$(".page").click(function() {
			var cur =$(this).attr("title");
			document.frm.curPage.value=cur;
			document.frm.search.value='${page.search}';
			document.frm.kind.value='${page.kind}';
			document.frm.submit();
		});
		
	});
</script>
<style type="text/css">
	.page{
		cursor: pointer;
	}
</style>
</head>
<body>
	<h1>${board}List</h1>
	<div>
	<form name="frm" action="./${board}List">
		<input type="hidden" name="curPage" value="1">
		<select name="kind">
			<option value="title">TITLE</option>
			<option value="writer">WRITER</option>
			<option value="contents">CONTENTS</option>
		</select>
		<input type="text" name="search">
		<input id="btn" type="button" value="SEARCH">
	</form>
	</div>
	
	<table>
		<tr>
			<td>NUM</td>
			<td>TITLE</td>
			<td>WRITER</td>
			<td>DATE</td>
			<td>HIT</td>
		</tr>
		<c:forEach items="${list}" var="dto">
			<tr>
			<td>
				<a href="${board}View?num=${dto.num}">${dto.title}</a>
				</td>
				<td>${dto.writer}</td>
				<td>${dto.reg_date}</td>
				<td>${dto.hit}</td>
			</tr>
		</c:forEach>		
	</table>
	
	<div>
		<c:if test="${page.curBlock > 1}">
			<span class="page" title="${page.startNum-1}">[이전]</span>
		</c:if>
		<c:forEach begin="${page.startNum}" end="${page.lastNum}" var="i">
			<span class="page" title="${i}">${i}</span>
		</c:forEach>
		<c:if test="${page.curBlock < page.totalBlock}">
			<span class="page" title="${page.lastNum+1}">[다음]</span>
		</c:if>
	</div>
	<div>
		<a href="./${board}Write">Write</a>	
	</div>
	
	
	
	

</body>
</html>