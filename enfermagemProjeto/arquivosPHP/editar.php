<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <title>Editar Medicamento</title>
</head>

<body class="bg-light">

    <div class="container mt-4">

        <form class="border border-warning rounded p-3 mx-auto mb-3" style="max-width: 700px; margin-top: 100px;"
            action="chamarFuncoes.php?acao=editar&id=<?php echo $_GET['id']; ?>" method="post"
            enctype="multipart/form-data" data-tipo="remedio">

            <a href="paginaDosRemedios_AcessoProfessor.php" class="btn btn-danger position-absolute top-0 start-0 m-3"
                style="font-size: 20px ;">
                X
            </a>

            <div class="text-center mb-4">
                <h1 class="fw-bold text-warning" style="font-size: 35px;">
                    Editar Medicamento
                </h1>

                <p class="text-muted small mb-0">
                    Altere as informações do medicamento abaixo
                </p>

                <hr class="w-25 mx-auto border-warning opacity-75">
            </div>

            <div class="row">

                <div class="col-md-6 mb-2">
                    <label class="form-label small" for="nome"> Nome
                    </label>

                    <input name="nome_paraAlterar" id="nome" class="form-control form-control-sm" maxlength="100"
                        value="<?php echo $_GET['nome']; ?>">

                    <p class="text-danger small mb-0 erros"></p>
                </div>

                <input type="hidden" name="bulaAtual" value="<?php echo $_GET['bula']; ?>">

                <div class="col-md-6 mb-2">

                    <label class="form-label small" for="bula"> Bula (PDF)
                    </label>

                    <input type="file" name="bula_paraAlterar" id="bula" class="form-control form-control-sm"
                        accept="application/pdf">

                    <p class="text-danger small mb-0 erros"></p>

                </div>

                <div class="col-md-6 mb-2">
                    <label class="form-label small" for="tipoRemedio"> Tipo
                    </label>

                    <input name="tipoRemedio_paraAlterar" id="tipoRemedio" class="form-control form-control-sm"
                        value="<?php echo $_GET['tipo']; ?>">

                    <p class="text-danger small mb-0 erros"></p>
                </div>

                <div class="col-md-6 mb-2">

                    <label class="form-label small"> Público-Alvo
                    </label>

                    <?php
