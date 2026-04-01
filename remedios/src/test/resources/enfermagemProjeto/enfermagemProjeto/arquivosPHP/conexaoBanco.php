<?php
$servidor = "localhost";   // Servidor do banco (localhost = máquina do cliente)
$username = "root";          // Usuário 
$password = "";              // Senha temporária
$nomeBanco = "remedios_db";        // Nome do banco

// Cria a conexão
$conn = new mysqli($servidor, $username, $password, $nomeBanco);

// Checa a conexão
if ($conn->connect_error) {
    die("Falha na conexão: " . $conn->connect_error);
}
?>