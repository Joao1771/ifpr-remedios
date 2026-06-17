<?php
include("verificarSessao.php");
?>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Confirmação</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light d-flex justify-content-center align-items-center vh-100">

    <div class="card shadow border-0" style="width: 350px;">

        <form action="chamarFuncoes.php?acao=excluir&id=<?php echo $_GET['id']; ?>" method="POST">

            <div class="card-header bg-danger text-white text-center">
                Confirmar Exclusão
            </div>

            <div class="card-body text-center">

                <p class="mb-4">
                    Deseja realmente excluir este registro?
                </p>

                <div class="d-flex justify-content-center gap-2">

                    <a href="paginaDosRemedios_AcessoProfessor.php" class="btn btn-secondary">
                        Cancelar
                    </a>

                    <button type="submit" class="btn btn-danger">
                        Sim, Excluir
                    </button>

                </div>

            </div>

        </form>

    </div>

</body>
</html>