<?PHP 

$conexion = mysqli_connect ("localhost", "id9949588_ricardoglr664", "qualcomm123") or die ("No se puede conectar con el servidor");
mysqli_select_db( $conexion, "id9949588_blackmarket" )or die ( "Error: No se pudo conectar a la BD" );

if(isset($_GET["itemName"]) && isset($_GET["itemDesc"]) && isset($_GET["itemCat"]) && isset($_GET["itemPrice"]) && isset($_GET["itemImage"])){
    
    $itemName=$_GET["itemName"];
  $itemDesc=$_GET["itemDesc"];
  $itemCat=$_GET["itemCat"];
  $itemPrice=$_GET["itemPrice"];
  $itemImage=$_GET["itemImage"];
    
    $insertar = mysqli_query($conexion, "INSERT INTO articulo VALUES (null,'$itemName','$itemDesc','$itemCat','$itemPrice','$itemImage')") or die ("Fallo de insercion");
}else{
    echo "no registrado";
}


mysqli_close($conexion);

?>
