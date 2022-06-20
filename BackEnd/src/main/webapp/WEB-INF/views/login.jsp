<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./template/header.jsp"%>
<script>
	let msg = "${msg}";
	if (msg)
		alert(msg);
	$(function() {
		$("#contactForm").submit(function() {
			let id = $("input[name=id]");
			let password = $("input[name=password]");
			if (id.val().length == 0) {
				alert("아이디를 입력하여 주세요.");
				id.focus();
				return false;
			} else if (password.val().length == 0) {
				alert("패스워드를 입력하여 주세요.");
				password.focus();
				return false;
			}
		});
	});
</script>
</head>

<body id="page-top">
	<!-- Navigation-->
	<%@ include file="./template/navigation.jsp"%>
	<!-- Contact-->
	<section class="page-section" id="contact">
		<div class="container mt-5">
			<div class="text-center">
				<h2 class="section-heading text-uppercase">Login</h2>
				<h3 class="section-subheading text-muted mb-6">Please put in your ID
					& PW</h3>
			</div>
			<!-- * * * * * * * * * * * * * * *-->
			<!-- * * SB Forms Contact Form * *-->
			<!-- * * * * * * * * * * * * * * *-->
			<!-- This form is pre-integrated with SB Forms.-->
			<!-- To make this form functional, sign up at-->
			<!-- https://startbootstrap.com/solution/contact-forms-->
			<!-- to get an API token!-->
			<form id="contactForm" action="${root}/user/login" method="post">
				<input type="hidden" name="action" value="login">
				<div class="row align-items-stretch mb-5 justify-content-center">
					<div class="col-md-6">
						<div class="form-group ">
							<!-- Name input-->
							<input class="form-control" id="ID" type="text"
								placeholder="Your ID *" name="id" />
							<div class="invalid-feedback" data-sb-feedback="name:required">A
								ID is required.</div>
						</div>
						<div class="form-group">
							<!-- Email address input-->
							<input class="form-control" id="PASSWORD" type="password"
								placeholder="Your password *" name="password" />
							<div class="invalid-feedback"
								data-sb-feedback="password:required">An Password is
								required.</div>
						</div>
					</div>
				</div>
				<div class="text-center">
					<input class="btn btn-primary btn-xl text-uppercase"
						id="submitButton" type="submit" value="LOGIN">
				</div>
			</form>
		</div>
	</section>
	<!-- Footer-->
	<%@ include file="./template/footer.jsp"%>