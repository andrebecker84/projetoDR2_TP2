package infnet.andre.tp2.exercicio11;

/**
 * Service de pedidos com switch exaustivo SEM default.
 *
 * <p><strong>MELHORIAS IMPLEMENTADAS:</strong></p>
 * <ul>
 *   <li>Switch exaustivo trata todos os casos explicitamente</li>
 *   <li>SEM default - compilador força tratamento de novos status</li>
 *   <li>Fail-Fast: adicionar status ao enum causa erro de compilação</li>
 *   <li>Impossível esquecer de tratar novos casos</li>
 *   <li>Maior segurança e confiabilidade do código</li>
 *   <li>Documentação viva do código</li>
 *   <li>Facilita manutenção e evolução</li>
 * </ul>
 *
 * @author André Becker
 * @version 1.0.0
 */
public class PedidoServiceDepois {

    /**
     * Obtém mensagem baseada no status do pedido.
     *
     * <p><strong>GARANTIA DO COMPILADOR:</strong> Se um novo status for
     * adicionado ao enum StatusPedido, este código não compilará até
     * que o novo caso seja explicitamente tratado.</p>
     *
     * @param status status do pedido
     * @return mensagem correspondente ao status
     * @throws IllegalArgumentException se status for null
     */
    public String getMensagemStatus(StatusPedido status) {
        if (status == null) {
            throw new IllegalArgumentException("Status não pode ser null");
        }

        // Switch exaustivo SEM default - compilador garante cobertura total
        return switch (status) {
            case PENDENTE -> "Pedido aguardando processamento";
            case PROCESSANDO -> "Pedido em processamento";
            case ENVIADO -> "Pedido enviado para entrega";
            case ENTREGUE -> "Pedido entregue";
            // SEM default: adicionar novo status causará erro de compilação
        };
    }

    /**
     * Verifica se o pedido pode ser cancelado baseado no status atual.
     *
     * @param status status atual do pedido
     * @return true se o pedido pode ser cancelado
     * @throws IllegalArgumentException se status for null
     */
    public boolean podeCancelar(StatusPedido status) {
        if (status == null) {
            throw new IllegalArgumentException("Status não pode ser null");
        }

        // Switch exaustivo para lógica de negócio
        return switch (status) {
            case PENDENTE, PROCESSANDO -> true;
            case ENVIADO, ENTREGUE -> false;
        };
    }

    /**
     * Obtém o próximo status no fluxo do pedido.
     *
     * @param statusAtual status atual do pedido
     * @return próximo status no fluxo
     * @throws IllegalArgumentException se status for null
     * @throws IllegalStateException se pedido já foi entregue
     */
    public StatusPedido getProximoStatus(StatusPedido statusAtual) {
        if (statusAtual == null) {
            throw new IllegalArgumentException("Status não pode ser null");
        }

        return switch (statusAtual) {
            case PENDENTE -> StatusPedido.PROCESSANDO;
            case PROCESSANDO -> StatusPedido.ENVIADO;
            case ENVIADO -> StatusPedido.ENTREGUE;
            case ENTREGUE -> throw new IllegalStateException(
                "Pedido já foi entregue, não há próximo status"
            );
        };
    }
}
