<?PHP 

$conexion = mysqli_connect ("localhost", "id9949588_ricardoglr664", "qualcomm123") or die ("No se puede conectar con el servidor");
mysqli_select_db( $conexion, "id9949588_blackmarket" )or die ( "Error: No se pudo conectar a la BD" );

if(isset($_GET["UserID"]))
	{
  $UserID=$_GET['UserID'];

		$consulta = mysqli_query($conexion, "select pedido.PedID, pedido.TotalPrice, direccion.DicID, direccion.direccion, direccion.cp, pedido.fecha, pedido.status from
			pedido INNER JOIN direccion ON pedido.DicUserID = direccion.DicID
			where pedido.UserID = '$UserID';");
			
			$nrows = mysqli_num_rows($consulta);

			if($nrows>0)
			{
			while ($row = mysqli_fetch_array( $consulta ))
				{
					$PedID=$row['PedID'];
					$total=$row['TotalPrice'];
					$DicID=$row['DicID'];   
					$direccion=$row['direccion'];
					$cp=$row['cp'];
					$fecha=$row['fecha'];   
					$status=$row['status'];   
					$datum['pedidojson'][] = array('PedID'=> $PedID,'TotalPrice'=>$total, 'DicID'=> $DicID, 'direccion'=> $direccion, 'cp'=> $cp, 'fecha'=> $fecha, 'status'=> $status);
				}
					echo json_encode($datum);
			}
			mysqli_close($conexion);
	}
	else
	{
    echo "no registrado";
	mysqli_close($conexion);
	}
?>