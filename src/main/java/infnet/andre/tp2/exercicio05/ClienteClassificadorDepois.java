package infnet.andre.tp2.exercicio05;

/**
 * Classificador de clientes refatorado usando Guard Clauses e métodos auxiliares.
 *
 * <p><strong>MELHORIAS IMPLEMENTADAS:</strong></p>
 * <ul>
 *   <li>Guard Clauses eliminam aninhamento profundo</li>
 *   <li>Métodos auxiliares com responsabilidade única (SRP)</li>
 *   <li>Código mais legível e fácil de manter</li>
 *   <li>Complexidade ciclomática reduzida</li>
 *   <li>Fácil adição de novos tipos sem modificar estrutura existente</li>
 *   <li>Validações explícitas com mensagens de erro claras</li>
 *   <li>Melhor testabilidade</li>
 * </ul>
 *
 * @author André Becker
 * @version 1.0.0
 */
public class ClienteClassificadorDepois {

    private static final int IDADE_MINIMA = 0;
    private static final int IDADE_MAXIMA = 150;
    private static final int IDADE_SENIOR = 60;
    private static final double LIMITE_BAIXA_RENDA = 2000.0;
    private static final double RENDA_MINIMA = 0.0;

    /**
     * Classifica um cliente baseado em múltiplos critérios.
     *
     * <p>Utiliza Guard Clauses para validação e retorno antecipado,
     * tornando o código mais linear e fácil de ler.</p>
     *
     * @param isPremium indica se o cliente é premium
     * @param idade idade do cliente
     * @param rendaMensal renda mensal do cliente
     * @return tipo do cliente classificado
     * @throws IllegalArgumentException se os parâmetros forem inválidos
     */
    public TipoCliente classificarCliente(boolean isPremium, int idade, double rendaMensal) {
        validarParametros(idade, rendaMensal);

        if (isSenior(idade)) {
            return classificarClienteSenior(isPremium, rendaMensal);
        }

        return classificarClienteJovem(isPremium, rendaMensal);
    }

    /**
     * Valida os parâmetros de entrada.
     *
     * @param idade idade do cliente
     * @param rendaMensal renda mensal do cliente
     * @throws IllegalArgumentException se os parâmetros forem inválidos
     */
    private void validarParametros(int idade, double rendaMensal) {
        if (idade < IDADE_MINIMA || idade > IDADE_MAXIMA) {
            throw new IllegalArgumentException(
                String.format("Idade inválida: %d. Deve estar entre %d e %d",
                    idade, IDADE_MINIMA, IDADE_MAXIMA)
            );
        }

        if (rendaMensal < RENDA_MINIMA) {
            throw new IllegalArgumentException(
                String.format("Renda mensal inválida: %.2f. Deve ser maior ou igual a %.2f",
                    rendaMensal, RENDA_MINIMA)
            );
        }
    }

    /**
     * Verifica se o cliente é considerado senior.
     *
     * @param idade idade do cliente
     * @return true se a idade for superior a 60 anos
     */
    private boolean isSenior(int idade) {
        return idade > IDADE_SENIOR;
    }

    /**
     * Verifica se o cliente é de baixa renda.
     *
     * @param rendaMensal renda mensal do cliente
     * @return true se a renda for inferior ao limite de baixa renda
     */
    private boolean isBaixaRenda(double rendaMensal) {
        return rendaMensal < LIMITE_BAIXA_RENDA;
    }

    /**
     * Classifica cliente senior baseado em seus atributos.
     *
     * @param isPremium indica se é cliente premium
     * @param rendaMensal renda mensal do cliente
     * @return tipo do cliente senior
     */
    private TipoCliente classificarClienteSenior(boolean isPremium, double rendaMensal) {
        if (isPremium) {
            return TipoCliente.PREMIUM_SENIOR;
        }

        if (isBaixaRenda(rendaMensal)) {
            return TipoCliente.SENIOR_BAIXA_RENDA;
        }

        return TipoCliente.REGULAR_SENIOR;
    }

    /**
     * Classifica cliente jovem baseado em seus atributos.
     *
     * @param isPremium indica se é cliente premium
     * @param rendaMensal renda mensal do cliente
     * @return tipo do cliente jovem
     */
    private TipoCliente classificarClienteJovem(boolean isPremium, double rendaMensal) {
        if (isPremium) {
            return TipoCliente.PREMIUM_JOVEM;
        }

        if (isBaixaRenda(rendaMensal)) {
            return TipoCliente.JOVEM_BAIXA_RENDA;
        }

        return TipoCliente.REGULAR_JOVEM;
    }
}
