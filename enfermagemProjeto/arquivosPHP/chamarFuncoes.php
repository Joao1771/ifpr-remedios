<?php
include "logarCadastrar_Funcoes.php";
include "conexaoBanco.php";
include "crud_funcoes.php";

// Variáveis usadas como parâmetro apenas pelas funções logar e cadastrar 
$email = $_POST["email"]?? null;                                                  
$senha = $_POST['senha']??  null;                                                  
$tipo = $_POST["tipo"]?? null;                                                   


$nome  = $_POST["nome"]?? null ; 
$bula = null;
$tipoRemedio  = $_POST["tipoRemedio"]?? null; 
$publicoAlvo = $_POST["publicoAlvo"] ?? [];


$contraIndicacoes  = $_POST["contraIndicacoes"]?? null; 
$efeitos  = $_POST["efeitos"]?? null; 
$restricao  = $_POST["restricao"] ?? null;
$empresa  = $_POST["empresa"]?? null ; 
$cnpj  = $_POST["cnpj"]?? null; 
$cidade  = $_POST["cidade"]?? null; 
$uf = $_POST["uf"]?? null; 
$substancia  = $_POST["substancia"]?? null; 
$substancia_tipo  = $_POST["substancia_tipo"]?? null; 
$tarja = $_POST['tarja']?? null;
$validade = $_POST['validade']?? null;
$conservacao = $_POST['conservacao']?? null;

$id = $_GET['id'] ?? null;

$nome_paraAlterar  = $_POST["nome_paraAlterar"] ?? null;
$tipoRemedio_paraAlterar  = $_POST["tipoRemedio_paraAlterar"] ?? null;
$publicoAlvo_paraAlterar = $_POST["publicoAlvo_paraAlterar"] ?? [];
$restricao_paraAlterar  = $_POST["restricao_paraAlterar"] ?? null;
$contraIndicacoes_paraAlterar  = $_POST["contraIndicacoes_paraAlterar"] ?? null;
$efeitos_paraAlterar  = $_POST["efeitos_paraAlterar"] ?? null; 
$tarja_paraAlterar = $_POST['tarja_paraAlterar']?? null; 
$validade_paraAlterar = $_POST['validade_paraAlterar']?? null; 
$conservacao_paraAlterar = $_POST['conservacao_paraAlterar']?? null; 
$empresa_paraAlterar = $_POST['empresa_paraAlterar'] ?? null; 
$cnpj_paraAlterar = $_POST['cnpj_paraAlterar'] ?? null; 
$cidade_paraAlterar = $_POST['cidade_paraAlterar'] ?? null; 
$uf_paraAlterar = $_POST['uf_paraAlterar'] ?? null; 

$substancia_paraAlterar = $_POST['substancia_paraAlterar'] ?? null; 
$substancia_tipo_paraAlterar = $_POST['substancia_tipo_paraAlterar'] ?? null; 

if ($uf === "NULL") { //adaptar UF para ser null no banco
    $uf = null;
}

//salva o pdf da bula no arquivo bulas em htdocs e envia apenas o caminho para o banco
if (isset($_FILES["bula"]) && $_FILES["bula"]["error"] === UPLOAD_ERR_OK) {

    $diretorioFisico = $_SERVER['DOCUMENT_ROOT'] . "/enfermagemProjeto/bulas/";

    if (!is_dir($diretorioFisico)) {
        mkdir($diretorioFisico, 0777, true);
    }

    $extensao = strtolower(pathinfo($_FILES["bula"]["name"], PATHINFO_EXTENSION));

    if ($extensao !== "pdf") {
        die("A bula deve ser PDF.");
    }

    $nomeFinal = "bula" . time() . ".pdf";

    $caminhoFisico = $diretorioFisico . $nomeFinal;

    move_uploaded_file($_FILES["bula"]["tmp_name"], $caminhoFisico);

    $bula = "bulas/" . $nomeFinal;
}
// EDITAR
$bula_paraAlterar = $_POST["bulaAtual"] ?? null;

if (
    isset($_FILES["bula_paraAlterar"]) &&
    $_FILES["bula_paraAlterar"]["error"] === UPLOAD_ERR_OK
) {

    $diretorioFisico = $_SERVER['DOCUMENT_ROOT'] . "/enfermagemProjeto/bulas/";

    if (!is_dir($diretorioFisico)) {
        mkdir($diretorioFisico, 0777, true);
    }

    $extensao = strtolower(
        pathinfo($_FILES["bula_paraAlterar"]["name"], PATHINFO_EXTENSION)
    );

    if ($extensao !== "pdf") {
        die("A bula deve ser PDF.");
    }

    $nomeFinal = "bula" . time() . ".pdf";

    $caminhoFisico = $diretorioFisico . $nomeFinal;

    move_uploaded_file(
        $_FILES["bula_paraAlterar"]["tmp_name"],
        $caminhoFisico
    );

    $bula_paraAlterar = "bulas/" . $nomeFinal;
}

// Ação de login/cadastro
if (isset($_GET["acao"]) && $_GET["acao"] === "login") {
    logar($email, $senha, $conn);
}

// Ação de registro
if (isset($_GET["acao"]) && $_GET["acao"] === "cadastro") {
    cadastrar($email, $senha, $conn, $tipo);
}
if (isset($_GET["acao"]) && $_GET["acao"] === "cadastroProfessor") {
    cadastroProfessor($email, $senha, $conn, $tipo);
}
// Ação de adicionar mais remédios
if (isset($_GET["acao"]) && $_GET["acao"] === "adicionar") {
    adicionar($conn,$nome,$bula, $tipoRemedio, $publicoAlvo, $restricao, $contraIndicacoes, $efeitos,$empresa,$cnpj,$cidade,$uf,$substancia,$substancia_tipo,$tarja,
$validade,$conservacao);
}
if (isset($_GET["acao"]) && $_GET["acao"] === "editar") {
    editar($conn,$id,$nome_paraAlterar,$bula_paraAlterar,$tipoRemedio_paraAlterar,$publicoAlvo_paraAlterar,$restricao_paraAlterar,$contraIndicacoes_paraAlterar,$efeitos_paraAlterar,
$empresa_paraAlterar,$cnpj_paraAlterar,$cidade_paraAlterar,$uf_paraAlterar,
$substancia_paraAlterar,$substancia_tipo_paraAlterar,$tarja_paraAlterar,
$validade_paraAlterar,$conservacao_paraAlterar);
}

if (isset($_GET["acao"]) && $_GET["acao"] === "excluir") {
    excluir($conn,$id);
}
?>