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
function verifyEmail() {

    if (!email) return false;

    const erroElemento = email.nextElementSibling;

    if (verifyCampo(email)) return true;

    const emailValue = email.value.trim().toLowerCase();

    const regex =
        /^[a-zA-Z0-9._%+-]+@((gmail|outlook|hotmail|email)\.com(\.br)?|ifpr\.edu\.br)$/;

    if (!regex.test(emailValue)) {

        erroElemento.innerHTML =
            "Use apenas gmail, outlook, hotmail, email ou ifpr.edu.br";

        return true;
    }

    return false;
}

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

function verifyCNPJ() {

    if (!cnpj) return false;

    const erroElemento = cnpj.parentElement.querySelector(".erros");

    const somenteNumeros = cnpj.value.replace(/\D/g, "");

    if (somenteNumeros.length !== 14 && somenteNumeros.length !== 0) {
        erroElemento.innerHTML = "CNPJ deve conter 14 números.";
        return true;
    }

    return false;
}

function verifyUF() {

    if (!uf) return false;

    const erroElemento = uf.parentElement.querySelector(".erros");

    if (verifyCampo(uf)) return true;

    if(uf.value === "NULL") return false;
    if (uf.value.length !== 2) {
        erroElemento.innerHTML = "UF deve conter 2 letras.";
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
            verifyCNPJ(),
            verifyCampo(cidade),
            verifyUF(),
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