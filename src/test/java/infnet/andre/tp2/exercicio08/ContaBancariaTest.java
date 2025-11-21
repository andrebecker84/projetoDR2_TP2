package infnet.andre.tp2.exercicio08;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para ContaBancariaDepois.
 *
 * Exercita o princípio CQS (Command Query Separation),
 * verificando que queries não alteram estado e commands
 * não retornam valores.
 *
 * @author André Becker
 * @version 1.0.0
 */
@DisplayName("ContaBancaria - Testes de CQS (Command Query Separation)")
class ContaBancariaTest {

    private ContaBancariaDepois conta;

    @BeforeEach
    void setUp() {
        conta = new ContaBancariaDepois(1000.0);
    }

    @Test
    @DisplayName("Deve verificar CQS: podeComprar é Query pura, realizarCompra é Command puro")
    void deveVerificarCQS() {
        // Arrange
        double saldoInicial = conta.getSaldo();

        // Act & Assert - Query pura (não altera estado)
        assertThat(conta.podeComprar(500.0), is(true));
        assertThat(conta.podeComprar(500.0), is(true));
        assertThat(conta.getSaldo(), is(closeTo(saldoInicial, 0.01)));

        // Act & Assert - Command puro (altera estado)
        conta.realizarCompra(300.0);
        assertThat(conta.getSaldo(), is(closeTo(700.0, 0.01)));

        // Verifica novamente query após command
        assertThat(conta.podeComprar(500.0), is(true));
        assertThat(conta.podeComprar(800.0), is(false));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-100.0, -1.0, 0.0})
    @DisplayName("Deve lançar exceção para valores inválidos")
    void deveLancarExcecaoParaValoresInvalidos(double valorInvalido) {
        // Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> conta.realizarCompra(valorInvalido));
        assertThrows(IllegalArgumentException.class,
                () -> conta.podeComprar(valorInvalido));
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar comprar com saldo insuficiente")
    void deveLancarExcecaoComSaldoInsuficiente() {
        // Act & Assert
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> conta.realizarCompra(1500.0)
        );
        assertThat(exception.getMessage(), containsString("Saldo insuficiente"));
        assertThat(conta.getSaldo(), is(closeTo(1000.0, 0.01))); // Saldo não alterado
    }

    @Test
    @DisplayName("Deve suportar múltiplas operações mantendo consistência")
    void deveSuportarMultiplasOperacoes() {
        // Operações sequenciais
        conta.realizarCompra(200.0); // 1000 - 200 = 800
        assertThat(conta.getSaldo(), is(closeTo(800.0, 0.01)));
        assertThat(conta.podeComprar(800.0), is(true));
        assertThat(conta.podeComprar(801.0), is(false));

        conta.realizarCompra(300.0); // 800 - 300 = 500
        assertThat(conta.getSaldo(), is(closeTo(500.0, 0.01)));

        conta.realizarCompra(500.0); // 500 - 500 = 0
        assertThat(conta.getSaldo(), is(closeTo(0.0, 0.01)));
        assertThat(conta.podeComprar(0.01), is(false));

        // Não pode mais comprar
        assertThrows(IllegalStateException.class,
                () -> conta.realizarCompra(1.0));
    }
}
