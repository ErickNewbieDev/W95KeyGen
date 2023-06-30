<!DOCTYPE html>
<html>
<head>
	<?php  
		include_once "Conexion.php";
		include_once "Tabla.php";
		$llaves = Keys95::obtener();
	?>
	<meta charset="UTF-8">
	<title>Windows 95 Key</title>
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Cairo+Play:wght@1000&display=swap" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="CSS/estilos.css">
	<link rel="stylesheet" type="text/css" href="CSS/parrafos.css">
	<link rel="icon" type="image/x-icon"  href="img/favicon.png">

</head>
<body>
	<header>
		<nav>
			<ul>
				<li><a href="index.php">Inicio</a></li>
				<li><a href="about.html">Acerca de</a></li>
				<li><a href="https://linktr.ee/ericknewbiedev">Contacto</a>
			</ul>
		</nav>
		<center>
			<h1 style="font-family: 'Cairo Play', sans-serif; font-size: 50px;">W95 Key Admin</h1>
		</center>
	</header>
	<main>
		<section>
			<h2>Tabla de llaves</h2>
			<p>Tabla con todas las llaves en la base de datos.</p>
			<center>
			<table>
		        <thead>
		            <tr>
		                <th>ID</th>
		                <th>Clave</th>
		            </tr>
		        </thead>
		        <tbody>
		            <?php foreach ($llaves as $llave) { ?>
		            	<tr>
		            		<td><?php echo $llave["id"] ?></td>
		                    <td><?php echo $llave["claves"] ?></td>
		            	</tr>
		            <?php } ?>
		        </tbody>
		    </table>
		    </center>
		</section>
		<aside>
			<h2>¿Como se crean estas claves?</h2>
			<p>En el caso de las claves "retail", se generan 10 numeros aleatorios.<br>
Los 3 primeros numeros pueden ser cuales quiera mientras no estén en la lista negra, la cual es:
<ol type="disc"></ol>
<li>333
<li>444
<li>555
<li>666
<li>777
<li>888
<li>999
<br><br>
Los siguientes 7 numeros son sumados y posteriormente divididos entre 7.
<br><br>
En caso que el resultado de la division no tenga decimales y sea un numero entero entre 2.0 y 4.0 entonces es una clave valida.</p>
<br>
<center><img src="img/Repre.gif" height="200" width="230"></center>
		</aside>
	</main>
	<footer>
		<a href="https://github.com/ErickNewbieDev" target="_blank">Erick NewbieDev © 2023</a>
	</footer>
</body>
</html>
