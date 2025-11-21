package infnet.andre.tp2.exercicio05;

/**
 * Enumeração que representa os tipos de clientes no sistema.
 *
 * <p>Classifica clientes baseado em dois critérios principais:
 * <ul>
 *   <li>Categoria de serviço: PREMIUM, REGULAR ou BAIXA_RENDA</li>
 *   <li>Faixa etária: SENIOR (acima de 60 anos) ou JOVEM (até 60 anos)</li>
 * </ul>
 *
 * @author André Becker
 * @version 1.0.0
 */
public enum TipoCliente {
    /**
     * Cliente premium com idade superior a 60 anos.
     * Recebe os maiores descontos e benefícios.
     */
    PREMIUM_SENIOR,

    /**
     * Cliente regular com idade superior a 60 anos.
     * Recebe descontos moderados para idosos.
     */
    REGULAR_SENIOR,

    /**
     * Cliente de baixa renda com idade superior a 60 anos.
     * Recebe descontos especiais considerando idade e renda.
     */
    SENIOR_BAIXA_RENDA,

    /**
     * Cliente premium com idade até 60 anos.
     * Recebe benefícios premium padrão.
     */
    PREMIUM_JOVEM,

    /**
     * Cliente regular com idade até 60 anos.
     * Categoria padrão com benefícios básicos.
     */
    REGULAR_JOVEM,

    /**
     * Cliente de baixa renda com idade até 60 anos.
     * Recebe descontos sociais.
     */
    JOVEM_BAIXA_RENDA
}
