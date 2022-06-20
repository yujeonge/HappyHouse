window.onload= function (){

  document.getElementById("ID").setAttribute("value",JSON.parse(localStorage.getItem("user")).id);
  document.getElementById("PASSWORD").setAttribute("value",JSON.parse(localStorage.getItem("user")).password);
  document.getElementById("NAME").setAttribute("value",JSON.parse(localStorage.getItem("user")).user_name);
  document.getElementById("EMAIL").setAttribute("value",JSON.parse(localStorage.getItem("user")).email);
  document.getElementById("TEL").setAttribute("value",JSON.parse(localStorage.getItem("user")).tel);
}

function edit(){

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
    location.href = './mypage.jsp'
    alert("수정되었습니다.");
    
}

function withdraw(){
  localStorage.clear();
  location.href='index.jsp';
  alert("탈퇴되었습니다.")
}