<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./template/header.jsp"%>


<script>
	let msg = "${msg}";
	if (msg)
		alert(msg);
</script>

</head>

<body id="page-top">
	<!-- Navigation-->
	<%@ include file="./template/navigation.jsp"%>
	<!-- Masthead-->
	<header class="masthead">
		<div class="container">
			<div class="masthead-subheading">공지사항</div>
		</div>
	</header>

	<div class="notice_content">
		<form id="contactForm" action="${root}/notice/update" method="post">
			<div class="row align-items-stretch mb-5 justify-content-center">
				<div class="col-md-6">
					<div class="form-group">
						<div class="text-left">
							<label class="section-heading text-uppercase">no</label>
						</div>
						<div class="form-control">${notice.no}</div>
					<input class="form-control" name="no" type="hidden"
							value="${notice.no}">
					</div>

					<div class="form-group">
						<div class="text-left">
							<label class="section-heading text-uppercase">title</label>
						</div>
						<div class="form-control">${notice.title}</div>
						<input class="form-control" name="title" type="text"
							placeholder="${notice.title}">
					</div>
					<div class="form-group mt-4">
						<div class="text-left">
							<label class="section-heading text-uppercase">content</label>
						</div>
						<input class="form-control noresize" id="content" name="content"
							type="textarea" placeholder="${notice.content}" maxlength="2000" />
					</div>
				</div>
			</div>
			<div class="row text-center">
				<div class="col-4">
					<p>
				</div>
				<div class="col-4">
					<input class="btn btn-primary btn-xl text-uppercase"
						id="submitButton" type="submit" value="적용">
				</div>
				<div class="col-4">
					<p>
				</div>
			</div>
		</form>

	</div>

</body>


<!-- Footer-->
<%@ include file="./template/footer.jsp"%>