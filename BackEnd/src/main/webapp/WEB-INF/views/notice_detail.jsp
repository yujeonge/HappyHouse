<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./template/header.jsp"%>
<script>
	let msg = "${msg}";
	if (msg)
		alert(msg);
	
	function notice_update(no) {
		location.href = "${root}/notice/update?no="+no;
	}
	
	function notice_delete(no) {
		location.href = "${root}/notice/delete?no="+no;
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
	
	<section class="container pt-2">
	<div class="notice_content">
		<div class="subject">
			<span> 글번호 : ${notice.no}</span> <span> 작성일 :
				${notice.regtime}</span>
		</div>
		<div>
		<div class="content_span">
			<h5>
				<span class="title_span" style="background-color: #b2bdf7;
    border: 5px solid white;">&nbsp;</span> 제목 &nbsp;&nbsp;
				[${notice.type}] ${notice.title}
			</h5>
			</div>
			<div class="br-class">&nbsp;</div>

			<div class="content_span2">
				<h4>
					<span class="title_span" style="background-color: #b2bdf7;
    border: 5px solid white;">&nbsp;</span> 내용 <div class="br-class">&nbsp;</div>
				</h4>
				<pre class="content">${notice.content}</pre>
			</div>
		</div>
	</div>
	<br>
		<div class="btn_area" >
			<button type="button" style="background-color: #5b80ef6e;" class="btn button_style3" id="btn-list"
				onclick="location.href='${root}/notice/list'">목록으로</button>
	<c:if test="${userinfo.id eq 'admin'}">	
			<button type="button" style="background-color: #5b80ef6e;" class="button_style3" id="btn-list"
				onclick="notice_update(<c:out value="${notice.no}"/>)">수정하기</button>
			<button type="button" style="background-color: #5b80ef6e;" class="button_style3" id="btn-list"
				onclick="notice_delete(<c:out value="${notice.no}"/>)">삭제하기</button>
		</div>
	</c:if>
	</section>
</body>


<!-- Footer-->
<%@ include file="./template/footer.jsp"%>