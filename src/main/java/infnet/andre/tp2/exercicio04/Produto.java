package infnet.andre.tp2.exercicio04;

import java.util.Objects;

/**
 * Classe que representa um produto no sistema.
 *
 * Esta classe é mutável por padrão (para demonstrar o problema),
 * mas a versão refatorada utilizará imutabilidade.
 *
 * @author André Becker
 * @version 1.0.0
 */
public class Produto {

    private String nome;
    private double preco;

    /**
     * Construtor que cria um produto com nome e preço.
     *
     * @param nome nome do produto
     * @param preco preço do produto
     */
    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto produto)) return false;
        return Double.compare(preco, produto.preco) == 0 &&
                Objects.equals(nome, produto.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, preco);
    }

    @Override
    public String toString() {
        return "Produto{nome='" + nome + "', preco=" + preco + "}";
    }
}
