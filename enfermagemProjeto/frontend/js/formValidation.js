const email = document.querySelector("#email");
const senha = document.querySelector("#senha");

const nome = document.querySelector("#nome");
const bula = document.querySelector("#bula");
const tipoRemedio = document.querySelector("#tipoRemedio");

const precaucoes = document.querySelector("#precaucoes");
const contraIndicacoes = document.querySelector("#contraIndicacoes");
const composicao = document.querySelector("#composicao");
const efeitos = document.querySelector("#efeitos");
const tarja = document.querySelector("#tarja");
const validade = document.querySelector("#validade");

const empresa = document.querySelector("#empresa");
const cnpj = document.querySelector("#cnpj");
const cidade = document.querySelector("#cidade");
const uf = document.querySelector("#uf");

const substancia = document.querySelector("#substancia");
const substanciaTipo = document.querySelector("#substancia_tipo");

const erros = document.querySelectorAll(".erros");
const form = document.querySelector("form");

const erroVazio = "Por favor, preencha este campo.";
const erroURL = "Digite uma URL válida (ex: https://site.com)";
const erroGrande = "O tamanho máximo permitido é de 200 caracteres.";
const formEmailCadastro = document.getElementsByTagName("form")[0];
const tiposLogin = ["@gmail", "@escola", "@ifpr",];

function clearErrors() {
    erros.forEach(p => p.innerHTML = "");
}

function isValidURL(value) {
    return /^(https?:\/\/)[^\s$.?#].[^\s]*$/.test(value);
}

function verifyCampo(campo, mensagem = erroVazio) {

    if (!campo) return false;

    let erroElemento = null;

    const container = campo.closest(".col-md-6");

    if (container) {
        erroElemento = container.querySelector(".erros");
    } else {
        erroElemento = campo.nextElementSibling;
    }

    const value = campo.value.trim();

    if (value === "") {
        erroElemento.innerHTML = mensagem;
        return true;
    }

    if (value.length >= 200) {
        erroElemento.innerHTML = erroGrande;
        return true;
    }

    return false;
}
formEmailCadastro.addEventListener("submit", function (e) {
    // limpa erros
    erros[0].innerHTML = "";
    erros[1].innerHTML = "";

    let temMaiuscula = false;
    let temNumero = false;
    let temErro = false;

    function nomeDominioEmail(emailTexto) {
        const partes = emailTexto.split("@");

        if (partes.length < 2) {
            return "";
        }

        const dominio = partes[1];
        const nomeDominio = dominio.split(".")[0];

        return "@" + nomeDominio;
    }

    let nomeDominio = nomeDominioEmail(email.value);

    let dominioPermitido = false;

    for (let indice = 0; indice < tiposLogin.length; indice++) {
        if (tiposLogin[indice] === nomeDominio) {
            dominioPermitido = true;
            break;
        }
    }

    if (!dominioPermitido && email.value !== "") {
        erros[0].innerHTML = "Use apenas gmail, outlook, hotmail, email ou ifpr.edu.br";
        temErro = true;
    }

    if (email.value === "") {
        erros[0].innerHTML = "Digite o e-mail";
        temErro = true;
    }

    for (let i = 0; i < senha.value.length; i++) {
        let caractere = senha.value[i];

        if (caractere >= "A" && caractere <= "Z") {
            temMaiuscula = true;
        }

        if (caractere >= "0" && caractere <= "9") {
            temNumero = true;
        }
    }

    if (!temMaiuscula || !temNumero) {
        erros[1].innerHTML = "A senha deve ter pelo menos uma letra maiúscula e um número";
        temErro = true;
    }

    if (senha.value === "") {
        erros[1].innerHTML = "Digite a senha";
        temErro = true;
    }

    if (senha.value.length > 30) {
        erros[1].innerHTML = "Senha deve ter no máximo 30 caracteres";
        temErro = true;
    }

    if (senha.value.length < 6 && senha.value !== "") {
        erros[1].innerHTML = "Senha deve ter pelo menos 6 caracteres";
        temErro = true;
    }

    // bloqueia envio se tiver erro
    if (temErro) {
        e.preventDefault();
    }
});

function verifySenha() {

    if (!senha) return false;

    const erroElemento = senha.nextElementSibling;

    if (verifyCampo(senha)) return true;

    if (senha.value.length < 4) {
        erroElemento.innerHTML = "Senha deve ter pelo menos 4 caracteres.";
        return true;
    }

    if (senha.value.length >= 250) {
        erroElemento.innerHTML = "Senha maior que 250 caracteres.";
        return true;
    }

    return false;
}

function verifyBula() {

    if (!bula) return false;

    const erroElemento = bula.closest(".col-md-6").querySelector(".erros");

    const file = bula.files[0];

    if (bula.name !== "bula") {
        return false;
    }

    // se não enviou arquivo
    if (!file) {
        erroElemento.innerHTML = "Envie a bula em PDF.";
        return true;
    }

    // valida extensão / tipo
    const isPDF = file.type === "application/pdf";

    if (!isPDF) {
        erroElemento.innerHTML = "O arquivo deve ser um PDF.";
        return true;
    }

    // valida tamanho (ex: 5MB)
    const maxSize = 5 * 1024 * 1024;

    if (file.size > maxSize) {
        erroElemento.innerHTML = "O arquivo deve ter no máximo 5MB.";
        return true;
    }

    return false;
}

function verifyPublicoAlvo() {

    const checkboxes = document.querySelectorAll(
        'input[name="publicoAlvo[]"]:checked, input[name="publicoAlvo_paraAlterar[]"]:checked'
    );

    if (checkboxes.length === 0) {
        erros[3].innerHTML = "Selecione ao menos uma opção";
        return true;
    }

    return false;
}

function validarLogin() {

    form.addEventListener("submit", e => {

        clearErrors();

        const emailErro = verifyEmail();
        const senhaErro = verifySenha();

        if (emailErro || senhaErro) {
            e.preventDefault();
        }
    });
}

function validarUsuario() {

    form.addEventListener("submit", e => {

        clearErrors();

        const emailErro = verifyEmail();
        const senhaErro = verifySenha();

        if (emailErro || senhaErro) {
            e.preventDefault();
        }
    });
}

function validarRemedio() {

    form.addEventListener("submit", e => {

        clearErrors();

        const errosList = [

            verifyCampo(nome),
            verifyBula(),
            verifyCampo(tipoRemedio),
            verifyPublicoAlvo(),
            verifyCampo(precaucoes),
            verifyCampo(contraIndicacoes),
            verifyCampo(composicao),
            verifyCampo(efeitos),
            verifyCampo(empresa),
            verifyCampo(uf),
            verifyCampo(cidade),
            verifyCampo(cnpj),
            verifyCampo(substancia),
            verifyCampo(substanciaTipo),
            verifyCampo(tarja),
            verifyCampo(validade),
        ];

        if (errosList.includes(true)) {
            e.preventDefault();
        }
    });
}

if (form) {

    const tipo = form.dataset.tipo;

    if (tipo === "login") {
        validarLogin();
    }

    if (tipo === "usuario") {
        validarUsuario();
    }

    if (tipo === "remedio") {
        validarRemedio();
    }
}