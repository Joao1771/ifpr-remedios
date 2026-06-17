<?php
include("verificarSessao.php");
?>

<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <title>Ver Mais</title>

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>

<body class="bg-light">

<div class="container py-5">

    <a href="paginaDosRemedios_AcessoProfessor.php"
       class="btn btn-outline-danger mb-4">
        Voltar
    </a>

    <div class="card shadow-sm border-0 rounded-4">

        <div class="card-header bg-primary text-white rounded-top-4 py-3">
            <h3 class="mb-0">Informações do Medicamento</h3>
        </div> 

        <div class="card-body p-4">

            <div class="row g-4">

                <!-- TARJA -->
                <div class="col-md-6">
                    <label class="form-label fw-semibold text-secondary">
                        Tarja
                    </label>
                    <input
                        type="text"
                        class="form-control"
                        readonly
                        value="<?= $_GET['tarja'] ?? '' ?>">
                </div>

                <!-- PÚBLICO ALVO -->
                <div class="col-md-6">
                    <label class="form-label fw-semibold text-secondary">
                        Público-Alvo
                    </label>
                    <input
                        type="text"
                        class="form-control"
                        readonly
                        value="<?= $_GET['publicoAlvo'] ?? '' ?>">
                </div>

                <!-- SUBSTÂNCIA -->
                <div class="col-md-6">
                    <label class="form-label fw-semibold text-secondary">
                        Substância
                    </label>
                    <input
                        type="text"
                        class="form-control"
                        readonly
                        value="<?= $_GET['substancia'] ?? '' ?>">
                </div>

                <!-- TIPO SUBSTÂNCIA -->
                <div class="col-md-6">
                    <label class="form-label fw-semibold text-secondary">
                        Tipo da Substância
                    </label>
                    <input
                        type="text"
                        class="form-control"
                        readonly
                        value="<?= $_GET['substanciaTipo'] ?? '' ?>">
                </div>

                <!-- EMPRESA -->
                <div class="col-md-6">
                    <label class="form-label fw-semibold text-secondary">
                        Empresa
                    </label>
                    <input
                        type="text"
                        class="form-control"
                        readonly
                        value="<?= $_GET['empresa'] ?? '' ?>">
                </div>

                <!-- CNPJ -->
                <div class="col-md-6">
                    <label class="form-label fw-semibold text-secondary">
                        CNPJ
                    </label>
                    <input
                        type="text"
                        class="form-control"
                        readonly
                        value="<?= $_GET['cnpj'] ?? '' ?>">
                </div>

                <!-- CIDADE -->
                <div class="col-md-6">
                    <label class="form-label fw-semibold text-secondary">
                        Cidade
                    </label>
                    <input
                        type="text"
                        class="form-control"
                        readonly
                        value="<?= $_GET['cidade'] ?? '' ?>">
                </div>

                <!-- UF -->
                <div class="col-md-6">
                    <label class="form-label fw-semibold text-secondary">
                        UF
                    </label>
                    <input
                        type="text"
                        class="form-control"
                        readonly
                        value="<?= $_GET['uf'] ?? '' ?>">
                </div>

                <!-- RESTRIÇÃO -->
                <div class="col-md-6">
                    <label class="form-label fw-semibold text-secondary">
                        Restrição
                    </label>
                    <input
                        type="text"
                        class="form-control"
                        readonly
                        value="<?= $_GET['restricao'] ?? '' ?>">
                </div>

                <!-- CONTRA INDICAÇÕES -->
                <div class="col-md-6">
                    <label class="form-label fw-semibold text-secondary">
                        Contraindicações
                    </label>
                    <input
                        type="text"
                        class="form-control"
                        readonly
                        value="<?= $_GET['contraIndicacoes'] ?? '' ?>">
                </div>

                <!-- EFEITOS -->
                <div class="col-md-6">
                    <label class="form-label fw-semibold text-secondary">
                        Efeitos
                    </label>
                    <input
                        type="text"
                        class="form-control"
                        readonly
                        value="<?= $_GET['efeitos'] ?? '' ?>">
                </div>

                <!-- VALIDADE -->
                <div class="col-md-6">
                    <label class="form-label fw-semibold text-secondary">
                        Validade
                    </label>
                    <input
                        type="text"
                        class="form-control"
                        readonly
                        value="<?= $_GET['validade'] ?? '' ?>">
                </div>

                <!-- CONSERVAÇÃO -->
                <div class="col-md-12">
                    <label class="form-label fw-semibold text-secondary">
                        Conservação
                    </label>
                    <textarea
                        class="form-control"
                        rows="3"
                        readonly><?= $_GET['conservacao'] ?? '' ?></textarea>
                </div>

            </div>

        </div>
    </div>

</div>

</body>
</html>