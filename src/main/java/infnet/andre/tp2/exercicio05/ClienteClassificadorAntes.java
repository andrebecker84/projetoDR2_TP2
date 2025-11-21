package infnet.andre.tp2.exercicio05;

/**
 * Classificador de clientes com ifs aninhados problemáticos.
 *
 * <p><strong>PROBLEMAS IDENTIFICADOS:</strong></p>
 * <ul>
 *   <li>Múltiplos níveis de aninhamento (até 3 níveis) dificulta legibilidade</li>
 *   <li>Complexidade ciclomática elevada (McCabe > 10)</li>
 *   <li>Difícil manutenção e inclusão de novos tipos de cliente</li>
 *   <li>Duplicação de lógica de validação</li>
 *   <li>Viola o princípio Open/Closed (SOLID)</li>
 *   <li>Código altamente acoplado e difícil de testar</li>
 * </ul>
 *
 * @author André Becker
 * @version 1.0.0
 * @deprecated Usar {@link ClienteClassificadorDepois} que implementa Strategy Pattern
 */
@Deprecated
public class ClienteClassificadorAntes {

    private static final int IDADE_SENIOR = 60;
    private static final double LIMITE_BAIXA_RENDA = 2000.0;

    /**
     * Classifica um cliente baseado em múltiplos critérios.
     *
     * <p>Este método possui ifs profundamente aninhados que tornam o código
     * difícil de entender e manter.</p>
     *
     * @param isPremium indica se o cliente é premium
     * @param idade idade do cliente
     * @param rendaMensal renda mensal do cliente
     * @return tipo do cliente classificado
     */
    public TipoCliente classificarCliente(boolean isPremium, int idade, double rendaMensal) {
        if (idade > IDADE_SENIOR) {
            if (isPremium) {
                return TipoCliente.PREMIUM_SENIOR;
            } else {
                if (rendaMensal < LIMITE_BAIXA_RENDA) {
                    return TipoCliente.SENIOR_BAIXA_RENDA;
                } else {
                    return TipoCliente.REGULAR_SENIOR;
                }
            }
        } else {
            if (isPremium) {
                return TipoCliente.PREMIUM_JOVEM;
            } else {
                if (rendaMensal < LIMITE_BAIXA_RENDA) {
                    return TipoCliente.JOVEM_BAIXA_RENDA;
                } else {
                    return TipoCliente.REGULAR_JOVEM;
                }
            }
        }
    }
}
