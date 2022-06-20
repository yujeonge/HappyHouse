/*!
* Start Bootstrap - Agency v7.0.10 (https://startbootstrap.com/theme/agency)
* Copyright 2013-2021 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-agency/blob/master/LICENSE)
*/
//
// Scripts
// 
window.onload = function(){
   let check = localStorage.getItem('logincheck');
   if(!check){
      document.getElementById('header_nav_confirm_off').style.display = "inline";
      document.getElementById('header_nav_confirm_on').style.display = "none";
   }else{
      document.getElementById('header_nav_confirm_off').style.display = "none";
      document.getElementById('header_nav_confirm_on').style.display = "inline";
      
   }
}

window.addEventListener('DOMContentLoaded', event => {

    // Navbar shrink function
    var navbarShrink = function () {
        const navbarCollapsible = document.body.querySelector('#mainNav');
        if (!navbarCollapsible) {
            return;
        }
        if (window.scrollY === 0) {
            navbarCollapsible.classList.remove('navbar-shrink')
        } else {
            navbarCollapsible.classList.add('navbar-shrink')
        }

    };

    // Shrink the navbar 
    navbarShrink();

    // Shrink the navbar when page is scrolled
    document.addEventListener('scroll', navbarShrink);

    // Activate Bootstrap scrollspy on the main nav element
    const mainNav = document.body.querySelector('#mainNav');
    if (mainNav) {
        new bootstrap.ScrollSpy(document.body, {
            target: '#mainNav',
            offset: 74,
        });
    };

    // Collapse responsive navbar when toggler is visible
    const navbarToggler = document.body.querySelector('.navbar-toggler');
    const responsiveNavItems = [].slice.call(
        document.querySelectorAll('#navbarResponsive .nav-link')
    );
    responsiveNavItems.map(function (responsiveNavItem) {
        responsiveNavItem.addEventListener('click', () => {
            if (window.getComputedStyle(navbarToggler).display !== 'none') {
                navbarToggler.click();
            }
        });
    });

});

function enter(){
  let id = document.getElementById("ID").value;
  let password = document.getElementById("PASSWORD").value;

  let enter_id = JSON.parse(localStorage.getItem("user")).id;
  let enter_pass = JSON.parse(localStorage.getItem("user")).password;

  console.log(id);
  if(!id){
    alert("아이디를 입력해주십시오");
    id.focus();
    return false;
  }
  if(!password){
    alert("비밀번호를 입력해주십시오");
    password.focus();
    return false;
  }

  if(id == enter_id && password == enter_pass){
    location.href="index.jsp";
    alert("로그인 성공");
  }else{
    alert("로그인 실패");
    return false;
  }
  
  let check_login = true;

  localStorage.setItem('logincheck',check_login);
  
}

function logout(){
  localStorage.removeItem('logincheck');
  location.href="index.jsp"
  alert("로그아웃 성공");
}

function check(){

  let id = document.getElementById("ID").value;
  let password = document.getElementById("PASSWORD").value;
  let user_name = document.getElementById("NAME").value;
  let email = document.getElementById("EMAIL").value;
  let tel = document.getElementById("TEL").value;

  console.log(id);
  if(!id){
    alert("아이디를 입력해주십시오");
    id.focus();
    return false;
  }
  if(!password){
    alert("비밀번호를 입력해주십시오");
    password.focus();
    return false;
  }
  if(!user_name){
    alert("이름을 입력해주십시오");
    user_name.focus();
    return false;
  }
  if(!email){
    alert("이메일을 입력해주십시오");
    email.focus();
    return false;
  }
  if(!tel){
    alert("전화번호를 입력해주십시오");
    tel.focus();
    return false;
  }

  let user_info ={
    id : id,
    password : password,
    user_name : user_name,
    email : email,
    tel : tel 
    }
  
    localStorage.setItem("user",JSON.stringify(user_info));
    location.href = 'index.jsp';
    alert("가입되었습니다.");
    
}

function withdraw(){
  localStorage.clear();
  location.href='index.jsp';
  alert("탈퇴되었습니다.")
}