package infnet.andre.tp2.exercicio03;

/**
 * Interface que define o contrato para objetos Cliente.
 *
 * Esta interface é parte da implementação do padrão Null Object,
 * permitindo que tanto clientes reais quanto clientes nulos
 * implementem o mesmo contrato.
 *
 * @author André Becker
 * @version 1.0.0
 */
public interface Cliente {

    /**
     * Retorna o nome do cliente.
     *
     * @return nome do cliente
     */
    String getNome();

    /**
     * Verifica se este cliente é um cliente nulo (não existente).
     *
     * @return true se for um cliente nulo, false caso contrário
     */
    boolean isNull();
}
