-- Ejecutar una sola vez luego de 01_esquema.sql
INSERT INTO permiso(codigo,descripcion) VALUES('TURNO_ABRIR','TURNO_ABRIR');
INSERT INTO permiso(codigo,descripcion) VALUES('VENTA_COBRAR','VENTA_COBRAR');
INSERT INTO permiso(codigo,descripcion) VALUES('CORTE_INTERMEDIO','CORTE_INTERMEDIO');
INSERT INTO permiso(codigo,descripcion) VALUES('REVERSION_SOLICITAR','REVERSION_SOLICITAR');
INSERT INTO permiso(codigo,descripcion) VALUES('REVERSION_AUTORIZAR','REVERSION_AUTORIZAR');
INSERT INTO permiso(codigo,descripcion) VALUES('TURNO_CERRAR','TURNO_CERRAR');
INSERT INTO permiso(codigo,descripcion) VALUES('USUARIO_ADMIN','USUARIO_ADMIN');
INSERT INTO permiso(codigo,descripcion) VALUES('ROL_ADMIN','ROL_ADMIN');
INSERT INTO permiso(codigo,descripcion) VALUES('CATALOGO_ADMIN','CATALOGO_ADMIN');
INSERT INTO rol(nombre,nivel) VALUES('CAJERO',1);
INSERT INTO rol(nombre,nivel) VALUES('SUPERVISOR',2);
INSERT INTO rol(nombre,nivel) VALUES('GERENTE',3);
INSERT INTO rol(nombre,nivel) VALUES('ADMIN',4);
INSERT INTO rol_permiso SELECT r.id,p.id FROM rol r CROSS JOIN permiso p WHERE r.nombre='ADMIN';
INSERT INTO rol_permiso SELECT r.id,p.id FROM rol r CROSS JOIN permiso p WHERE r.nombre='CAJERO' AND p.codigo IN ('TURNO_ABRIR','VENTA_COBRAR','CORTE_INTERMEDIO','REVERSION_SOLICITAR','TURNO_CERRAR');
INSERT INTO rol_permiso SELECT r.id,p.id FROM rol r CROSS JOIN permiso p WHERE r.nombre IN ('SUPERVISOR','GERENTE') AND p.codigo IN ('TURNO_ABRIR','VENTA_COBRAR','CORTE_INTERMEDIO','REVERSION_SOLICITAR','REVERSION_AUTORIZAR','TURNO_CERRAR');
INSERT INTO caja(numero,nombre) VALUES(1,'Caja 1');
INSERT INTO caja(numero,nombre) VALUES(2,'Caja 2');
INSERT INTO caja(numero,nombre) VALUES(3,'Caja 3');
INSERT INTO producto(codigo_barras,nombre,precio,stock) VALUES('750001','Arroz',12.5,100);
INSERT INTO producto(codigo_barras,nombre,precio,stock) VALUES('750002','Leche',9.0,80);
INSERT INTO producto(codigo_barras,nombre,precio,stock) VALUES('750003','Aceite',22.0,50);
-- Crear administrador con java com.supermercado.pos.util.GenerarHash Admin123!
-- Copiar el hash producido a la siguiente instrucción y ejecutarla manualmente:
-- INSERT INTO usuario(username,password_hash,nombre_completo) VALUES ('admin','PEGAR_HASH','Administrador');
-- INSERT INTO usuario_rol SELECT u.id,r.id FROM usuario u CROSS JOIN rol r WHERE u.username='admin' AND r.nombre='ADMIN';
COMMIT;
