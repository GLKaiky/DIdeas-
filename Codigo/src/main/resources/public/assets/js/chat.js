const Frases = [
    "Digite seu nivel"
];

function gerarFrase() {
    const indice = Math.floor(Math.random() * Frases.length);
    return Frases[indice];
}

function button2(classe, numero) {
    var message = document.querySelector("#message-input");
    console.log(message.value)
    if (!message.value) {
        message.style.border = '1px solid red'
        return
    }
    message.style.border = "none"

    //requisição da mensagem digitada pelo usuário
    var userMessage = message.value;
    showHistoric(userMessage)
    message.value = ' ';

    //fim
    var status = document.getElementById('status');
    var button = document.getElementById('button');
    button.disabled = true;
    button.style.cursor = 'not-allowed'

    setTimeout(function () {
        button.disabled = false;
        button.style.cursor = 'pointer';
    }, 2000);

    recomendacao(classe, numero)

}

async function recomendacao(classe, numero) {
    console.log("Personagem encontrado:", classe);
    console.log("Nível:", numero);

    if (numero != null) {
        const data = {
            nome_classe: classe,
            nivel: numero
        };

        console.log("Dados a serem enviados:", JSON.stringify(data, null, 2));

        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        };
        const url = 'http://localhost:9090/recomendar';

        try {
            const response = await fetch(url, requestOptions);
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const result = await response.json();
            console.log("Recomendações:", JSON.stringify(result.recomendacoes, null, 2));
            responseMessage(result.recomendacoes);
            return result.recomendacoes;
        } catch (error) {
            console.error('Erro:', error);
        }
    } else {
        console.warn('Número é nulo ou indefinido.');
    }
}

function showHistoric(message) {
    var historic = document.getElementById('historic')

    //minhas mensagens
    var boxMyMessage = document.createElement('div')
    boxMyMessage.className = 'box-my-message'

    var MyMessage = document.createElement('p')
    MyMessage.className = 'my-message'
    MyMessage.innerHTML = message

    boxMyMessage.appendChild(MyMessage)
    historic.appendChild(boxMyMessage)
}

function selecionar() {
    let pessoas = document.getElementById("pessoas");
    let bandas = document.getElementById("bandas");
    var friends = document.getElementsByClassName('pessoa1');
    var header = document.getElementById('header1'); // Seleciona o elemento header existente
    var name_friend = document.createElement('p');
    name_friend.className = 'name_friend1';

    name_friend.innerHTML = '';

    for (var i = 0; i < friends.length; i++) {
        friends[i].style.display = 'block';

        friends[i].addEventListener('click', function (event) {
            Nome(event.target.innerText);
        });
    }

    pessoas.addEventListener('click', function () {
        if (pessoas.checked === true) {
            bandas.checked = false;
            pessoas.checked = true;
        } else if (bandas.checked === true) {
            pessoas.checked = false;
            bandas.checked = true;
        }
    })

    bandas.addEventListener('click', function () {
        if (bandas.checked === true) {
            pessoas.checked = false;
            bandas.checked = true;

            for (var i = 0; i < friends.length; i++) {
                friends[i].style.display = 'none';
            }
        } else if (pessoa.checked === true) {
            pessoas.checked = false;
            bandas.checked = true;
        }
    })

    for (var i = 0; i < friends.length; i++) {
        Limpar();
    }
}

function Nome(amigo) {
    var name_friend = document.createElement('p');
    name_friend.className = 'name_friend1';
    name_friend.innerHTML = amigo;

    // Remove o conteúdo anterior do header
    var header = document.getElementById('header1');
    header.innerHTML = '';

    // Adiciona o novo nome ao header
    header.appendChild(name_friend);
}

function Limpar(clique) {
    var historic = document.getElementById('historic');
    historic.innerHTML = '';
}

function mensagem() {
    var RespostaMessage = document.createElement('div')
    RespostaMessage.className = 'box-response-message'

    var chatResponse = document.createElement('p')
    chatResponse.className = 'chat-response'

    setTimeout(function () {
        historic.removeChild(AnimationMessage)
        chatResponse.innerHTML = gerarFrase();
        chatResponse.style.marginLeft = '-5%'
    }, 3000);

    RespostaMessage.appendChild(chatResponse)
    historic.appendChild(RespostaMessage)

    //animação
    var AnimationMessage = document.createElement('div')
    AnimationMessage.className = 'box-animation-message'

    var animation = document.createElement('p')
    animation.className = 'animação';

    AnimationMessage.appendChild(animation)
    historic.appendChild(AnimationMessage)
}

