<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tabela de Produtos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-4">
    <h3> Lista de Produtos</h3>
    <table class="table table-bordered table-striped">
        <thead class="thead-dark"">
            <tr>
                  
                    <th  style= "background-color: 	black; color: white;"> NOME </th>
                    <th style= "background-color: 	black; color: white;"> BULA </th>
                     <th style= "background-color: 	black; color: white;">PUBLICO_ALVO </th>
                    <th style= "background-color: 	black; color: white;"> PRECAUCOES </th>
                     <th style= "background-color: 	black; color: white;">CONTRA_INDICACOES </th>
                     <th style= "background-color: 	black; color: white;">COMPOSICAO</th>
                     <th style= "background-color: 	black; color: white;">EFEITOS</th>
            </tr>
        </thead>
        <tbody>
        <?php
        $url = "http://localhost:8080/remedios/api/remedios";

        // chama a API Java
        $response = file_get_contents($url);

        // transforma JSON em array PHP
        $remedios = json_decode($response, true);

        if ($remedios && count($remedios) > 0) {
            foreach ($remedios as $r) {

                echo "<tr>";

                echo "<td>" . $r['nome'] . "</td>";
                echo "<td>" . $r['bula'] . "</td>";

                // dados da descrição (relacionamento)
                echo "<td>" . ($r['descricao']['publicoAlvo'] ?? '') . "</td>";
                echo "<td>" . ($r['descricao']['precaucoes'] ?? '') . "</td>";
                echo "<td>" . ($r['descricao']['contraIndicacoes'] ?? '') . "</td>";
                echo "<td>" . ($r['descricao']['composicao'] ?? '') . "</td>";
                echo "<td>" . ($r['descricao']['efeitos'] ?? '') . "</td>";

                echo "</tr>";
            }
        } else {
            echo "<tr><td colspan='7'>Nenhum registro encontrado</td></tr>";
        }
        ?>
        </tbody>
    </table>
</div>

</body>
</html>