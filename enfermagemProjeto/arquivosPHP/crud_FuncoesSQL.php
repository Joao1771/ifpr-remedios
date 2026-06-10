<?php
include "conexaoBanco.php";
mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);
// Função de registro
function adicionar(
    $conn,
    $nome,
    $bula,
    $tipoRemedio,
    $publicoAlvo,
    $restricao,
    $contraIndicacoes,
    $efeitos,
    $empresa,
    $substancia,
    $substancia_tipo,
    $tarja,
    $validade,
    $conservacao
){

    // PRESCRIÇÃO
    $consultaPrescricao = $conn->prepare("
        INSERT INTO PRESCRICOES
        (
            RESTRICAO,
            CONTRA_INDICACOES,
            EFEITOS,
            VALIDADE,
            CONSERVACAO
        )
        VALUES (?, ?, ?, ?, ?)
    ");

    $consultaPrescricao->bind_param(
        "sssss",
        $restricao,
        $contraIndicacoes,
        $efeitos,
        $validade,
        $conservacao
    );

    $consultaPrescricao->execute();

    $idPrescricao = $conn->insert_id;



    // SUBSTÂNCIA
    $consultaSubstancia = $conn->prepare("
        INSERT INTO SUBSTANCIAS
        (
            NOME,
            TIPO
        )
        VALUES (?, ?)
    ");

    $consultaSubstancia->bind_param(
        "ss",
        $substancia,
        $substancia_tipo
    );

    $consultaSubstancia->execute();

    $idSubstancia = $conn->insert_id;



    // REMÉDIO
    $consultaRemedio = $conn->prepare("
        INSERT INTO REMEDIOS
        (
            NOME,
            BULA,
            TIPO,
            ID_EMPRESA,
            ID_PRESCRICAO,
            ID_TARJA,
            ID_SUBSTANCIA
        )
        VALUES (?, ?, ?, ?, ?, ?, ?)
    ");

    $consultaRemedio->bind_param(
        "sssiiii",
        $nome,
        $bula,
        $tipoRemedio,
        $empresa,
        $idPrescricao,
        $tarja,
        $idSubstancia
    );

    $consultaRemedio->execute();

    $idRemedio = $conn->insert_id;



    // PÚBLICO-ALVO
    foreach ($publicoAlvo as $idPublico) {

        $consultaRemedioPublico = $conn->prepare("
            INSERT INTO REMEDIOS_PUBLICO_ALVO
            (
                ID_REMEDIO,
                ID_PUBLICO_ALVO
            )
            VALUES (?, ?)
        ");

        $consultaRemedioPublico->bind_param(
            "ii",
            $idRemedio,
            $idPublico
        );

        $consultaRemedioPublico->execute();

        $consultaRemedioPublico->close();
    }


    header("Location: paginaDosRemedios_AcessoProfessor.php?sucesso=1");
    exit;
}



function editar(
    $conn,
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

    // IDs
    $consultaIds = $conn->prepare("
        SELECT
            R.ID_PRESCRICAO,
            R.ID_TARJA
        FROM REMEDIOS R
        WHERE R.ID_REMEDIO = ?
    ");

    $consultaIds->bind_param("i", $id);
    $consultaIds->execute();

    $resultadoIds = $consultaIds->get_result();
    $dados = $resultadoIds->fetch_assoc();

    $idPrescricao = $dados['ID_PRESCRICAO'];



    // BUSCAR NOVA TARJA
    $idTarja = (int)$tarja_paraAlterar;



    // PRESCRIÇÕES
    $consultaPrescricao = $conn->prepare("
        UPDATE PRESCRICOES
        SET
            RESTRICAO = ?,
            CONTRA_INDICACOES = ?,
            EFEITOS = ?,
            VALIDADE = ?,
            CONSERVACAO = ?
        WHERE ID_PRESCRICAO = ?
    ");

    $consultaPrescricao->bind_param(
        "sssssi",
        $restricao_paraAlterar,
        $contraIndicacoes_paraAlterar,
        $efeitos_paraAlterar,
        $validade_paraAlterar,
        $conservacao_paraAlterar,
        $idPrescricao
    );

    $consultaPrescricao->execute();



    // REMÉDIOS
    $consultaRemedio = $conn->prepare("
    UPDATE REMEDIOS
    SET
        NOME = ?,
        BULA = ?,
        TIPO = ?,
        ID_EMPRESA = ?,
        ID_TARJA = ?
    WHERE ID_REMEDIO = ?
");

    $consultaRemedio->bind_param(
        "sssiii",
        $nome_paraAlterar,
        $bula_paraAlterar,
        $tipoRemedio_paraAlterar,
        $empresa_paraAlterar,
        $idTarja,
        $id
    );

    $consultaRemedio->execute();



    // REMOVER PÚBLICOS ANTIGOS
    $deletePublicos = $conn->prepare("
        DELETE FROM REMEDIOS_PUBLICO_ALVO
        WHERE ID_REMEDIO = ?
    ");

    $deletePublicos->bind_param("i", $id);
    $deletePublicos->execute();



    // INSERIR NOVOS PÚBLICOS
    foreach ($publicoAlvo_paraAlterar as $idPublico) {

    $insertPublico = $conn->prepare("
        INSERT INTO REMEDIOS_PUBLICO_ALVO
        (
            ID_REMEDIO,
            ID_PUBLICO_ALVO
        )
        VALUES (?, ?)
    ");

    $insertPublico->bind_param(
        "ii",
        $id,
        $idPublico
    );

    $insertPublico->execute();
    $insertPublico->close();
}

    //SUBSTANCIAS
    $consultaSubstanciaId = $conn->prepare("
    SELECT ID_SUBSTANCIA
    FROM REMEDIOS
    WHERE ID_REMEDIO = ?
");

$consultaSubstanciaId->bind_param("i", $id);
$consultaSubstanciaId->execute();

$resultadoSubstancia = $consultaSubstanciaId->get_result();
$dadosSubstancia = $resultadoSubstancia->fetch_assoc();

$idSubstancia = $dadosSubstancia['ID_SUBSTANCIA'];

$consultaSubstancia = $conn->prepare("
    UPDATE SUBSTANCIAS
    SET
        NOME = ?,
        TIPO = ?
    WHERE ID_SUBSTANCIA = ?
");

$consultaSubstancia->bind_param(
    "ssi",
    $substancia_paraAlterar,
    $substancia_tipo_paraAlterar,
    $idSubstancia
);

$consultaSubstancia->execute();


    header("Location: paginaDosRemedios_AcessoProfessor.php");
    exit;
}

function excluir($conn, $id){

    $consulta = $conn->prepare("
        DELETE FROM REMEDIOS
        WHERE ID_REMEDIO = ?
    ");

    $consulta->bind_param("i", $id);

    if($consulta->execute()){
        header("Location: paginaDosRemedios_AcessoProfessor.php?excluido=1");
        exit;
    }
}
?>