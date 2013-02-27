CREATE TABLE `categorias` (
  `id`       bigint  unsigned NOT NULL AUTO_INCREMENT,
  `nombre`   varchar(128) NOT NULL,
  `peso_min` decimal(5,2) unsigned DEFAULT NULL,
  `peso_max` decimal(5,2) unsigned DEFAULT NULL,
  `edad_min` tinyint(3) unsigned NOT NULL,
  `edad_max` tinyint(3) unsigned NOT NULL,
  `sexo` enum('MASCULINA','FEMENINO','MIXTA') NOT NULL,
  `modalidad` enum('KATA','KUMITE') NOT NULL,
  PRIMARY KEY (`id`)
) 

