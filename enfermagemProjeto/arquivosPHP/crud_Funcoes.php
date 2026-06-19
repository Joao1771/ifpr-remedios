<?php

// Função de registro
function adicionar(
    $nome,
    $bula,
    $tipoRemedio,
    $publicoAlvo,
    $restricao,
    $contraIndicacoes,
    $efeitos,
    $empresa,
    $cnpj,
    $cidade,
    $uf,
    $substancia,
    $substancia_tipo,
    $tarja,
    $validade,
    $conservacao
) {

$dados = [

    "nome" => $nome,
    "bula" => $bula,
    "tipo" => $tipoRemedio,

    "idEmpresa" => (int)$empresa,
    "idTarja" => (int)$tarja,

    "idPublicoAlvo" => array_map('intval', $publicoAlvo),

    "restricao" => $restricao,
    "contraIndicacoes" => $contraIndicacoes,
    "efeitos" => $efeitos,
    "validade" => $validade,
    "conservacao" => $conservacao,

    "substancia" => $substancia,
    "substanciaTipo" => $substancia_tipo
];

    $json = json_encode($dados);

    $ch = curl_init(
        "http://localhost:8080/remedios/api/remedios"
    );

    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

    curl_setopt($ch, CURLOPT_POST, true);

    curl_setopt($ch, CURLOPT_HTTPHEADER, [
        "Content-Type: application/json"
    ]);

    curl_setopt($ch, CURLOPT_POSTFIELDS, $json);

    $resposta = curl_exec($ch);

    $httpCode = curl_getinfo($ch, CURLINFO_HTTP_CODE);

    curl_close($ch);


    if ($httpCode == 201 || $httpCode == 200) {

        header(
            "Location: paginaDosRemedios_AcessoProfessor.php?sucesso=1"
        );

        exit;

    } else {

        echo "Erro ao cadastrar remédio.";
        echo "<br>";
        echo $resposta;
    }
}



function editar(
    $id,
    $nome_paraAlterar,
    $bula_paraAlterar,
    $tipoRemedio_paraAlterar,
    $publicoAlvo_paraAlterar,
    $restricao_paraAlterar,
    $contraIndicacoes_paraAlterar,
    $efeitos_paraAlterar,
    $empresa_paraAlterar,
    $substancia_paraAlterar,
    $substancia_tipo_paraAlterar,
    $tarja_paraAlterar,
    $validade_paraAlterar,
    $conservacao_paraAlterar
) {

    $dados = [

        "nome" => $nome_paraAlterar,
        "bula" => $bula_paraAlterar,
        "tipo" => $tipoRemedio_paraAlterar,

        "idEmpresa" => (int)$empresa_paraAlterar,
        "idTarja" => (int)$tarja_paraAlterar,

        "idPublicoAlvo" => array_map(
            'intval',
            $publicoAlvo_paraAlterar ?? []
        ),

        "restricao" => $restricao_paraAlterar,
        "contraIndicacoes" => $contraIndicacoes_paraAlterar,
        "efeitos" => $efeitos_paraAlterar,
        "validade" => $validade_paraAlterar,
        "conservacao" => $conservacao_paraAlterar,

        "substancia" => $substancia_paraAlterar,
        "substanciaTipo" => $substancia_tipo_paraAlterar
    ];

    $json = json_encode(
        $dados,
        JSON_UNESCAPED_UNICODE
    );

    $curl = curl_init(
        "http://localhost:8080/remedios/api/remedios/" . $id
    );

    curl_setopt_array($curl, [
        CURLOPT_RETURNTRANSFER => true,
        CURLOPT_CUSTOMREQUEST => "PUT",
        CURLOPT_HTTPHEADER => [
            "Content-Type: application/json"
        ],
        CURLOPT_POSTFIELDS => $json
    ]);

    $resposta = curl_exec($curl);
    $httpCode = curl_getinfo($curl, CURLINFO_HTTP_CODE);

    curl_close($curl);

    if ($httpCode >= 200 && $httpCode < 300) {

        header(
            "Location: paginaDosRemedios_AcessoProfessor.php?editado=1"
        );
        exit;
    }

    echo "Erro ao editar.<br>";
    echo $resposta;
}

function excluir($id)
{
    // Buscar o remédio para obter a bula
    $urlBusca = "http://localhost:8080/remedios/api/remedios/" . $id;

    $ch = curl_init($urlBusca);

    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

    $response = curl_exec($ch);

    curl_close($ch);

    $remedio = json_decode($response, true);

    // Apagar o PDF do frontend se existir
    if (!empty($remedio['bula'])) {

        $caminhoFisico =
            $_SERVER['DOCUMENT_ROOT'] .
            "/enfermagemProjeto/" .
            $remedio['bula'];

        if (file_exists($caminhoFisico)) {
            unlink($caminhoFisico);
        }
    }

    // Excluir o remédio pela API
    $urlDelete =
        "http://localhost:8080/remedios/api/remedios/" . $id;

    $ch = curl_init($urlDelete);

    curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "DELETE");
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

    $response = curl_exec($ch);

    $httpCode = curl_getinfo($ch, CURLINFO_HTTP_CODE);

    curl_close($ch);

    if ($httpCode == 200 || $httpCode == 204) {

        header(
            "Location: paginaDosRemedios_AcessoProfessor.php?excluido=1"
        );

        exit;
    }

    echo "Erro ao excluir. Código HTTP: " . $httpCode;
}
?>