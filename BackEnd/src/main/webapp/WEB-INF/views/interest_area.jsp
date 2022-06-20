<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./template/header.jsp"%>


<c:if test="${empty userinfo}">
	<script>
 		alert("로그인 사용자만 볼 수 있는 페이지입니다.");
 		location.href = "${root}/index";
</script>
</c:if>
<c:if test="${!empty userinfo}">

	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=7bc0fbf2c9c4b882b7b0f9c9005a5250&libraries=services"></script>
	<script type="text/javascript" src="resources/js/map.js"></script>
	<script type="text/javascript">
	/* 추가 */
	let juso ="";
	var dongCode = "";
	let colorArr = ['table-primary','table-success','table-danger'];
	
	$(document).ready(function(){
		
		/* //회원 목록
		$.ajax({
			url:'${root}/interest/searchAll/'+${userinfo.id},  
			type:'GET',
			contentType:'application/json;charset=utf-8',
			dataType:'json',
			
			error:function(xhr, status, error){
				console.log("상태값 : " + xhr.status + "\tHttp 에러메시지 : " + xhr.responseText);
			},
			statusCode: {
				500: function() {
					alert("서버에러.");
					// location.href = "/error/e500";
				},
				404: function() {
					alert("페이지없다.");
					// location.href = "/error/e404";
				}
			}	
		}); */
		
		
		$.get(root + "/map/sido"
			,function(data, status){
				$.each(data, function(index, vo) {
					$("#sido").append("<option value='"+vo.sidoCode+"'>"+vo.sidoName+"</option>");
				});
			}
			, "json"
		);
	});
	$(document).on("change", "#sido", function() {
		juso += $(this).find("option:selected").text() + " ";
		$.get(root + "/map/gugun"
				,{sido: $("#sido").val()}
				,function(data, status){
					$("#gugun").empty();
					$("#gugun").append('<option value="0">선택</option>');
					$.each(data, function(index, vo) {
						$("#gugun").append("<option value='"+vo.gugunCode+"'>"+vo.gugunName+"</option>");
					});
				}
				, "json"
		);
	});
	$(document).on("change", "#gugun", function() {
		 juso += $(this).find("option:selected").text() + " ";
		$.get(root + "/map/dong"
				,{gugun: $("#gugun").val()}
				,function(data, status){
					$("#dong").empty();
					$("#dong").append('<option value="0">선택</option>');
					$.each(data, function(index, vo) {
						$("#dong").append("<option value='"+vo.dongCode+"'>"+vo.dongName+"</option>");
					});
				}
				, "json"
		);
	});
	
	/* $(document).on("change", "#dong", function() {
		juso += $(this).find("option:selected").text();
	} */
	
	$(document).on("click", "#btn-list", function () {
		juso += $("#dong").find("option:selected").text();
		let dongCode = $("#dong").val();
		registerAjax(dongCode);
		/* 추가하고난뒤에 비우는작업 */
		alert("관심지역이 추가되었습니다.")
		juso = "";
    });
	
	function registerAjax(dongCode){
		let interestAreaDto = JSON.stringify({
			"id" : "${userinfo.id}", 
			"dongCode" : dongCode,
			"juso" : juso
		   });
		$.ajax({
			url:'${root}/interest/register',  
			type:'POST',
			contentType:'application/json;charset=utf-8',
			dataType:'json',
			data: interestAreaDto,
			error:function(xhr,status,msg){
				console.log("상태값 : " + status + " Http에러메시지 : "+msg);
			}
		});
	}
	
	/* 삭제 */
	$(document).on("click", ".deleteArea", function () {
    	//삭제 구현하고 다시 돌아오기
    	/* $.ajax({
			url:'${root}/interest/delete/${userinfo.id}/'+$(this).attr('id'),  
			type:'DELETE',
			contentType:'application/json;charset=utf-8',
			dataType:'json',
			error:function(xhr,status,msg){
				console.log("상태값 : " + status + " Http에러메시지 : "+msg);
			}
		}); */
		location.href = "${root}/interest/delete/${userinfo.id}/"+$(this).attr('id');
		alert("삭제되었습니다.");
    });
	
	
	/* 지도 */
	
	$(function () {
	
      var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
      var options = { //지도를 생성할 때 필요한 기본 옵션
	    center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
	    level: 3 //지도의 레벨(확대, 축소 정도)
      };
  	
      var map = new kakao.maps.Map(container, options);
      
    });
	$(document).on("click", ".moveArea", function () {
    	//지도 이동 구현하기
    	const juso = $(this).attr('id');
    	
        var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
        var options = { //지도를 생성할 때 필요한 기본 옵션
  	    center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
  	    level: 5 //지도의 레벨(확대, 축소 정도)
        };
    	
    	var map = new kakao.maps.Map(container, options);
    	
    	// 주소-좌표 변환 객체를 생성합니다
    	var geocoder = new kakao.maps.services.Geocoder();

    	// 주소로 좌표를 검색합니다
    	geocoder.addressSearch(juso, function(result, status) {

    	    // 정상적으로 검색이 완료됐으면 
    	     if (status === kakao.maps.services.Status.OK) {

    	        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

    	        // 결과값으로 받은 위치를 마커로 표시합니다
    	        var marker = new kakao.maps.Marker({
    	            map: map,
    	            position: coords
    	        });

    	        // 인포윈도우로 장소에 대한 설명을 표시합니다
    	        var infowindow = new kakao.maps.InfoWindow({
    	        	 content: '<div style="width:160px;text-align:center;padding:5px 0;">'+juso+'</div>'
    	        });
    	        infowindow.open(map, marker);

    	        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
    	        map.setCenter(coords);
    	    } 
    	});    
    	
    	
    });
	
  </script>
	</head>

	<body id="page-top">
		<!-- Navigation-->
		<%@ include file="./template/navigation.jsp"%>
		<!-- Masthead-->
		<header class="masthead">
			<div class="container">
				<div class="masthead-subheading">SSAFY PROJECT!</div>
				<div class="masthead-heading text-uppercase">HAPPY HOUSE</div>
				<a class="btn btn-primary btn-xl text-uppercase" id="searchAll"
					href="#services">관심 지역 조회</a>
			</div>
		</header>
		<!-- Services-->
		<section class="page-section">
			<div class="container-fluid">
				<div class="border border-secondary text-center m-3 p-2">
					<h4 class="section-heading text-uppercase">관심 지역 설정</h4>

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
					</div>

					<button type="button" class="mt-2 btn btn-primary" id="btn-list">추가하기</button>
				</div>
			</div>
		</section>
		<section class="page-section" id="services">
		<div class="container-fluid">
			<div class="row text-center">
				<c:if test="${!empty list}">
					<div class="col-md-3">
						<h3 id="area_list">관심 지역 목록</h3>
						<c:forEach var="area" items="${list}" varStatus="cnt">
							<script>
						location.href = "#services";
						</script>
							<div class="border-top m-4 b-4">
							<h5>
								<a class="moveArea text-muted" id="${area.juso}">
									${area.juso}
											<button type="button" class="deleteArea mt-2 btn btn-outline-primary" id="${area.dongCode}">삭제</button>
									
								</a>
								</h5>
							</div>
						</c:forEach>
					</div>
					<div class="col-md-9">
						<div id="map" style="width: 98%; height: 600px;"></div>
					</div>
				</c:if>
			</div>
</div>
		</section>
		<%@ include file="./template/footer.jsp"%>
</c:if>
