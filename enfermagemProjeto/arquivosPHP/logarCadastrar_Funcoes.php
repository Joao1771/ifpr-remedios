<?php
include "conexaoBanco.php";
// Função de validação/login
function logar($email, $senha, $conn){

    $senha = md5($senha);

    $consultaLogin = $conn->prepare("
        SELECT tipo 
        FROM remedios_db.usuarios 
        WHERE email = ? AND senha = ?
    ");

    $consultaLogin->bind_param("ss", $email, $senha);
    $consultaLogin->execute();
    $resultadoLogin = $consultaLogin->get_result();

    if ($resultadoLogin->num_rows > 0) {

        $usuario = $resultadoLogin->fetch_assoc();

        if ($usuario['tipo'] === 'Aluno') {
            header("Location: /enfermagemProjeto/arquivosPHP/paginaDosRemedios_AcessoAluno.php");
            exit;
        } else {
            header("Location: /enfermagemProjeto/arquivosPHP/paginaDosRemedios_AcessoProfessor.php");
            exit;
        }

    } else {
        echo "<script>
            alert('Login não encontrado. Tente outro email/senha.');
            window.location.href = '/enfermagemProjeto/frontend/login.html';
        </script>";
        exit;
    }
}

// Função de registro
function cadastrar($email, $senha, $conn, $tipo){

    $senha = md5($senha);
    $consultaSQL = $conn->prepare("INSERT INTO remedios_db.usuarios(email, senha, tipo) VALUES (?, ?, ?)");
    $consultaSQL->bind_param("sss", $email, $senha, $tipo);

    $resultado = $consultaSQL->execute();

  
    if ($resultado === true) {
        header("Location: /enfermagemProjeto/frontend/login.html");
        exit;
    } else {
        echo "<script>alert('Não foi possível. Tente novamente');</script>";
        exit;
    }
}

function cadastroProfessor($email, $senha, $conn, $tipo){

    $senha = md5($senha);
    $consultaSQL = $conn->prepare("INSERT INTO remedios_db.usuarios(email, senha, tipo) VALUES (?, ?, ?)");
    $consultaSQL->bind_param("sss", $email, $senha, $tipo);

    $resultado = $consultaSQL->execute();

  
    if ($resultado === true) {
        header("Location: /enfermagemProjeto/arquivosPHP/paginaDosRemedios_AcessoProfessor.php");
        exit;
    } else {
        echo "<script>alert('Não foi possível. Tente novamente');</script>";
        exit;
    }
}

?>