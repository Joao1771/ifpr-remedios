<?php
//include("verificarSessao.php");
?>

<!DOCTYPE html>
<html lang="pt-BR">

<head>
  <title>Enfermagem Cadastro</title>

  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="/enfermagemProjeto/styles2.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>

  <div id="centralizarCadastro">

    <img id="imagemLogo" style="width: 160px;" src="/enfermagemProjeto/imagens/logoIFPR.png" alt="IFPR Londrina">



    <h2 id="cadastroDeAcesso">
      CADASTRO DE ACESSO AO PROFESSOR
    </h2>


    <form data-tipo="usuario"
    action="/enfermagemProjeto/arquivosPHP/chamarFuncoes.php?acao=cadastro" method="post"
      style="width: 100%; max-width: 500px;">

      <div id="bordaDosInputs">

        <label class="form-label" for="email">E-mail</label>

        <input 
          type="email" 
          class="form-control" 
          style="margin-bottom:25px; height: 45px;" 
          name="email"
          placeholder="Digite seu email"
          id="email">
        <p class="erros"></p>

        <label class="form-label" for="senha">Senha</label>

        <input 
          type="password" 
          class="form-control" 
          style="margin-bottom:25px; height: 45px;" 
          name="senha"
          placeholder="Digite sua senha"
          id="senha">
          <p class="erros"></p>

        <label class="form-label">Nível de acesso</label>
        <input type="text" value="Professor" name="tipo" readonly class="form-control"
          style="margin-bottom:25px; height:45px; background-color:#e9ecef; color:#6c757d; cursor:not-allowed; border:1px solid #ced4da;">


        <button id="submit-btn" class="btn btn-primary" type="submit">
          Criar conta
        </button>
      </div>

    </form>

  </div>
  <script src="../frontend/js/formValidation.js"></script>
</body>

</html>