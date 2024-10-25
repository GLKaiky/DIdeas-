const form = document.querySelector("#formulario");
var btn = document.querySelector("#enviar");

function enviar(){
        const Nome = document.querySelector("#first-name").value;
        const NickName = document.querySelector("#Username").value;
        const Email = document.querySelector("#email").value;
        const Password = document.querySelector("#senha").value;

        const dados = {
            Nome,
            NickName,
            Email,
            Password
        };

        console.log(dados);

        fetch("/produto/insert", {
            method: "POST",
            headers: {
                "Content-Type" : "application/json"
            },
            body: JSON.stringify(dados)
        })
            .then(response=>{
                if(response.ok){
                    alert("Registrado!");
                    window.location.href = 'login.html';
                }else{

                    throw new Error("Erro no registro");
                }
            })
            .catch(error=>{
                alert("Erro no registro: " + error.message);
                console.error("Erro no registro: ", error);
            })
}

form.addEventListener("submit", function (e) {
    e.preventDefault();

    enviar();
})



