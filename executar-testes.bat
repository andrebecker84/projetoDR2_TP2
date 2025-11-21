@echo off
REM ============================================================================
REM Script para executar o menu interativo de testes do projeto Clean Code
REM
REM Autor: André Becker
REM Descrição: Compila e executa o ExecutorTestes (menu interativo de testes)
REM Uso: Dê duplo-clique no arquivo ou execute via CMD
REM ============================================================================

REM Define codificação UTF-8 para suportar caracteres especiais
chcp 65001 > nul

REM Exibe cabeçalho
echo.
echo ============================================================================
echo   Projeto DR2 - TP2: Clean Code
echo   Menu Interativo de Testes
echo ============================================================================
echo.

REM Navega para o diretório do script (raiz do projeto)
cd /d "%~dp0"

REM Compila as classes de teste
echo [1/2] Compilando classes de teste...
echo.
call mvn test-compile -q
if %ERRORLEVEL% NEQ 0 (
    echo.
    echo ERRO: Falha na compilacao das classes de teste!
    echo Verifique os erros acima e tente novamente.
    pause
    exit /b %ERRORLEVEL%
)

echo.
echo [2/2] Iniciando menu interativo...
echo.

REM Executa o menu interativo de testes usando Maven exec plugin
REM Vantagens: classpath automático, multiplataforma, sem hardcode de JARs
call mvn exec:java ^
    -Dexec.mainClass="infnet.andre.tp2.ExecutorTestes" ^
    -Dexec.classpathScope="test" ^
    -q

REM Captura código de saída
set EXIT_CODE=%ERRORLEVEL%

echo.
echo ============================================================================
if %EXIT_CODE% EQU 0 (
    echo   Execucao concluida com sucesso!
) else (
    echo   Execucao finalizada com codigo de saida: %EXIT_CODE%
)
echo ============================================================================
echo.

pause
