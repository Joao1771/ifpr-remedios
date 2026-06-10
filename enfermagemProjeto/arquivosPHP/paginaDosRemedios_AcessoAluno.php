<?php
//include("verificarSessao.php");
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
    <!-- Campo de pesquisa simples -->
    <input class="form-control mb-3" id="filtroInput" type="text" placeholder="Pesquisar por nome...">

    <table class="table table-bordered table-striped" id="tabelaProdutos";
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
                 
            </tr>
        </thead>

        <tbody>
            <?php
            $url = "http://localhost:8080/remedios/api/remedios";

            $json = file_get_contents($url);

            $remedios = json_decode($json, true);
            

            if (!empty($remedios)) {

                foreach ($remedios as $row) {
                    echo "<tr>";
                    echo "<td>". $row['nome'] ."</td>";
                    echo "<td><a href='". $row['bula'] ."' target='_blank'>Ver Bula</a></td>";
                    echo "<td>". $row['tipo'] ."</td>";
                    echo "<td>". implode(", ", $row['publicoAlvo']) ."</td>";
                    echo "<td>". $row['restricao'] ."</td>";
                    echo "<td>". $row['contraIndicacoes'] ."</td>";
                    echo "<td>". $row['substancia'] ."</td>";
                    echo "<td>". $row['efeitos'] ."</td>";
                }
            } else {
                echo "<tr><td colspan='7' class='text-center'>Nenhum registro encontrado</td></tr>";
            }

            ?>
        </tbody>
    </table>
</div>


<script>
     document.getElementById('filtroInput').addEventListener('keyup', function() {
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