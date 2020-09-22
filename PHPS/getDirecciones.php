<?php

$conexion = mysqli_connect ("localhost", "id9949588_ricardoglr664", "qualcomm123") or die ("No se puede conectar con el servidor");
mysqli_select_db( $conexion, "id9949588_blackmarket" )or die ( "Error: No se pudo conectar a la BD" );

if(isset($_GET['usuario'])){
    $userid=$_GET['usuario'];

    $consulta = mysqli_query ($conexion, "CALL getDirecciones('$userid')")or die ("Fallo en la consulta");
    $direcciones = array();
    $nrows = mysqli_num_rows($consulta);

    if($nrows>0){
	while ($row = mysqli_fetch_array( $consulta )){

	$usuarioid=$row['Userid'];
    $dicid=$row['DicId'];   
    $direccion=$row['direccion'];   
    $ciudad=$row['ciudad'];   
    $estado=$row['estado'];
    $cp=$row['cp'];   
    //$categorias[] = array('id'=> $id, 'nombre'=> $nombre);*/
	
	
	$datum['itemsjson'][] = array('usuarioid'=> $usuarioid, 'dicid'=> $dicid, 'direccion'=> $direccion, 'ciudad'=> $ciudad, 'estado'=> $estado, 'cp'=> $cp);
	}
	echo json_encode($datum);
}

}else{
    echo "datos incompletos...";
}


mysqli_close( $conexion );

?>