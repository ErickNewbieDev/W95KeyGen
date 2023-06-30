<?php
$host = "localhost";
$usuario = "root";
$contrasenia = "";
$base_de_datos = "w95k";
$mysqli = new mysqli($host, $usuario, $contrasenia, $base_de_datos);
if ($mysqli->connect_errno) {
    echo "Falló la conexión a la base de datos: (" . $mysqli->connect_errno . ") " . $mysqli->connect_error;
}