function excluir(){
    fetch("/produto/deleteUser",{
        method: "POST",
    })
    .then(response=>{
        if(response.ok){
            alert("Usuario deletado com sucesso, volte sempre");
            return response.text();
        }else{
            alert("Erro ao encontrar usuario, tente novamente mais tarde")
        }
    })

    .then(data=>{
        console.log(data);
        window.location.href = "index.html";
    })
}

const btn = document.querySelector("#sendMod");

btn.addEventListener('click', function(e){
    var senha = document.querySelector("#modifyPassword").value;
    fetch("/produto/modifyUser/1", {
        method:"POST",
        body: senha,
    })
    .then(response=>{
        if(response.ok){
            return response.text();
        }else{
            alert("Senha invalida, tente novamente");
        }
    })
    .then(data=>{
        alert("Senha alterada com sucesso");
        console.log(data);
        window.location.href = "/assets/paginas/perfil.html"
    })
})


const btn2 = document.querySelector("#pedranocaminho");

btn2.addEventListener('click', function(e){
    var username = document.querySelector("#nomemod").value;

    fetch("/produto/modifyUser/0", {
        method: "POST",
        body: username,
    })
    .then(response=>{
        if(response.ok){
            return response.text();
        }else{
            alert("Usuario ja existente, ou invalido, tente novamente");
        }
    })
    .then(data =>{
        console.log(data);
        alert("Modificado com sucesso");
        window.location.href = "/assets/paginas/login.html"
    })
})