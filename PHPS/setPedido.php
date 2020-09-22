<?PHP 

$conexion = mysqli_connect ("localhost", "id9949588_ricardoglr664", "qualcomm123") or die ("No se puede conectar con el servidor");
mysqli_select_db( $conexion, "id9949588_blackmarket" )or die ( "Error: No se pudo conectar a la BD" );

if(isset($_GET["UserID"])&& isset($_GET["TotalPrice"]) 
	&& isset($_GET["Fecha"])&& isset($_GET["Status"]) && isset($_GET["DicUserID"]))
	{
    
  $UserID=$_GET['UserID'];
  $TotalPrice=$_GET['TotalPrice'];
  $Fecha=$_GET['Fecha'];
  $Status=$_GET['Status'];
  $DicUserID=$_GET['DicUserID'];
        $insertar = mysqli_query($conexion, "INSERT INTO pedido VALUES (null, '$UserID','$TotalPrice','$Fecha','$Status','$DicUserID')") or die ("Fallo de insersion");
		if($insertar)
		{
			$consulta = "SELECT * FROM pedido WHERE UserID = '$UserID' && TotalPrice= '$TotalPrice' && Fecha = '$Fecha' && Status = '$Status' && DicUserID = '$DicUserID'";
			$resultado = mysqli_query($conexion,$consulta);
		
			if($registro=mysqli_fetch_array($resultado))
			{
			$json['pedidojson'][]=$registro;
			}
			echo json_encode($json);
			mysqli_close($conexion);
		}
	}
	else
	{
    echo "no registrado";
	mysqli_close($conexion);
	}




?>