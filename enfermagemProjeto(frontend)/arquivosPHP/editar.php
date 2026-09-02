<?php
include("verificarSessao.php");
?>

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

                    <input name="nome_paraAlterar" id="nome" class="form-control form-control-sm" maxlength="95"
                        value="<?php echo $_GET['nome_paraAlterar']; ?>">

                    <p class="text-danger small mb-0 erros"></p>
                </div>

                <input type="hidden" name="bulaAtual" value="<?php echo $_GET['bula_paraAlterar'] ?? ''; ?>">

                <div class="col-md-6 mb-2">
                    <p class="small">
                        Bula atual:
                        <a href="/enfermagemProjeto/<?php echo $_GET['bula_paraAlterar'] ?? ''; ?>"
                            target="_blank">
                            Ver PDF
                        </a>
                    </p>
                    <input
                        type="file"
                        name="bula_paraAlterar"
                        id="bula"
                        class="form-control form-control-sm"
                        accept="application/pdf">

                    <p class="text-danger small mb-0 erros"></p>

                </div>

                <div class="col-md-6 mb-2">
                    <label class="form-label small" for="tipoRemedio"> Tipo
                    </label>

                    <input name="tipoRemedio_paraAlterar" id="tipoRemedio" class="form-control form-control-sm" maxlength="15"
                        value="<?php echo $_GET['tipo_paraAlterar']; ?>">

                    <p class="text-danger small mb-0 erros"></p>
                </div>

                <div class="col-md-6 mb-2">

                    <label class="form-label small"> Público-Alvo
                    </label>

                    <?php
                    $publicos = explode(",", $_GET['publicoAlvo_paraAlterar']);
                    ?>

                    <div class="row">

                        <div class="col-6">
                            <div class="form-check">
                                <input
                                    class="form-check-input"
                                    type="checkbox"
                                    name="publicoAlvo_paraAlterar[]"
                                    value="1"
                                    <?php if (in_array("Crianças", $publicos)) echo "checked"; ?>>

                                <label class="form-check-label" for="criancas">
                                    Crianças
                                </label>
                            </div>
                        </div>

                        <div class="col-6">
                            <div class="form-check">
                                <input
                                    class="form-check-input"
                                    type="checkbox"
                                    name="publicoAlvo_paraAlterar[]"
                                    value="2"
                                    <?php if (in_array("Adultos", $publicos)) echo "checked"; ?>>

                                <label class="form-check-label" for="adultos">
                                    Adultos
                                </label>
                            </div>
                        </div>

                        <div class="col-6">
                            <div class="form-check">
                                <input
                                    class="form-check-input"
                                    type="checkbox"
                                    name="publicoAlvo_paraAlterar[]"
                                    value="3"
                                    <?php if (in_array("Idosos", $publicos)) echo "checked"; ?>>

                                <label class="form-check-label" for="idosos">
                                    Idosos
                                </label>
                            </div>
                        </div>

                        <div class="col-6">
                            <div class="form-check">
                                <input
                                    class="form-check-input"
                                    type="checkbox"
                                    name="publicoAlvo_paraAlterar[]"
                                    value="4"
                                    <?php if (in_array("Gestantes", $publicos)) echo "checked"; ?>>

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

                    <input name="restricao_paraAlterar" id="restricao" class="form-control form-control-sm" maxlength="245"
                        value="<?php echo $_GET['restricao_paraAlterar']; ?>">

                    <p class="text-danger small mb-0 erros"></p>
                </div>

                <div class="col-md-6 mb-2">
                    <label class="form-label small" for="contraIndicacoes"> Contraindicações
                    </label>

                    <input name="contraIndicacoes_paraAlterar" id="contraIndicacoes"
                        class="form-control form-control-sm" maxlength="1995"
                        value="<?php echo $_GET['contraIndicacoes_paraAlterar']; ?>">

                    <p class="text-danger small mb-0 erros"></p>
                </div>

                <div class="col-md-6 mb-2">
                    <label class="form-label small" for="efeitos"> Efeitos
                    </label>

                    <input name="efeitos_paraAlterar" id="efeitos" class="form-control form-control-sm" maxlength="995"
                        value="<?php echo $_GET['efeitos_paraAlterar']; ?>">

                    <p class="text-danger small mb-0 erros"></p>
                </div>

                <div class="col-md-6 mb-2">
                    <label class="form-label small" for="substancia"> Substância presente no medicamento
                    </label>

                    <input name="substancia_paraAlterar" class="form-control form-control-sm" id="substancia"
                        maxlength="195" value="<?php echo $_GET['substancia_paraAlterar']; ?>">

                    <p class="text-danger small mb-0 erros"></p>
                </div>

                <div class="col-md-6 mb-2">
                    <label class="form-label small" for="substancia_tipo"> Tipo de substância
                    </label>

                    <input name="substancia_tipo_paraAlterar" class="form-control form-control-sm" id="substancia_tipo"
                        maxlength="95" value="<?php echo $_GET['substancia_tipo_paraAlterar']; ?>">

                    <p class="text-danger small mb-0 erros"></p>
                </div>

                <div class="col-md-6 mb-2">

                    <label class="form-label small" for="tarja"> Tarja
                    </label>

                    <select name="tarja_paraAlterar" class="form-select form-select-sm" id="tarja">

                        <option value="">Selecione a Tarja</option>
                        <option value="1"
                            <?php if ($_GET['tarja_paraAlterar'] == "Sem Tarja") echo "selected"; ?>>
                            Sem Tarja
                        </option>

                        <option value="2"
                            <?php if ($_GET['tarja_paraAlterar'] == "Vermelha") echo "selected"; ?>>
                            Vermelha
                        </option>

                        <option value="3"
                            <?php if ($_GET['tarja_paraAlterar'] == "Preta") echo "selected"; ?>>
                            Preta
                        </option>

                    </select>

                    <p class="text-danger small mb-0 erros"></p>

                </div>

                <div class="col-md-6 mb-2">
                    <label class="form-label small" for="validade"> Prazo de validade
                    </label>

                    <input name="validade_paraAlterar" class="form-control form-control-sm" id="validade"
                        maxlength="245" placeholder="em meses ou anos" value="<?php echo $_GET['validade_paraAlterar']; ?>">

                    <p class="text-danger small mb-0 erros"></p>
                </div>

                <div class="col-md-6 mb-2">
                    <label class="form-label small" for="conservacao"> Modo de conservação
                    </label>

                    <input name="conservacao_paraAlterar" class="form-control form-control-sm" id="conservacao"
                        maxlength="95" placeholder="Ex: Local seco e refrigerado" value="<?php echo $_GET['conservacao_paraAlterar']; ?>">

                    <p class="text-danger small mb-0 erros"></p>
                </div>

                <div class="d-flex align-items-center my-3">

                    <div class="flex-grow-1 border-top"></div>

                    <span class="px-3 fw-semibold text-warning">
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
                        value="<?php echo $_GET['empresa_paraAlterar']; ?>"
                        class="form-control form-control-sm"
                        placeholder="Digite o nome da empresa"
                        readonly>

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
                        style="display:inline-block;">
                        Trocar empresa
                    </button>
                </div>


                <div class="col-md-6 mb-2">

                    <label class="form-label small">CNPJ</label>

                    <input
                        type="text"
                        id="cnpj"
                        class="form-control form-control-sm"
                        value="<?php echo $_GET['cnpj_paraAlterar']; ?>"
                        readonly>

                    <p class="text-danger small mb-0 erros"></p>

                </div>


                <div class="col-md-6 mb-2">

                    <label class="form-label small">Cidade</label>

                    <input
                        type="text"
                        id="cidade"
                        class="form-control form-control-sm"
                        value="<?php echo $_GET['cidade_paraAlterar']; ?>"
                        readonly>

                    <p class="text-danger small mb-0 erros"></p>

                </div>


                <div class="col-md-6 mb-2">

                    <label class="form-label small">Estado</label>

                    <input
                        type="text"
                        id="uf"
                        class="form-control form-control-sm"
                        value="<?php echo $_GET['uf_paraAlterar']; ?>"
                        readonly>

                    <p class="text-danger small mb-0 erros"></p>

                </div>


            </div>

            <button class="btn btn-warning btn-sm w-100 mt-2" style="color: white;">
                <strong>Alterar</strong>
            </button>

        </form>
    </div>

    <script src="../frontend/js/formValidation.js"></script>
    <script src="../frontend/js/empresasSearch.js"></script>
    <script>
        // Ao carregar, busca o ID da empresa atual pelo nome e já preenche o hidden
        (async () => {
            const nomeAtual = document.getElementById("empresaBusca").value.trim();

            const response = await fetch(
                `http://localhost:8080/remedios/api/empresas/busca?nome=${encodeURIComponent(nomeAtual)}`
            );
            const empresas = await response.json();

            // Encontra a empresa pelo nome do input empresaBusca
            const empresa = empresas.find(e => e.nome === nomeAtual);
            // preenche o valor do input com o id da empresa correspondente
            document.getElementById("empresa").value = empresa.id;

        })();
    </script>
</body>

</html>