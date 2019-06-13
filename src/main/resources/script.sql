DROP TABLE IF EXISTS `Colaborador`;
CREATE TABLE `Colaborador` (
`idColaborador` bigint unsigned NOT NULL AUTO_INCREMENT,
`Nombre` varchar(100) NOT NULL,
`Contrasena` varchar(64) NOT NULL,
`Apellido1` varchar(55) NOT NULL,
`Apellido2` varchar(55) NOT NULL,
`Puesto` varchar(55) NOT NULL,
`Departamento` bigint NOT NULL,
`FechaAlta` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (`idColaborador`)
);
INSERT INTO colaborador(usuario, contrasena, nombre, apellido1, apellido2, puesto, departamento) values('house', '123', 'José Luis',  'Flores', 'García', 'CEO', 7);DROP TABLE IF EXISTS `Comentario`;
CREATE TABLE Comentario (
idcomentario bigint unsigned NOT NULL AUTO_INCREMENT,
idcolaborador bigint NOT NULL,
comentario text,
fecha timestamp DEFAULT CURRENT_TIMESTAMP,
idencargo bigint not null,
PRIMARY KEY (`idComentario`),
 FOREIGN KEY (`idColaborador`) REFERENCES COLABORADOR(`idColaborador`),
FOREIGN KEY (`idencargo`) REFERENCES ENCARGO(`idencargo`)
);

DROP TABLE IF EXISTS `ENCARGO`;
CREATE TABLE `ENCARGO`(
`idEncargo` bigint unsigned NOT NULL AUTO_INCREMENT,
`descripcion` varchar(250) NOT NULL,
`fechaInicio` date NOT NULL,
`fechaFin` date NOT NULL,
`colaborador` bigint NOT NULL,
`status` varchar(50) NOT NULL,
`responsable` bigint NOT NULL,
PRIMARY KEY (`idEncargo`),
FOREIGN KEY (`colaborador`) REFERENCES COLABORADOR(`idColaborador`),
FOREIGN KEY (`responsable`) REFERENCES COLABORADOR(`idColaborador`) 
);
