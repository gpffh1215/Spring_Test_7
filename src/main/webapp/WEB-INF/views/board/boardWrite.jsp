<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript"
	src="../resources/SE2/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
	$(function() {
		var i = 0;

		$("#fileButton").click(function() {
			if (i < 5) {
				var ex = $("#ex").html();
				$("#result").append(ex);
				i++;
			}
		});

		$("#result").on("click", ".X", function() {
			$(this).remove();
			$(this).prev().remove();
			i--;
		});

		//전역변수
		var obj = [];
		//스마트에디터 프레임생성
		nhn.husky.EZCreator.createInIFrame({
			oAppRef : obj,
			elPlaceHolder : "contents",
			sSkinURI : "../resources/SE2/SmartEditor2Skin.html",
			htParams : {
				// 툴바 사용 여부
				bUseToolbar : true,
				// 입력창 크기 조절바 사용 여부
				bUseVerticalResizer : true,
				// 모드 탭(Editor | HTML | TEXT) 사용 여부
				bUseModeChanger : true,
			}
		});
		//전송버튼
		$("#save").click(function() {
			//id가 smarteditor인 textarea에 에디터에서 대입
			obj.getById["contents"].exec("UPDATE_CONTENTS_FIELD", []);
			//폼 submit
			$("#frm").submit();
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
#ex {
	display: none;
}

.X {
	cursor: pointer;
}
</style>
</head>
<body>
	<h1>${board}Write</h1>
	<form id="frm" action="./${board}Write" method="post"
		enctype="multipart/form-data">
		<p>
			WRITER: <input type="text" name="writer" value="${writer}">
		</p>
		<p>
			TITLE: <input type="text" name="title" value="${title}">
		</p>
		<p>
			CONTENTS:
			<textarea id="contents" name="contents"></textarea>
		</p>
		<p>
			<input type="button" value="FileAdd" id="fileButton">
		</p>
		<div id="result"></div>
		<div id="ex">
			<input type="file" name="file"><span class="X">X</span>
		</div>
		<input type="button" value="Write" id="save">
	</form>

</body>
</html>