<!DOCTYPE html>
<html>
<head>
<title>Page Title</title>
</head>
<body>

<h1>Digite no Formulário a mensagem que quer enviar ao servidor</h1>

<?php

if(!isset($_POST['mensagem']))
{

	echo '
<form action="" method="post">
	<label for="mensagem">Mensagem</label>
	<input type="text" name="mensagem" id="mensagem" />
	<input type="submit" value="Enviar" />

</form>';

}else{

	$mensagem = $_POST['mensagem'];
	$host = "localhost";
	$port = 12345;
	$socket = socket_create ( AF_INET, SOCK_STREAM, 0 );
	socket_connect ( $socket, $host, $port );

	socket_write($socket, $mensagem."\n", strlen ( $mensagem."\n" ) ) or die ( "Could not send data to server\n" );
	$resposta = "";
	while ( $result = socket_read ( $socket, 1024 ) ) {
		$resposta .= $result;
	}
	echo "<p> Resposta do servidor: ".$resposta."</p>";
	echo '<meta http-equiv="refresh" content="4; url=index.php">';

}
?>

</body>
</html>