function responseMessageModified(jsonArray) {
    var RespostaMessage = document.createElement('div');
    RespostaMessage.className = 'box-response-message';

    setTimeout(function () {
        if (AnimationMessage.parentElement) {
            historic.removeChild(AnimationMessage);
        }

        // Garantir que jsonArray seja um array
        if (!Array.isArray(jsonArray)) {
            jsonArray = [jsonArray]; // Converte em um array, se não for um
        }

        var idElementoPai = document.getElementById("historic");
        var html = ''; // Inicialize a string HTML vazia
        jsonArray.forEach(item => {
            html += '<div class="container">' +
                '<div class="card">' +
                '<label>' +
                '<span class="name" style="word-wrap: break-word;">' + item.description.tags + '<br>' + '</span>' +
                '<span class="name" style="word-wrap: break-word;">' + item.description.captions[0].text + '</span>' +
                '</label>' +
                '<br>' +
                '</div>' +
                '</div>';
        });
        
        idElementoPai.innerHTML = html;
        

        historic.appendChild(RespostaMessage);

        if (AnimationMessage.parentElement) {
            historic.removeChild(AnimationMessage);
        }
    }, 3000);

    var AnimationMessage = document.createElement('div');
    AnimationMessage.className = 'box-animation-message';

    var animation = document.createElement('p');
    animation.className = 'animação';

    AnimationMessage.appendChild(animation);
    historic.appendChild(AnimationMessage);
}

function responseMessage(jsonArray) {
    var RespostaMessage = document.createElement('div');
    RespostaMessage.className = 'box-response-message';

    setTimeout(function () {
        if (AnimationMessage.parentElement) {
            historic.removeChild(AnimationMessage);
        }

        // Garantir que jsonArray seja um array
        if (!Array.isArray(jsonArray)) {
            jsonArray = [jsonArray]; // Converte em um array, se não for um
        }

        var idElementoPai = document.getElementById("historic");
        var html = ''; // Inicialize a string HTML vazia

        jsonArray.forEach(item => {
            html += '<div class="container">' +
                '<div class="card">' +
                '<label>' +
                '<input id="names-' + item.Nome + '" type="radio" name="personagem" value="' + item.Nome + '">' +
                '<span class="name">' + item.Nome + '</span>' +
                '</label>' +
                '<br>' +
                '</div>' +
                '</div>';

        });
        idElementoPai.innerHTML = html;

        var magicInputs = document.querySelectorAll('input[name="personagem"]');
        magicInputs.forEach(magicInput => {
            magicInput.addEventListener('click', function () {
                // Ao clicar em magicInput, chame a função showPopup com o Nome e a Descrição correspondentes
                var selectedItem = jsonArray.find(item => item.Nome === this.value);
                showPopup(selectedItem.Nome, selectedItem.Descricao);
            });
        });

        historic.appendChild(RespostaMessage);

        if (AnimationMessage.parentElement) {
            historic.removeChild(AnimationMessage);
        }
    }, 3000);

    var AnimationMessage = document.createElement('div');
    AnimationMessage.className = 'box-animation-message';

    var animation = document.createElement('p');
    animation.className = 'animação';

    AnimationMessage.appendChild(animation);
    historic.appendChild(AnimationMessage);
}

function showPopup(nome, descricao) {
    var popup = document.createElement('div');
    popup.className = 'popup';

    var popupContent = document.createElement('div');
    popupContent.className = 'popup-content';

    var popupClose = document.createElement('span');
    popupClose.className = 'popup-close';
    popupClose.innerHTML = '&times;';

    var popupNome = document.createElement('h2');
    popupNome.innerText = nome;

    var popupDescricao = document.createElement('p');
    popupDescricao.innerText = descricao;

    var popupBotao = document.createElement('button')
    popupBotao.innerText = "Adicionar ao Personagem"
    popupBotao.id = 'adicionar_magia'

    popupBotao.addEventListener("click", function (e) {
        console.log(NomePersonagem);
        fetch("/produto/getPersonbyName/" + NomePersonagem,{
            method: "GET",
        })
        .then(response => {
            if(response.ok){
                console.log(response.status)
                return response.json();
            }else{
                console.log("Deu ruim" + response.status);
            }
        })
        .then(data => {
            console.log(data)  
            const descricaoCodificada = encodeURIComponent(descricao);
            fetch("/produto/setMagias/" +  nome + "/" + descricaoCodificada + "/" + data.nome + "/" + Nivel,{
                method: "POST",
            }) 
            .then(response =>{
                if(response.ok){
                    console.log(response.status);
                    alert("Adicionada com Sucesso")
                    window.location.href = "/assets/paginas/perfil.html"
                    return response;
                }else{
                    console.log("Deu ruim", response.status);
                }
            })
        })
    });

    popupClose.addEventListener('click', function () {
        document.body.removeChild(popup);
    });

    popupContent.appendChild(popupClose);
    popupContent.appendChild(popupNome);
    popupContent.appendChild(popupDescricao);
    popup.appendChild(popupContent);
    document.body.appendChild(popup);
    popupContent.appendChild(popupBotao);
}



