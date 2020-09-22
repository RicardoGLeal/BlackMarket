<?PHP 

$conexion = mysqli_connect ("localhost", "id9949588_ricardoglr664", "qualcomm123") or die ("No se puede conectar con el servidor");
mysqli_select_db( $conexion, "id9949588_blackmarket" )or die ( "Error: No se pudo conectar a la BD" );

if(isset($_GET["userid"]) && isset($_GET["direccion"]) && isset($_GET["ciudad"]) && isset($_GET["estado"]) && isset($_GET["cp"])){
    
    $usuario=$_GET['userid'];
  $direccion=$_GET['direccion'];
  $ciudad=$_GET['ciudad'];
  $estado=$_GET['estado'];
  $cp=$_GET['cp'];
    
    $insertar = mysqli_query($conexion, "INSERT INTO direccion VALUES (null,'$direccion','$ciudad','$estado','$cp');") or die ("Fallo de insercion en direccion");
    
    $insertar2 = mysqli_query($conexion, "INSERT INTO direccion_usuario VALUES (null,(SELECT dicid FROM direccion WHERE direccion = '$direccion' AND ciudad = '$ciudad' AND estado = '$estado' AND cp = '$cp'),'$usuario');") or die ("Fallo de insercion en direccion_usuario");
}else{
    echo "no registrado";
}


mysqli_close($conexion);

?>