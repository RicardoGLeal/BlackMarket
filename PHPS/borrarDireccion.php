<?PHP 

$conexion = mysqli_connect ("localhost", "id9949588_ricardoglr664", "qualcomm123") or die ("No se puede conectar con el servidor");
mysqli_select_db( $conexion, "id9949588_blackmarket" )or die ( "Error: No se pudo conectar a la BD" );

if(isset($_GET["userid"]) && isset($_GET["dirid"])){
    
    $usuario=$_GET['userid'];
  $direccion=$_GET['dirid'];

    $eliminar = mysqli_query($conexion, "DELETE FROM direccion_usuario WHERE userid = '$usuario' AND dicID = '$direccion'") or die ("Fallo de insercion en direccion_usuario");
    $eliminar2 = mysqli_query($conexion, "DELETE FROM direccion WHERE dicid = '$direccion'") or die ("Fallo delete en direccion");
    
}else{
    echo "no registrado";
}


mysqli_close($conexion);

?>