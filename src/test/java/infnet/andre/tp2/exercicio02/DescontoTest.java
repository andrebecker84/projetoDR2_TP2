package infnet.andre.tp2.exercicio02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para DescontoDepois.
 * Exercita eliminação de valores mágicos através de constantes nomeadas.
 *
 * @author André Becker
 * @version 1.0.0
 */
@DisplayName("Desconto - Testes de Valores Mágicos")
class DescontoTest {

    private DescontoDepois desconto;

    @BeforeEach
    void setUp() {
        desconto = new DescontoDepois();
    }

    @ParameterizedTest
    @CsvSource({
            "1000.0, 900.0",   // Exatamente no limite - com desconto
            "1500.0, 1350.0",  // Acima do limite - com desconto
            "2000.0, 1800.0",  // Bem acima - com desconto
            "999.99, 999.99",  // Abaixo do limite - sem desconto
            "500.0, 500.0",    // Bem abaixo - sem desconto
            "0.01, 0.01"       // Mínimo - sem desconto
    })
    @DisplayName("Deve calcular preço com desconto corretamente")
    void deveCalcularPrecoComDescontoCorretamente(double precoOriginal, double precoEsperado) {
        // Act
        double resultado = desconto.calcularPrecoComDesconto(precoOriginal);

        // Assert
        assertThat(resultado, is(closeTo(precoEsperado, 0.01)));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-100.0, -1.0, -0.01})
    @DisplayName("Deve lançar exceção para preços inválidos")
    void deveLancarExcecaoParaPrecosInvalidos(double precoInvalido) {
        // Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> desconto.calcularPrecoComDesconto(precoInvalido));
    }

    @Test
    @DisplayName("Deve usar constantes nomeadas ao invés de valores mágicos")
    void deveUsarConstantesNomeadas() {
        // Verifica comportamento nos limites (edge cases)
        assertThat(desconto.calcularPrecoComDesconto(1000.0), is(closeTo(900.0, 0.01)));
        assertThat(desconto.calcularPrecoComDesconto(999.99), is(closeTo(999.99, 0.01)));

        // Desconto de 10% aplicado corretamente
        double precoComDesconto = desconto.calcularPrecoComDesconto(2000.0);
        assertThat(precoComDesconto, is(lessThan(2000.0)));
        assertThat(precoComDesconto, is(closeTo(1800.0, 0.01)));
    }
}
