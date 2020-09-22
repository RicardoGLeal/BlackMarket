<?PHP 

$conexion = mysqli_connect ("localhost", "id9949588_ricardoglr664", "qualcomm123") or die ("No se puede conectar con el servidor");
mysqli_select_db( $conexion, "id9949588_blackmarket" )or die ( "Error: No se pudo conectar a la BD" );

if(isset($_GET["cateID"]) && isset($_GET["cateName"])){
    
    $cateID=$_GET['cateID'];
  $cateName=$_GET['cateName'];
    
    $insertar = mysqli_query($conexion, "UPDATE categoria SET cateName='$cateName' WHERE cateID = '$cateID'") or die ("Fallo de insercion");
}else{
    echo "no registrado";
}


mysqli_close($conexion);

?>
