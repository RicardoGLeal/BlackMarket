<?php
/*$cateID = $_GET['cateID'];
$catename = $_GET['catename'];*/


$conexion = mysqli_connect ("localhost", "id9949588_ricardoglr664", "qualcomm123") or die ("No se puede conectar con el servidor");
mysqli_select_db( $conexion, "id9949588_blackmarket" )or die ( "Error: No se pudo conectar a la BD" );

$consulta = mysqli_query ($conexion, "select * from categoria")or die ("Fallo en la consulta");
$categorias = array();
$nrows = mysqli_num_rows($consulta);

if($nrows>0)
{
	while ($row = mysqli_fetch_array( $consulta ))
	{
	$id=$row['cateID'];
    $nombre=$row['cateName'];   

    //$categorias[] = array('id'=> $id, 'nombre'=> $nombre);*/
	
	

	$datum['categoriasjson'][] = array('id'=> $id, 'nombre'=> $nombre);
	}
	echo json_encode($datum);
}


mysqli_close( $conexion );
/*
$json_string = json_encode($categorias);
echo $json_string;*/
?>