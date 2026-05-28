<?php

// URL da sua API Java
$url = "http://localhost:8080/remedios/api/remedios";

// monta o JSON igual o esperado no Java
$data = [
    "nome" => $_POST['nome'],
    "bula" => $_POST['bula'],
    "PRESCRICOES" => [
        "publicoAlvo" => $_POST['publicoAlvo'],
        "precaucoes" => $_POST['precaucoes'],
        "contraIndicacoes" => $_POST['contraIndicacoes'],
        "composicao" => $_POST['composicao'],
        "efeitos" => $_POST['efeitos']
    ]
];

// converte para JSON
$json = json_encode($data);

// configura requisição POST
$options = [
    "http" => [
        "header"  => "Content-Type: application/json",
        "method"  => "POST",
        "content" => $json
    ]
];

$context = stream_context_create($options);

// envia para API Java
$result = file_get_contents($url, false, $context);

// redireciona de volta pra lista
header("Location: paginaDosRemedios_AcessoProfessor.php?sucesso=1");
exit;
?>