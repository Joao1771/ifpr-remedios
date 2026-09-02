<?php
include("verificarSessao.php");

if ($_SESSION["tipo"] !== "Professor" ) {
    header("Location: /enfermagemProjeto/frontend/login.html");
    exit;
}
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

    <table class="table table-bordered table-striped" id="tabelaRemedios">

        <thead class="table-dark">
            <tr>
                <th>NOME</th>
                <th>BULA</th>
                <th>TIPO</th>
                <th>RESTRIÇÃO</th>
                <th>CONTRA INDICAÇÕES</th>
                <th>EFEITOS</th>
                <th>VALIDADE</th>
                <th>AÇÕES</th>
            </tr>
        </thead>

        <tbody>

<?php
$url = "http://localhost:8080/remedios/api/remedios";

$json = file_get_contents($url);

$remedios = json_decode($json, true);

if (!empty($remedios)) {

    foreach ($remedios as $row) {

    $urlVerMais =
        "verMais.php?id=" . $row['id'] .
        "&tarja=" . urlencode($row['tarja']) .
        "&substancia=" . urlencode($row['substancia']) .
        "&substanciaTipo=" . urlencode($row['substanciaTipo']) .
        "&publicoAlvo=" . urlencode(implode(", ", $row['publicoAlvo'] ?? [])) .
        "&empresa=" . urlencode($row['empresa']) .
        "&cnpj=" . urlencode($row['cnpj']) .
        "&cidade=" . urlencode($row['cidade']) .
        "&uf=" . urlencode($row['uf']) .
        "&restricao=" . urlencode($row['restricao']) .
        "&contraIndicacoes=" . urlencode($row['contraIndicacoes']) .
        "&efeitos=" . urlencode($row['efeitos']) .
        "&validade=" . urlencode($row['validade']) .
        "&conservacao=" . urlencode($row['conservacao']);

    $urlEditar =
        "editar.php?id=" . $row['id'] .
        "&nome_paraAlterar=" . urlencode($row['nome']) .
        "&bula_paraAlterar=" . urlencode($row['bula']) .
        "&tipo_paraAlterar=" . urlencode($row['tipo']) .
        "&restricao_paraAlterar=" . urlencode($row['restricao']) .
        "&contraIndicacoes_paraAlterar=" . urlencode($row['contraIndicacoes']) .
        "&efeitos_paraAlterar=" . urlencode($row['efeitos']) .
        "&validade_paraAlterar=" . urlencode($row['validade']) .
        "&conservacao_paraAlterar=" . urlencode($row['conservacao']) .
        "&tarja_paraAlterar=" . urlencode($row['tarja']) .
        "&substancia_paraAlterar=" . urlencode($row['substancia']) .
        "&substancia_tipo_paraAlterar=" . urlencode($row['substanciaTipo']) .
        "&publicoAlvo_paraAlterar=" . urlencode(implode(",", $row['publicoAlvo'] ?? [])) .
        "&empresa_paraAlterar=" . urlencode($row['empresa']) .
        "&cnpj_paraAlterar=" . urlencode($row['cnpj']) .
        "&cidade_paraAlterar=" . urlencode($row['cidade']) .
        "&uf_paraAlterar=" . urlencode($row['uf']);

    echo "<tr>";

    echo "<td>{$row['nome']}</td>";

    echo "<td>
            <a href='/enfermagemProjeto/{$row['bula']}' target='_blank'>
                Ver Bula
            </a>
          </td>";

    echo "<td>{$row['tipo']}</td>";
    echo "<td>{$row['restricao']}</td>";
    echo "<td>{$row['contraIndicacoes']}</td>";
    echo "<td>{$row['efeitos']}</td>";
    echo "<td>{$row['validade']}</td>";

    echo "<td>

            <a href='{$urlVerMais}'
               class='link-offset-2 link-underline link-underline-opacity-100'
               style='margin-right:10px;'>
                Ver Mais
            </a>

            <a href='{$urlEditar}'
               class='btn btn-warning'>
                Editar
            </a>

            <a href='excluir.php?id={$row['id']}'
               class='btn btn-danger'
               style='margin-left:20px;'>
                Excluir
            </a>

          </td>";

    echo "</tr>";
    }}
else {

    echo "
        <tr>
            <td colspan='9' class='text-center'>
                Nenhum remédio encontrado
            </td>
        </tr>
    ";
}
?>

        </tbody>
    </table>
</div>

<script>
    // script para pesquisa que troca o estilo dos tr para ficar invisíveis caso não sejam pesquisados
    document.getElementById('filtroInput').addEventListener('keyup', function () {
    let filtro = this.value.toLowerCase();
    let linhas = document.querySelectorAll('#tabelaRemedios tbody tr');
    for (let i = 0; i < linhas.length; i++) {
        let nome = linhas[i].cells[0].textContent.toLowerCase();
        linhas[i].style.display = nome.includes(filtro) ? '' : 'none';
    }
});

</script>

</body>
</html>