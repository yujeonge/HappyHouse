<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ include file="./template/header.jsp"%>
<script type="text/javascript">
var cnt = 1;
$(document).ready(function() {
   
   //회원 목록
   $.ajax({
      url:'${root}/admin/user',  
      type:'GET',
      contentType:'application/json;charset=utf-8',
      dataType:'json',
      success:function(users) {
         makeList(users);
      },
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
   });
   
   $("#registerBtn").click(function() {
      let registerinfo = JSON.stringify({
         "id" : $("#id").val(), 
         "name" : $("#name").val(), 
         "email" : $("#email").val(), 
         "tel" : $("#tel").val()
         });
      $.ajax({
         url:'${root}/admin/user',  
         type:'POST',
         contentType:'application/json;charset=utf-8',
         dataType:'json',
         data: registerinfo,
         success:function(users) {
            $("#id").val('');
            $("#name").val('');
            $("#email").val('');
            $("#tel").val('');
            $("#userRegModal").modal("hide");
            makeList(users);
         },
         error:function(xhr,status,msg){
            console.log("상태값 : " + status + " Http에러메시지 : "+msg);
         }
      });
   });
   
   $(document).on("dblclick", "tr.view", function() {
      let vid = $(this).attr("data-id");
      $.ajax({
         url:'${root}/admin/user/' + vid,  
         type:'GET',
         contentType:'application/json;charset=utf-8',
         success:function(user) {
            $("#vid").text(user.id);
            $("#vname").text(user.name);
            $("#vemail").text(user.email);
            $("#vtel").text(user.tel);
            $("#userViewModal").modal();
         },
         error:function(xhr,status,msg){
            console.log("상태값 : " + status + " Http에러메시지 : "+msg);
         }            
      });         
   });
   
   // 회원 정보 수정 보기
   $(document).on("click", ".modiBtn", function() {
      let mid = $(this).parents("tr").attr("data-id");
      $("#view_" + mid).css("display", "none");
      $("#mview_" + mid).css("display", "");
   });
   
   // 회원 정보 수정 실행
   $(document).on("click", ".modifyBtn", function() {
      let mid = $(this).parents("tr").attr("data-id");
      let modifyinfo = JSON.stringify({
               "id" : mid, 
               "email" : $("#email" + mid).val(), 
               "tel" : $("#tel" + mid).val()
               //"address" : $("#address" + mid).val()
               });
      $.ajax({
         url:'${root}/admin/user',  
         type:'PUT',
         contentType:'application/json;charset=utf-8',
         dataType:'json',
         data: modifyinfo,
         success:function(users) {
            makeList(users);
         },
         error:function(xhr,status,msg){
            console.log("상태값 : " + status + " Http에러메시지 : "+msg);
         }
      });
   });
   
   // 회원 정보 수정 취소
   $(document).on("click", ".cancelBtn", function() {
      let mid = $(this).parents("tr").attr("data-id");
      $("#view_" + mid).css("display", "");
      $("#mview_" + mid).css("display", "none");
   });
   
   // 회원 탈퇴
   $(document).on("click", ".delBtn", function() {
      if(confirm("정말 삭제?")) {
         let delid = $(this).parents("tr").attr("data-id");
         $.ajax({
            url:'${root}/admin/user/' + delid,  
            type:'DELETE',
            contentType:'application/json;charset=utf-8',
            dataType:'json',
            success:function(users) {
               makeList(users);
            },
            error:function(xhr,status,msg){
               console.log("상태값 : " + status + " Http에러메시지 : "+msg);
            }
         });
      }
   });
});

function makeList(users) {
   $("#userlist").empty();
   $(users).each(function(index, user) {
      /*
      let str = "<tr id=\"view_" + user.id + "\" class=\"view\" data-id=\"" + user.id + "\">"
      + "   <td>" + user.id + "</td>"
      + "   <td>" + user.password + "</td>"
      + "   <td>" + user.name + "</td>"
      + "   <td>" + user.email + "</td>"
      + "   <td>" + user.tel + "</td>"
      + "   <td><button type=\"button\" class=\"modiBtn btn btn-outline-primary btn-sm\">수정</button> "
      + "      <button type=\"button\" class=\"delBtn btn btn-outline-danger btn-sm\">삭제</button></td>"
      + "</tr>"
      + "<tr id=\"mview_" + user.id + "\" data-id=\"" + user.id + "\" style=\"display: none;\">"
      + "   <td>" + user.id + "</td>"
      + "   <td><input type=\"text\" name=\"userpwd\" id=\"userpwd" + user.id + "\" value=\"" + user.password + "\"></td>"
      + "   <td>" + user.name + "</td>"
      + "   <td><input type=\"text\" name=\"email\" id=\"email" + user.id + "\" value=\"" + user.email + "\"></td>"
      + "   <td>" + user.tel + "</td>"
      + "   <td><button type=\"button\" class=\"modifyBtn btn btn-primary btn-sm\">수정</button> "
      + "      <button type=\"button\" class=\"cancelBtn btn btn-danger btn-sm\">취소</button></td>"
      + "</tr>";
      */
      let str = `
      <tr id="view_${'${user.id}'}" class="view" data-id="${'${user.id}'}">
         <td>${'${user.id}'}</td>
         <td>${'${user.password}'}</td>
         <td>${'${user.name}'}</td>
         <td>${'${user.email}'}</td>
         <td>${'${user.tel}'}</td>
         <td>
            <button type="button" class="modiBtn btn btn-outline-primary btn-sm">수정</button>
            <button type="button" class="delBtn btn btn-outline-danger btn-sm">삭제</button>
         </td>
      </tr>
      <tr id="mview_${'${user.id}'}" data-id="${'${user.id}'}" style="display: none;">
         <td>${'${user.id}'}</td>
         <td><input type="text" name="userpwd" id="userpwd${'${user.id}'}" value="${'${user.password}'}"></td>
         <td>${'${user.name}'}</td>
         <td><input type="text" name="email" id="email${'${user.id}'}" value="${'${user.email}'}"></td>
         <td>${'${user.tel}'}</td>
         <td>
            <button type="button" class="modifyBtn btn btn-primary btn-sm">수정</button>
            <button type="button" class="cancelBtn btn btn-danger btn-sm">취소</button>
         </td>
      </tr>
      `;
      $("#userlist").append(str);
   });//each
}
</script>

</head>


<body id="page-top">
   <!-- Navigation-->
   <%@ include file="./template/navigation.jsp"%>
   <!-- Masthead-->
   <!-- Contact-->

   <section class="page-section" id="contact">
      <div class="container">
         <div class="text-center">
            <h2 class="section-heading text-uppercase">회원 목록</h2>
            <h3 class="section-subheading text-muted">User info</h3>
         </div>
         <div class="container">
            <table class="table table-dark table-hover" id="admin_table">
               <thead>
                  <tr>
                     <th>no</th>
                     <th>아이디</th>
                     <th>이름</th>
                     <th>이메일</th>
                     <th>전화번호</th>
                     <th>수정/삭제</th>
                  </tr>
               </thead>
               <tbody>

                  <tr>
                     <td><script type="text/javascript">document.write(cnt++);</script></td>
                     <td id="vid"></td>
                     <td id="vname"></td>
                     <td id="vemail"></td>
                     <td id="vtel"></td>
                  </tr>

               </tbody>
            </table>

            <c:if test="${empty users}">
               <p>검색된 회원이 없습니다.</p>
            </c:if>

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
            </div>
         </div>
      </div>
   </section>
   <!-- Footer-->
   <%@ include file="./template/footer.jsp"%>