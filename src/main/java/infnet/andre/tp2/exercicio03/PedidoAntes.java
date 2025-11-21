package infnet.andre.tp2.exercicio03;

/**
 * Código original com risco de NullPointerException.
 *
 * Problemas identificados:
 * - Não há verificação de nulidade do cliente
 * - Se cliente for null, o método getNomeCliente() lançará NullPointerException
 * - Sistema pode quebrar inesperadamente em tempo de execução
 * - Dificulta debugging e manutenção
 *
 * @author André Becker
 * @deprecated Use {@link PedidoDepois} para código refatorado
 */
@Deprecated
public class PedidoAntes {

    private ClienteReal cliente;

    /**
     * Construtor que aceita cliente potencialmente nulo.
     *
     * @param cliente cliente do pedido (pode ser null)
     */
    public PedidoAntes(ClienteReal cliente) {
        this.cliente = cliente;
    }

    /**
     * Retorna nome do cliente sem verificação de nulidade.
     * PERIGO: Pode lançar NullPointerException se cliente for null.
     *
     * @return nome do cliente
     * @throws NullPointerException se cliente for null
     */
    public String getNomeCliente() {
        return cliente.getNome(); // NullPointerException se cliente == null
    }
}
