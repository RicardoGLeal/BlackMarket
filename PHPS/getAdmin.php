<?PHP 

$conexion = mysqli_connect ("localhost", "id9949588_ricardoglr664", "qualcomm123") or die ("No se puede conectar con el servidor");
mysqli_select_db( $conexion, "id9949588_blackmarket" )or die ( "Error: No se pudo conectar a la BD" );

if(isset($_GET["correo"])){
    
 
  $correo=$_GET['correo'];
    
    $consultar = mysqli_query($conexion, "SELECT * FROM admins WHERE correo='{$correo}'") or die ("Fallo en la consulta");
    
    if($registro=mysqli_fetch_array($consultar)){
        $json['usuario'][] = $registro;
        echo json_encode($json);
    }else{
        echo "error";
    }
}else{
    echo "datos no completos";
}


mysqli_close($conexion);

?>