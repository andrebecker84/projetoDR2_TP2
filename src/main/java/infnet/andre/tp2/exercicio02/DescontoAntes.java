package infnet.andre.tp2.exercicio02;

/**
 * Código original com valores mágicos e lógica de desconto problemática.
 *
 * Problemas identificados:
 * - Uso de valores mágicos (1000, 0.9) sem contexto
 * - Operador de comparação incorreto (>) ao invés de (>=)
 * - Regra de negócio não documentada e difícil de entender
 * - Impossível de manter e testar adequadamente
 *
 * Bug crítico:
 * Clientes que compram exatamente R$ 1000,00 não recebem desconto,
 * apenas aqueles que ultrapassam esse valor.
 *
 * @author André Becker
 * @deprecated Use {@link DescontoDepois} para código refatorado
 */
@Deprecated
public class DescontoAntes {

    /**
     * Calcula preço com desconto usando valores mágicos.
     *
     * @param preco preço original do produto
     * @return preço final após aplicação de desconto (se aplicável)
     */
    public double calcular(double preco) {
        return preco > 1000 ? preco * 0.9 : preco;
    }
}
