const btn = document.querySelector("#enviar");
btn.addEventListener("click", function(e){
    e.preventDefault();

    const Username = document.querySelector("#Username").value;
    const Senha = document.querySelector("#senha").value;

    fetch("/produto/get/" + Username + "/" + Senha, {
        method : "GET"
    })
    .then(response => {
        if(response.ok){
            return response.json();
        } else {
            throw new Error('Erro ao obter os dados');
        }
    })
    .then(data => {
        var expirationDate = new Date();
        expirationDate.setTime(expirationDate.getTime() + (24 * 60 * 60 * 1000));

        var expires = "expires=" + expirationDate.toUTCString();
        document.cookie = "userData=" + JSON.stringify(data) + ";" + expires + ";path=/";

        if(data != null){
            pegarPersonagem(data.ID); // Chama a função para obter os personagens do usuário
        } else {
            alert("Usuário ou Senha incorretos");
        }
    })
    .catch(error => {
        console.error('Erro:', error);
    });
});

function pegarPersonagem(id){
    var frase = document.querySelector("#frase");
    frase.innerHTML = "Aguarde...";
    btn.style.backgroundColor = '#999'; // Cor de fundo mais escura
    btn.style.color = '#ccc'; // Cor do texto mais clara
    btn.style.cursor = 'not-allowed'; // Altera o cursor para indicar que está desativado
    btn.style.pointerEvents = 'none'; // Impede interações de clique no botão

    fetch("/produto/getComId/" + id, {
        method:"GET",
    })
    .then(response=>{
        if(response.ok){
             return response.json(); // Retorna a Promise com os dados dos personagens
        } else {
            throw new Error("Erro ao obter personagens");
        }
    })
    .then(personagens => {
        localStorage.setItem('personagensSalvos', JSON.stringify(personagens)); // Armazena os personagens no localStorage
        window.location.href = 'perfil.html'; // Redireciona para a página de perfil após carregar os personagens
    })
    .catch(error => {
        console.error('Erro:', error);
    });
}
