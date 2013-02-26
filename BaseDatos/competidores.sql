

CREATE TABLE `competidores` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(128) NOT NULL,
  `peso` decimal(5,2) NOT NULL,
  `edad` tinyint(3) unsigned NOT NULL,
  `sexo` enum('masculino','femenino') NOT NULL,
  `pais` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$


