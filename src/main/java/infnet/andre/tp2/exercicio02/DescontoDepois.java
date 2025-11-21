package infnet.andre.tp2.exercicio02;

/**
 * Calculadora de descontos com código limpo e autoexplicativo.
 *
 * Esta classe implementa a política de descontos da empresa de forma clara
 * e manutenível, utilizando constantes nomeadas para eliminar valores mágicos.
 *
 * Melhorias aplicadas:
 * - Constantes nomeadas para valores de negócio (VALOR_MINIMO_PARA_DESCONTO, PERCENTUAL_DESCONTO)
 * - Operador de comparação correto (>=) conforme regra de negócio
 * - Método auxiliar que verifica elegibilidade para desconto
 * - Documentação clara da regra de negócio
 * - Código autoexplicativo e de fácil manutenção
 *
 * Princípios aplicados:
 * - Clean Code: Evite números mágicos
 * - Clean Code: Use constantes nomeadas
 * - Clean Code: Faça o código falar por si mesmo
 * - SRP: Uma única responsabilidade (calcular descontos)
 *
 * @author André Becker
 * @version 1.0.0
 */
public class DescontoDepois {

    /**
     * Valor mínimo de compra necessário para receber desconto.
     * Clientes com compras a partir deste valor (inclusive) são elegíveis ao desconto.
     */
    private static final double VALOR_MINIMO_PARA_DESCONTO = 1000.0;

    /**
     * Percentual de desconto aplicado a compras elegíveis.
     * Valor de 0.10 representa 10% de desconto (preço final = 90% do original).
     */
    private static final double PERCENTUAL_DESCONTO = 0.10;

    /**
     * Multiplicador para calcular preço final com desconto.
     * Valor de 0.90 significa que o cliente pagará 90% do preço original.
     */
    private static final double MULTIPLICADOR_PRECO_COM_DESCONTO = 1.0 - PERCENTUAL_DESCONTO;

    /**
     * Calcula o preço final aplicando desconto quando aplicável.
     *
     * Regra de negócio:
     * - Compras >= R$ 1.000,00 recebem 10% de desconto
     * - Compras < R$ 1.000,00 mantêm o preço original
     *
     * @param precoOriginal preço original do produto ou serviço
     * @return preço final após aplicação de desconto (se elegível)
     * @throws IllegalArgumentException se precoOriginal for negativo
     */
    public double calcularPrecoComDesconto(double precoOriginal) {
        validarPreco(precoOriginal);

        if (isElegivelParaDesconto(precoOriginal)) {
            return aplicarDesconto(precoOriginal);
        }

        return precoOriginal;
    }

    /**
     * Verifica se o valor da compra é elegível para receber desconto.
     *
     * @param preco valor da compra a ser verificado
     * @return true se o valor é maior ou igual ao mínimo necessário, false caso contrário
     */
    private boolean isElegivelParaDesconto(double preco) {
        return preco >= VALOR_MINIMO_PARA_DESCONTO;
    }

    /**
     * Aplica o desconto ao preço original.
     *
     * @param precoOriginal preço antes do desconto
     * @return preço com desconto aplicado
     */
    private double aplicarDesconto(double precoOriginal) {
        return precoOriginal * MULTIPLICADOR_PRECO_COM_DESCONTO;
    }

    /**
     * Valida se o preço fornecido é válido.
     *
     * @param preco preço a ser validado
     * @throws IllegalArgumentException se o preço for negativo
     */
    private void validarPreco(double preco) {
        if (preco < 0) {
            throw new IllegalArgumentException("O preço não pode ser negativo: " + preco);
        }
    }

    /**
     * Retorna o percentual de desconto aplicado pela empresa.
     *
     * @return percentual de desconto como valor decimal (0.10 = 10%)
     */
    public static double getPercentualDesconto() {
        return PERCENTUAL_DESCONTO;
    }

    /**
     * Retorna o valor mínimo necessário para receber desconto.
     *
     * @return valor mínimo em reais
     */
    public static double getValorMinimoParaDesconto() {
        return VALOR_MINIMO_PARA_DESCONTO;
    }
}
