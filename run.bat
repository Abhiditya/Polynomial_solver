@echo off
echo Compiling Java files...
javac -cp ".;lib/gson-2.10.1.jar" src/*.java -d bin
if %errorlevel% neq 0 (
    echo Compilation failed!
    pause
    exit /b %errorlevel%
)

echo Running program...
java -cp "bin;lib/gson-2.10.1.jar" Main
pause