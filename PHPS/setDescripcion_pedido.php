<?PHP 

$conexion = mysqli_connect ("localhost", "id9949588_ricardoglr664", "qualcomm123") or die ("No se puede conectar con el servidor");
mysqli_select_db( $conexion, "id9949588_blackmarket" )or die ( "Error: No se pudo conectar a la BD" );

if(isset($_GET["PedID"]) && isset($_GET["itemID"]) && isset($_GET["Quant"]) && isset($_GET["subtotal"])){
    
  $PedID=$_GET['PedID'];
  $itemID=$_GET['itemID'];
  $Quant=$_GET['Quant'];
  $subtotal=$_GET['subtotal'];
  
    
    $insertar = mysqli_query($conexion, "INSERT INTO descripcion_pedido VALUES (null,'$PedID','$itemID','$Quant','$subtotal')") or die ("Fallo de insercion en direccion");
}else{
    echo "no registrado";
}


mysqli_close($conexion);

?>