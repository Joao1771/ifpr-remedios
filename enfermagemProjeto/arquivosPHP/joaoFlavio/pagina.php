<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tabela de Produtos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-4">
    <h3>Lista de Produtos</h3>
    <table class="table table-bordered table-striped">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Código Produto</th>
                <th>Tipo</th>
                <th>Descrição</th>
            </tr>
        </thead>
        <tbody>
            <?php
            include("conexaoBanco.php"); 

            $sql = "SELECT * FROM PRESCRICOES";
            $result = $conn->query($sql);

            if ($result->num_rows > 0) {
                while ($row = $result->fetch_assoc()) {
                    // echo "<tr>";
                    // echo "<td>". $row['id'] ."</td>";
                    // echo "<td>". $row['codigo_produto'] ."</td>";
                    // echo "<td>". $row['codigo_tipo'] ."</td>";
                    // echo "<td>". $row['PRESCRICOES'] ."</td>";
                    // echo "</tr>";
                }
            } else {
                echo "<tr><td colspan='4' class='text-center'>Nenhum registro encontrado</td></tr>";
            }

            $conn->close(); // fecha a conexão
            ?>
        </tbody>
    </table>
</div>

</body>
</html>