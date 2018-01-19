<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	 taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>${board} View</h1>
	
	<h1>Title: ${view.title}</h1>
	<h1>Contents: ${view.contents}</h1>
	
	<!-- 첨부파일 a 태그 사용 -->
	<c:forEach items="${fileList}" var="file">
		<a href="../resources/upload/${file.fname}">${file.oname}</a>	
	</c:forEach>
	
	
	
	
</body>
</html>