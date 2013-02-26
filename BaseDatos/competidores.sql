

CREATE TABLE `competidores` (
  `id` 		bigint(20) UNSIGNED 			NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `nombre` 	varchar(128) 					NOT NULL,
  `peso` 	decimal(5,2) 					NOT NULL,
  `edad` 	tinyint(3) UNSIGNED 			NOT NULL,
  `sexo` 	enum('MASCULINO','FEMENINO') 	NOT NULL,
  `pais` 	varchar(50) 					NOT NULL,
  `foto` 	varchar(255)
)


