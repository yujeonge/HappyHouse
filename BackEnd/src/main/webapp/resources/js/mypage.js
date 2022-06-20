window.onload= function (){
 let user_id = document.getElementById("ID");
 let user_password = document.getElementById("PASSWORD");
 let user_email = document.getElementById("EMAIL");
 let user_tel = document.getElementById("TEL");
 let user_name = document.getElementById("NAME");

 
 user_id.innerHTML = JSON.parse(localStorage.getItem("user")).id
 user_password.innerHTML = JSON.parse(localStorage.getItem("user")).password
 user_email.innerHTML = JSON.parse(localStorage.getItem("user")).email
 user_name.innerHTML = JSON.parse(localStorage.getItem("user")).user_name
 user_tel.innerHTML = phoneFomatter(JSON.parse(localStorage.getItem("user")).tel)
}

function phoneFomatter(num,type){

  var formatNum = '';

  if(num.length==11){
      if(type==0){
          formatNum = num.replace(/(\d{3})(\d{4})(\d{4})/, '$1-****-$3');
      }else{
          formatNum = num.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
      }
  }
  return formatNum;

}