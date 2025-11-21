package infnet.andre.tp2.exercicio04;

import java.util.Objects;

/**
 * Versão imutável da classe Produto.
 *
 * Esta classe garante que uma vez criado, o produto não pode ser modificado,
 * eliminando efeitos colaterais e facilitando o uso em ambientes concorrentes.
 *
 * Benefícios da imutabilidade:
 * - Thread-safe por natureza
 * - Sem efeitos colaterais inesperados
 * - Facilita testes e debugging
 * - Permite cache seguro de objetos
 * - Código mais previsível e confiável
 *
 * Princípios aplicados:
 * - Clean Code: Evite efeitos colaterais
 * - Effective Java: Minimize mutabilidade
 * - Functional Programming: Imutabilidade
 *
 * @author André Becker
 * @version 1.0.0
 */
public final class ProdutoImutavel {

    private final String nome;
    private final double preco;

    /**
     * Construtor que cria um produto imutável.
     *
     * @param nome nome do produto
     * @param preco preço do produto
     * @throws IllegalArgumentException se nome for nulo/vazio ou preço negativo
     */
    public ProdutoImutavel(String nome, double preco) {
        validarNome(nome);
        validarPreco(preco);
        this.nome = nome;
        this.preco = preco;
    }

    /**
     * Retorna um novo produto com desconto aplicado.
     *
     * Este método não modifica o produto atual, mas retorna uma nova instância
     * com o preço ajustado, mantendo a imutabilidade.
     *
     * @param valorDesconto valor a ser descontado do preço
     * @return novo produto com preço ajustado
     * @throws IllegalArgumentException se desconto for maior que o preço
     */
    public ProdutoImutavel aplicarDesconto(double valorDesconto) {
        validarDesconto(valorDesconto);
        double novoPreco = this.preco - valorDesconto;
        return new ProdutoImutavel(this.nome, novoPreco);
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    private void validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto não pode ser nulo ou vazio");
        }
    }

    private void validarPreco(double preco) {
        if (preco < 0) {
            throw new IllegalArgumentException("Preço não pode ser negativo: " + preco);
        }
    }

    private void validarDesconto(double desconto) {
        if (desconto < 0) {
            throw new IllegalArgumentException("Desconto não pode ser negativo: " + desconto);
        }
        if (desconto > this.preco) {
            throw new IllegalArgumentException(
                    "Desconto (" + desconto + ") não pode ser maior que o preço (" + this.preco + ")"
            );
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProdutoImutavel that)) return false;
        return Double.compare(that.preco, preco) == 0 &&
                Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, preco);
    }

    @Override
    public String toString() {
        return "ProdutoImutavel{nome='" + nome + "', preco=" + preco + "}";
    }
}
