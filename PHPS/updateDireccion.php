<?PHP 

$conexion = mysqli_connect ("localhost", "id9949588_ricardoglr664", "qualcomm123") or die ("No se puede conectar con el servidor");
mysqli_select_db( $conexion, "id9949588_blackmarket" )or die ( "Error: No se pudo conectar a la BD" );

if(isset($_GET["dirid"])&& isset($_GET["direccion"]) && isset($_GET["ciudad"]) && isset($_GET["estado"]) && isset($_GET["cp"])){
    

  $direccionid=$_GET['dirid'];
  $direccion=$_GET['direccion'];
  $ciudad=$_GET['ciudad'];
  $estado=$_GET['estado'];
  $cp=$_GET['cp'];


    $update = mysqli_query($conexion, "UPDATE direccion SET direccion = '$direccion', ciudad = '$ciudad', estado='$estado', cp = '$cp' WHERE dicid = '$direccionid'") or die ("Fallo delete en direccion");
    
}else{
    echo "no registrado";
}


mysqli_close($conexion);

?>