$publicos = array_map('trim', explode(", ", $_GET['publicoAlvo']));
?>

                    <div class="row">

                        <div class="col-6">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" name="publicoAlvo_paraAlterar[]"
                                    value="Crianças" id="criancas" <?php if(in_array("Crianças", $publicos))
                                    echo "checked" ; ?>
                                >

                                <label class="form-check-label" for="criancas">
                                    Crianças
                                </label>
                            </div>
                        </div>

                        <div class="col-6">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" name="publicoAlvo_paraAlterar[]"
                                    value="Adultos" id="adultos" <?php if(in_array("Adultos", $publicos)) echo "checked"
                                    ; ?>
                                >

                                <label class="form-check-label" for="adultos">
                                    Adultos
                                </label>
                            </div>
                        </div>

                        <div class="col-6">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" name="publicoAlvo_paraAlterar[]"
                                    value="Idosos" id="idosos" <?php if(in_array("Idosos", $publicos)) echo "checked" ;
                                    ?>
                                >

                                <label class="form-check-label" for="idosos">
                                    Idosos
                                </label>
                            </div>
                        </div>

                        <div class="col-6">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" name="publicoAlvo_paraAlterar[]"
                                    value="Gestantes" id="gestantes" <?php if(in_array("Gestantes", $publicos))
                                    echo "checked" ; ?>
                                >

                                <label class="form-check-label" for="gestantes">
                                    Gestantes
                                </label>
                            </div>
                        </div>

                    </div>

                    <p class="text-danger small mb-0 erros"></p>

                </div>

                <div class="col-md-6 mb-2">
                    <label class="form-label small" for="restricao"> Restrição
                    </label>

                    <input name="restricao_paraAlterar" id="restricao" class="form-control form-control-sm"
                        value="<?php echo $_GET['restricao']; ?>">

                    <p class="text-danger small mb-0 erros"></p>
                </div>

                <div class="col-md-6 mb-2">
                    <label class="form-label small" for="contraIndicacoes"> Contraindicações
                    </label>

                    <input name="contraIndicacoes_paraAlterar" id="contraIndicacoes"
                        class="form-control form-control-sm" maxlength="250"
                        value="<?php echo $_GET['contraIndicacoes']; ?>">

                    <p class="text-danger small mb-0 erros"></p>
                </div>

                <div class="col-md-6 mb-2">
                    <label class="form-label small" for="efeitos"> Efeitos
                    </label>

                    <input name="efeitos_paraAlterar" id="efeitos" class="form-control form-control-sm" maxlength="250"
                        value="<?php echo $_GET['efeitos']; ?>">

                    <p class="text-danger small mb-0 erros"></p>
                </div>

                <div class="col-md-6 mb-2">
                    <label class="form-label small" for="substancia"> Substância presente no medicamento
                    </label>

                    <input name="substancia_paraAlterar" class="form-control form-control-sm" id="substancia"
                        maxlength="200" value="<?php echo $_GET['substancia']; ?>">

                    <p class="text-danger small mb-0 erros"></p>
                </div>

                <div class="col-md-6 mb-2">
                    <label class="form-label small" for="substancia_tipo"> Tipo de substância
                    </label>

                    <input name="substancia_tipo_paraAlterar" class="form-control form-control-sm" id="substancia_tipo"
                        maxlength="100" value="<?php echo $_GET['substancia_tipo']; ?>">

                    <p class="text-danger small mb-0 erros"></p>
                </div>

                <div class="col-md-6 mb-2">

                    <label class="form-label small" for="tarja"> Tarja
                    </label>

                    <select name="tarja_paraAlterar" class="form-select form-select-sm" id="tarja">

                        <option value="">Selecione a Tarja</option>

                        <option value="Sem tarja" <?php if($_GET['tarja']=="Sem tarja" ) echo "selected" ; ?>>
                            Sem Tarja
                        </option>

                        <option value="Vermelha" <?php if($_GET['tarja']=="Vermelha" ) echo "selected" ; ?>>
                            Vermelha
                        </option>

                        <option value="Preta" <?php if($_GET['tarja']=="Preta" ) echo "selected" ; ?>>
                            Preta
                        </option>

                    </select>

                    <p class="text-danger small mb-0 erros"></p>

                </div>

                <div class="col-md-6 mb-2">
                    <label class="form-label small" for="validade"> Prazo de validade
                    </label>

                    <input name="validade_paraAlterar" class="form-control form-control-sm" id="validade"
                        maxlength="100" placeholder="em meses ou anos" value="<?php echo $_GET['validade']; ?>">

                    <p class="text-danger small mb-0 erros"></p>
                </div>

                <div class="col-md-6 mb-2">
                    <label class="form-label small" for="conservacao"> Modo de conservação
                    </label>

                    <input name="conservacao_paraAlterar" class="form-control form-control-sm" id="conservacao"
                        maxlength="100" placeholder="Ex: Local seco e refrigerado" value="<?php echo $_GET['conservacao']; ?>">

                    <p class="text-danger small mb-0 erros"></p>
                </div>

                <div class="d-flex align-items-center my-3">

                    <div class="flex-grow-1 border-top"></div>

                    <span class="px-3 fw-semibold text-warning">
                        Dados da Empresa fabricante
                    </span>

                    <div class="flex-grow-1 border-top"></div>

                </div>

                <div class="col-md-6 mb-2">
                    <label class="form-label small" for="empresa"> Nome
                    </label>

                    <input name="empresa_paraAlterar" class="form-control form-control-sm" id="empresa"
                        value="<?php echo $_GET['empresa']; ?>">

                    <p class="text-danger small mb-0 erros"></p>
                </div>

                <div class="col-md-6 mb-2">
                    <label class="form-label small" for="cnpj"> CNPJ </label>

                    <input name="cnpj_paraAlterar" class="form-control form-control-sm" id="cnpj" inputmode="numeric"
                        maxlength="18" placeholder="00.000.000/0001-00" value="<?php echo $_GET['cnpj']; ?>">

                    <p class="text-danger small mb-0 erros"></p>
                </div>

                <div class="col-md-6 mb-2">
                    <label class="form-label small" for="cidade"> Cidade
                    </label>

                    <input name="cidade_paraAlterar" class="form-control form-control-sm" id="cidade"
                        value="<?php echo $_GET['cidade']; ?>">

                    <p class="text-danger small mb-0 erros"></p>
                </div>

                <div class="col-md-6 mb-2">

                    <label class="form-label small" for="uf"> Estado
                    </label>

                    <select name="uf_paraAlterar" class="form-select form-select-sm" id="uf">

                        <option value="">Selecione um estado</option>

                        <option value="NULL" <?php if($_GET['uf']=="NULL" ) echo "selected" ; ?>>
                            Fora do Brasil
                        </option>

                        <option value="AC" <?php if($_GET['uf']=="AC" ) echo "selected" ; ?>>Acre</option>
                        <option value="AL" <?php if($_GET['uf']=="AL" ) echo "selected" ; ?>>Alagoas</option>
                        <option value="AP" <?php if($_GET['uf']=="AP" ) echo "selected" ; ?>>Amapá</option>
                        <option value="AM" <?php if($_GET['uf']=="AM" ) echo "selected" ; ?>>Amazonas</option>
                        <option value="BA" <?php if($_GET['uf']=="BA" ) echo "selected" ; ?>>Bahia</option>
                        <option value="CE" <?php if($_GET['uf']=="CE" ) echo "selected" ; ?>>Ceará</option>
                        <option value="DF" <?php if($_GET['uf']=="DF" ) echo "selected" ; ?>>Distrito Federal</option>
                        <option value="ES" <?php if($_GET['uf']=="ES" ) echo "selected" ; ?>>Espírito Santo</option>
                        <option value="GO" <?php if($_GET['uf']=="GO" ) echo "selected" ; ?>>Goiás</option>
                        <option value="MA" <?php if($_GET['uf']=="MA" ) echo "selected" ; ?>>Maranhão</option>
                        <option value="MT" <?php if($_GET['uf']=="MT" ) echo "selected" ; ?>>Mato Grosso</option>
                        <option value="MS" <?php if($_GET['uf']=="MS" ) echo "selected" ; ?>>Mato Grosso do Sul</option>
                        <option value="MG" <?php if($_GET['uf']=="MG" ) echo "selected" ; ?>>Minas Gerais</option>
                        <option value="PA" <?php if($_GET['uf']=="PA" ) echo "selected" ; ?>>Pará</option>
                        <option value="PB" <?php if($_GET['uf']=="PB" ) echo "selected" ; ?>>Paraíba</option>
                        <option value="PR" <?php if($_GET['uf']=="PR" ) echo "selected" ; ?>>Paraná</option>
                        <option value="PE" <?php if($_GET['uf']=="PE" ) echo "selected" ; ?>>Pernambuco</option>
                        <option value="PI" <?php if($_GET['uf']=="PI" ) echo "selected" ; ?>>Piauí</option>
                        <option value="RJ" <?php if($_GET['uf']=="RJ" ) echo "selected" ; ?>>Rio de Janeiro</option>
                        <option value="RN" <?php if($_GET['uf']=="RN" ) echo "selected" ; ?>>Rio Grande do Norte
                        </option>
                        <option value="RS" <?php if($_GET['uf']=="RS" ) echo "selected" ; ?>>Rio Grande do Sul</option>
                        <option value="RO" <?php if($_GET['uf']=="RO" ) echo "selected" ; ?>>Rondônia</option>
                        <option value="RR" <?php if($_GET['uf']=="RR" ) echo "selected" ; ?>>Roraima</option>
                        <option value="SC" <?php if($_GET['uf']=="SC" ) echo "selected" ; ?>>Santa Catarina</option>
                        <option value="SP" <?php if($_GET['uf']=="SP" ) echo "selected" ; ?>>São Paulo</option>
                        <option value="SE" <?php if($_GET['uf']=="SE" ) echo "selected" ; ?>>Sergipe</option>
                        <option value="TO" <?php if($_GET['uf']=="TO" ) echo "selected" ; ?>>Tocantins</option>

                    </select>

                    <p class="text-danger small mb-0 erros"></p>

                </div>

            </div>

            <button class="btn btn-warning btn-sm w-100 mt-2" style="color: white;">
                <strong>Alterar</strong>
            </button>

        </form>
    </div>

    <script src="../frontend/js/formValidation.js"></script>
</body>

</html>