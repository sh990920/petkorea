function boardAdd(f){
    let title = document.getElementById("title").value;
    let writer = document.getElementById("writer").value;
    let content = document.getElementById("content").value;
    if(title == ""){
        alert("제목을 적어주세요");
        return;
    }
    if(writer == ""){
        location.reload();
        return;
    }
    if(content == ""){
        alert("내용을 작성해주세요");
        return;
    }
    f.submit();
}