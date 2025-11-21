package infnet.andre.tp2.exercicio04;

/**
 * Implementação que evita mutação de parâmetros através de imutabilidade.
 *
 * Esta classe demonstra como aplicar descontos sem modificar o objeto original,
 * retornando uma nova instância com o valor ajustado.
 *
 * Melhorias aplicadas:
 * - Não modifica o parâmetro de entrada
 * - Retorna novo objeto com valor ajustado
 * - Preserva objeto original intacto
 * - Facilita rastreamento de mudanças
 * - Evita efeitos colaterais
 *
 * Princípios aplicados:
 * - Clean Code: Evite efeitos colaterais
 * - Functional Programming: Imutabilidade
 * - Effective Java: Minimize mutabilidade
 * - SRP: Responsabilidade única (aplicar desconto)
 *
 * @author André Becker
 * @version 1.0.0
 */
public class AjusteDepois {

    private static final double VALOR_DESCONTO_PADRAO = 10.0;

    /**
     * Aplica desconto retornando um novo produto sem modificar o original.
     *
     * Este método não altera o produto passado como parâmetro,
     * garantindo que o objeto original permaneça intacto.
     *
     * @param produto produto original (não será modificado)
     * @return novo produto com desconto aplicado
     * @throws IllegalArgumentException se produto for null
     */
    public ProdutoImutavel aplicarDesconto(ProdutoImutavel produto) {
        validarProduto(produto);
        return produto.aplicarDesconto(VALOR_DESCONTO_PADRAO);
    }

    /**
     * Aplica desconto personalizado retornando um novo produto.
     *
     * @param produto produto original (não será modificado)
     * @param valorDesconto valor do desconto a ser aplicado
     * @return novo produto com desconto aplicado
     * @throws IllegalArgumentException se produto for null ou desconto inválido
     */
    public ProdutoImutavel aplicarDescontoPersonalizado(
            ProdutoImutavel produto,
            double valorDesconto) {
        validarProduto(produto);
        return produto.aplicarDesconto(valorDesconto);
    }

    private void validarProduto(ProdutoImutavel produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser null");
        }
    }
}
