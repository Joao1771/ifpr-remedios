<?php
include "conexaoBanco.php";

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
    $cnpj,
    $cidade,
    $uf,
    $substancia,
    $substancia_tipo,
    $tarja,
    $validade,
    $conservacao
){
    // PRESCRIÇÕES
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

    if (!$consultaPrescricao) {
        die("Erro PRESCRICOES: " . $conn->error);
    }

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



    // BUSCAR ID TARJA
    $consultaTarja = $conn->prepare("
        SELECT ID_TARJA
        FROM TARJAS
        WHERE NOME = ?
    ");

    $consultaTarja->bind_param("s", $tarja);
    $consultaTarja->execute();

    $resultadoTarja = $consultaTarja->get_result();
    $dadosTarja = $resultadoTarja->fetch_assoc();

    $idTarja = $dadosTarja['ID_TARJA'];



    // REMÉDIOS
    $consultaRemedio = $conn->prepare("
        INSERT INTO REMEDIOS
        (
            NOME,
            BULA,
            TIPO,
            ID_PRESCRICAO,
            ID_TARJA
        )
        VALUES (?, ?, ?, ?, ?)
    ");

    if (!$consultaRemedio) {
        die("Erro REMEDIOS: " . $conn->error);
    }

    $consultaRemedio->bind_param(
        "sssii",
        $nome,
        $bula,
        $tipoRemedio,
        $idPrescricao,
        $idTarja
    );

    $consultaRemedio->execute();

    $idRemedio = $conn->insert_id;



    // PÚBLICO-ALVO
    if (is_array($publicoAlvo)) {

        foreach ($publicoAlvo as $publico) {

    // BUSCA O ID DO PÚBLICO
    $consultaPublico = $conn->prepare("
        SELECT ID_PUBLICO_ALVO
        FROM PUBLICO_ALVO
        WHERE NOME = ?
    ");

    if (!$consultaPublico) {
        die("Erro PUBLICO_ALVO: " . $conn->error);
    }

    $consultaPublico->bind_param("s", $publico);
    $consultaPublico->execute();

    $resultadoPublico = $consultaPublico->get_result();
    $dadosPublico = $resultadoPublico->fetch_assoc();

    // VERIFICA SE ENCONTROU
    if (!$dadosPublico) {
        die("Público não encontrado: " . $publico);
    }

    $idPublico = $dadosPublico['ID_PUBLICO_ALVO'];

    // RELAÇÃO REMÉDIO x PÚBLICO
    $consultaRemedioPublico = $conn->prepare("
        INSERT INTO REMEDIOS_PUBLICO_ALVO
        (
            ID_REMEDIO,
            ID_PUBLICO_ALVO
        )
        VALUES (?, ?)
    ");

    if (!$consultaRemedioPublico) {
        die("Erro REMEDIOS_PUBLICO_ALVO: " . $conn->error);
    }

    // PRIMEIRO bind_param
    $consultaRemedioPublico->bind_param(
        "ii",
        $idRemedio,
        $idPublico
    );

    // DEPOIS execute
    if (!$consultaRemedioPublico->execute()) {
        die("Erro ao inserir REMEDIOS_PUBLICO_ALVO: " . $consultaRemedioPublico->error);
    }

    $consultaPublico->close();
    $consultaRemedioPublico->close();
}}



    // CIDADE
    $consultaCidade = $conn->prepare("
        INSERT INTO CIDADES
        (
            NOME,
            UF
        )
        VALUES (?, ?)
    ");

    $consultaCidade->bind_param(
        "ss",
        $cidade,
        $uf
    );

    $consultaCidade->execute();

    $idCidade = $conn->insert_id;



    // EMPRESA
    $consultaEmpresa = $conn->prepare("
        INSERT INTO EMPRESAS
        (
            NOME,
            CNPJ,
            ID_CIDADE
        )
        VALUES (?, ?, ?)
    ");

    $consultaEmpresa->bind_param(
        "ssi",
        $empresa,
        $cnpj,
        $idCidade
    );

    $consultaEmpresa->execute();

    $idEmpresa = $conn->insert_id;



    // EMPRESA x REMÉDIO
    $consultaEmpresaRemedio = $conn->prepare("
        INSERT INTO EMPRESAS_REMEDIOS
        (
            ID_EMPRESA,
            ID_REMEDIO
        )
        VALUES (?, ?)
    ");

    $consultaEmpresaRemedio->bind_param(
        "ii",
        $idEmpresa,
        $idRemedio
    );

    $consultaEmpresaRemedio->execute();



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



    // SUBSTÂNCIA x REMÉDIO
    $consultaSubstanciaRemedio = $conn->prepare("
        INSERT INTO SUBSTANCIAS_REMEDIOS
        (
            ID_SUBSTANCIA,
            ID_REMEDIO
        )
        VALUES (?, ?)
    ");

    $consultaSubstanciaRemedio->bind_param(
        "ii",
        $idSubstancia,
        $idRemedio
    );

    $consultaSubstanciaRemedio->execute();



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
    $cnpj_paraAlterar,
    $cidade_paraAlterar,
    $uf_paraAlterar,
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
    $consultaTarja = $conn->prepare("
        SELECT ID_TARJA
        FROM TARJAS
        WHERE NOME = ?
    ");

    $consultaTarja->bind_param("s", $tarja_paraAlterar);
    $consultaTarja->execute();

    $resultadoTarja = $consultaTarja->get_result();
    $dadosTarja = $resultadoTarja->fetch_assoc();

    $idTarja = $dadosTarja['ID_TARJA'];



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
        "ssssssi",
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
            ID_TARJA = ?
        WHERE ID_REMEDIO = ?
    ");

    $consultaRemedio->bind_param(
        "sssii",
        $nome_paraAlterar,
        $bula_paraAlterar,
        $tipoRemedio_paraAlterar,
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
    if (is_array($publicoAlvo_paraAlterar)) {

        foreach ($publicoAlvo_paraAlterar as $publico) {

            $consultaPublico = $conn->prepare("
                SELECT ID_PUBLICO_ALVO
                FROM PUBLICO_ALVO
                WHERE NOME = ?
            ");

            $consultaPublico->bind_param("s", $publico);
            $consultaPublico->execute();

            $resultadoPublico = $consultaPublico->get_result();
            $dadosPublico = $resultadoPublico->fetch_assoc();

            $idPublico = $dadosPublico['ID_PUBLICO_ALVO'];


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

            $consultaPublico->close();
            $insertPublico->close();
        }
    }



    // EMPRESA + CIDADE
    $consultaEmpresa = $conn->prepare("
        UPDATE EMPRESAS E
        INNER JOIN EMPRESAS_REMEDIOS ER
            ON ER.ID_EMPRESA = E.ID_EMPRESA
        INNER JOIN CIDADES C
            ON C.ID_CIDADE = E.ID_CIDADE

        SET
            E.NOME = ?,
            E.CNPJ = ?,
            C.NOME = ?,
            C.UF = ?

        WHERE ER.ID_REMEDIO = ?
    ");

    $consultaEmpresa->bind_param(
        "ssssi",
        $empresa_paraAlterar,
        $cnpj_paraAlterar,
        $cidade_paraAlterar,
        $uf_paraAlterar,
        $id
    );

    $consultaEmpresa->execute();



    // SUBSTÂNCIAS
    $consultaSubstancia = $conn->prepare("
        UPDATE SUBSTANCIAS S
        INNER JOIN SUBSTANCIAS_REMEDIOS SR
            ON SR.ID_SUBSTANCIA = S.ID_SUBSTANCIA

        SET
            S.NOME = ?,
            S.TIPO = ?

        WHERE SR.ID_REMEDIO = ?
    ");

    $consultaSubstancia->bind_param(
        "ssi",
        $substancia_paraAlterar,
        $substancia_tipo_paraAlterar,
        $id
    );

    $consultaSubstancia->execute();



    header("Location: paginaDosRemedios_AcessoProfessor.php");
    exit;
}

function excluir($conn, $id){

    // Buscar ID_PRESCRICAO
    $consultaId = $conn->prepare("
        SELECT ID_PRESCRICAO
        FROM REMEDIOS
        WHERE ID_REMEDIO = ?
    ");

    $consultaId->bind_param("i", $id);
    $consultaId->execute();

    $resultadoId = $consultaId->get_result();
    $dados = $resultadoId->fetch_assoc();

    $idPrescricao = $dados['ID_PRESCRICAO'];

    // Excluir remédio
    $consultaSQL_1 = $conn->prepare("
        DELETE FROM remedios_db.REMEDIOS
        WHERE ID_REMEDIO = ?
    ");

    $consultaSQL_1->bind_param("i", $id);
    $resultado_1 = $consultaSQL_1->execute();

    // Excluir prescrição
    $consultaSQL_2 = $conn->prepare("
        DELETE FROM remedios_db.PRESCRICOES
        WHERE ID_PRESCRICAO = ?
    ");

    $consultaSQL_2->bind_param("i", $idPrescricao);
    $resultado_2 = $consultaSQL_2->execute();

    if ($resultado_1 && $resultado_2) {
        header("Location: paginaDosRemedios_AcessoProfessor.php");
        exit;
    }
}
?>