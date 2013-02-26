CREATE DATABASE karate;
CREATE TABLE usuarios;delimiter $$

CREATE TABLE `categorias` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(128) NOT NULL,
  `peso_min` decimal(5,2) DEFAULT NULL,
  `peso_max` decimal(5,2) DEFAULT NULL,
  `edad_min` tinyint(3) unsigned NOT NULL,
  `edad_max` tinyint(3) unsigned NOT NULL,
  `sexo` enum('masculino','femenino','mixto') NOT NULL,
  `modalidad` enum('kata','kumite') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$


