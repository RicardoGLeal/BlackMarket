<?PHP 

$conexion = mysqli_connect ("localhost", "id9949588_ricardoglr664", "qualcomm123") or die ("No se puede conectar con el servidor");
mysqli_select_db( $conexion, "id9949588_blackmarket" )or die ( "Error: No se pudo conectar a la BD" );

if(isset($_GET["correo"]) && isset($_GET["triedpassword"]) ){
 
  $correo=$_GET['correo'];
  $hased_pass=hash("sha256", $_GET['triedpassword'], false); 
    
    $consultar = mysqli_query($conexion, "SELECT userid,nombre,apellidos, correo, password FROM usuario WHERE correo='{$correo}' AND password='{$hased_pass}'") or die ("Fallo en la consulta");

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
