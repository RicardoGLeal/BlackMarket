<?PHP 

$conexion = mysqli_connect ("localhost", "id9949588_ricardoglr664", "qualcomm123") or die ("No se puede conectar con el servidor");
mysqli_select_db( $conexion, "id9949588_blackmarket" )or die ( "Error: No se pudo conectar a la BD" );

if(isset($_GET["cateName"]))
{
	
    $cateName=$_GET['cateName']; 
    $insertar = mysqli_query($conexion, "INSERT INTO categoria (cateName) VALUES ('$cateName')") or die ("Fallo de insersion");
	
	if($insertar)
	{
		$consulta = "SELECT * FROM categoria WHERE cateName = '$cateName'";
		$resultado = mysqli_query($conexion,$consulta);
		
		if($registro=mysqli_fetch_array($resultado))
		{
		$json['newcate'][]=$registro;
		}
		mysqli_close($conexion);
		echo json_encode($json);
	}
}
else{
    echo "no registrado";
}



?>