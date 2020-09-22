<?php
/*$cateID = $_GET['cateID'];
$catename = $_GET['catename'];*/


$conexion = mysqli_connect ("localhost", "id9949588_ricardoglr664", "qualcomm123") or die ("No se puede conectar con el servidor");
mysqli_select_db( $conexion, "id9949588_blackmarket" )or die ( "Error: No se pudo conectar a la BD" );

if(isset($_GET['PedID']))
{
    $PedID=$_GET['PedID'];
	
	$consulta = mysqli_query ($conexion, "SELECT pedido.PedID, articulo.itemName, articulo.itemPrice, articulo.itemImage, 
	descripcion_pedido.Quant, descripcion_pedido.subtotal FROM articulo 
	INNER JOIN descripcion_pedido ON descripcion_pedido.itemID = articulo.itemID
	INNER JOIN pedido ON pedido.PedID = descripcion_pedido.PedID where pedido.PedID = '$PedID'")or die ("Fallo en la consulta");
	$categorias = array();
	$nrows = mysqli_num_rows($consulta);

	if($nrows>0)
	{
		while ($row = mysqli_fetch_array( $consulta ))
		{
		$itemName=$row['itemName'];   
			$itemPrice=$row['itemPrice'];   
			$itemImage=$row['itemImage'];   
			$Quant=$row['Quant'];
			$subtotal=$row['subtotal'];  

    //$categorias[] = array('id'=> $id, 'nombre'=> $nombre);*/
	
	

		$datum['pedidojson'][] = array('PedID'=> $PedID, 'itemName'=> $itemName, 'itemPrice'=> $itemPrice, 'itemImage'=> $itemImage, 'Quant'=> $Quant, 'subtotal'=> $subtotal);
		}
		echo json_encode($datum);
	}
}

mysqli_close( $conexion );
/*
$json_string = json_encode($categorias);
echo $json_string;*/
?>