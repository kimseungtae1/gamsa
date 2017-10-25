<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<main id="main">
		<div class="login-wrap">
			<div class="disp-table">
				<div class="disp-table-cell">
					<form method="post">
						<div class="view-wrap">
							<h1>회원가입.</h1>
							<p class="ep-text">간단한 회원가입으로 더 많은 사진을 이용하세요!</p>
							<div class="login-box">
								<input type="text" name="id" placeholder="이메일"/>
								<input type="text" name="pwd" placeholder="비밀번호"/>
								<input type="text" placeholder="비밀번호확인"/>
								<div>
									<label>
										<input type="checkbox" value="1"/>약관동의
									</label>
									<a href="" class="atag-btn">이용약관</a>
									<a href="" class="atag-btn">개인정보보호정책</a>
								</div>
								<input type="submit" value="회원가입">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</main>
</body>
</html>