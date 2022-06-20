<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./template/header.jsp"%>
</head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	/* $(function(){
		$("#bupd").click(function(){
			//console.log("click");
			//var userinfo = window.sessionStorage.getItem("userinfo");
			var uid = ${userinfo.id};
			var uname = ${userinfo.id};
			//console.log(uid);
			let modifyinfo = JSON.stringify({
				"id" : uid,
				"password" : $("#password").val(),
				"name" : uname,
				"email" : $("#email").val(),
				"tel" : $("#tel").val()
			});
			$.ajax({
				url: "${root}/user",
				type: "post",
				contentType: "application/json;charset=utf-8",
				data : modifyinfo,
				//data: JSON.stringify(getFormData()),
				//data: JSON.stringify({num: $("#num").val(), salary: $("#salary").val()}),
				success: function(data){
					//modify(data);
					//console.log("수정 성공");
				},
				error: function(xhr,status,msg){
					//alert("요청 실패");
				}
			});
		});
	}); */ 
	
</script>
<body id="page-top">
	<!-- Navigation-->
	<%@ include file="./template/navigation.jsp"%>
	<!-- Contact-->
	<section class="page-section" id="contact">
		<div class="container mt-5">
			<div class="text-center">
				<h2 class="section-heading text-uppercase">Edit page</h2>
				<h3 class="section-subheading text-muted">Can edit your Info</h3>
			</div>
			<!-- * * * * * * * * * * * * * * *-->
			<!-- * * SB Forms Contact Form * *-->
			<!-- * * * * * * * * * * * * * * *-->
			<!-- This form is pre-integrated with SB Forms.-->
			<!-- To make this form functional, sign up at-->
			<!-- https://startbootstrap.com/solution/contact-forms-->
			<!-- to get an API token!-->
			<%-- <form id="contactForm"
				action="${root}/user?action=update&id=${userinfo.id}&name=${userinfo.name}"
				method="post"> --%>
			<form id="contactForm"
				action="${root}/user/edit"
				method="post">
			<!-- <form id="contactForm"> -->
				<div class="row align-items-stretch mb-4 justify-content-center">
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
							<input class="form-control" id="password" name="password"
								type="password" placeholder="${userinfo.password}" />
						</div>
						<div class="form-group mt-4">
							<div class="text-left">
								<label class="section-heading text-uppercase">Name</label>
							</div>
							<div class="form-control">${userinfo.name}</div>
						</div>
						<div class="form-group mt-4">
							<div class="text-left">
								<label class="section-heading text-uppercase" id="password">email</label>
							</div>
							<input class="form-control" id="email" name="email" type="text"
								placeholder="${userinfo.email}">
						</div>
						<div class="form-group mt-4">
							<div class="text-left">
								<label class="section-heading text-uppercase"">TEL</label>
							</div>
							<input class="form-control" id="tel" name="tel" type="text"
								placeholder="${userinfo.tel}">
						</div>
					</div>
				</div>
				<div class="row text-center">
					<div class="col-4">
						<p>
					</div>
					<div class="col-4">
						<!-- <button id="bupd" type="button" class="modifyBtn btn btn-primary btn-sm">적용</button> -->
						<input class="btn btn-primary btn-xl text-uppercase"
							id="submitButton" type="submit" value="적용">
					</div>
					<div class="col-4">
						<p>
					</div>
				</div>
			</form>
		</div>
	</section>
	<!-- Footer-->
	<%@ include file="./template/footer.jsp"%>