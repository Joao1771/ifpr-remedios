<?php

if (!isset($_GET['id'])) {
    header("Location: paginaDosRemedios_AcessoProfessor.php");
    exit;
}

$id = $_GET['id'];

// URL da API com id
$url = "http://localhost:8080/remedios/api/remedios?id=" . $id;

// configuração do DELETE
$options = [
    "http" => [
        "method" => "DELETE"
    ]
];

$context = stream_context_create($options);

// envia requisição DELETE
file_get_contents($url, false, $context);

// volta pra lista
header("Location: paginaDosRemedios_AcessoProfessor.php?excluido=1");
exit;
?>