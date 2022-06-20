<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./template/header.jsp"%>

<script>
	let msg = "${msg}";
	if (msg)
		alert(msg);

	function reset() {
		if (confirm("정말로 취소??")) {
			location.href = "${root}/notice?action=searchAll";
		}
		;
	};
</script>

</head>

<body id="page-top">
	<!-- Navigation-->
	<%@ include file="./template/navigation.jsp"%>
	<!-- Masthead-->
	<header class="masthead">
		<div class="container">
			<div class="masthead-subheading">공지사항 등록</div>
		</div>
	</header>
	<div class="content">
		<div class="section notice_content3">
			<div class="board-wrap">
				<div class="row-table-type">
					<form action="${root}/notice/register" id="frm"
						method="post">
						<div class="t-row">
							<div class="t-col">
								<br> <label for="brdItmCdVal" class="label"> <i
									class="icon required"><span class="blind">* </span></i> 공지사항 유형
								</label>
							</div>

							<div class="t-col">
								<select class="select filter-select select2-hidden-accessible"
									name="type" id="brdItmCdVal" tabindex="-1" aria-hidden="true">

									<option value="">공지사항 유형을 선택하세요.</option>


									<option value="전체">전체</option>

									<option value="학습">학습</option>

									<option value="평가">평가</option>

									<option value="운영">운영</option>

									<option value="사이트">사이트</option>
									<option value="기타">기타</option>


								</select><span
									class="select2 select2-container select2-container--default"
									dir="ltr" style="width: 450px;"><span class="selection"><span
										class="select2-selection select2-selection--single"
										role="combobox" aria-haspopup="true" aria-expanded="false"
										tabindex="0" aria-labelledby="select2-brdItmCdVal-container"><span
											class="select2-selection__rendered"
											id="select2-brdItmCdVal-container" title="공지사항 유형을 선택하세요."></span><span
											class="select2-selection__arrow" role="presentation"><b
												role="presentation"></b></span></span></span><span class="dropdown-wrapper"
									aria-hidden="true"></span></span>
							</div>
						</div>
						<div class="br-class">&nbsp;</div>
						<div class="t-row">
							<div class="t-col">
								<label for="titNm" class="label"> <i
									class="icon required"><span class="blind">*</span></i> 제목
								</label>
							</div>
							<div class="t-col">
								<input type="text" class="form-control wide" id="titNm"
									name="title" placeholder="제목을 입력하세요." maxlength="45" value="">
							</div>
						</div>
						<div class="br-class">&nbsp;</div>
						<div class="t-row">
							<div class="t-col">
								<label for="txtCont" class="label"> <i
									class="icon required"><span class="blind">*</span></i> 내용
								</label>
							</div>

							<div class="t-col">
								<div class="textarea">
									<textarea name="content" id="txtCont"
										class="form-control noresize" placeholder="내용을 입력하세요."
										maxlength="2000"></textarea>
								</div>
							</div>
						</div>
						<div class="board-btn-wrap tac">

							<div>
								<div class="col-1">
									<input type="submit"
										class="btn btn-lg btn-primary _btnfileSave" id="btn"
										value="등록">
								</div>
							</div>
							<div>
								<div class="col-4">
									<a href="javascript:reset();"
										class="btn btn-lg btn-primary _btnfileSave">취소</a>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>

	</div>

	<!-- Footer-->
	<%@ include file="./template/footer.jsp"%>