<?php
include "conexaoBanco.php";
// Função de validação/login
function logar($email, $senha)
{
    $dados = [
        "email" => $email,
        "senha" => md5(strtolower($email) . $senha)
    ];

    $curl = curl_init("http://localhost:8080/remedios/api/usuarios/login");

    curl_setopt_array($curl, [
        CURLOPT_RETURNTRANSFER => true,
        CURLOPT_POST => true,
        CURLOPT_HTTPHEADER => [
            "Content-Type: application/json"
        ],
        CURLOPT_POSTFIELDS => json_encode($dados)
    ]);

    $response = curl_exec($curl);
    $httpCode = curl_getinfo($curl, CURLINFO_HTTP_CODE);

    curl_close($curl);

    if ($httpCode == 200) {

        $usuario = json_decode($response, true);

        session_start();

        $_SESSION["email"] = $usuario["email"];
        $_SESSION["tipo"] = $usuario["tipo"];

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
            window.location.href='/enfermagemProjeto/frontend/login.html';
        </script>";

        exit;
    }
}

// Função de registro
function cadastrar($email, $senha, $tipo)
{
    // Busca usuários existentes
    $curl = curl_init("http://localhost:8080/remedios/api/usuarios");

    curl_setopt_array($curl, [
        CURLOPT_RETURNTRANSFER => true
    ]);

    $response = curl_exec($curl);
    curl_close($curl);

    $usuarios = json_decode($response, true);

    foreach ($usuarios as $usuario) {

        if ($usuario['email'] === $email) {

            echo "<script>
                alert('Este email já está cadastrado.');
                history.back();
            </script>";

            exit;
        }
    }

    // Cadastra
    $dados = [
        "email" => $email,
        "senha" => md5(strtolower($email) . $senha),
        "tipo" => $tipo
    ];

    $curl = curl_init("http://localhost:8080/remedios/api/usuarios");

    curl_setopt_array($curl, [
        CURLOPT_RETURNTRANSFER => true,
        CURLOPT_POST => true,
        CURLOPT_HTTPHEADER => [
            "Content-Type: application/json"
        ],
        CURLOPT_POSTFIELDS => json_encode($dados)
    ]);

    curl_exec($curl);

    $httpCode = curl_getinfo($curl, CURLINFO_HTTP_CODE);

    curl_close($curl);

    if ($httpCode == 200 || $httpCode == 201) {
        if ($tipo === "Professor") {
            
            header("Location: /enfermagemProjeto/arquivosPHP/paginaDosRemedios_AcessoProfessor.php");
        } else {
            header("Location: /enfermagemProjeto/frontend/login.html");
        }
        exit;
    }
}

?>