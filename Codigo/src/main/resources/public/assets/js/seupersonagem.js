function getCookie(cookieName) {
    const cookiesArray = document.cookie.split(";");

    for (let cookie of cookiesArray) {
        cookie = cookie.trim();

        if (cookie.startsWith(cookieName + "=")) {
            return cookie.substring(cookieName.length + 1);
        }
    }

    return null;
}

globalThis.NomePersonagem = "";

const userDataString = getCookie("PersonagemData");
if (userDataString) {
    const userData = JSON.parse(userDataString);
    console.log(userData);
    var Nome = document.getElementById("Nome")
    NomePersonagem = userData.nome
    Nome.innerHTML = userData.nome
    var Descricao = document.getElementById("Descrição");
    Descricao.innerHTML = userData.descricao;
    var Religiao = document.getElementById("Religiao");
    Religiao.innerHTML = userData.religiao;
    var Tendencia = document.getElementById("Tendencia");
    Tendencia.innerHTML = userData.tendencia
    var Classe = document.getElementById("Classe");
    var DescricaoClasse = document.getElementById("DescricaoClasse");
    Classe.innerHTML = userData.classe.name
    DescricaoClasse.innerHTML = userData.classe.descricao
    var Raca = document.getElementById("Raca");
    var DescricaoRaca = document.getElementById("DescricaoRaca");
    Raca.innerHTML = userData.raca.nome
    DescricaoRaca.innerHTML = userData.raca.descricao
    var Carisma = document.getElementById("Carisma");
    Carisma.innerHTML = userData.atributo.carisma
    var Constituicao = document.getElementById("Constituicao");
    Constituicao.innerHTML = userData.atributo.constituicao;
    var Destreza = document.getElementById("Destreza")
    Destreza.innerHTML = userData.atributo.destreza
    var Forca = document.getElementById("Forca")
    Forca.innerHTML = userData.atributo.forca
    var Inteligencia = document.getElementById("Inteligencia")
    Inteligencia.innerHTML = userData.atributo.inteligencia
    var Sabedoria = document.getElementById("Sabedoria")
    Sabedoria.innerHTML = userData.atributo.sabedoria;
} else {
    alert("cookie não encontrado");
}


function limparCookiesERedirecionar() {
    // Limpar todos os cookies
    document.cookie.split(";").forEach(function (c) { document.cookie = c.replace(/^ +/, "").replace(/=.*/, "=;expires=" + new Date().toUTCString() + ";path=/"); });

    // Redirecionar para a tela de login
    window.location.href = "/assets/paginas/login.html"; // Substitua "/login" pela URL da sua tela de login
}

const imagem = document.getElementById("imageInput")

imagem.addEventListener("change", function(e){
    var imagem = document.getElementById("imageInput").files;

    if(imagem.length > 0){
        var carregar = imagem[0];
        console.log(carregar)

        var lerarquivo = new FileReader();
        lerarquivo.onload = function(Carregado){
            var imagemBase = Carregado.target.result;

            console.log(imagemBase);
            const userDataString = getCookie("PersonagemData");
            const userData = JSON.parse(userDataString);

            fetch("/produto/setPersonImage/" + userData.nome, {
                method: "POST",
                body: imagemBase,
            })
                .then(response=>{
                    if(response.ok){
                        alert("Foto Aprovada");
                        console.log("deu certo");
                        limparCookiesERedirecionar();
                    }else{
                        alert("Algo deu errado, tente novamente")
                    }
                })

        }   
        lerarquivo.readAsDataURL(carregar);
    }
})

document.addEventListener('DOMContentLoaded', function(e){
    var perfil = document.getElementById("perfil2");
    e.preventDefault();
    const userDataString = getCookie("PersonagemData");
    const userData = JSON.parse(userDataString);
    fetch("/produto/getPersonimage/" + userData.nome, {
        method: "GET",
    })
        .then(response=>{
            if(response.ok){
                return response.text();
            }else{
                throw new Error("Erro ao obter imagem" + response.statusText);
            }
        })
        .then(data=>{
            perfil.src = data;
            console.log(data)
        })
        .catch(error=>{
            console.error("Erro ao obter a imagem: ", error);
        })
    fetch("/produto/getAllMages/" + NomePersonagem, {
        method:"GET"        
    })
    .then(response=>{
        if(response.ok){
            console.log("Deu boa", response.status);
            return response.json();
        }else{
            console.log("Deu ruim", response.status);
        }
    })
    .then(data => {
        var magiaParagrafo = document.getElementById("magias");
        magiaParagrafo.innerHTML = ""; // Limpa o conteúdo existente
        // Verifica se o JSON retornado possui dados
        if (data.length > 0) {
            // Itera sobre cada objeto de magia no JSON
            data.forEach(magia => {
                console.log(magia)
                // Cria um elemento <p> para cada magia e adiciona ao parágrafo
                var magiaElemento = document.createElement("p");
                magiaElemento.innerHTML = "<b>Nome: </b>" + "<b>" + magia.Nome + "</b>" + '<br>' + magia.Descricao + '<br>' + "Nível Necessário: " + magia.nivel;
                magiaParagrafo.appendChild(magiaElemento);
            });
        } else {
            magiaParagrafo.textContent = "Nenhuma magia encontrada.";
        }
    })
    .catch(error => {
        console.error("Erro ao processar dados:", error);
    });
    
})


function deletar(){
    const userDataString = getCookie("PersonagemData");
    const userData = JSON.parse(userDataString);
    fetch("/produto/deletePerson",{
        method:"POST",
        body:userData.nome,
    })
        .then(response=>{
            if(response.ok){
                alert("Excluido com sucesso");
                window.location.href = '/assets/paginas/perfil.html';
            }else{
                alert("Alto deu errado, tente novamente")
            }
        })
}