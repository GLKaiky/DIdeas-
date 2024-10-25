const form = document.querySelector("#formulario");

form.addEventListener("submit", function (e) {
    e.preventDefault()

    enviar();
})

function enviar() {
    var btn = document.getElementById("enviar");

    var frase = document.querySelector("#frase");
    frase.innerHTML = "Aguarde...";
    btn.style.backgroundColor = '#999'; // Cor de fundo mais escura
    btn.style.color = '#ccc'; // Cor do texto mais clara
    btn.style.cursor = 'not-allowed'; // Altera o cursor para indicar que está desativado
    btn.style.pointerEvents = 'none'; // Impede interações de clique no botão

    const nome = document.querySelector("#nomedopersonagem").value;
    const descricao = document.querySelector("#descricao").value;
    const historia = document.querySelector("#historia").value;

    // Obtendo a tendência selecionada
    const tendenciaRadios = document.querySelectorAll('input[name="tendencia"]');
    let tendencia;
    tendenciaRadios.forEach(radio => {
        if (radio.checked) {
            tendencia = radio.value;
        }
    });

    const religiao = document.querySelector("#religiao").value;

    // Obtendo a classe selecionada
    const selectClasse = document.querySelector('#classe');
    const name_classe = selectClasse.options[selectClasse.selectedIndex].value;

    // Obtendo a raça selecionada
    const selectRaca = document.querySelector('#racas');
    const name_raca = selectRaca.options[selectRaca.selectedIndex].value;

    // Obtendo os atributos
    const sabedoria = parseInt(document.querySelector("#sabedoria").value);
    const forca = parseInt(document.querySelector("#forca").value);
    const destreza = parseInt(document.querySelector("#destreza").value);
    const constituicao = parseInt(document.querySelector("#constituicao").value);
    const inteligencia = parseInt(document.querySelector("#inteligencia").value);
    const carisma = parseInt(document.querySelector("#carisma").value);


    // Criando o objeto de dados
    const dados = {
        nome,
        religiao,
        descricao,
        historia,
        tendencia
    };

    // Log dos dados
    console.log(dados);

    // Enviando os dados para o backend
    fetch("/produto/insertPersonagem" + "/" + name_classe + "/" + name_raca + "/" + carisma + "/" + inteligencia + "/" + constituicao + "/" + destreza + "/" + forca + "/" + sabedoria, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(dados)
    })
        .then(response => {
            if (response.ok) {
                return response;
            } else {
                throw new Error("Erro no registro")
            }
        })

        .then(data => {

            alert("Registrado!");
            window.location.href = '/assets/paginas/login.html'
        })
        .catch(error => {
            alert("Erro no registro:" + error.message);
            console.error("Erro no registro: ", error);
        });


}
