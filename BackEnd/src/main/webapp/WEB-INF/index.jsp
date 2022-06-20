<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./template/header.jsp"%>
</head>

<body id="page-top">
	<!-- Navigation-->
	<%@ include file="./template/navigation.jsp"%>
	<!-- Masthead-->
	<header class="masthead">
		<div class="container">
			<div class="masthead-subheading">SSAFY PROJECT!</div>
			<div class="masthead-heading text-uppercase">HAPPY HOUSE</div>
			<a class="btn btn-primary btn-xl text-uppercase" href="#services">데이터
				검색하기</a>
		</div>
	</header>
	<!-- Services-->

	<section class="page-section container" id="services">
	<!-- 시구동 -->
	<div>
		<div class="form-group form-inline row justify-content-center">
			<div class="col-sm">
				<label class="mr-2 ml-3" for="gugun">시도 : </label><select
					class="form-control" id="sido">
					<option value="0">선택</option>
				</select>
			</div>
			<div class="col-sm">
				<label class="mr-2 ml-3" for="gugun">구군 : </label> <select
					class="form-control" id="gugun">
					<option value="0">선택</option>
				</select>
			</div>
			<div class="col-sm">
				<label class="mr-2 ml-3" for="gugun">읍면동 : </label><select
					class="form-control" id="dong">
					<option value="0">선택</option>
				</select>
			</div>
			<div class="col-sm">
				<label class="mr-2 ml-3">금액 : </label>
				<div>
				<input class="mr-2 ml-3" id="target_lower" type="text"
							name="target_lower" maxlength="6" size="6" value="0" />
					원&nbsp;&nbsp;&nbsp;~&nbsp;&nbsp;&nbsp;<input class="mr-2 ml-3" id="target_upper" type="text" name="target_upper" maxlength="6" size="6" value="999999" />원 
					<input class="btn btn-primary btn-small mb-2" id="boundSerach" type="button"	value="검색">
				</div>
				
			</div>
			
		</div>
		</div>

	</section>
	<div class="row justify-content-center mt-2 form-group form-inline">
	
			<div class="mb-1 btn-group col-sm px-5" role="group" id="radioGroup" aria-label="Basic radio toggle button group">
			  <input type="radio" class="btn-check" name="radioName" id="btnradio1" value="가격순" checked>
			  <label class="btn btn-outline-primary" for="btnradio1">가격순</label>
			
			  <input type="radio" class="btn-check" name="radioName" id="btnradio2" value="최신순" autocomplete="off">
			  <label class="btn btn-outline-primary" for="btnradio2">최신순</label>
			
			  <input type="radio" class="btn-check" name="radioName" id="btnradio3" value="이름순" autocomplete="off">
			  <label class="btn btn-outline-primary" for="btnradio3">이름순</label>
			</div>	
			<div class="col-10"></div>
		</div>
		
	<div class="row text-center mb-3">
		<div class="px-5 col-md" id="aptlist">
			<table class="table table-striped">
				<colgroup>
					<col width="100" />
					<col width="150" />
					<col width="*" />
					<col width="120" />
					<col width="120" />
				</colgroup>
				<thead class="thead-dark">
					<tr>
						<th>번호</th>
						<th>아파트이름</th>
						<th class="text-center">주소</th>
						<th>건축연도</th>
						<th>최근거래금액</th>
					</tr>
				</thead>
				<tbody id="searchResult"></tbody>
			</table>
		</div>
		<div class="col-md">
			
		
			<div id="map" style="width: 90%; height: 550px;"></div>
			<script type="text/javascript"
				src="//dapi.kakao.com/v2/maps/sdk.js?appkey=7bc0fbf2c9c4b882b7b0f9c9005a5250&libraries=services"></script>
			<script type="text/javascript" src="resources/js/map.js"></script>
			<script type="text/javascript">
				var juso = "";
				let colorArr = [ "table-primary", "table-success",
						"table-danger" ];
				$(document).ready(
						function() {
							$.get(root + "/map/sido", function(data, status) {
								$.each(data, function(index, vo) {
									$("#sido")
											.append(
													"<option value='" + vo.sidoCode + "'>"
															+ vo.sidoName
															+ "</option>");
								});
							}, "json");
						});
				$(document).on(
						"change",
						"#sido",
						function() {
							juso += $(this).find("option:selected").text()
									+ " ";
							$.get(root + "/map/gugun", {
								sido : $("#sido").val()
							}, function(data, status) {
								$("#gugun").empty();
								$("#gugun").append(
										'<option value="0">선택</option>');
								$.each(data, function(index, vo) {
									$("#gugun").append(
											"<option value='" + vo.gugunCode + "'>"
													+ vo.gugunName
													+ "</option>");
								});
							}, "json");
						});
				$(document).on(
						"change",
						"#gugun",
						function() {
							juso += $(this).find("option:selected").text()
									+ " ";
							$.get(root + "/map/dong", {
								gugun : $("#gugun").val()
							}, function(data, status) {
								$("#dong").empty();
								$("#dong").append(
										'<option value="0">선택</option>');
								$.each(data, function(index, vo) {
									$("#dong")
											.append(
													"<option value='" + vo.dongCode + "'>"
															+ vo.dongName
															+ "</option>");
								});
							}, "json");
						});
				//읍면동 까지 모두 입력시 아파트 조회
				$(document).on("change", "#dong", 
						function() {
							juso += $(this).find("option:selected").text();
							console.log(juso);
							var orderType  = $('input[name=radioName]:checked', '#radioGroup').val();
							$.get(root + "/map/apt", {
										dong : $("#dong").val(),
										target_lower : $("#target_lower").val(),
										target_upper : $("#target_upper").val(),
										orderType : orderType,
										})
												.done(
													function(data, status) {
														$("tbody").empty();
														$.each(data, function(index, vo) {
															console.log(vo);
															let str = "<tr><td>" + vo.aptCode+ "</td>"
																+ "<td>"+ vo.aptName+ "</td>"
																+ "<td>"+ vo.sidoName + " "+ vo.gugunName+ " "+ vo.dongName+ " "+ vo.jibun+ "</td>"
																+ "<td>"+ vo.buildYear+ "</td>"
																+ "<td>"+ vo.recentPrice+ "</td>";
															console.log(str);
															$("tbody").append(str);
															});
															displayMarkers(data);
															juso = "";
														}, "json")
												.fail(
														function() {
															$("tbody").empty();
															$("tbody").append("<tr><td colspan=\"5\">데이터가 없습니다. </td>");
															displayJuso(juso);
															juso = "";
														});
									});
				
				
				//읍면동 까지 입력이 되어 있는 상태에서, 검색 버튼을 누르면 가격 설정, 조회 키워드를 이용해서 다시 조회
								$(document).on("click", "#boundSerach", 
						function() {
							var orderType  = $('input[name=radioName]:checked', '#radioGroup').val();
							juso += $(this).find("option:selected").text();
							console.log(juso);
							$.get(root + "/map/apt", {
										dong : $("#dong").val(),
										target_lower : $("#target_lower").val(),
										target_upper : $("#target_upper").val(),
										orderType : orderType,
										})
												.done(
													function(data, status) {
														$("tbody").empty();
														$.each(data, function(index, vo) {
															console.log(vo);
															let str = "<tr><td>" + vo.aptCode+ "</td>"
																+ "<td>"+ vo.aptName+ "</td>"
																+ "<td>"+ vo.sidoName + " "+ vo.gugunName+ " "+ vo.dongName+ " "+ vo.jibun+ "</td>"
																+ "<td>"+ vo.buildYear+ "</td>"
																+ "<td>"+ vo.recentPrice+ "</td>";
															console.log(str);
															$("tbody").append(str);
															});
															displayMarkers(data);
															juso = "";
														}, "json")
												.fail(
														function() {
															$("tbody").empty();
															$("tbody").append("<tr><td colspan=\"5\">데이터가 없습니다. </td>");
															displayJuso(juso);
															juso = "";
														});
									});
				
				
			</script>
		</div>
	</div>


	<%@ include file="./template/footer.jsp"%>