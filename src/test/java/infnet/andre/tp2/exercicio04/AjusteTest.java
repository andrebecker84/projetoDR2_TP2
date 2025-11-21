package infnet.andre.tp2.exercicio04;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para o princípio de imutabilidade.
 *
 * @author André Becker
 * @version 1.0.0
 */
@DisplayName("Ajuste - Testes de Imutabilidade")
class AjusteTest {

    private AjusteDepois ajuste;

    @BeforeEach
    void setUp() {
        ajuste = new AjusteDepois();
    }

    @Test
    @DisplayName("Deve retornar novo produto sem modificar original (Imutabilidade)")
    void deveRetornarNovoProdutoSemModificarOriginal() {
        // Arrange
        ProdutoImutavel original = new ProdutoImutavel("Notebook", 3000.0);

        // Act
        ProdutoImutavel comDesconto = ajuste.aplicarDesconto(original);
        ProdutoImutavel comDescontoPersonalizado = ajuste.aplicarDescontoPersonalizado(original, 150.0);

        // Assert - Original permanece inalterado
        assertThat(original.getPreco(), is(closeTo(3000.0, 0.01)));
        assertThat(original.getNome(), is(equalTo("Notebook")));

        // Assert - Novos objetos foram criados (desconto de 10.0 e 150.0)
        assertThat(comDesconto.getPreco(), is(closeTo(2990.0, 0.01))); // 3000 - 10 = 2990
        assertThat(comDescontoPersonalizado.getPreco(), is(closeTo(2850.0, 0.01))); // 3000 - 150 = 2850
        assertThat(comDesconto, is(not(sameInstance(original))));
        assertThat(comDescontoPersonalizado, is(not(sameInstance(original))));
    }

    @Test
    @DisplayName("Deve aplicar descontos corretamente")
    void deveAplicarDescontosCorretamente() {
        ProdutoImutavel produto = new ProdutoImutavel("Mouse", 100.0);

        // Desconto padrão de 10.0
        ProdutoImutavel comDesconto = ajuste.aplicarDesconto(produto);
        assertThat(comDesconto.getPreco(), is(closeTo(90.0, 0.01))); // 100 - 10 = 90

        // Desconto personalizado de 25.0
        ProdutoImutavel comDescontoPersonalizado = ajuste.aplicarDescontoPersonalizado(produto, 25.0);
        assertThat(comDescontoPersonalizado.getPreco(), is(closeTo(75.0, 0.01))); // 100 - 25 = 75
    }

    @Test
    @DisplayName("Deve lançar exceção para produtos null")
    void deveLancarExcecaoParaProdutosNull() {
        assertThrows(IllegalArgumentException.class,
                () -> ajuste.aplicarDesconto(null));
        assertThrows(IllegalArgumentException.class,
                () -> ajuste.aplicarDescontoPersonalizado(null, 0.1));
    }

    @Test
    @DisplayName("Deve permitir operações encadeadas mantendo imutabilidade")
    void devePermitirOperacoesEncadeadasMantendoImutabilidade() {
        ProdutoImutavel original = new ProdutoImutavel("Teclado", 200.0);

        // Operações encadeadas
        ProdutoImutavel resultado = ajuste.aplicarDesconto(
                ajuste.aplicarDescontoPersonalizado(original, 20.0)
        );

        // Original não muda
        assertThat(original.getPreco(), is(closeTo(200.0, 0.01)));

        // Resultado: (200 - 20) - 10 = 170
        assertThat(resultado.getPreco(), is(closeTo(170.0, 0.01)));
    }

    @Test
    @DisplayName("Deve lançar exceção ao criar produto com nome null")
    void deveLancarExcecaoAoCriarProdutoComNomeNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new ProdutoImutavel(null, 100.0));
    }

    @Test
    @DisplayName("Deve lançar exceção ao criar produto com nome vazio")
    void deveLancarExcecaoAoCriarProdutoComNomeVazio() {
        assertThrows(IllegalArgumentException.class,
                () -> new ProdutoImutavel("", 100.0));
        assertThrows(IllegalArgumentException.class,
                () -> new ProdutoImutavel("   ", 100.0));
    }

    @Test
    @DisplayName("Deve lançar exceção ao criar produto com preço negativo")
    void deveLancarExcecaoAoCriarProdutoComPrecoNegativo() {
        assertThrows(IllegalArgumentException.class,
                () -> new ProdutoImutavel("Produto", -10.0));
    }

    @Test
    @DisplayName("Deve lançar exceção ao aplicar desconto negativo")
    void deveLancarExcecaoAoAplicarDescontoNegativo() {
        ProdutoImutavel produto = new ProdutoImutavel("Monitor", 500.0);
        assertThrows(IllegalArgumentException.class,
                () -> produto.aplicarDesconto(-50.0));
    }

    @Test
    @DisplayName("Deve lançar exceção ao aplicar desconto maior que preço")
    void deveLancarExcecaoAoAplicarDescontoMaiorQuePreco() {
        ProdutoImutavel produto = new ProdutoImutavel("Mouse", 50.0);
        assertThrows(IllegalArgumentException.class,
                () -> produto.aplicarDesconto(100.0));
    }

    @Test
    @DisplayName("Deve implementar equals corretamente")
    void deveImplementarEqualsCorretamente() {
        ProdutoImutavel produto1 = new ProdutoImutavel("Notebook", 3000.0);
        ProdutoImutavel produto2 = new ProdutoImutavel("Notebook", 3000.0);
        ProdutoImutavel produto3 = new ProdutoImutavel("Mouse", 100.0);

        // Reflexivo: x.equals(x) deve ser true
        assertThat(produto1.equals(produto1), is(true));

        // Simétrico: se x.equals(y) então y.equals(x)
        assertThat(produto1.equals(produto2), is(true));
        assertThat(produto2.equals(produto1), is(true));

        // Diferentes
        assertThat(produto1.equals(produto3), is(false));

        // Null
        assertThat(produto1.equals(null), is(false));

        // Tipo diferente
        assertThat(produto1.equals("String"), is(false));
    }

    @Test
    @DisplayName("Deve implementar hashCode corretamente")
    void deveImplementarHashCodeCorretamente() {
        ProdutoImutavel produto1 = new ProdutoImutavel("Teclado", 150.0);
        ProdutoImutavel produto2 = new ProdutoImutavel("Teclado", 150.0);

        // Objetos iguais devem ter mesmo hashCode
        assertThat(produto1.hashCode(), is(equalTo(produto2.hashCode())));
    }

    @Test
    @DisplayName("Deve implementar toString corretamente")
    void deveImplementarToStringCorretamente() {
        ProdutoImutavel produto = new ProdutoImutavel("Webcam", 250.0);
        String resultado = produto.toString();

        assertThat(resultado, containsString("ProdutoImutavel"));
        assertThat(resultado, containsString("Webcam"));
        assertThat(resultado, containsString("250.0"));
    }

    @Test
    @DisplayName("Deve permitir preço zero")
    void devePermitirPrecoZero() {
        ProdutoImutavel produto = new ProdutoImutavel("Brinde", 0.0);
        assertThat(produto.getPreco(), is(closeTo(0.0, 0.01)));
    }

    @Test
    @DisplayName("Deve permitir desconto exato do preço (resultado zero)")
    void devePermitirDescontoExatoDoPreco() {
        ProdutoImutavel produto = new ProdutoImutavel("Liquidação", 50.0);
        ProdutoImutavel comDesconto = produto.aplicarDesconto(50.0);
        assertThat(comDesconto.getPreco(), is(closeTo(0.0, 0.01)));
    }
}
