<?PHP 

$conexion = mysqli_connect ("localhost", "id9949588_ricardoglr664", "qualcomm123") or die ("No se puede conectar con el servidor");
mysqli_select_db( $conexion, "id9949588_blackmarket" )or die ( "Error: No se pudo conectar a la BD" );

if(isset($_GET["PedID"]))
	{

  $PedID=$_GET['PedID'];
 
 //select * from direccion inner join pedido on pedido.DicUserID = direccion.DicID where pedido.PedID = 9;
			$consulta = "SELECT * FROM direccion inner join pedido on pedido.DicUserID = direccion.DicID where pedido.PedID = '$PedID'";
			$resultado = mysqli_query($conexion,$consulta);
		
			if($registro=mysqli_fetch_array($resultado))
			{
			$json['direccionjson'][]=$registro;
			}
			echo json_encode($json);
			mysqli_close($conexion);
	}
	else
	{
    echo "no registrado";
	mysqli_close($conexion);
	}
?>