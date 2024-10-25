function limparCookiesERedirecionar() {
    // Limpar todos os cookies
    document.cookie.split(";").forEach(function (c) { document.cookie = c.replace(/^ +/, "").replace(/=.*/, "=;expires=" + new Date().toUTCString() + ";path=/"); });

    // Redirecionar para a tela de login
    window.location.href = "/assets/paginas/login.html"; // Substitua "/login" pela URL da sua tela de login
}


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


const userDataString = getCookie("userData");
if (userDataString) {
    const userData = JSON.parse(userDataString);
    console.log(userData);
    var nome = document.getElementById("nome");
    nome.innerHTML = userData.Nome;
    var nickname = document.getElementById("nickName");
    nickname.innerHTML = userData.NickName;

} else {
    alert("cookie não encontrado");
}


const input_image = document.getElementById("input_imagem");

input_image.addEventListener("change", function (event) {
    var imagem = document.getElementById("input_imagem").files;
    console.log(imagem);

    if (imagem.length > 0) {
        var carregar = imagem[0]
        console.log(carregar);

        var lerArquivo = new FileReader();
        lerArquivo.onload = function (arquivoCarregado) {
            var imagemBase64 = arquivoCarregado.target.result;

            console.log(imagemBase64);

            fetch("/produto/set_image", {
                method: "POST",
                body: imagemBase64,
            })
                .then(response => {
                    if (response.ok) {
                        alert("Foto aprovada");
                        console.log("deu certo");
                        limparCookiesERedirecionar();
                    } else {
                        console.log("deu ruim");
                    }
                })
        }
        lerArquivo.readAsDataURL(carregar);
    }
});

var perfil = document.getElementById("perfil");


document.addEventListener('DOMContentLoaded', function (e) {
    const userData = localStorage.getItem('personagensSalvos');
    if(userData) {
        const data = JSON.parse(userData);
        console.log(data); // Aqui você terá acesso aos dados do usuário, incluindo os personagens
        // Use os dados para exibir os personagens na página perfil.html
                    var idElementoPai = document.getElementById("card1");
                    var html = ''; // Inicialize a string HTML vazia

                    // Iterar sobre os dados e criar um card para cada personagem
                    data.forEach(personagem => {
                        html += '<div class = "container">' +
                            '<div class="card">' +
                            '<div class="img" style="background-image: url(' + getImageUrl(personagem.classe.name) + ');">' +
                            '<span class="name">' + personagem.nome + '</span>' +
                            '</div>' +
                            '<div class="content">' +
                            '<span class="title">' + personagem.classe.name + '</span>' +
                            '<p class="descricao">' + personagem.descricao + '</p>' +
                            '</div>' +
                            '<div class="arrow">' +
                            '<button id="Info" class="info-button">Mais Informações</button>' +
                            '<span>&#8673;</span>' +
                            '</div>' +
                            '</div>' +
                            '</div>';
                    });

                    // Adicione o HTML ao elemento pai
                    idElementoPai.innerHTML = html;

                                document.querySelectorAll('.info-button').forEach((button, index) => {
                                    button.addEventListener('click', () => {
                                        console.log(data[index]); // Exibe os detalhes do personagem correspondente ao botão clicado
                                        document.cookie = "PersonagemData=" + JSON.stringify(data[index]) + ";" + ";path=/";
                                        window.location.href = "/assets/paginas/personagem.html"
                                    });
                                });

    } else {
        console.error('Dados do usuário não encontrados no cache.');
    }

    var perfil = document.getElementById("perfil");
    e.preventDefault();

    fetch("/produto/get_image", {
        method: "GET",
    })
        .then(response => {
            if (response.ok) {
                return response.text(); // Retorna o conteúdo da imagem como texto
            } else {
                throw new Error("Erro ao obter imagem" + response.statusText);
            }
        })
        .then(data => {
            perfil.src = data;
            console.log(data)
        })
        .catch(error => {
            console.error('Erro ao obter imagem:', error);
        });

    fetch("/produto/getPersonagem", {
        method: "GET",
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error("Erro no envio de personagens");
            }
        })
        .then(data => {

            // Adicione o evento de clique para cada botão de "Mais Informações"

        })
        .catch(error => {
            console.error('Erro no envio de personagens:', error);
        });

    // Função para obter a URL da imagem com base no nome da classe do personagem
    function getImageUrl(className) {
        switch (className) {
            case "Bárbaro":
                return "/assets/img/Bárbaro.webp";
            case "Bardo":
                return "/assets/img/Bardo.jpg";
            case "Clérigo":
                return "/assets/img/Clérigo.jpg";
            case "Druida":
                return "/assets/img/Druida.jpg";
            case "Guerreiro":
                return "/assets/img/Guerreiro.jpg";
            case "Monge":
                return "/assets/img/Monge.webp";
            case "Paladino":
                return "/assets/img/Paladino.webp";
            case "Ladino":
                return "/assets/img/Ladino.png";
            case "Feiticeiro":
                return "/assets/img/Feiticeiro.webp";
            case "Mago":
                return "/assets/img/Mago.jpg";
            default:
                return "";
        }
    }
})