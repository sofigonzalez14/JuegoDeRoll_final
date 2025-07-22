@echo off
cd /d "%~dp0"
echo Compilando el proyecto...

REM Creamos carpeta bin si no existe
if not exist bin mkdir bin

REM Compilamos todos los .java
javac -d bin src\modelo\*.java src\gestion\*.java src\main\*.java

if %errorlevel% neq 0 (
    echo  Error al compilar. Revisá tu código.
    pause
    exit /b
)

echo Compilacion exitosa.
echo Ejecutando el juego...

java -cp bin main.Main

pause
