<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body class="main">

	<form action="notice-reg" method="post" enctype="multipart/form-data">
		<fieldset>



			<link type="text/css" rel="stylesheet" href="Reg.css">
			<link type="text/css" rel="stylesheet" href="reset.css">


	

			<div>
				<div class="view_wrap" id="view_top">
					<div class="profile">

						<div class="left">
							<h2>제목:
								<input type="text" name="title" value="${n.title}" />
							</h2>
						</div>

						<div class="right">
							<h2>사진작가명</h2>
						</div>
					</div>






					<div class="view_big_img">
					사진
						<img src="">
						<button>사진 업로드</button>


					</div>
					
					
					<h2>설명</h2>
					<div class="detail"><textarea name="content"></textarea></div>
					<br>
						<div class="view_img_btn">
							<ul>
								<li></li>
								<li><button type="button" class="view_down btn_down">
										<img src="/static/common/img/view_icon_01.png" alt="등록"><span
											class="view_btn_txt"></span>
									</button></li>



								<li><button type="button" class="view_sns">
										<img src="/static/common/img/view_icon_02.png" alt="취소">
									</button></li>

							</ul>
						</div>


					</div>









			</div>
		</fieldset>

	</form>

</body>
</html>