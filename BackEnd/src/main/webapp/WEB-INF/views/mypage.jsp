<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./template/header.jsp"%>
</head>

<body id="page-top">
	<!-- Navigation-->
	<%@ include file="./template/navigation.jsp"%>
	<!-- Contact-->
	<section class="page-section" id="contact">
		<div class="container mt-5">
			<div class="text-center">
				<h2 class="section-heading text-uppercase">My Page</h2>
				<h3 class="section-subheading text-muted">your Info</h3>
			</div>
			<!-- * * * * * * * * * * * * * * *-->
			<!-- * * SB Forms Contact Form * *-->
			<!-- * * * * * * * * * * * * * * *-->
			<!-- This form is pre-integrated with SB Forms.-->
			<!-- To make this form functional, sign up at-->
			<!-- https://startbootstrap.com/solution/contact-forms-->
			<!-- to get an API token!-->
			<form id="contactForm" data-sb-form-api-token="API_TOKEN">
				<div class="row align-items-stretch mb-5 justify-content-center">
					<div class="col-md-6">
						<div class="form-group">
							<!-- Name input-->
							<div class="text-left">
								<label class="section-heading text-uppercase">ID</label>
							</div>
							<div class="form-control">${userinfo.id}</div>
						</div>
						<div class="form-group mt-4">
							<div class="text-left">
								<label class="section-heading text-uppercase">password</label>
							</div>
							<div class="form-control">${userinfo.password}</div>
						</div>
						<div class="form-group mt-4">
							<div class="text-left">
								<label class="section-heading text-uppercase">Name</label>
							</div>
							<div class="form-control">${userinfo.name}</div>
						</div>
						<div class="form-group mt-4">
							<div class="text-left">
								<label class="section-heading text-uppercase">email</label>
							</div>
							<div class="form-control">${userinfo.email}</div>
						</div>
						<div class="form-group mt-4">
							<div class="text-left">
								<label class="section-heading text-uppercase">TEL</label>
							</div>
							<div class="form-control">${userinfo.tel}</div>
						</div>
					</div>
				</div>
			</form>
			<div class="container row float-right text-center">
				<div class="col-4">
					<p>
				</div>

				<c:if test="${userinfo.id eq 'admin'}">
					<div class="col-1">
						<form class="form-inline float-right justify-content-end" id="contactForm" action="${root}/user/edit" method="get">
							<input type="hidden" name="action" value="edit"> 
							<input class="btn btn-primary btn-xl text-uppercase float-right" id="submitButton" type="submit" value="관리자정보수정">
						</form>
					</div>
					<div class="col-5">
						<form class="form-inline" id="contactForm" action="${root}/user/list" method="get" style="width: 93%;">
							<input type="hidden" name="action" value="findAll"> 
							<input class="btn btn-primary btn-xl xt-uppercase" id="submitButton" type="submit" value="전체회원조회">
						</form>
					</div>
				</c:if>
				<c:if test="${userinfo.id ne 'admin'}">
					<div class="col-1">
						<form class="form-inline float-right justify-content-end" id="contactForm" action="${root}/user/edit" method="get" style="width: 40px;">
							<input type="hidden" name="action" value="edit"> 
							<input class="btn btn-primary btn-xl text-uppercase float-right" id="submitButton" type="submit" value="회원정보수정">
						</form>
					</div>
					<div class="col-4">
						<form class="form-inline" id="contactForm" action="${root}/user/delete" method="post">
							<input type="hidden" name="action" value="delete"> 
							<input class="btn btn-primary btn-xl xt-uppercase" id="submitButton" type="submit" value="회원탈퇴">
						</form>
					</div>
				</c:if>
				<div class="col-4">
					<p>
				</div>
			</div>

		</div>
	</section>
	<!-- Footer-->

	<%@ include file="./template/footer.jsp"%>