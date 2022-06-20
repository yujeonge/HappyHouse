<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./template/header.jsp"%>
<script>
   let msg = "${msg}";
   if (msg)
      alert(msg);
   $(function() {
      $("#contactForm").submit(function() {
         let id = $("input[name=id]");
         let password = $("input[name=password]");
         let name = $("input[name=name]");
         let email = $("input[name=email]");
         let tel = $("input[name=tel]");
         if (id.val().length == 0) {
            alert("아이디를 입력하여 주세요.");
            id.focus();
            return false;
         } else if (password.val().length == 0) {
            alert("패스워드를 입력하여 주세요.");
            password.focus();
            return false;
         } else if (name.val().length == 0) {
            alert("이름을 입력하여 주세요.");
            name.focus();
            return false;
         } else if (email.val().length == 0) {
            alert("이메일을 입력하여 주세요.");
            email.focus();
            return false;
         } else if (tel.val().length == 0) {
            alert("전화번호를 입력하여 주세요.");
            tel.focus();
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
   <section class="page-section" id="contact" style="height: 850px;">
      <div class="container mt-5">
         <div class="text-center">
            <h2 class="section-heading text-uppercase">Sign up</h2>
         </div>
         <!-- * * * * * * * * * * * * * * *-->
         <!-- * * SB Forms Contact Form * *-->
         <!-- * * * * * * * * * * * * * * *-->
         <!-- This form is pre-integrated with SB Forms.-->
         <!-- To make this form functional, sign up at-->
         <!-- https://startbootstrap.com/solution/contact-forms-->
         <!-- to get an API token!-->
         <form id="contactForm" action="${root}/user/signup" method="post">
            <input type="hidden" name="action" value="regist">
            <div class="row align-items-stretch mb-2 justify-content-center">
               <div class="col-md-6">
                  <div class="form-group ">
                     <!-- Name input-->
                     <div class="text-left">
                        <label class="section-heading text-uppercase">ID</label>
                     </div>
                     <input class="form-control" id="ID" type="text"
                        placeholder="Your ID *" name="id" />
                     <div class="invalid-feedback" data-sb-feedback="ID:required">A
                        ID is required.</div>
                  </div>
                  <div class="form-group">
                     <!-- Email address input-->
                     <div class="text-left">
                        <label class="section-heading text-uppercase">password</label>
                     </div>
                     <input class="form-control" id="PASSWORD" type="password"
                        placeholder="Your password *" name="password" />
                     <div class="invalid-feedback"
                        data-sb-feedback="password:required">An Password is
                        required.</div>
                  </div>
                  <div class="form-group ">
                     <!-- Name input-->
                     <div class="text-left">
                        <label class="section-heading text-uppercase">Name</label>
                     </div>
                     <input class="form-control" id="NAME" type="text"
                        placeholder="Your NAME *" name="name" />
                     <div class="invalid-feedback" data-sb-feedback="ID:required">A
                        NAME is required.</div>
                  </div>
                  <div class="form-group ">
                     <!-- Name input-->
                     <div class="text-left">
                        <label class="section-heading text-uppercase">email</label>
                     </div>
                     <input class="form-control" id="EMAIL" type="text"
                        placeholder="Your email *" name="email" />
                     <div class="invalid-feedback" data-sb-feedback="email:required">A
                        Email is required.</div>
                  </div>
                  <div class="form-group ">
                     <!-- Name input-->
                     <div class="text-left">
                        <label class="section-heading text-uppercase">TEL</label>
                     </div>
                     <input class="form-control" id="TEL" type="text"
                        placeholder="Your TEL *" name="tel" />
                     <div class="invalid-feedback" data-sb-feedback="TEL:required">A
                        TEL is required.</div>
                  </div>
               </div>
            </div>
            <div class="text-center">
               <input class="btn btn-primary btn-xl text-uppercase"
                  id="submitButton" type="submit" value="regist">
            </div>
         </form>
      </div>
   </section>
   <!-- Footer-->
   <footer class="footer py-4">
      <div class="container">
         <div class="row align-items-center">
            <div class="col-lg-4 text-lg-start">Copyright &copy; Your
               Website 2021</div>
            <div class="col-lg-4 my-3 my-lg-0">
               <a class="btn btn-dark btn-social mx-2" href="#!"><i
                  class="fab fa-twitter"></i></a> <a
                  class="btn btn-dark btn-social mx-2" href="#!"><i
                  class="fab fa-facebook-f"></i></a> <a
                  class="btn btn-dark btn-social mx-2" href="#!"><i
                  class="fab fa-linkedin-in"></i></a>
            </div>
            <div class="col-lg-4 text-lg-end">
               <a class="link-dark text-decoration-none me-3" href="#!">Privacy
                  Policy</a> <a class="link-dark text-decoration-none" href="#!">Terms
                  of Use</a>
            </div>
         </div>
      </div>
   </footer>

   <!-- Bootstrap core JS-->
   <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
   <!-- Core theme JS-->
	<script src="http://localhost:8080/happyhouse/dist/js/scripts.js"></script>
	<script src="http://localhost:8080/happyhouse/dist/js/mypage.js"></script>
	<script src="http://localhost:8080/happyhouse/dist/js/edit.js"></script>
   <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
   <!-- * *                               SB Forms JS                               * *-->
   <!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
   <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
   <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
</body>

</html>