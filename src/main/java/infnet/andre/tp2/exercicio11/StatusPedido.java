package infnet.andre.tp2.exercicio11;

/**
 * Enumeração que representa os status possíveis de um pedido.
 *
 * <p>Define o ciclo de vida completo de um pedido no sistema,
 * desde sua criação até a entrega final.</p>
 *
 * @author André Becker
 * @version 1.0.0
 */
public enum StatusPedido {
    /**
     * Pedido criado mas ainda não iniciado processamento.
     */
    PENDENTE,

    /**
     * Pedido em processamento (separação, embalagem).
     */
    PROCESSANDO,

    /**
     * Pedido enviado para entrega.
     */
    ENVIADO,

    /**
     * Pedido entregue ao destinatário.
     */
    ENTREGUE
}
