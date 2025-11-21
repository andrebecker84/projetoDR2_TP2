@echo off
REM Script para executar o menu interativo de testes

echo.
echo ================================================
echo   Iniciando Menu Interativo de Testes...
echo ================================================
echo.

cd /d "%~dp0"
mvn test-compile
java -cp "target/test-classes;target/classes;%USERPROFILE%\.m2\repository\org\junit\platform\junit-platform-launcher\1.11.4\junit-platform-launcher-1.11.4.jar;%USERPROFILE%\.m2\repository\org\junit\platform\junit-platform-engine\1.11.4\junit-platform-engine-1.11.4.jar;%USERPROFILE%\.m2\repository\org\junit\platform\junit-platform-commons\1.11.4\junit-platform-commons-1.11.4.jar;%USERPROFILE%\.m2\repository\org\junit\jupiter\junit-jupiter-engine\5.11.4\junit-jupiter-engine-5.11.4.jar;%USERPROFILE%\.m2\repository\org\junit\jupiter\junit-jupiter-api\5.11.4\junit-jupiter-api-5.11.4.jar;%USERPROFILE%\.m2\repository\org\opentest4j\opentest4j\1.3.0\opentest4j-1.3.0.jar;%USERPROFILE%\.m2\repository\org\apiguardian\apiguardian-api\1.1.2\apiguardian-api-1.1.2.jar;%USERPROFILE%\.m2\repository\org\hamcrest\hamcrest\3.0\hamcrest-3.0.jar" infnet.andre.tp2.InteractiveTestRunner

pause
