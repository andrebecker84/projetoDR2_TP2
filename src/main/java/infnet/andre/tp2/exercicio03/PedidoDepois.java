package infnet.andre.tp2.exercicio03;

/**
 * Implementação de Pedido utilizando o padrão Null Object.
 *
 * Esta classe demonstra como eliminar verificações de null e
 * NullPointerException através do uso do padrão Null Object.
 *
 * Melhorias aplicadas:
 * - Uso da interface Cliente ao invés de ClienteReal
 * - Garantia de que cliente nunca será null (Null Object Pattern)
 * - Código mais limpo sem verificações de nulidade
 * - Comportamento previsível e seguro
 *
 * Princípios aplicados:
 * - Design Pattern: Null Object
 * - Clean Code: Evite retornar null
 * - Clean Code: Evite passar null
 * - SOLID: Dependency Inversion Principle (depende de abstração)
 *
 * @author André Becker
 * @version 1.0.0
 */
public class PedidoDepois {

    private final Cliente cliente;

    /**
     * Construtor que garante um cliente válido (nunca null).
     *
     * Se o cliente fornecido for null, um ClienteNulo será utilizado,
     * evitando NullPointerException e fornecendo comportamento padrão seguro.
     *
     * @param cliente cliente do pedido (pode ser null, será tratado)
     */
    public PedidoDepois(Cliente cliente) {
        this.cliente = cliente != null ? cliente : new ClienteNulo();
    }

    /**
     * Retorna o nome do cliente de forma segura.
     *
     * Este método nunca lançará NullPointerException, pois o cliente
     * sempre será uma instância válida (real ou nula).
     *
     * @return nome do cliente ou mensagem padrão se cliente não cadastrado
     */
    public String getNomeCliente() {
        return cliente.getNome();
    }

    /**
     * Retorna o cliente associado ao pedido.
     *
     * @return cliente do pedido (nunca null)
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Verifica se o pedido possui um cliente válido cadastrado.
     *
     * @return true se cliente está cadastrado, false caso contrário
     */
    public boolean possuiClienteCadastrado() {
        return !cliente.isNull();
    }
}
