#!/bin/bash
# ============================================================================
# Script para executar o menu interativo de testes do projeto Clean Code
#
# Autor: André Becker
# Descrição: Compila e executa o ExecutorTestes (menu interativo de testes)
# Uso: ./executar-testes.sh
# ============================================================================

# Define cores para output (opcional, melhora legibilidade)
GREEN='\033[0;32m'
BLUE='\033[0;34m'
RED='\033[0;31m'
NC='\033[0m' # No Color

# Função para exibir mensagens coloridas
print_info() {
    echo -e "${BLUE}$1${NC}"
}

print_success() {
    echo -e "${GREEN}$1${NC}"
}

print_error() {
    echo -e "${RED}$1${NC}"
}

# Exibe cabeçalho
echo ""
echo "============================================================================"
echo "   Projeto DR2 - TP2: Clean Code"
echo "   Menu Interativo de Testes"
echo "============================================================================"
echo ""

# Navega para o diretório do script (raiz do projeto)
cd "$(dirname "$0")" || exit 1

# Compila as classes de teste
print_info "[1/2] Compilando classes de teste..."
echo ""

if ! mvn test-compile -q; then
    echo ""
    print_error "ERRO: Falha na compilação das classes de teste!"
    print_error "Verifique os erros acima e tente novamente."
    exit 1
fi

echo ""
print_info "[2/2] Iniciando menu interativo..."
echo ""

# Executa o menu interativo de testes usando Maven exec plugin
# Vantagens: classpath automático, multiplataforma, sem hardcode de JARs
mvn exec:java \
    -Dexec.mainClass="infnet.andre.tp2.ExecutorTestes" \
    -Dexec.classpathScope="test" \
    -q

# Captura código de saída
EXIT_CODE=$?

echo ""
echo "============================================================================"
if [ $EXIT_CODE -eq 0 ]; then
    print_success "   Execução concluída com sucesso!"
else
    print_error "   Execução finalizada com código de saída: $EXIT_CODE"
fi
echo "============================================================================"
echo ""

exit $EXIT_CODE
