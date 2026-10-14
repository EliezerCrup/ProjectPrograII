# Sistema POS para supermercado — Programación II

Proyecto Java 21 con Swing, JDBC y Oracle XE 21c, basado en la estructura y fases del PDF proporcionado por el ingeniero. Sin Maven, Gradle ni JUnit.

## Estado del proyecto
**Implementación de revisión, NO certificada como proyecto final funcional.** Se incluyen las 12 pantallas previstas, modelo de dominio, DAOs básicos, operaciones transaccionales, SQL, permisos y contraseñas PBKDF2. Compila con JDK 21, pero **no se ha probado contra Oracle XE real**. Antes de entregar, completar pruebas de integración, pulir interfaces, reportes/tickets y reglas de negocio pendientes.

## Preparación
1. Instalar JDK 21 y Oracle XE 21c; crear usuario POS ejecutando `sql/00_usuario.sql` como SYSTEM.
2. Conectarse como POS a XEPDB1; ejecutar `sql/01_esquema.sql` y después `sql/02_datos_iniciales.sql`.
3. Descargar de Oracle el controlador oficial `ojdbc11.jar` y colocarlo en `lib/`. **No se incluye por licencia/descarga externa.**
4. Revisar `config.properties` (URL, usuario, contraseña de desarrollo y mínimo operativo). No publicar contraseñas reales.
5. Crear hash de administrador mediante `java ... GenerarHash Admin123!` después de compilar; pegar hash en los INSERT comentados de `02_datos_iniciales.sql`, ejecutarlos y hacer COMMIT.
6. Compilar/ejecutar desde la carpeta `pos` con `compilar_windows.bat` y `ejecutar_windows.bat` o abrir `src` en VS Code, NetBeans, Eclipse o IntelliJ y configurar Java 21 + JDBC.

## Estructura
- `src/com/supermercado/pos/dominio`: POJO y enums.
- `dao`: acceso JDBC mediante PreparedStatement y DAOs.
- `servicio`: autenticación, reglas de negocio y transacciones.
- `ui`: pantallas Swing y diálogos.
- `util`: conexión, sesión y seguridad.
- `excepcion`: excepciones de negocio.
- `sql`: esquema y datos de ejemplo.

## Pendientes para revisión del grupo
- **Reversiones**: el ejemplo implementado devuelve en efectivo el valor revertido. Debe acordarse y corregirse el criterio para ventas con tarjeta o mixtas y el reparto de reembolsos; de lo contrario el efectivo esperado puede ser incorrecto.
- **Reversiones**: al revertir todas las unidades de una venta debe marcarse ANULADA; el prototipo deja PARCIALMENTE_REVERSADA.
- **Roles**: falta interfaz completa de asignación de roles y permisos extra a usuarios, baja/edición, y controles adicionales para evitar auto-escalamiento.
- **Cierre**: faltan impresión de reporte, arqueo detallado y recibos/tickets.
- **Operaciones**: reforzar bloqueo/concurrencia, validación de importes, restricciones de efectivo y pruebas del escenario completo de la fase 11.
- **Pruebas**: las 3 pruebas locales de `Pruebas.java` no reemplazan las pruebas manuales de Oracle y las 9 pruebas indicadas por el ingeniero.
- **Integración**: crear diagrama ER final, capturas de 12 pantallas, README final con usuarios de prueba y repositorio Git con commits por fase.

## Aviso
El proyecto contiene implementación adicional realizada a partir de los requerimientos del PDF, **no es una copia textual de código entregado por el profesor**. Comparar con el PDF y validar las decisiones antes de presentar.
