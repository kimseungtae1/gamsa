<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<link type="text/css" rel="stylesheet" href="reg.css">
	<form action="?${_csrf.parameterName}=${_csrf.token}" method="post"
		enctype="multipart/form-data">
		<fieldset>
		<legend>공지사항 수정정보 필드</legend>
			<div class="view_wrap">
				<table class="board">
					<tr>
						<th>번호</th>
						<td>1</td>
					</tr>
					<tr>
						<th>제목</th>
						<td><input type="text" name="title" /></td>
					</tr>
					<tr>
						<th>게시일</th>
						<td>2017-10-18</td>
					</tr>
				</table>
				<div class="page_num">
					<textarea name="content" rows="20" cols="60">${n.content}</textarea>

					<div class="reg-button">
						<a href="">등록</a>
					</div>
					<div class="reg-button">
						<a href="../">취소</a>
					</div>

				</div>
			</div>

		</fieldset>
	</form>

</body>
</html>