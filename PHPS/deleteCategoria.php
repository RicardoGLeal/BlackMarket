<?PHP 

$conexion = mysqli_connect ("localhost", "id9949588_ricardoglr664", "qualcomm123") or die ("No se puede conectar con el servidor");
mysqli_select_db( $conexion, "id9949588_blackmarket" )or die ( "Error: No se pudo conectar a la BD" );

if(isset($_GET["cateID"])){
    
    $cateID=$_GET['cateID'];
    
    $insertar = mysqli_query($conexion, "DELETE FROM categoria WHERE cateID='$cateID'") or die ("Fallo de insercion");
}else{
    echo "no registrado";
}


mysqli_close($conexion);

?>
