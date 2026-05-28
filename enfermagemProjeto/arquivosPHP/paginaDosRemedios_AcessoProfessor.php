<?php
include("conexaoBanco.php"); 
?>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Remédios Cadastrados</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>

<div class="container mt-4">

    <h3>Remédios Cadastrados</h3>

    <a href="adicionar.php" class="btn btn-success mb-3">
        Adicionar Remédio
    </a>

    <a href="cadastrarProfessor.php" class="btn btn-primary mb-3">
        Cadastrar Mais Professores
    </a>

    <input 
        class="form-control mb-3"
        id="filtroInput"
        type="text"
        placeholder="Pesquisar por nome..."
    >

    <?php
    if (isset($_GET['sucesso'])) {
        echo "<div class='alert alert-success'>
                Remédio salvo com sucesso!
              </div>";
    }

    if (isset($_GET['editado'])) {
        echo "<div class='alert alert-success'>
                Remédio atualizado com sucesso!
              </div>";
    }

    if (isset($_GET['excluido'])) {
        echo "<div class='alert alert-success'>
                Remédio excluído com sucesso!
              </div>";
    }
    ?>

    <table class="table table-bordered table-striped" id="tabelaProdutos">

        <thead class="table-dark">
            <tr>
                <th>NOME</th>
                <th>BULA</th>
                <th>TIPO</th>
                <th>PÚBLICO ALVO</th>
                <th>RESTRIÇÃO</th>
                <th>CONTRA INDICAÇÕES</th>
                <th>SUBSTÂNCIAS</th>
                <th>EFEITOS</th>
                <th>AÇÕES</th>
            </tr>
        </thead>

        <tbody>

<?php

$sql = file_get_contents("../bancoDeDados/listarRemedios.sql");

$result = $conn->query($sql);

if ($result->num_rows > 0) {

    while ($row = $result->fetch_assoc()) {

        $linkEditar = "editar.php?" .

        "id=" . urlencode($row['ID_REMEDIO']) .

        "&nome=" . urlencode($row['NOME']) .

        "&bula=" . urlencode($row['BULA']) .

        "&tipo=" . urlencode($row['TIPO']) .

        "&publicoAlvo=" . urlencode($row['PUBLICO_ALVO']) .

        "&restricao=" . urlencode($row['RESTRICAO']) .

        "&contraIndicacoes=" . urlencode($row['CONTRA_INDICACOES']) .

        "&efeitos=" . urlencode($row['EFEITOS']) .

        "&empresa=" . urlencode($row['EMPRESA'] ?? '') .

        "&cnpj=" . urlencode($row['CNPJ'] ?? '') .

        "&cidade=" . urlencode($row['CIDADE'] ?? '') .

        "&uf=" . urlencode($row['UF'] ?? '') .

        "&substancia=" . urlencode($row['SUBSTANCIA'] ?? '') .

        "&substancia_tipo=" . urlencode($row['SUBSTANCIA_TIPO'] ?? '') .

        "&tarja=" . urlencode($row['TARJA'] ?? '') .

        "&validade=" . urlencode($row['VALIDADE'] ?? '') .

        "&conservacao=" . urlencode($row['CONSERVACAO'] ?? '');



        echo "<tr>";

        echo "<td>" . htmlspecialchars($row['NOME']) . "</td>";

        echo "<td>
                <a href='../"  . htmlspecialchars($row['BULA']) . "' target='_blank'>
                    Ver Bula
                </a>
              </td>";

        echo "<td>" . htmlspecialchars($row['TIPO']) . "</td>";

        echo "<td>" . htmlspecialchars($row['PUBLICO_ALVO']) . "</td>";

        echo "<td>" . htmlspecialchars($row['RESTRICAO']) . "</td>";

        echo "<td>" . htmlspecialchars($row['CONTRA_INDICACOES']) . "</td>";

        echo "<td>" . htmlspecialchars($row['SUBSTANCIA']) . "</td>";

        echo "<td>" . htmlspecialchars($row['EFEITOS']) . "</td>";

        echo "<td>

            <a 
                href='verMais.php?id=" . $row['ID_REMEDIO'] . "'
                style='margin-right:10px;'
                class='link-offset-2 link-underline link-underline-opacity-100'
            >
                Ver Mais
            </a>

            <a 
                href='$linkEditar'
                class='btn btn-warning'
            >
                Editar
            </a>

            <a 
                href='excluir.php?id=" . $row['ID_REMEDIO'] . "'
                style='margin-left:20px; margin-right:5px;'
                class='btn btn-danger'
                onclick='return confirm(\"Deseja realmente excluir este remédio?\")'
            >
                Excluir
            </a>

        </td>";

        echo "</tr>";
    }

} else {

    echo "
        <tr>
            <td colspan='9' class='text-center'>
                Nenhum registro encontrado
            </td>
        </tr>
    ";
}

$conn->close();
?>

        </tbody>
    </table>
</div>

<script>
document.getElementById('filtroInput').addEventListener('keyup', function () {

    let filtro = this.value.toLowerCase();
    let linhas = document.querySelectorAll('#tabelaProdutos tbody tr');
    for (let i = 0; i < linhas.length; i++) {
        let nome = linhas[i].cells[0].textContent.toLowerCase();
        linhas[i].style.display = nome.includes(filtro) ? '' : 'none';
    }
});

</script>

</body>
</html>