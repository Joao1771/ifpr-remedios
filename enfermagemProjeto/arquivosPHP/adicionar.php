<?php
include("verificarSessao.php");
?>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<title>Adicionar Remédio</title>
</head>
<body class="bg-light">

<div class="container mt-4">
<a href="paginaDosRemedios_AcessoProfessor.php" 
class="btn btn-danger  position-absolute top-0 start-0 m-4" style ="font-size:20px;">
X
</a>
<form class="border border-success rounded p-3 mx-auto mb-3" style="max-width: 700px; margin-top: 100px;" 
action="chamarFuncoes.php?acao=adicionar" method="post" enctype="multipart/form-data" data-tipo="remedio">
<div class="text-center mb-4">
    <h1 class="fw-bold text-success" style = "font-size: 35px;">
        Cadastro de Medicamento
    </h1>
    <p class="text-muted small mb-0">
        Preencha as informações abaixo para registrar um novo medicamento
    </p>
    <hr class="w-25 mx-auto border-success opacity-75">
</div><div class="row">

<div class="col-md-6 mb-2">
<label class="form-label small" for="">
  <span style="color:red">*</span>Nome</label>
<input name="nome" class="form-control form-control-sm" id="nome" maxlength="100">
<p class="text-danger small mb-0 erros"></p>
</div>
<div class="col-md-6 mb-2">

    <label class="form-label small" for="bula">
        <span style="color:red">*</span>Bula (PDF)
    </label>

    <input 
        type="file"
        name="bula"
        id="bula"
        class="form-control form-control-sm"
        accept="application/pdf"
        required
    >

    <p class="text-danger small mb-0 erros"></p>

</div>

<div class="col-md-6 mb-2">
<label class="form-label small" for="tipoRemedio">
  <span style="color:red">*</span>Tipo</label>
<input name="tipoRemedio" class="form-control form-control-sm" id="tipoRemedio">
<p class="text-danger small mb-0 erros"></p>
</div>


<div class="col-md-6 mb-2">
<label class="form-label small" for="">
  <span style="color:red">*</span>Público-Alvo</label>
<div class="row">

<div class="col-6">
<div class="form-check">
  <input class="form-check-input" type="checkbox" name="publicoAlvo[]" value="1" id="criancas">
  <label class="form-check-label">Crianças</label>
</div>
</div>

<div class="col-6">
<div class="form-check">
  <input class="form-check-input" type="checkbox" name="publicoAlvo[]" value="2" id="adultos">
  <label class="form-check-label">Adultos</label>
</div>
</div>

<div class="col-6">
<div class="form-check">
  <input class="form-check-input" type="checkbox" name="publicoAlvo[]" value="3" id="idosos">
  <label class="form-check-label">Idosos</label>
</div>
</div>

<div class="col-6">
<div class="form-check">
  <input class="form-check-input" type="checkbox" name="publicoAlvo[]" value="4" id="gestantes">
  <label class="form-check-label">Gestantes</label>
</div>
</div>
<p class="text-danger small mb-0 erros"></p>
</div>
</div>  

<div class="col-md-6 mb-2">
<label class="form-label small" for="restricao">
  <span style="color:red">*</span>Restrição</label>
<input name="restricao" class="form-control form-control-sm" id="restricao">
<p class="text-danger small mb-0 erros"></p>
</div>

<div class="col-md-6 mb-2">
<label class="form-label small" for="contraIndicacoes">
  <span style="color:red">*</span>Contraindicações</label>
<input name="contraIndicacoes" class="form-control form-control-sm" id="contraIndicacoes" maxlength="250">
<p class="text-danger small mb-0 erros"></p>
</div>

<div class="col-md-6 mb-2">
<label class="form-label small" for="efeitos">
  <span style="color:red">*</span>Efeitos</label>
<input name="efeitos" class="form-control form-control-sm" id="efeitos" maxlength="250">
<p class="text-danger small mb-0 erros"></p>
</div>

<div class="col-md-6 mb-2">
<label class="form-label small" for="substancia">
  <span style="color:red">*</span>Substância presente no medicamento</label>
<input name="substancia" class="form-control form-control-sm" id="substancia" maxlength="200">
<p class="text-danger small mb-0 erros"></p>
</div>

<div class="col-md-6 mb-2">
<label class="form-label small" for="substancia_tipo">
  <span style="color:red">*</span>Tipo de substância</label>
<input name="substancia_tipo" class="form-control form-control-sm" id="substancia_tipo" maxlength="100">
<p class="text-danger small mb-0 erros"></p>
</div>
<div class="col-md-6 mb-2">

    <label class="form-label small" for="tarja">
      <span style="color:red">*</span>Tarja</label>

    <select name="tarja" class="form-select form-select-sm" id="tarja">

        <option value="">Selecione a Tarja</option>
        <option value="1">Sem Tarja</option>
        <option value="2">Vermelha</option>
        <option value="3">Preta</option>

    </select>

    <p class="text-danger small mb-0 erros"></p>

</div>

<div class="col-md-6 mb-2">
<label class="form-label small" for="validade">
  <span style="color:red">*</span>Prazo de validade</label>
<input name="validade" class="form-control form-control-sm" id="validade" maxlength="100" placeholder="em meses ou anos">
<p class="text-danger small mb-0 erros"></p>
</div>

<div class="col-md-6 mb-2">
<label class="form-label small" for="conservacao">
  <span style="color:red">*</span>Modo de conservação</label>
<input name="conservacao" class="form-control form-control-sm" id="conservacao" 
maxlength="100" placeholder="Ex: Local seco e refrigerado">
<p class="text-danger small mb-0 erros"></p>
</div>


<div class="d-flex align-items-center my-3">

    <div class="flex-grow-1 border-top"></div>

    <span class="px-3 fw-semibold text-success">
        Dados da Empresa fabricante
    </span>

    <div class="flex-grow-1 border-top"></div>

</div>

<div class="col-md-6 mb-2 position-relative">
  


    <label class="form-label small" for="empresaBusca">
        <span style="color:red">*</span> Empresa
    </label>

<input
    type="text"
    id="empresaBusca"
    class="form-control form-control-sm"
    placeholder="Digite o nome da empresa">
<p class="text-danger small mb-0 erros"></p>

<input
    type="hidden"
    name="empresa"
    id="empresa">

<div
    id="listaEmpresas"
    class="list-group position-absolute"
    style="z-index:1000; width:95%;">
</div>

<button
    type="button"
    id="trocarEmpresa"
    class="btn btn-secondary btn-sm mt-2"
    style="display:none;">
    Trocar empresa
</button>
</div>


<div class="col-md-6 mb-2">
<label class="form-label small">CNPJ</label>

<input
    type="text"
    id="cnpj"
    class="form-control form-control-sm"
    readonly>

<p class="text-danger small mb-0 erros"></p>
</div>


<div class="col-md-6 mb-2">
<label class="form-label small">Cidade</label>

<input
    type="text"
    id="cidade"
    class="form-control form-control-sm"
    readonly>

<p class="text-danger small mb-0 erros"></p>
</div>


<div class="col-md-6 mb-2">
<label class="form-label small">Estado</label>

<input
    type="text"
    id="uf"
    class="form-control form-control-sm"
    readonly>

<p class="text-danger small mb-0 erros"></p>
</div>


</div>

<button class="btn btn-success btn-sm w-100 mt-2">Salvar</button>

</form>
</div>

<script src="../frontend/js/formValidation.js"></script>
<script src="../frontend/js/empresasSearch.js"></script>
<script src="../frontend/js/addValues.js"></script>
</body>
</html>