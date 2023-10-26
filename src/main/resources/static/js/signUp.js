function signUp(){
    let signUp_id = document.getElementById("signUp_id").value;
    let signUp_email = document.getElementById("signUp_email").value;
    let signUp_password = document.getElementById("signUp_password").value;
    const pattern = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-za-z0-9\-]+/;
    if(signUp_id == ""){
        alert("아이디를 입력해주세요");
        return;
    }
    if(signUp_email == ""){
        alert("이메일을 입력해주세요");
        return;
    }
    if(pattern.test(signUp_email) === false){
        alert("이메일 형식에 맞게 다시 작성해주세요.");
        return;
    }
    if(signUp_password == ""){
        alert("비밀번호를 입력해주세요");
        return;
    }
    let url = "/loginPage/signUp/";
    let param = "id=" + signUp_id +
        "&password=" + signUp_password +
        "&email=" + signUp_email;
    sendRequest(url, param, resultSignUp, "POST");
}
function signInChange(){
    document.getElementById("signIn").click();
    document.getElementById("signUp_id").value = "";
    document.getElementById("signUp_email").value = "";
    document.getElementById("signUp_password").value = "";
}

function resultSignUp(){
    if ( xhr.readyState == 4 && xhr.status == 200 ) {
        let data = xhr.responseText;

        if (data == "exist") {
            alert("이미 가입된 회원정보가 있습니다.\n로그인 혹은 아이디/비밀번호 찾기를 이용해 주세요");
            signInChange();
        } else {
            alert("가입이 완료되었습니다.");
            signInChange();
        }
    }
}