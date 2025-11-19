@echo off
echo ========================================
echo  Attendance Management System
echo ========================================
echo.

echo [Step 1/2] Compiling Java files...
javac -d bin src\models\*.java src\services\*.java src\Main.java

if %errorlevel% neq 0 (
    echo.
    echo ERROR: Compilation failed!
    pause
    exit /b 1
)

echo Compilation successful!
echo.
echo [Step 2/2] Running program...
echo.

java -cp bin Main

echo.
echo ========================================
echo Program finished!
echo ========================================
pause
