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
            include("conexaoBanco.php"); 

            $sql = file_get_contents("../bancoDeDados/listarRemedios.sql");

            $result = $conn->query($sql);
            

            if ($result->num_rows > 0) {
                while ($row = $result->fetch_assoc()) {
                    echo "<tr>";
                    echo "<td>". $row['NOME'] ."</td>";
                    echo "<td><a href='". $row['BULA'] ."' target='_blank'>Ver Bula</a></td>";
                    echo "<td>". $row['TIPO'] ."</td>";
                    echo "<td>". $row['PUBLICO_ALVO'] ."</td>";
                    echo "<td>". $row['RESTRICAO'] ."</td>";
                    echo "<td>". $row['CONTRA_INDICACOES'] ."</td>";
                    echo "<td>". $row['SUBSTANCIA'] ."</td>";
                    echo "<td>". $row['EFEITOS'] ."</td>";
                }
            } else {
                echo "<tr><td colspan='7' class='text-center'>Nenhum registro encontrado</td></tr>";
            }

            $conn->close();
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