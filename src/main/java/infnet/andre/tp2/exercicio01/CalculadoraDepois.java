package infnet.andre.tp2.exercicio01;

/**
 * Calculadora que implementa operações matemáticas com código limpo e legível.
 *
 * Esta classe demonstra a aplicação dos princípios de Clean Code relacionados
 * à nomeação clara e descritiva de classes, métodos e variáveis.
 *
 * Melhorias aplicadas:
 * - Nome de classe descritivo que reflete sua responsabilidade
 * - Nome de método que expressa claramente sua intenção
 * - Parâmetros com nomes significativos que descrevem seu propósito
 * - Variáveis intermediárias eliminadas quando não agregam valor
 *
 * Princípios aplicados:
 * - Clean Code: Nomes reveladores de intenção
 * - Clean Code: Evite desinformação
 * - Clean Code: Faça distinções significativas
 *
 * @author André Becker
 * @version 1.0.0
 */
public class CalculadoraDepois {

    /**
     * Calcula o dobro da soma de dois números inteiros.
     *
     * Esta operação primeiro soma os dois valores fornecidos e então
     * multiplica o resultado por dois, retornando o valor final.
     *
     * Exemplos de uso:
     * - calcularDobroDaSoma(3, 5) retorna 16 (pois (3 + 5) * 2 = 16)
     * - calcularDobroDaSoma(10, 20) retorna 60 (pois (10 + 20) * 2 = 60)
     *
     * @param primeiroNumero o primeiro valor a ser somado
     * @param segundoNumero o segundo valor a ser somado
     * @return o dobro da soma dos dois números fornecidos
     */
    public static int calcularDobroDaSoma(int primeiroNumero, int segundoNumero) {
        int soma = primeiroNumero + segundoNumero;
        return soma * 2;
    }

    /**
     * Versão alternativa mais concisa que elimina a variável intermediária.
     *
     * Esta implementação é igualmente legível e mais enxuta, demonstrando
     * que nem sempre é necessário criar variáveis intermediárias quando
     * a operação é simples e direta.
     *
     * @param primeiroNumero o primeiro valor a ser somado
     * @param segundoNumero o segundo valor a ser somado
     * @return o dobro da soma dos dois números fornecidos
     */
    public static int calcularDobroDaSomaConcisa(int primeiroNumero, int segundoNumero) {
        return (primeiroNumero + segundoNumero) * 2;
    }
}
