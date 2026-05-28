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
<label class="form-label small" for="">
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
  <input class="form-check-input" type="checkbox" name="publicoAlvo[]" value="Crianças" id="criancas">
  <label class="form-check-label">Crianças</label>
</div>
</div>

<div class="col-6">
<div class="form-check">
  <input class="form-check-input" type="checkbox" name="publicoAlvo[]" value="Adultos" id="adultos">
  <label class="form-check-label">Adultos</label>
</div>
</div>

<div class="col-6">
<div class="form-check">
  <input class="form-check-input" type="checkbox" name="publicoAlvo[]" value="Idosos" id="idosos">
  <label class="form-check-label">Idosos</label>
</div>
</div>

<div class="col-6">
<div class="form-check">
  <input class="form-check-input" type="checkbox" name="publicoAlvo[]" value="Gestantes" id="gestantes">
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
        <option value="Sem tarja">Sem Tarja</option>
        <option value="Vermelha">Vermelha</option>
        <option value="Preta">Preta</option>

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

<div class="col-md-6 mb-2">
<label class="form-label small" for="empresa">
  <span style="color:red">*</span>Nome</label>
<input name="empresa" class="form-control form-control-sm" id="empresa">
<p class="text-danger small mb-0 erros"></p>
</div>

<div class="col-md-6 mb-2">
<label class="form-label small" for="cnpj">CNPJ</label>
<input name="cnpj" class="form-control form-control-sm" id="cnpj" inputmode="numeric" maxlength="18" placeholder="00.000.000/0001-00">
<p class="text-danger small mb-0 erros"></p>
</div>

<div class="col-md-6 mb-2">
<label class="form-label small" for="cidade">
  <span style="color:red">*</span>Cidade</label>
<input name="cidade" class="form-control form-control-sm" id="cidade">
<p class="text-danger small mb-0 erros"></p>
</div>

<div class="col-md-6 mb-2">

    <label class="form-label small" for="uf">
      <span style="color:red">*</span>Estado</label>

    <select name="uf" class="form-select form-select-sm" id="uf">

        <option value="">Selecione um estado</option>
        <option value="NULL">Fora do Brasil</option>

        <option value="AC">Acre</option>
        <option value="AL">Alagoas</option>
        <option value="AP">Amapá</option>
        <option value="AM">Amazonas</option>
        <option value="BA">Bahia</option>
        <option value="CE">Ceará</option>
        <option value="DF">Distrito Federal</option>
        <option value="ES">Espírito Santo</option>
        <option value="GO">Goiás</option>
        <option value="MA">Maranhão</option>
        <option value="MT">Mato Grosso</option>
        <option value="MS">Mato Grosso do Sul</option>
        <option value="MG">Minas Gerais</option>
        <option value="PA">Pará</option>
        <option value="PB">Paraíba</option>
        <option value="PR">Paraná</option>
        <option value="PE">Pernambuco</option>
        <option value="PI">Piauí</option>
        <option value="RJ">Rio de Janeiro</option>
        <option value="RN">Rio Grande do Norte</option>
        <option value="RS">Rio Grande do Sul</option>
        <option value="RO">Rondônia</option>
        <option value="RR">Roraima</option>
        <option value="SC">Santa Catarina</option>
        <option value="SP">São Paulo</option>
        <option value="SE">Sergipe</option>
        <option value="TO">Tocantins</option>

    </select>

    <p class="text-danger small mb-0 erros"></p>

</div>


</div>

<button class="btn btn-success btn-sm w-100 mt-2">Salvar</button>

</form>
</div>

<script src="../frontend/js/formValidation.js"></script>
<script src="../frontend/js/addValues.js"></script>
</body>
</html>