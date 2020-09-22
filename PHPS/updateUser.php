<?PHP 

$conexion = mysqli_connect ("localhost", "id9949588_ricardoglr664", "qualcomm123") or die ("No se puede conectar con el servidor");
mysqli_select_db( $conexion, "id9949588_blackmarket" )or die ( "Error: No se pudo conectar a la BD" );

if(isset($_GET["nombre"]) && isset($_GET["apellidos"]) && isset($_GET["contrasena"]) && isset($_GET["correo"]) && isset($_GET["userid"])){
    
    $nombre=$_GET['nombre'];
  $apellidos=$_GET['apellidos'];
  $contrasena=$_GET['contrasena'];
  $correo=$_GET['correo'];
  $userid=$_GET['userid'];
    
    $insertar = mysqli_query($conexion, "UPDATE usuario SET nombre='$nombre',apellidos='$apellidos',correo='$correo',password='$contrasena' WHERE userid = '$userid'") or die ("Fallo de insercion");
}else{
    echo "no registrado";
}


mysqli_close($conexion);

?>