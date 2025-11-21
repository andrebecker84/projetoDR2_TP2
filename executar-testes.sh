#!/bin/bash
# Script para executar o menu interativo de testes

echo ""
echo "================================================"
echo "   Iniciando Menu Interativo de Testes..."
echo "================================================"
echo ""

cd "$(dirname "$0")"
mvn test-compile

# Executar usando Maven exec plugin (funciona melhor multiplataforma)
mvn exec:java -Dexec.mainClass="infnet.andre.tp2.InteractiveTestRunner" \
              -Dexec.classpathScope="test"
