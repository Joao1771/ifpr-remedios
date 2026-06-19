<?php
include("verificarSessao.php");
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
    <input class="form-control mb-3" id="filtroInput" type="text" placeholder="Pesquisar por nome...">

    <table class="table table-bordered table-striped" id="tabelaRemedios";
    >
        <thead class="table-dark">
            <tr>
                 <th>NOME</th>
                 <th>BULA</th>  
                 <th>TIPO</th>  
                 <th>PUBLICO ALVO</th>
                 <th>PRECAUÇÕES</th>
                 <th>CONTRA INDICAÇÕES</th>
                 <th>SUBSTÂNCIA</th>
                 <th>EFEITOS</th>
                 <th>VER MAIS</th>
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

                    echo "<tr>";
                    echo "<td>". $row['nome'] ."</td>";
                    echo "<td><a href='". $row['bula'] ."' target='_blank'>Ver Bula</a></td>";
                    echo "<td>". $row['tipo'] ."</td>";
                    echo "<td>". implode(", ", $row['publicoAlvo']) ."</td>";
                    echo "<td>". $row['restricao'] ."</td>";
                    echo "<td>". $row['contraIndicacoes'] ."</td>";
                    echo "<td>". $row['substancia'] ."</td>";
                    echo "<td>". $row['efeitos'] ."</td>";
                    echo "<td>

                        <a href='{$urlVerMais}'
                        class='link-offset-2 link-underline link-underline-opacity-100'
                        style='margin-right:10px;'>
                            Ver Mais
                        </a>";
                }
            } else {
                echo "<tr><td colspan='7' class='text-center'>Nenhum registro encontrado</td></tr>";
            }

            ?>
        </tbody>
    </table>
</div>


<script>
    // script para pesquisa que troca o estilo dos tr para ficar invisíveis caso não sejam pesquisados
     document.getElementById('filtroInput').addEventListener('keyup', function() {
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