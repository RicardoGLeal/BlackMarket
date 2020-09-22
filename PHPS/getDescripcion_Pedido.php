<?PHP 

$conexion = mysqli_connect ("localhost", "id9949588_ricardoglr664", "qualcomm123") or die ("No se puede conectar con el servidor");
mysqli_select_db( $conexion, "id9949588_blackmarket" )or die ( "Error: No se pudo conectar a la BD" );

if(isset($_GET["PedID"]))
{
    
 
  $PedID=$_GET['PedID'];
  $consulta = mysqli_query($conexion, "SELECT * FROM descripcion_pedido WHERE PedID='{$PedID}'") or die ("Fallo en la consulta");
  $nrows = mysqli_num_rows($consulta);
	if($nrows>0)
	{
		while ($row = mysqli_fetch_array( $consulta ))
		{
		    $itemid=$row['itemId'];
		    $cant=$row['Quant'];
		    
		    $datum['descripcion_pedido'][] =array('itemID'=>$itemid, 'Quant'=>$cant);
			echo json_encode($datum);
		}	
	}
	else
	{
    echo "datos no completos";
	}	
}
echo "datos no completos";
mysqli_close($conexion);

?>