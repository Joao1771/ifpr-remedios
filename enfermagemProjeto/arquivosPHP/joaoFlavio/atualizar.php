<?php

$url = "http://localhost:8080/remedios/api/remedios";

$data = [
    "id" => $_POST['id'],
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

$json = json_encode($data);

$options = [
    "http" => [
        "header"  => "Content-Type: application/json\r\n",
        "method"  => "PUT",
        "content" => $json
    ]
];

$context = stream_context_create($options);

file_get_contents($url, false, $context);

// volta pra lista
header("Location: paginaDosRemedios_AcessoProfessor.php?editado=1");
exit;
?>