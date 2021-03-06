<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body class="main">




	<link type="text/css" rel="stylesheet" href="Detail.css">
	<link type="text/css" rel="stylesheet" href="reset.css">


	<!--화살표-->
	<a href="images/l.png" class="arrow prev"> <img src="images/l.png" />
		<!-- alt="이전 이미지" --></a>
	<br>
	<a href="images/r.png" class="arrow next"> <img src="images/r.png" />
		<!--  alt="다음 이미지" --></a>

	<div>
		<div class="view_wrap" id="view_top">
			<div class="profile">

				<div class="left">
					<h2>${n.title}</h2>
				</div>

				<div class="right">
					<h2>${n.writerId}</h2>
				</div>
			</div>






			<div class="view_big_img">
				<img
					src="http://mblogthumb4.phinf.naver.net/20160927_255/happy_day555_1474946998707Gp84L_JPEG/eaed2ca9aeaf949ef94e70f36ce74fcd.jpeg?type=w2">
				
				<div class="view_img_btn">
					<ul>
						<li></li>
						<li><button type="button" class="view_down btn_down">
								<img src="/static/common/img/view_icon_01.png" alt="다운로드">dd<span
									class="view_btn_txt"></span>
							</button></li>

						<li><input type="checkbox" id="photo_view_like"
							class="hidden view_like" value="좋아요"> <label
							for="photo_view_like" class=""> <span class="check_box"></span>
						</label></li>
						<li><input type="checkbox" id="photo_view_wish"
							class="hidden view_wish" value="wish"> <label
							for="photo_view_wish" class=""> <span class="check_box"></span>
						</label></li>

						<li><a href="/map/view/3598" target="_blank" class="viewMap"><img
								src="/static/common/img/view_icon_04.png" alt="촬영 포인트 정보"></a></li>


						<li><button type="button" class="view_sns">
								<img src="/static/common/img/view_icon_02.png" alt="공유하기">
							</button></li>

					</ul>
				</div>

			</div>
			<div class="detail">이 사진은 잘 찍은 사진입니다!!</div>

			<div class="small_img">



				<a href="/photo/view/3603" title="img_01"> <img
					src="http://mblogthumb4.phinf.naver.net/20160927_255/happy_day555_1474946998707Gp84L_JPEG/eaed2ca9aeaf949ef94e70f36ce74fcd.jpeg?type=w2"
					alt="img_01"></a> <a href="/photo/view/3603" title="img_01">
					<img
					src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTLqD7ffn6mEUrO8KShRa1U9apMjvOpnUa1HNBU2B6hwOPMm2di"
					alt="img_01">
				</a>




			</div>

			<button class="more_btn"><h2>더보기</h2></button>
			</div>

		</div>


		<div class="view_detail">
			<div class="wrap">
				<section>
					<h2>세부정보</h2>
					<div class="table">
						<table>
							<colgroup>
								<col class="w015">
								<col class="w027">
								<col class="w015">
								<col class="">
							</colgroup>
							<tbody>
								<tr>
									<th>카메라</th>
									<td colspan="3">SM-G930K</td>
								</tr>
								<!-- 								
								<tr>
									<th>렌즈</th>
									<td>E 18-55mm</td>
									<th>업로드</th>
									<td>약 14시간 전</td>
								</tr>
								 -->
								<tr>
									<th>초첨거리</th>
									<td>4.2</td>
									<th>카테고리</th>
									<td>풍경</td>
								</tr>
								<tr>
									<th>셔터속도</th>
									<td>1/5264 s</td>
									<th>담은날짜</th>
									<td>Oct 7, 2017</td>
								</tr>
								<tr>
									<th>조리개</th>
									<td>f/1.7</td>
									<th>라이센스</th>
									<td>FREE LICENSE</td>
								</tr>
								<tr>
									<th>ISO/필름</th>
									<td>40</td>
									<th>사진사이즈</th>
									<td>4032 x 2268</td>
								</tr>
								<tr>
									<th>해상도</th>
									<td>72dpi</td>
									<th>컬렉션명</th>
									<td>하늘&amp;구름</td>
								</tr>
							</tbody>
						</table>
					</div>
					<p></p>
					<h2>Creative Commons License</h2>
					<div class="table">
						<table>
							<colgroup>
								<col class="w015">
								<col class="w027">
								<col class="w015">
								<col class="">
							</colgroup>
							<tbody>
								<tr>
									<th>표시방식</th>

									<td colspan="3"><a href="/company/license" target="_blank">CC0
											( CC0 Public Domain )</a></td>

								</tr>
								<tr>
									<th>용도범위</th>
									<td colspan="3">상업적 용도로 사용 가능.</td>
								</tr>
								<tr>
									<th>출처표시</th>

									<td colspan="3">출처 안 밝혀도 됨.</td>

								</tr>
								<tr>
									<th></th>
									<td colspan="3"><a href="/company/license" target="_blank">
											<u>라이선스 자세히보기</u>
									</a></td>
								</tr>

							</tbody>
						</table>
					</div>
					<p></p>

					<div class="tagWrap"></div>
				</section>

				<section>
					<div class="tagWrap"></div>
				</section>
			</div>
		</div>

		<div class="talk"></div>

		<div class="random_list">
			<div class="rand_listWrap listWrap">
				<div class="random_head">
					<h3 class="aritadsb">유사한 이미지</h3>
					<strong class="eng aritadl">Other photos you may like</strong>
				</div>
				<ul class="viewRand_list">


					<li><a href="">
							<div>
								<img src="">

							</div>


					</a></li>
					<li><a href="">
							<div>
								<img src="">

							</div>


					</a></li>
					<li><a href="">
							<div>
								<img src="">

							</div>


					</a></li>
					<li><a href="">
							<div>
								<img src="">

							</div>


					</a></li>
					<li><a href="">
							<div>
								<img src="">

							</div>


					</a></li>






				</ul>
			</div>
		</div>


	</div>


</body>
</html>