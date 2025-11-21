package infnet.andre.tp2.exercicio05;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para ClienteClassificadorDepois.
 * Exercita redução de complexidade ciclomática com Guard Clauses.
 *
 * @author André Becker
 * @version 1.0.0
 */
@DisplayName("ClienteClassificador - Testes de Complexidade Ciclomática")
class ClienteClassificadorTest {

    private ClienteClassificadorDepois classificador;

    @BeforeEach
    void setUp() {
        classificador = new ClienteClassificadorDepois();
    }

    @ParameterizedTest
    @CsvSource({
            "true, 65, 5000.0, PREMIUM_SENIOR",       // Premium + Senior
            "true, 65, 1000.0, PREMIUM_SENIOR",       // Premium + Senior + Baixa renda
            "true, 30, 8000.0, PREMIUM_JOVEM",        // Premium + Jovem + Renda alta
            "true, 30, 1000.0, PREMIUM_JOVEM",        // Premium + Jovem + Baixa renda
            "false, 65, 5000.0, REGULAR_SENIOR",      // Senior + Renda adequada
            "false, 65, 1000.0, SENIOR_BAIXA_RENDA",  // Senior + Baixa renda
            "false, 30, 3000.0, REGULAR_JOVEM",       // Jovem + Renda adequada
            "false, 30, 1000.0, JOVEM_BAIXA_RENDA"    // Jovem + Baixa renda
    })
    @DisplayName("Deve classificar clientes corretamente")
    void deveClassificarClientesCorretamente(
            boolean isPremium, int idade, double rendaMensal, TipoCliente esperado) {
        // Act
        TipoCliente resultado = classificador.classificarCliente(isPremium, idade, rendaMensal);

        // Assert
        assertThat(resultado, is(equalTo(esperado)));
    }

    @ParameterizedTest
    @CsvSource({
            "-1, 3000.0",    // Idade negativa
            "200, 3000.0",   // Idade muito alta
            "30, -100.0"     // Renda negativa
    })
    @DisplayName("Deve lançar exceção para valores inválidos")
    void deveLancarExcecaoParaValoresInvalidos(int idade, double rendaMensal) {
        // Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> classificador.classificarCliente(false, idade, rendaMensal));
    }

    @Test
    @DisplayName("Deve aplicar Guard Clauses corretamente (edge cases)")
    void deveAplicarGuardClausesCorretamente() {
        // Premium + Senior
        assertThat(classificador.classificarCliente(true, 61, 3000.0),
                is(equalTo(TipoCliente.PREMIUM_SENIOR)));

        // Premium + Jovem
        assertThat(classificador.classificarCliente(true, 60, 3000.0),
                is(equalTo(TipoCliente.PREMIUM_JOVEM)));

        // Senior + Renda adequada
        assertThat(classificador.classificarCliente(false, 61, 2000.0),
                is(equalTo(TipoCliente.REGULAR_SENIOR)));

        // Senior + Baixa renda
        assertThat(classificador.classificarCliente(false, 61, 1999.99),
                is(equalTo(TipoCliente.SENIOR_BAIXA_RENDA)));

        // Jovem + Renda adequada
        assertThat(classificador.classificarCliente(false, 60, 2000.0),
                is(equalTo(TipoCliente.REGULAR_JOVEM)));

        // Jovem + Baixa renda
        assertThat(classificador.classificarCliente(false, 60, 1999.99),
                is(equalTo(TipoCliente.JOVEM_BAIXA_RENDA)));
    }

    @Test
    @DisplayName("Deve verificar limites de classificação (boundary testing)")
    void deveVerificarLimitesDeClassificacao() {
        // Limite de idade para senior: > 60 (61+ é senior, 60 é jovem)
        assertThat(classificador.classificarCliente(false, 61, 3000.0),
                is(equalTo(TipoCliente.REGULAR_SENIOR)));
        assertThat(classificador.classificarCliente(false, 60, 3000.0),
                is(equalTo(TipoCliente.REGULAR_JOVEM)));

        // Limite de baixa renda: < 2000
        assertThat(classificador.classificarCliente(false, 30, 2000.0),
                is(equalTo(TipoCliente.REGULAR_JOVEM)));
        assertThat(classificador.classificarCliente(false, 30, 1999.99),
                is(equalTo(TipoCliente.JOVEM_BAIXA_RENDA)));

        // Limite de baixa renda para senior: < 2000
        assertThat(classificador.classificarCliente(false, 65, 2000.0),
                is(equalTo(TipoCliente.REGULAR_SENIOR)));
        assertThat(classificador.classificarCliente(false, 65, 1999.99),
                is(equalTo(TipoCliente.SENIOR_BAIXA_RENDA)));
    }
}
