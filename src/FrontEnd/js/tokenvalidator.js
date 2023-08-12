let z =  localStorage.getItem('RecycleSAsessionToken')
if(z == null){
    window.location.href = "login.html"
}else{
    const url = `http://localhost:8080/api/validate`;
    fetch(url, {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          },
        body:JSON.stringify({"token":z})
    }).then((response)=>{
        if (!response.ok) {
            alert("session expired please login again")
            window.location.href = "login.html"
        }
    });

   
}

function logout(){
    localStorage.clear();
    window.location.href = "login.html"
}
