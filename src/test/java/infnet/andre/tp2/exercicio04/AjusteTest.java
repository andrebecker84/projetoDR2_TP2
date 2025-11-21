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
}
