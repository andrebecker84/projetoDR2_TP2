package infnet.andre.tp2.exercicio03;

import java.util.Objects;

/**
 * Implementação concreta de um cliente real do sistema.
 *
 * Esta classe representa um cliente válido e cadastrado,
 * contendo informações reais como nome.
 *
 * @author André Becker
 * @version 1.0.0
 */
public class ClienteReal implements Cliente {

    private final String nome;

    /**
     * Construtor que cria um cliente real com o nome especificado.
     *
     * @param nome nome do cliente
     * @throws IllegalArgumentException se o nome for nulo ou vazio
     */
    public ClienteReal(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do cliente não pode ser nulo ou vazio");
        }
        this.nome = nome;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClienteReal that)) return false;
        return Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nome);
    }

    @Override
    public String toString() {
        return "ClienteReal{nome='" + nome + "'}";
    }
}
