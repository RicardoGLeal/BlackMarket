<?php
/*$cateID = $_GET['cateID'];
$catename = $_GET['catename'];*/


$conexion = mysqli_connect ("localhost", "id9949588_ricardoglr664", "qualcomm123") or die ("No se puede conectar con el servidor");
mysqli_select_db( $conexion, "id9949588_blackmarket" )or die ( "Error: No se pudo conectar a la BD" );

$consulta = mysqli_query ($conexion, "select * from articulo")or die ("Fallo en la consulta");
$categorias = array();
$nrows = mysqli_num_rows($consulta);

if($nrows>0)
{
	while ($row = mysqli_fetch_array( $consulta ))
	{
	$id=$row['itemID'];
    $nombre=$row['itemName'];   
    $desc=$row['itemDesc'];   
    $cat=$row['itemCat'];   
    $price=$row['itemPrice'];
    $image=$row['itemImage'];   
    //$categorias[] = array('id'=> $id, 'nombre'=> $nombre);*/
	
	

	$datum['itemsjson'][] = array('id'=> $id, 'nombre'=> $nombre, 'desc'=> $desc, 'cat'=> $cat, 'price'=> $price, 'image'=> $image);
	}
	echo json_encode($datum);
}


mysqli_close( $conexion );
/*
$json_string = json_encode($categorias);
echo $json_string;*/
?>