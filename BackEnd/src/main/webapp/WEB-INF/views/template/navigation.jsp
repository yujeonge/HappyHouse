<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
	<div class="container">
		<a class="navbar-brand" href="${root}/"><label alt="...">HAPPYHOUSE</label></a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			Menu <i class="fas fa-bars ms-1"></i>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav text-uppercase ms-auto py-4 py-lg-0">
				<li class="nav-item"><a class="nav-link m-2"
					href="${root}/notice/list">공지사항</a></li>
				<li class="nav-item"><a class="nav-link m-2" href="#">오늘의뉴스</a></li>
				<li class="nav-item"><a class="nav-link m-2" href="#">주변탐방</a></li>
				<li class="nav-item">
				<a class="nav-link m-2"	href="${root}/interestarea">관심지역 설정</a></li>
				<li class="nav-item">
         <c:if test="${empty userinfo}">
            <a class="nav-link m-2"   href="${root}/interestarea/searchAll/null">관심지역 둘러보기</a></li>
         </c:if>
         <c:if test="${!empty userinfo}">
            <a class="nav-link m-2"   href="${root}/interestarea/searchAll/${userinfo.id}">관심지역 둘러보기</a></li>
         </c:if>         
				
					
			</ul>
			<c:if test="${empty userinfo}">
				<div id="header_nav_confirm_off">
					<button type="button" class="btn btn-outline-primary m-2">
						<a href="${root}/user/login" style="text-decoration: none;">로그인</a>
					</button>
					<button type="button" class="btn btn-outline-primary m-2">
						<a href="${root}/user/signup" style="text-decoration: none;">회원가입</a>
					</button>
				</div>
			</c:if>
			<c:if test="${!empty userinfo}">
				<div id=user_name>&nbsp;&nbsp;&nbsp;[${userinfo.name}]님 &nbsp;</div>
				<form id="contactForm" action="${root}/user/logout" method="get">
					<input type="hidden" name="action" value="logout"> <input
						class="btn btn-outline-primary m-2" id="submitButton"
						type="submit" value="로그아웃">
				</form>
				<form id="contactForm" action="${root}/user/view" method="get">
					<input type="hidden" name="action" value="view"> <input
						class="btn btn-outline-primary m-2" id="submitButton"
						type="submit" value="회원정보">
				</form>

			</c:if>
		</div>
	</div>
</nav>