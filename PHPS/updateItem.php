<?PHP 

$conexion = mysqli_connect ("localhost", "id9949588_ricardoglr664", "qualcomm123") or die ("No se puede conectar con el servidor");
mysqli_select_db( $conexion, "id9949588_blackmarket" )or die ( "Error: No se pudo conectar a la BD" );

if(isset($_GET["itemName"]) && isset($_GET["itemDesc"]) && isset($_GET["itemCat"]) && isset($_GET["itemPrice"]) && isset($_GET["itemImage"])  && isset($_GET["itemID"])){
    
    $itemName=$_GET["itemName"];
  $itemDesc=$_GET["itemDesc"];
  $itemCat=$_GET["itemCat"];
  $itemPrice=$_GET["itemPrice"];
  $itemImage=$_GET["itemImage"];
  $itemID   =$_GET["itemID"];
  
    $insertar = mysqli_query($conexion, "UPDATE articulo SET itemName='$itemName',itemDesc='$itemDesc',itemCat='$itemCat',itemPrice='$itemPrice', itemImage='$itemImage' WHERE itemID = '$itemID'") or die ("Fallo de insercion");
}else{
    echo "no registrado";
}


mysqli_close($conexion);

?>
