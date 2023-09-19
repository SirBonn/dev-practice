DROP SCHEMA WebBanco;
CREATE SCHEMA WebBanco;
USE WebBanco;

CREATE TABLE usuario (
  codigo INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(45) NOT NULL,
  direccion VARCHAR(250) NOT NULL,
  noIdentificacion VARCHAR(15) NOT NULL,
  sexo VARCHAR(10) NOT NULL,
  password VARCHAR(65) NOT NULL,
  tipoUsuario INT NOT NULL,
  PRIMARY KEY (codigo));


CREATE TABLE turno (
  id INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(45) NOT NULL,
  horaEntrada TIME NOT NULL,
  horaSalida TIME NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE empleado (
  codigoUsuario INT NOT NULL,
  idTurno INT NOT NULL,
  PRIMARY KEY (codigoUsuario),
  CONSTRAINT FK_EMPLEADO_TO_USUARIO
    FOREIGN KEY (codigoUsuario)
    REFERENCES usuario (codigo)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT FK_EMPLEADO_TO_TURNO
    FOREIGN KEY (idTurno)
    REFERENCES turno (id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);

CREATE TABLE cliente (
  codigoUsuario INT NOT NULL,
  birth DATE NOT NULL,
  pdfDPI MEDIUMBLOB ,
  PRIMARY KEY (codigoUsuario),
  CONSTRAINT FK_CLIENTE_TO_USUARIO
    FOREIGN KEY (codigoUsuario)
    REFERENCES usuario (codigo)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);

CREATE TABLE cuenta (
  codigo INT NOT NULL AUTO_INCREMENT,
  codigoCliente INT NOT NULL,
  fechaCreacion DATE NOT NULL,
  saldo FLOAT NOT NULL,
  PRIMARY KEY (codigo),
  CONSTRAINT FK_CUENTA_TO_CLIENTE
    FOREIGN KEY (codigoCliente)
    REFERENCES cliente (codigoUsuario)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);

CREATE TABLE limite (
  id INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(60) NOT NULL,
  valor FLOAT NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE transaccion (
  codigo INT NOT NULL AUTO_INCREMENT,
  codCuenta INT NOT NULL,
  tipo VARCHAR(8) NOT NULL, /*debito o credito*/
  fecha DATE NOT NULL,
  hora TIME NOT NULL,
  monto FLOAT NOT NULL,
  codCajero INT NOT NULL, /* usar cajero virutal 101 en las tranferencias a terceros*/
  saldoCuenta FLOAT NOT NULL,
  PRIMARY KEY (codigo),
  CONSTRAINT FK_TRANSACCION_TO_CUENTA
    FOREIGN KEY (codCuenta)
    REFERENCES cuenta (codigo)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT FK_TRANSACCION_TO_EMPLEADO
    FOREIGN KEY (codCajero)
    REFERENCES empleado (codigoUsuario)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);

CREATE TABLE cambioRealizado (
  id INT NOT NULL AUTO_INCREMENT,
  codGerente INT NOT NULL,
  codUsuarioModificado INT NOT NULL,
  fecha DATE NOT NULL,
  hora TIME NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_CAMBIO_TO_EMPLEADO
    FOREIGN KEY (codGerente)
    REFERENCES empleado (codigoUsuario)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT FK_CAMBIO_TO_USUARIO
    FOREIGN KEY (codUsuarioModificado)
    REFERENCES usuario (codigo)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE cuentaAsociada (
  codCliente INT NOT NULL,
  codCuenta INT NOT NULL,
  PRIMARY KEY (codCliente, codCuenta),
  CONSTRAINT FK_CUENTAASO_TO_CLIENTE
    FOREIGN KEY (codCliente)
    REFERENCES cliente (codigoUsuario)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT FK_CUENTAASO_TO_CUENTA
    FOREIGN KEY (codCuenta)
    REFERENCES cuenta (codigo)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);

CREATE TABLE solicitudAsociacion (
  id INT NOT NULL AUTO_INCREMENT,
  codCliente INT NOT NULL,
  codCuenta INT NOT NULL,
  fecha DATE NOT NULL,
  estado INT NOT NULL DEFAULT 0, /*0, pendiente, 1 aceptada, 2 rechazada*/
  PRIMARY KEY (id),
  CONSTRAINT FK_SOLICITUD_TO_CLIENTE
    FOREIGN KEY (codCliente)
    REFERENCES cliente (codigoUsuario)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT FK_SOLICITUD_TO_CUENTA
    FOREIGN KEY (codCuenta)
    REFERENCES cuenta (codigo)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);
    
INSERT INTO turno VALUES(1, 'Matutino', '6:00', '14:30');
INSERT INTO turno VALUES(2, 'Vespertino', '13:00', '22:00');
INSERT INTO turno VALUES(3, 'Toda Hora', '00:00', '23:59:59');
INSERT INTO usuario VALUES (100, 'Banca admin', 'dir101', '100', 'na', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 0); /*Usuario 0 administrador*/
INSERT INTO usuario VALUES (101, 'Banca empleado', 'dir101', '101', 'na', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 1); /*Usuario 1 empleado*/
INSERT INTO usuario VALUES (102, 'Banca cliente', 'dir101', '102', 'na', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 2); /*Usuario 2 cliente*/
INSERT INTO cliente VALUES (102, '2000-12-10');
INSERT INTO empleado VALUES (100, 3);
INSERT INTO empleado VALUES (101, 3);

INSERT INTO limite VALUES(1, 'Limite reporte 2 (Transacciones)', 0);
INSERT INTO limite VALUES(2, 'Limite reporte 3 (Transacciones sumadas)', 0);
    
    
