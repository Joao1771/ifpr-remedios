<?php
$servidor = "localhost";   // Servidor do banco
$nomeBanco = "remedios_db"; // Nome do banco

$username = "appuser";         // Usuário
$password = "1234";             // Senha (se houver)

// Cria a conexão (corrigido: variáveis consistentes)
$conn = new mysqli($servidor, $username, $password, $nomeBanco);

// Checa a conexão
if ($conn->connect_error) {
    die("Falha na conexão: " . $conn->connect_error);
}   
?>