<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./template/header.jsp"%>
<script>
	let msg = "${msg}";
	if (msg)
		alert(msg);
	
	function notice_detail(no) {
		location.href = "${root}/notice/detail?no="+no;
	}

	
	
</script>

</head>

<body id="page-top">
	<!-- Navigation-->
	<%@ include file="./template/navigation.jsp"%>
	<!-- Masthead-->
	<header class="masthead">
		<div class="container">
			<div class="masthead-heading text-uppercase mb-2">공지사항</div>
		</div>
	</header>

	<div class="content">

		<c:if test="${userinfo.id eq 'admin'}">
			<div class="text-end">
				<a class="btn btn-danger text-uppercase m-4" id="mvRegisterBtn"
					href="${root}/notice/register">관리자 글작성</a>
			</div>
		</c:if>


		<section class="page-section container pt-2">
			<div class="list-util-wrap" style="display: none;">
				<div class="list-util position-base type-scroll">
					<div class="list-filter-wrap wrap-unit">
						<ul class="list-filter tabs">
							<li class="unit active"><a href="#;">전체</a></li>


							<li class="unit"><a href="#;" rel="1">학습</a></li>

							<li class="unit"><a href="#;" rel="5">평가</a></li>

							<li class="unit"><a href="#;" rel="6">운영</a></li>

							<li class="unit"><a href="#;" rel="2">사이트</a></li>

							<li class="unit"><a href="#;" rel="4">기타</a></li>

						</ul>
						<div class="bar"
							style="transform: translate3d(0px, 0px, 0px); width: 84px;"></div>
					</div>
				</div>
			</div>

			<div class="section notice_content2">
				<div class="board-wrap m-5">
					<table class="table table-hover table-responsive-lg">
						<thead class="thead-dark">
							<tr>
								<th scope="col" class="num type2 hidden-field"><span>번호</span></th>
								<th scope="col" class="link text-center"><span>제목</span></th>
								<th scope="col" class="date"><span>등록일</span></th>
							</tr>
						</thead>
						<c:if test="${empty list}">
							<h3>공지사항이 없습니다.</h3>
						</c:if>

						<c:if test="${!empty list}">
							<c:forEach var="notice" items="${list}">
								<tr>
									<td class="num tac hidden-field"><span>${notice.no}</span></td>
									<td class="link text-xs-center"><a
										style="color: #2d1d89d9; font-weight: bold;" href="#"
										onClick="notice_detail(<c:out value="${notice.no}"/>)">
											[${notice.type}] ${notice.title}</a></td>
									<td class="date tac"><span>${notice.regtime}</span></td>
								</tr>

								<tr>
									<td><a
										href="<%=request.getContextPath() %>/main.do?action=VIEW&noteCode=${notebook.noteCode}">
											${notebook.noteCode}</a></td>
									<td>${notebook.model}</td>
									<td>${notebook.price}</td>
									<td>${notebook.company}</td>
							</c:forEach>
						</c:if>
					</table>
				</div>

				<!-- 페이징 처리 나중에 구현 -->
				<div class="pagination tac row m-5">
					<div class="d-flex justify-content-center">
						<ul class="pagination">
							<li class="page-item"><a class="page-link" href="#">Previous</a></li>
							<li class="page-item active"><a class="page-link" href="#">1</a></li>
							<li class="page-item"><a class="page-link" href="#">2</a></li>
							<li class="page-item"><a class="page-link" href="#">3</a></li>
							<li class="page-item"><a class="page-link" href="#">Next</a></li>
						</ul>
					</div>
					<!-- 제목, 내용으로 검색 나중에 구현 -->
					<div class="list-control tac row">
						<div class="field-set search d-flex justify-content-center">
							<input type="text" class="search-bar" title="검색"
								placeholder="제목, 내용" name="searchWord" id="searchWord" value="">
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="btn btn-primary btn-lg"
								onclick="fnSearch();">검색</button>
						</div>
					</div>

				</div>
			</div>
	</section>
	</div>

	<!-- Footer-->
	<%@ include file="./template/footer.jsp"%>