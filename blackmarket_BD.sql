-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 17-06-2019 a las 03:41:19
-- Versión del servidor: 10.3.14-MariaDB
-- Versión de PHP: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `id9949588_blackmarket`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`id9949588_ricardoglr664`@`%` PROCEDURE `getDirecciones` (IN `idUser` SMALLINT)  BEGIN
SELECT usuario.Userid, direccion.DicId, direccion.direccion, direccion.ciudad, direccion.estado, direccion.cp
FROM usuario INNER JOIN direccion_usuario ON usuario.userid = direccion_usuario.userid
INNER JOIN direccion ON direccion_usuario.DicId = direccion.dicid
WHERE usuario.userid =  idUser;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `admins`
--

CREATE TABLE `admins` (
  `adminID` smallint(6) NOT NULL,
  `correo` varchar(320) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(30) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `admins`
--

INSERT INTO `admins` (`adminID`, `correo`, `password`) VALUES
(1, 'ricardoglr664@gmail.com', 'qualcomm123'),
(2, 'padilla.angel88@gmail.com', 'asd123');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `articulo`
--

CREATE TABLE `articulo` (
  `itemID` smallint(6) NOT NULL,
  `itemName` varchar(150) DEFAULT NULL,
  `itemDesc` varchar(500) NOT NULL,
  `itemCat` smallint(6) NOT NULL,
  `itemPrice` int(11) NOT NULL,
  `itemImage` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `articulo`
--

INSERT INTO `articulo` (`itemID`, `itemName`, `itemDesc`, `itemCat`, `itemPrice`, `itemImage`) VALUES
(1, 'Xbox One S', 'Para Toda la FamiliaASIN: B07P3L5JFCDimensiones del producto: 45 x 20 x 30 cmFecha de lanzamiento: 19 de marzo de 2019', 4, 5249, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQOAQd_MB17Nyt4vty4U03eBR7WaJ2BRSztSQ31wnFS9Qcl4fuZ'),
(18, 'Nintendo Switch', 'No solo se puede conectar a un televisor en tu casa, sino que también se transforma al instante en una consola portátil con una pantalla de 6.2\"', 4, 6500, 'https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcSIDVuHSibCnqpoC_whyMNnvURl3CX4y4vm7dW-r0gq1LexKGY8rndxKKLMizcUFMNPru2zXsz0'),
(21, 'Godzilla', 'Un mounstro bien chido', 3, 500000, 'https://asia.ric-toy.com/media/catalog/product/cache/5/image/9df78eab33525d08d6e5fb8d27136e95/g/o/godzilla1964cs_eng0.jpg'),
(22, 'Audífonos Gamer', 'Apariencia Genial Iluminación LED con modo respiración con 7 colores que cambian automáticamente para mejorar la atmósfera de juego. Aspecto único, llamativo y nítido con muchos elementos de juego. Rendimiento de alta calidad, auriculares para juegos profesionales con una apariencia genial y efectos de iluminación.', 4, 599, 'https://images-na.ssl-images-amazon.com/images/I/81WMJVIcZjL._SL1500_.jpg'),
(23, 'Dinosaurio real', 'Es un dinosaurio muy real, come 3 vacas diarias y es la mascota de rick', 3, 78932, 'https://http2.mlstatic.com/D_NQ_NP_645348-MLA29580978321_032019-Q.jpg'),
(24, 'Sonolife - Par de Guantes ', 'MUY FÁCIL DE LIMPIAR Y REMOVER EXCESO DE PELO - La limpieza del guante se lleva a cabo de forma sencilla ya que se enreda alrededor de la textura y solo basta jalarlo para que en conjunto se retire. El guante se puede lavar sin problemas.', 3, 1599, 'https://images-na.ssl-images-amazon.com/images/I/71aKTqoBLfL._SL1000_.jpg'),
(25, 'ASPECTS 430 Hummzinger ', 'Dimensiones del producto: 19.7 x 19.7 x 6.8 cm ; 295 g\r\nPeso del envío: 340 g\r\nNúmero de modelo del producto: 430\r\nASIN: B0080DJNFW', 3, 429, 'https://images-na.ssl-images-amazon.com/images/I/61K9D07dXQL.jpg'),
(26, 'Funda para mascotas', 'Funda estilo hamaca para asiento que mantendrá tu coche limpio mientras transportas a tus mascotas.', 3, 341, 'https://images-na.ssl-images-amazon.com/images/I/51-ZzTmzyPL.jpg'),
(27, 'bolsas para Popo ', 'BIODEGRADABLES: Nuestras bolsas Bamboo Bags son totalmente biodegradables y compostables. Hechas de bamboo, 100% amigables con el medio ambiente. No Tóxico', 3, 199, 'https://images-na.ssl-images-amazon.com/images/I/61dHLjwnE-L._SL1000_.jpg'),
(28, 'Fancypets TX11134 Cojín ', 'Proporciona al perro descanso y comodidad, el relleno lo mantendrá aislado del suelo', 3, 189, 'https://images-na.ssl-images-amazon.com/images/I/41ucRNWWn3L._SX425_.jpg'),
(29, 'Tropical Flakes', 'Mezcla científicamente desarrollada de ingredientes altamente nutritivos, con vitaminas, minerales y oligoelementos', 3, 403, 'https://images-na.ssl-images-amazon.com/images/I/81Tjdv4i2kL._SL1500_.jpg'),
(30, 'Árbol de actividades', 'Árbol interior para gatos con dos plataformas y 3 postes rascadores revestidos en yute.', 3, 867, 'https://images-na.ssl-images-amazon.com/images/I/91mqPXJY2OL._SL1500_.jpg'),
(31, ' Puerta para Gato ', 'Una gran solución para su acceso a cualquier habitación interior, mientras que le da un control completo; Perfecto para el acceso a la recámara o incluso a la comida o la caja de arena.', 3, 349, 'https://images-na.ssl-images-amazon.com/images/I/713xeENZ5lL._SL1500_.jpg'),
(32, 'Cargador para Controles', 'DOCK ESPECIAL PARA CONTROLES: Tiene un diseño único y diseñado para colocar y cargar tus controladores de PS4', 4, 199, 'https://images-na.ssl-images-amazon.com/images/I/61Wex%2BMBpML._SL1000_.jpg'),
(33, 'The Last of Us Part II - PS4', 'En esencia, The Last of Us sigue girando en torno a Ellie y Joel. Sin embargo, en esta ocasión, queríamos presentarte a varios personajes nuevos que desempeñarán un papel fundamental en la próxima aventura que emprenderá el dúo.', 4, 1180, 'https://images-na.ssl-images-amazon.com/images/I/71lEVEfSITL._SL1500_.jpg'),
(34, 'Final Fantasy VII: Remake - PlayStation 4', 'FINAL FANTASY VII REMAKE es una re-imaginación del icónico juego original que redefinió el género RPG\r\nFINAL FANTASY VII REMAKE presenta un sistema híbrido de batalla que une la acción en tiempo real con un combate estratégico basado en comandos.', 4, 1260, 'https://images-na.ssl-images-amazon.com/images/I/71iDap%2BnuZL._SL1500_.jpg'),
(35, 'Cyberpunk 2077 - PlayStation 4 - Standard Edition', 'JUEGA COMO UN MERCENARIO FORAJIDO: Conviértete en un cyberpunk, un mercenario urbano equipado con mejoras cibernéticas y crea tu leyenda en las calles de Night City.\r\nDESCUBRE LA CIUDAD DEL FUTURO: Conoce el enorme mundo abierto de Night City, una ciudad que establece una nueva referencia en calidad visual, complejidad y profundidad.', 4, 1260, 'https://images-na.ssl-images-amazon.com/images/I/81SRQTQM%2BhL._SL1500_.jpg'),
(36, 'Consola PlayStation 4 PRO de 1TB con juego Horizon Zero Dawn: Complete Edition', 'Explora mundos vívidos en juegos con gráficos vibrantes, potenciados gracias a PlayStation 4 Pro\r\nGameplay mejorado: la compatibilidad con mayores velocidades de fotogramas brinda una acción nítida en juegos exclusivos para PlayStation 4', 4, 8099, 'https://images-na.ssl-images-amazon.com/images/I/611Cq7YKqEL._SL1237_.jpg'),
(37, 'Redlemon Mouse Gamer de 7200 DPI, Ergonómico, Iluminación en 7 colores', 'TU ARMA PERFECTA AL JUGAR: Si eres gamer de corazón, necesitas este mouse diseñado especialmente para hacerte ganar todas las partidas. Cuenta con botones especiales para gaming, así como rueda de desplazamiento y sistema óptico', 4, 251, 'https://http2.mlstatic.com/mouse-gamer-redragon-griffin-m607-iluminacion-rgb-7200dpi-pc-D_NQ_NP_803583-MLA30262438646_052019-Q.jpg'),
(38, 'Joystick USB Stick Buttons Controller Control Device para PC Computer Arcade Game', 'Este controlador de juego tiene cero retraso, la superficie oblicua garantiza una gran experiencia de juego\r\nEl joystick de ocho direcciones tiene una vara de metal y cubierta de goma, no es fácil de oxidar', 4, 960, 'https://images-na.ssl-images-amazon.com/images/I/61ohKzcKcIL._SL1001_.jpg'),
(39, 'Google Pixel 3a, 64 GB, Color Negro', 'Haga más con una cámara extraordinaria, una batería de carga rápida y el asistente de Google integrado\r\nMotion Auto Focus se asegurará de que las personas y mascotas en su chupito permanezcan afiladas mientras se rompe.', 1, 9030, 'https://images-na.ssl-images-amazon.com/images/I/81T-FKC695L._SL1500_.jpg'),
(40, 'Xiaomi Smartphone Mi 9 SE Desbloqueado - 64GB - Color Negro', 'Pantalla 5.9 pulgadas\r\nCámara trasera triple 48MP + 13MP + 8MP y Cámara Frontal 20MP\r\nVelocidad del Procesador Octa-core 2.3 Ghz\r\nVersión Sistema Operativo Android 9.0 Pie\r\nBatería 3070 mAh', 1, 6799, 'https://images-na.ssl-images-amazon.com/images/I/51VeG1vmY8L._SL1000_.jpg'),
(41, 'Huawei Honor 8X (64 GB + 4 GB de RAM) 6.5 Pulgadas HD 4G LTE gsm ', '4G LTE FDD B1/B2/B3/B4/B5/B7/B28 LTE TDD: B41 3G UMTS: B1/2/4/5/8 GSM: B2/3/5/8 - Dual SIM (Nano-SIM, doble modo de espera)> (asegúrese de verificar la compatibilidad con su operador antes de comprar)', 1, 4398, 'https://images-na.ssl-images-amazon.com/images/I/81f891yYQhL._SL1500_.jpg'),
(42, 'Huawei MateBook 13, 13, Intel i5-8265U 8va gen, 1.6Ghz, 8GB RAM, 256GB SSD, Windows 10 Home (Wright-W19A)', 'Cargador Cable Tipo C\r\nAdaptador Huawei USB tipo C\r\nVideos 4K', 1, 15999, 'https://images-na.ssl-images-amazon.com/images/I/51Fdnx%2BuVkL._SL1000_.jpg'),
(43, 'Asus Vivobook, 14 inches, Intel Core i3-7020U , 8 GB / 256 GB SSD , Windows 10, Color Transparent Silver (X420UA-BV123T)', 'ntel Core i3-7020U Processor\r\nRam 8 GB\r\nSata 3 256 Gb M.2 SSD\r\nIntel UHD Graphics 620\r\n14.0\'//Ultra Slim 200nits//HD 1366x768 16: 9//Anti-Glare//NTSC: 45%', 1, 12699, 'https://images-na.ssl-images-amazon.com/images/I/7149UC9EdKL._SL1500_.jpg'),
(44, 'Samsung LU28E590DS/PE Monitor 28 LED-Lit UHD 4K, 3840 x 2160, 2 x HDMI, Silver/Black', 'Ultra HD 4K\r\n1 (GTG) ms\r\n3840 x 2160 resolución\r\nEasy Setting Box', 1, 6707, 'https://images-na.ssl-images-amazon.com/images/I/A1mCuvqUMpL._SL1500_.jpg'),
(45, 'Monitor BenQ 4K HDR para Diseño Gráfico UHD 27” PD2700U, 100% Rec.709, eye-care', 'TECNOLOGÍA AQCOLOR: Reproducción precisa de color para un trabajo creativo profesional, garantizando un espacio de color 100% sRGB y Rec.709 calibrado en fábrica\r\nAUMENTA LA PRODUCTIVIDAD A TRAVÉS DE LA FUNCIÓN DOBLE VISTA: Trabaja en dos modos, como sRGB y Darkroom Mode sin la necesidad de dos pantallas', 1, 13498, 'https://images-na.ssl-images-amazon.com/images/I/61LlfCQR6ZL._SL1000_.jpg'),
(46, 'NVIDIA Quadro P2000', 'Chipset: NVIDIA Quadro P2000\r\nMemoria de video: 5GB GDDR5\r\nInterfaz de memoria: 160 bits\r\nMax. Resolución: 5120 x 2880, soporta monitores 4x Display', 1, 9572, 'https://images-na.ssl-images-amazon.com/images/I/81tDoRoKhhL._SL1500_.jpg'),
(47, 'PNY VC-839 Tarjeta de Video Nvidia Quadro Rtx4000/PCI Express 3.0 X16/GDDR6/8 GB/DP 1.4/3X DP/USB Tipo C//Gama Alta/Diseño', 'PNY VC-839 Tarjeta de Video Nvidia Quadro Rtx4000/PCI Express 3.0 X16/GDDR6/8 GB/DP 1.4/3X DP/USB Tipo C//Gama Alta/Diseño', 1, 19970, 'https://images-na.ssl-images-amazon.com/images/I/41VYNVy1H2L.jpg'),
(48, 'Intel Core i9-9900K - Procesador Core i9-9xxx, 3.6 GHz', '8 Núcleos/ 16 Hilos3.60 GHz máximo 5.00 GHz/ 16 MB de CacheCompatible con tarjetas madres basadas en Chips Intel 300 Series', 1, 9600, 'http://static1.caseking.de/media/image/thumbnail/hpit-530_hpit_530_01_800x800.jpg'),
(49, 'Cien años de soledad (2015)', 'Muchos años después, frente al pelotón de fusilamiento, el coronel Aureliano Buendía había de recordar aquella tarde remota en que su padre lo llevó a conocer el hielo. Macondo era entonces una aldea de veinte casas de barro y cañabrava construidas a la orilla de un río de aguas diáfanas que se precipitaban por un ', 2, 149, 'https://images-na.ssl-images-amazon.com/images/I/41RmW2XanXL._SX326_BO1,204,203,200_.jpg'),
(50, '¡Eres un chingón!', 'En esta guía, la coach Jen Sincero nos ofrece 27 capítulos llenos de historias divertidas e inspiradoras, sabios consejos, ejercicios sencillos y una que otra grosería. Todo para ayudarte a:- Identificar y cambiar tus conductas de sabotaje, que no te permiten obtener lo que realmente quieres.- Crear una vida que ames. Y crearla ahora.', 2, 169, 'https://images-na.ssl-images-amazon.com/images/I/51hI2B6aGkL._SX324_BO1,204,203,200_.jpg'),
(51, 'Los hornos de Hitler', 'Una sobreviviente de los campos de concentración de Auschwitz y de Birkenau. La visión de cinco chimeneas arrojando el humo de la carne quemada de centenares de miles de seres humanos, entre ellos los padres y los dos hijos de la escritora. Crónica auténtica y documentada del genocidio más conocido de la historia del siglo XX. ', 2, 93, 'https://images-na.ssl-images-amazon.com/images/I/51QpflprZBL._SX325_BO1,204,203,200_.jpg'),
(52, 'Silmarillion, El', 'El Silmarillion cuenta la historia de la Primera Edad, el antiguo drama del que hablan los personajes de El Señor de los Anillos, y en cuyos acontecimientos algunos tomaron parte, como Elrond y Galadriel.', 2, 594, 'https://images-na.ssl-images-amazon.com/images/I/51Y5ByOS%2B7L._SX329_BO1,204,203,200_.jpg'),
(53, 'La Naranja Mecanica', 'La Naranja Mecanica', 2, 129, 'https://images-na.ssl-images-amazon.com/images/I/4162wzph6tL._SX325_BO1,204,203,200_.jpg'),
(54, 'El Señor de los Anillos I. La Comunidad del Anillo. Booket - Biblioteca J R R Tolkien', 'En la adormecida e idílica Comarca, un joven hobbit recibe un encargo: custodiar el Anillo Único y emprender el viaje para su destrucción en la Grieta del Destino. Acompañado por magos, hombres, elfos y enanos, atravesará la Tierra Media y se internará en las sombras de Mordor, perseguido siempre por las huestes de Sauron, el Señor Oscuro, dispuesto a recuperar su creación para establecer el dominio definitivo del Mal.', 2, 466, 'https://images-na.ssl-images-amazon.com/images/I/51UdKQyH6jL._SX327_BO1,204,203,200_.jpg'),
(55, 'El Señor de los Anillos II. Las Dos Torres', 'La Compañía se ha disuelto y sus integrantes emprenden caminos separados. Frodo y Sam continúan solos su viaje a lo largo del río Anduin, perseguidos por la sombra misteriosa de un ser extraño que también ambiciona la posesión del Anillo. Mientras, hombres, elfos y enanos se preparan para la batalla final contra las fuerzas del Señor del Mal.', 2, 2303, 'https://images-na.ssl-images-amazon.com/images/I/51jH0VB5f9L._SX327_BO1,204,203,200_.jpg'),
(56, 'Tokio Blues ', 'Toru Watanabe, un ejecutivo de 37 años, escucha casualmente mientras aterriza en un aeropuerto europeo una vieja canción de los beatles, y la música le hace retroceder a su juventud, al turbulento Tokio de finales de los sesenta. ', 2, 149, 'https://images-na.ssl-images-amazon.com/images/I/41Fl3HLmZvL._SX318_BO1,204,203,200_.jpg'),
(57, 'Los hijos de Húrin ', 'Los hijos de Húrin es uno de los grandes relatos que fundamentan la historia de la Tierra Media y se sitúa en la Primera Edad, cuando elfos, hombres y enanos llevaban unos pocos siglos sobre la tierra', 2, 735, 'https://images-na.ssl-images-amazon.com/images/I/51i-cz28MZL._SX329_BO1,204,203,200_.jpg'),
(58, 'La insoportable levedad del ser', 'Una brillante disección del amor en nuestros días que se ha convertido en uno de los grandes hitos de la literatura contemporánea.', 2, 121, 'https://images-na.ssl-images-amazon.com/images/I/51cglAnbZFL._SX258_BO1,204,203,200_.jpg'),
(59, 'Meguiar\'s´s G14512 Ultimate Protectant', 'Fácil de aplicar/ usar\r\nSirve para interior y exterior\r\nSirve para interior y exterior', 50, 357, 'https://images-na.ssl-images-amazon.com/images/I/51VFhdjoYHL._SL800_.jpg'),
(60, 'Alfombras para coche, 3 piezas, negro', 'Conjunto de 3 piezas de tapetes delanteros y traseros; protege los pisos de los vehículos de la nieve, la suciedad, el barro, los derrames y más', 50, 362, 'https://images-na.ssl-images-amazon.com/images/I/91RiFVppDPL._SL1500_.jpg'),
(61, 'Schumacher SC1279 SC1279 - Cargador rápido (8 A, 12 V)', 'Microprocesador controlado para ajustar automáticamente la tasa de amperaje, para cargar y mantener.\r\nIndicadores LED muestran el estado de carga.', 50, 556, 'https://images-na.ssl-images-amazon.com/images/I/61uQ332miCL._SL1100_.jpg'),
(62, 'Funda Protectora de Asiento Trasero 3000721, Negro', 'Facil instalacion\r\nLe queda a asientos traseros con y sin cabecera\r\nresistente al algua\r\n100% poliester\r\nlavable', 50, 466, 'https://images-na.ssl-images-amazon.com/images/I/61%2BNdZY5CzL._SL1402_.jpg'),
(63, 'Slowton Cinturón de seguridad para perros', 'Manteniéndose seguro y cómodo --- Este cinturón de seguridad para perros ayuda a garantizar la seguridad de su mascota y evita distracciones al conducir', 50, 259, 'https://images-na.ssl-images-amazon.com/images/I/71uyXtO1x1L._SL1000_.jpg'),
(64, 'Michelin 8524 Stealth Ultra Windshield Wiper Blade with Smart Technology', 'Las articulaciones inteligentes de las bisagras permiten que el limpiaparabrisas agarre firmemente el parabrisas en toda la hoja', 50, 308, 'https://images-na.ssl-images-amazon.com/images/I/51TE3KfLe8L._SL1500_.jpg'),
(65, 'Motocicleta Italika de Motoneta - Modelo Vitalia150', 'Tipo de Motor: 4 Tiempos monocilindrico, Cilindrada: 149.6 cc\r\nVelocidad máxima: 85 km/h, Potencia Máxima: 8.31Hp/7500 RPM, Torque máximo:  8.8 Nm @ 5500 RPM', 50, 23000, 'https://images-na.ssl-images-amazon.com/images/I/51Ptl1dAj8L.jpg'),
(66, 'ITALIKA Motocicleta de Adventure - Modelo VX250', 'Tipo de Motor:4 Tiempos monocilindrico 4 valvulas, Cilindrada: 249.6cc\r\nVelocidad máxima: 137 km/h, Potencia Máxima: 24 Hp @ 9000 RPM, Torque máximo: 22 Nm @ 7000 RPM', 50, 37000, 'https://images-na.ssl-images-amazon.com/images/I/41VL86cQ2GL.jpg'),
(67, 'Bell Casco para Bicicleta de Thalia de la Mujer', 'Características Tecnología sin cintas\r\nVisera protege del sol brillos y otros elementos\r\nPinch Guardia garantiza abrocharlos No-pinch en todo momento', 50, 379, 'https://images-na.ssl-images-amazon.com/images/I/61nx3Zk5K8L._SL1050_.jpg'),
(68, 'Monk Bicicleta ECONÓMICA STARBIKE DE MONTAÑA RODADA 26 18 VELOCIDADES', 'Bicicleta rodada 26 de 18 velocidades\r\nCuadro MTB de acero y tijera de acero\r\nFrenos caliper y rines de aluminio\r\nAsiento acojinado y ajustable\r\nAltura recomendada para personas de 145 cm en adelante', 50, 2660, 'https://images-na.ssl-images-amazon.com/images/I/81nAnSaOb9L._SL1500_.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `cateID` smallint(6) NOT NULL,
  `cateName` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`cateID`, `cateName`) VALUES
(1, 'Electronicos'),
(2, 'Libros'),
(3, 'Mascotas'),
(4, 'Videojuegos'),
(50, 'Autos'),
(73, 'Jabones');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `descripcion_pedido`
--

CREATE TABLE `descripcion_pedido` (
  `DescPedidID` smallint(6) NOT NULL,
  `PedID` smallint(6) DEFAULT NULL,
  `itemID` smallint(6) DEFAULT NULL,
  `Quant` smallint(6) DEFAULT NULL,
  `subtotal` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `descripcion_pedido`
--

INSERT INTO `descripcion_pedido` (`DescPedidID`, `PedID`, `itemID`, `Quant`, `subtotal`) VALUES
(10, 22, 36, 2, 16198),
(11, 22, 61, 2, 1112),
(12, 23, 62, 2, 932),
(13, 23, 68, 2, 5320),
(14, 24, 42, 3, 47997),
(15, 24, 50, 3, 507),
(16, 25, 23, 10, 789320),
(17, 26, 40, 1, 6799),
(18, 27, 39, 1, 9030),
(19, 28, 50, 1, 169),
(20, 29, 49, 1, 149),
(21, 30, 40, 1, 6799),
(22, 30, 41, 1, 4398),
(23, 31, 24, 2, 3198),
(24, 31, 23, 1, 78932),
(25, 32, 60, 1, 362),
(26, 32, 18, 1, 6500),
(27, 33, 52, 1, 594),
(28, 33, 31, 1, 349),
(29, 33, 22, 1, 599),
(30, 33, 27, 1, 199),
(31, 33, 42, 1, 15999),
(32, 33, 47, 1, 19970),
(33, 33, 18, 1, 6500),
(34, 34, 42, 1, 15999),
(35, 34, 27, 1, 199),
(36, 34, 52, 1, 594),
(37, 34, 31, 1, 349),
(38, 34, 22, 1, 599),
(39, 34, 18, 1, 6500),
(40, 34, 47, 1, 19970),
(41, 34, 23, 1, 78932),
(42, 34, 25, 1, 429),
(43, 34, 26, 1, 341),
(44, 35, 26, 1, 341),
(45, 36, 50, 1, 169),
(46, 36, 26, 1, 341),
(47, 37, 49, 1, 149),
(50, 39, 1, 1, 5249),
(51, 39, 21, 1, 500000),
(52, 40, 18, 1, 6500),
(53, 40, 22, 1, 599),
(54, 41, 29, 1, 403),
(55, 41, 30, 2, 1734),
(56, 42, 43, 1, 12699),
(63, 50, 39, 1, 9030);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `direccion`
--

CREATE TABLE `direccion` (
  `DicID` smallint(6) NOT NULL,
  `direccion` varchar(30) DEFAULT NULL,
  `ciudad` varchar(20) DEFAULT NULL,
  `estado` varchar(20) DEFAULT NULL,
  `cp` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `direccion`
--

INSERT INTO `direccion` (`DicID`, `direccion`, `ciudad`, `estado`, `cp`) VALUES
(87, 'ggg', 'jjj', 'lll', 0),
(98, 'Lázaro Cárdenas 2959', 'Guadalajara', 'Jalisco', 44500),
(106, 'B', 'N', 'N', 9),
(112, 'Lázaro Cárdenas 2959', 'Guadalajara', 'Jalisco', 44500),
(113, 'Lázaro Cárdenas 2959', 'Guadalajara', 'Jalisco', 44500),
(115, 'Castillo 244', 'Gdl', 'Jal', 45135),
(118, 'Ndn', 'Bdb', 'Bdn', 99),
(119, 'Nn', 'Bb', 'Bb', 99),
(120, 'Vuvuvu', 'Ghj', 'Hj', 79),
(121, 'López mateos 346', 'Gdl', 'Jal', 45123),
(122, 'Santa margarita 34', 'Gdl', 'Jal', 45136),
(123, 'Patria 35', 'Gdl', 'Jal', 45132),
(124, 'bugambiñias', 'jfjfjf', 'jdkdjd', 6787),
(125, 'kfkf', 'fjfj', 'fjdj', 88),
(126, 'N', 'N', 'N', 9),
(127, 'Lj', 'B', 'B', 0),
(128, 'Av Las torres', 'GDl', 'jal', 4500),
(129, 'Lázaro Cárdenas 2959', 'Guadalajara', 'Jalisco', 44500),
(130, 'ff', 'ff', 'f', 500),
(131, 'el country', 'gdl', 'jal', 5677);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `direccion_usuario`
--

CREATE TABLE `direccion_usuario` (
  `DicUserID` smallint(6) NOT NULL,
  `DicID` smallint(6) DEFAULT NULL,
  `UserID` smallint(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `direccion_usuario`
--

INSERT INTO `direccion_usuario` (`DicUserID`, `DicID`, `UserID`) VALUES
(105, 113, 1),
(107, 115, 2),
(113, 121, 2),
(114, 122, 2),
(115, 123, 2),
(116, 124, 1),
(117, 125, 1),
(122, 131, 46);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `PedID` smallint(6) NOT NULL,
  `UserID` smallint(6) DEFAULT NULL,
  `TotalPrice` int(11) NOT NULL,
  `Fecha` varchar(30) DEFAULT NULL,
  `Status` int(11) NOT NULL,
  `DicUserID` smallint(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `pedido`
--

INSERT INTO `pedido` (`PedID`, `UserID`, `TotalPrice`, `Fecha`, `Status`, `DicUserID`) VALUES
(9, 1, 78932, 'Sun, 16 Jun 2019, 13:52:01', 1, 113),
(10, 1, 15999, 'Sun, 16 Jun 2019, 14:15:54', 1, 113),
(13, 1, 5600, '16Jun', 1, 113),
(14, 1, 93, 'Sun, 16 Jun 2019, 14:19:05', 1, 113),
(15, 1, 6500, 'Sun, 16 Jun 2019, 14:22:59', 1, 113),
(16, 1, 362, 'Sun, 16 Jun 2019, 14:26:57', 1, 113),
(17, 1, 5600, '16Jun', 1, 113),
(18, 1, 357, 'Sun, 16 Jun 2019, 14:42:45', 1, 113),
(19, 1, 121, 'Sun, 16 Jun 2019, 14:45:06', 1, 113),
(20, 1, 93, 'Sun, 16 Jun 2019, 14:46:43', 1, 113),
(21, 1, 867, 'Sun, 16 Jun 2019, 14:48:20', 1, 113),
(22, 1, 17310, 'Sun, 16 Jun 2019, 14:51:25', 1, 113),
(23, 1, 6252, 'Sun, 16 Jun 2019, 14:55:31', 1, 113),
(24, 1, 48504, 'Sun, 16 Jun 2019, 14:58:38', 1, 113),
(25, 1, 789320, 'Sun, 16 Jun 2019, 15:00:01', 1, 113),
(26, 1, 6799, 'Sun, 16 Jun 2019, 16:33:17', 1, 113),
(27, 1, 9030, 'Sun, 16 Jun 2019, 18:29:47', 1, 113),
(28, 2, 169, 'dom., 16 jun 2019, 19:20:23', 1, 115),
(29, 1, 149, 'Sun, 16 Jun 2019, 19:24:36', 1, 124),
(30, 2, 11197, 'dom., 16 jun 2019, 19:54:57', 1, 115),
(31, 2, 82130, 'dom., 16 jun 2019, 20:01:42', 1, 115),
(32, 2, 6862, 'dom., 16 jun 2019, 20:08:50', 1, 115),
(33, 2, 44210, 'dom., 16 jun 2019, 20:27:10', 1, 115),
(34, 2, 123912, 'dom., 16 jun 2019, 20:28:21', 1, 115),
(35, 2, 341, 'dom., 16 jun 2019, 20:29:16', 1, 115),
(36, 2, 510, 'dom., 16 jun 2019, 20:29:39', 1, 115),
(37, 2, 149, 'dom., 16 jun 2019, 20:47:44', 1, 115),
(39, 1, 505249, 'Sun, 16 Jun 2019, 21:03:44', 1, 113),
(40, 1, 7099, 'Sun, 16 Jun 2019, 21:06:38', 1, 113),
(41, 1, 2137, 'Sun, 16 Jun 2019, 21:14:01', 1, 113),
(42, 2, 12699, 'dom., 16 jun 2019, 21:15:04', 1, 115),
(44, 2, 2660, 'dom., 16 jun 2019, 21:19:29', 1, 122),
(50, 46, 9030, 'Sun, 16 Jun 2019, 22:32:20', 1, 131);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `UserID` smallint(6) NOT NULL,
  `Nombre` varchar(30) NOT NULL,
  `Apellidos` varchar(50) NOT NULL,
  `Telefono` varchar(10) DEFAULT NULL,
  `Correo` varchar(320) NOT NULL,
  `Password` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`UserID`, `Nombre`, `Apellidos`, `Telefono`, `Correo`, `Password`) VALUES
(1, 'Ricardo', 'González Leal', '3310851164', 'ricardoglr664@gmail.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'),
(2, 'Angel', 'Padilla Esqueda', '3322513356', 'padilla.angel88@gmail.com', 'b221d9dbb083a7f33428d7c2a3c3198ae925614d70210e28716ccaa7cd4ddb79'),
(46, 'Mario', 'Enrique Paz', '3325841236', 'mario@gmail.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'),
(47, 'Roberto', 'Glez Mtz', '658523698', 'roberto@gmail.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3');

--
-- Disparadores `usuario`
--
DELIMITER $$
CREATE TRIGGER `hash_alterpassword` BEFORE UPDATE ON `usuario` FOR EACH ROW BEGIN
IF CHAR_LENGTH(NEW.Password) != 64 THEN
SET NEW.Password = SHA2(NEW.Password,256);
END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `hash_password` BEFORE INSERT ON `usuario` FOR EACH ROW BEGIN
IF CHAR_LENGTH(NEW.Password) != 64 THEN
SET NEW.Password = SHA2(NEW.Password,256);
END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `view_productos_electronicos`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `view_productos_electronicos` (
`itemID` smallint(6)
,`itemName` varchar(150)
,`itemDesc` varchar(500)
,`itemCat` smallint(6)
,`itemPrice` int(11)
,`itemImage` varchar(500)
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `view_productos_libros`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `view_productos_libros` (
`itemID` smallint(6)
,`itemName` varchar(150)
,`itemDesc` varchar(500)
,`itemCat` smallint(6)
,`itemPrice` int(11)
,`itemImage` varchar(500)
);

-- --------------------------------------------------------

--
-- Estructura para la vista `view_productos_electronicos`
--
DROP TABLE IF EXISTS `view_productos_electronicos`;

CREATE ALGORITHM=UNDEFINED DEFINER=`id9949588_ricardoglr664`@`%` SQL SECURITY DEFINER VIEW `view_productos_electronicos`  AS  select `articulo`.`itemID` AS `itemID`,`articulo`.`itemName` AS `itemName`,`articulo`.`itemDesc` AS `itemDesc`,`articulo`.`itemCat` AS `itemCat`,`articulo`.`itemPrice` AS `itemPrice`,`articulo`.`itemImage` AS `itemImage` from `articulo` where `articulo`.`itemCat` = 1 ;

-- --------------------------------------------------------

--
-- Estructura para la vista `view_productos_libros`
--
DROP TABLE IF EXISTS `view_productos_libros`;

CREATE ALGORITHM=UNDEFINED DEFINER=`id9949588_ricardoglr664`@`%` SQL SECURITY DEFINER VIEW `view_productos_libros`  AS  select `articulo`.`itemID` AS `itemID`,`articulo`.`itemName` AS `itemName`,`articulo`.`itemDesc` AS `itemDesc`,`articulo`.`itemCat` AS `itemCat`,`articulo`.`itemPrice` AS `itemPrice`,`articulo`.`itemImage` AS `itemImage` from `articulo` where `articulo`.`itemCat` = 2 ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`adminID`);

--
-- Indices de la tabla `articulo`
--
ALTER TABLE `articulo`
  ADD PRIMARY KEY (`itemID`),
  ADD KEY `itemCat` (`itemCat`);

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`cateID`);

--
-- Indices de la tabla `descripcion_pedido`
--
ALTER TABLE `descripcion_pedido`
  ADD PRIMARY KEY (`DescPedidID`),
  ADD KEY `itemID` (`itemID`),
  ADD KEY `PedID` (`PedID`);

--
-- Indices de la tabla `direccion`
--
ALTER TABLE `direccion`
  ADD PRIMARY KEY (`DicID`);

--
-- Indices de la tabla `direccion_usuario`
--
ALTER TABLE `direccion_usuario`
  ADD PRIMARY KEY (`DicUserID`),
  ADD KEY `DicID` (`DicID`),
  ADD KEY `UserID` (`UserID`);

--
-- Indices de la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`PedID`),
  ADD KEY `UserID` (`UserID`),
  ADD KEY `pedido_ibfk_2` (`DicUserID`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`UserID`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `admins`
--
ALTER TABLE `admins`
  MODIFY `adminID` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `articulo`
--
ALTER TABLE `articulo`
  MODIFY `itemID` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=69;

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `cateID` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=74;

--
-- AUTO_INCREMENT de la tabla `descripcion_pedido`
--
ALTER TABLE `descripcion_pedido`
  MODIFY `DescPedidID` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;

--
-- AUTO_INCREMENT de la tabla `direccion`
--
ALTER TABLE `direccion`
  MODIFY `DicID` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=132;

--
-- AUTO_INCREMENT de la tabla `direccion_usuario`
--
ALTER TABLE `direccion_usuario`
  MODIFY `DicUserID` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=123;

--
-- AUTO_INCREMENT de la tabla `pedido`
--
ALTER TABLE `pedido`
  MODIFY `PedID` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `UserID` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `articulo`
--
ALTER TABLE `articulo`
  ADD CONSTRAINT `articulo_ibfk_1` FOREIGN KEY (`itemCat`) REFERENCES `categoria` (`cateID`);

--
-- Filtros para la tabla `descripcion_pedido`
--
ALTER TABLE `descripcion_pedido`
  ADD CONSTRAINT `descripcion_pedido_ibfk_1` FOREIGN KEY (`PedID`) REFERENCES `pedido` (`PedID`) ON DELETE CASCADE,
  ADD CONSTRAINT `descripcion_pedido_ibfk_2` FOREIGN KEY (`itemID`) REFERENCES `articulo` (`itemID`) ON DELETE CASCADE;

--
-- Filtros para la tabla `direccion_usuario`
--
ALTER TABLE `direccion_usuario`
  ADD CONSTRAINT `direccion_usuario_ibfk_1` FOREIGN KEY (`DicID`) REFERENCES `direccion` (`DicID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `direccion_usuario_ibfk_2` FOREIGN KEY (`UserID`) REFERENCES `usuario` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `usuario` (`UserID`) ON DELETE CASCADE,
  ADD CONSTRAINT `pedido_ibfk_2` FOREIGN KEY (`DicUserID`) REFERENCES `direccion` (`DicID`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
