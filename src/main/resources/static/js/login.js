function login(f){
    let id = document.getElementById("id").value;
    let password = document.getElementById("password").value;
    if(id == ""){
        alert("아이디를 입력해주세요");
        return;
    }
    if(password == ""){
        alert("비밀번호를 입력해주세요");
        return;
    }
    f.submit();
}