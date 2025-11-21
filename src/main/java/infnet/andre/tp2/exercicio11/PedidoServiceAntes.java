package infnet.andre.tp2.exercicio11;

/**
 * Service de pedidos com switch problemático.
 *
 * <p><strong>PROBLEMAS IDENTIFICADOS:</strong></p>
 * <ul>
 *   <li>Switch com default oculta novos status adicionados ao enum</li>
 *   <li>Compilador não força tratamento de novos casos</li>
 *   <li>Fácil esquecer de atualizar switch ao adicionar status</li>
 *   <li>Default pode mascarar bugs e casos não tratados</li>
 *   <li>Viola princípio Fail-Fast</li>
 *   <li>Dificulta detecção de problemas em tempo de compilação</li>
 * </ul>
 *
 * @author André Becker
 * @version 1.0.0
 * @deprecated Usar {@link PedidoServiceDepois} com switch exaustivo
 */
@Deprecated
public class PedidoServiceAntes {

    /**
     * Obtém mensagem baseada no status do pedido.
     *
     * <p><strong>PROBLEMA:</strong> O default oculta novos status.
     * Se adicionarmos CANCELADO ao enum, este método não será
     * atualizado e o compilador não irá avisar.</p>
     *
     * @param status status do pedido
     * @return mensagem correspondente ao status
     */
    public String getMensagemStatus(StatusPedido status) {
        switch (status) {
            case PENDENTE:
                return "Pedido aguardando processamento";
            case PROCESSANDO:
                return "Pedido em processamento";
            case ENVIADO:
                return "Pedido enviado para entrega";
            case ENTREGUE:
                return "Pedido entregue";
            default:
                return "Status desconhecido";  // MASCARA PROBLEMAS!
        }
    }
}
