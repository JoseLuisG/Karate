
CREATE TABLE `usuarios` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `nombre_usuario` varchar(32) UNIQUE NOT NULL,
  `password` varchar(24) DEFAULT NULL,
  `nombre` varchar(128) NOT NULL,
  `rol` enum('COLABORADOR','ADMINISTRADOR') NOT NULL,
  `pais` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8$$

