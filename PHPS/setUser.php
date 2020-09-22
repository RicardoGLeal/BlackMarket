<?PHP 

$conexion = mysqli_connect ("localhost", "id9949588_ricardoglr664", "qualcomm123") or die ("No se puede conectar con el servidor");
mysqli_select_db( $conexion, "id9949588_blackmarket" )or die ( "Error: No se pudo conectar a la BD" );

if(isset($_GET["nombre"]) && isset($_GET["apellidos"]) && isset($_GET["contrasena"]) && isset($_GET["telefono"]) && isset($_GET["correo"])){
    
    $nombre=$_GET['nombre'];
  $apellidos=$_GET['apellidos'];
  $contrasena=$_GET['contrasena'];
  $telefono=$_GET['telefono'];
  $correo=$_GET['correo'];
    
    $insertar = mysqli_query($conexion, "INSERT INTO usuario VALUES (null,'$nombre','$apellidos','$telefono','$correo','$contrasena')") or die ("Fallo de insercion");
}else{
    echo "no registrado";
}


mysqli_close($conexion);

?>