var style = document.createElement('style');
style.innerHTML = `
    .popup {
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(0,0,0,0.5);
        display: flex;
        justify-content: center;
        align-items: center;
        z-index: 1000;
    }
    .popup-content h2, .popup-content p {
        color: black;
    }
    .popup-content {
        background-color: #fff;
        padding: 20px;
        border-radius: 10px;
        text-align: center;
        position: relative;
    }
    .popup-close {
        color: black;
        position: absolute;
        top: 10px;
        right: 10px;
        cursor: pointer;
    }
`;
document.head.appendChild(style);

globalThis.NomePersonagem = " ";
globalThis.Nivel = " ";

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

document.addEventListener('DOMContentLoaded', function (e) {
    const userData = localStorage.getItem('personagensSalvos');
    if(userData) {
        const data = JSON.parse(userData);
        console.log(data);

                var idElementoPai = document.getElementById("procurar");
                var html = ''; // Inicialize a string HTML vazia

                // Iterar sobre os dados e criar um card para cada personagem
                data.forEach(personagem => {
                console.log(personagem)
                    html += '<div class="container">' +
                        '<div class="card">' +
                        '<label>' +
                        '<input id="names-' + personagem.nome + personagem.classe.name + '" type="radio" name="personagem" value="' + personagem.nome + '">' +
                        '<span class="name">' + personagem.nome + "--" + personagem.classe.name + '</span>' +
                        '<br>' +
                        '</label>' +
                        '<br>' +
                        '</div>' +
                        '</div>';
                });

                // Adicione o HTML ao elemento pai
                idElementoPai.innerHTML = html;

                        // Adicionar evento de clique a cada rádio
                        document.querySelectorAll('input[name="personagem"]').forEach(radio => {
                            radio.addEventListener('click', function () {
                                mensagem();
                                console.log(this.value); // Exibe o valor do rádio selecionado
                                NomePersonagem = this.value
                                // Remover qualquer evento click previamente adicionado ao botão
                                var button = document.getElementById("button");
                                var newButton = button.cloneNode(true);
                                button.parentNode.replaceChild(newButton, button);

                                // Adicionar o novo evento click
                                newButton.addEventListener("click", function () {
                                    var message = document.querySelector("#message-input");
                                    var selectedPersonagem = data.find(personagem => personagem.nome === radio.value);
                                    if (selectedPersonagem) {
                                        Nivel = message.value;
                                        button2(selectedPersonagem.classe.name, message.value);
                                    }
                                });
                            });
                        });
    }
});

/*fetch("/produto/getPersonagem", {
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
        var idElementoPai = document.getElementById("procurar");
        var html = ''; // Inicialize a string HTML vazia

        // Iterar sobre os dados e criar um card para cada personagem
        data.forEach(personagem => {
        console.log(personagem)
            html += '<div class="container">' +
                '<div class="card">' +
                '<label>' +
                '<input id="names-' + personagem.nome + personagem.classe.name + '" type="radio" name="personagem" value="' + personagem.nome + '">' +
                '<span class="name">' + personagem.nome + "--" + personagem.classe.name + '</span>' +
                '<br>' +
                '</label>' +
                '<br>' +
                '</div>' +
                '</div>';
        });

        // Adicione o HTML ao elemento pai
        idElementoPai.innerHTML = html;


    })
    .catch(error => {
        console.error('Erro no envio de personagens:', error);
    });*/

const fileInput = document.getElementById('file-input'); // Supondo que você tenha um input de arquivo com id 'file-input'
const file = fileInput.files[0];

const reader = new FileReader();
// Adicione um event listener para o evento 'change' do input file
fileInput.addEventListener('change', function (event) {
    const file = event.target.files[0]; // Obtenha o arquivo selecionado

    // Verifique se um arquivo foi selecionado
    if (file) {
        const reader = new FileReader();

        // Defina a função a ser executada quando a leitura do arquivo for concluída
        reader.onloadend = function () {
            // Enviar a imagem codificada em base64 para o servidor
            const imageData = reader.result.replace(/^data:image\/(png|jpg|jpeg);base64,/, ''); // Remover o cabeçalho da codificação base64
            fetch('http://localhost:9090/analisar', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ image: imageData })
            })
                .then(response => response.json())
                .then(data => {
                    console.log(data);
                    responseMessageModified(data)
                })
                .catch(error => {
                    console.error('Erro ao enviar imagem:', error);
                });
        };

        // Leia o arquivo como uma URL de dados (base64)
        reader.readAsDataURL(file);
    }
});
