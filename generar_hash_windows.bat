@echo off
set /p CLAVE=Clave de administrador (solo entorno de prueba): 
java -cp "out;lib\ojdbc11.jar" com.supermercado.pos.util.GenerarHash "%CLAVE%"
