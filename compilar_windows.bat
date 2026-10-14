@echo off
if not exist out mkdir out
if not exist lib\ojdbc11.jar echo AVISO: falta lib\ojdbc11.jar para ejecutar con Oracle.
dir /s /b src\*.java > fuentes.txt
javac -encoding UTF-8 -cp "lib\ojdbc11.jar" -d out @fuentes.txt
if errorlevel 1 exit /b 1
echo Compilacion correcta.
