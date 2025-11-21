package infnet.andre.tp2.exercicio10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para MonitoramentoDepois.
 *
 * Exercita o princípio CQS aplicado a getters com efeitos
 * colaterais, verificando separação entre queries e commands.
 *
 * @author André Becker
 * @version 1.0.0
 */
@DisplayName("Monitoramento - Testes de CQS para Getters")
class MonitoramentoTest {

    private MonitoramentoDepois monitoramento;

    @BeforeEach
    void setUp() {
        monitoramento = new MonitoramentoDepois(25.0);
    }

    @Test
    @DisplayName("Deve verificar que getTemperatura não incrementa contador (Query pura - CQS)")
    void deveVerificarQueGetTemperaturaNaoIncrementaContador() {
        // Arrange
        int contadorInicial = monitoramento.getContadorAcessos();

        // Act - múltiplas leituras
        monitoramento.getTemperatura();
        monitoramento.getTemperatura();
        monitoramento.getTemperatura();

        // Assert - contador não muda (query pura)
        assertThat(monitoramento.getContadorAcessos(), is(equalTo(contadorInicial)));
        assertThat(monitoramento.getTemperatura(), is(closeTo(25.0, 0.01)));
    }

    @Test
    @DisplayName("Deve incrementar contador ao chamar command explícito (Command puro - CQS)")
    void deveIncrementarContadorAoChamarCommandExplicito() {
        // Arrange - estado inicial
        assertThat(monitoramento.getContadorAcessos(), is(equalTo(0)));

        // Act - commands explícitos
        monitoramento.incrementarAcessos();
        monitoramento.incrementarAcessos();
        monitoramento.incrementarAcessos();

        // Assert - contador incrementado
        assertThat(monitoramento.getContadorAcessos(), is(equalTo(3)));

        // Act - resetar
        monitoramento.resetarContadorAcessos();

        // Assert - contador zerado
        assertThat(monitoramento.getContadorAcessos(), is(equalTo(0)));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-300.0, -273.16, 1000.01, 1500.0, 2000.0})
    @DisplayName("Deve lançar exceção para temperaturas inválidas")
    void deveLancarExcecaoParaTemperaturasInvalidas(double temperaturaInvalida) {
        // Act & Assert - construtor
        assertThrows(IllegalArgumentException.class,
                () -> new MonitoramentoDepois(temperaturaInvalida));

        // Act & Assert - setter
        assertThrows(IllegalArgumentException.class,
                () -> monitoramento.setTemperatura(temperaturaInvalida));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-273.15, -50.0, 0.0, 25.0, 100.0, 500.0, 1000.0})
    @DisplayName("Deve aceitar temperaturas válidas")
    void deveAceitarTemperaturasValidas(double temperaturaValida) {
        // Act - construtor
        MonitoramentoDepois novoMonitor = new MonitoramentoDepois(temperaturaValida);

        // Assert - construtor aceita
        assertThat(novoMonitor.getTemperatura(), is(closeTo(temperaturaValida, 0.01)));

        // Act - setter
        monitoramento.setTemperatura(temperaturaValida);

        // Assert - setter aceita
        assertThat(monitoramento.getTemperatura(), is(closeTo(temperaturaValida, 0.01)));
    }

    @Test
    @DisplayName("Deve manter independência entre temperatura e contador")
    void deveManterIndependenciaEntreTemperaturaEContador() {
        // Arrange
        double temperaturaInicial = monitoramento.getTemperatura();

        // Act - alterar contador não afeta temperatura
        monitoramento.incrementarAcessos();
        monitoramento.incrementarAcessos();

        // Assert
        assertThat(monitoramento.getTemperatura(), is(closeTo(temperaturaInicial, 0.01)));
        assertThat(monitoramento.getContadorAcessos(), is(equalTo(2)));

        // Act - alterar temperatura não afeta contador
        monitoramento.setTemperatura(30.0);

        // Assert
        assertThat(monitoramento.getContadorAcessos(), is(equalTo(2)));
        assertThat(monitoramento.getTemperatura(), is(closeTo(30.0, 0.01)));
    }

    @Test
    @DisplayName("Deve suportar workflow completo de monitoramento")
    void deveSuportarWorkflowCompletoDeMonitoramento() {
        // Estado inicial
        monitoramento = new MonitoramentoDepois(20.0);
        assertThat(monitoramento.getTemperatura(), is(closeTo(20.0, 0.01)));
        assertThat(monitoramento.getContadorAcessos(), is(equalTo(0)));

        // Simular monitoramento
        monitoramento.getTemperatura(); // Query pura - não incrementa
        monitoramento.getTemperatura(); // Query pura - não incrementa
        assertThat(monitoramento.getContadorAcessos(), is(equalTo(0)));

        // Registrar acessos manualmente
        monitoramento.incrementarAcessos();
        monitoramento.incrementarAcessos();
        assertThat(monitoramento.getContadorAcessos(), is(equalTo(2)));

        // Alterar temperatura
        monitoramento.setTemperatura(25.0);
        assertThat(monitoramento.getTemperatura(), is(closeTo(25.0, 0.01)));
        assertThat(monitoramento.getContadorAcessos(), is(equalTo(2))); // Contador não afetado

        // Mais acessos
        monitoramento.incrementarAcessos();
        assertThat(monitoramento.getContadorAcessos(), is(equalTo(3)));

        // Resetar contador mantém temperatura
        monitoramento.resetarContadorAcessos();
        assertThat(monitoramento.getContadorAcessos(), is(equalTo(0)));
        assertThat(monitoramento.getTemperatura(), is(closeTo(25.0, 0.01)));
    }

    @Test
    @DisplayName("Deve validar limites de temperatura (zero absoluto e máximo)")
    void deveValidarLimitesDeTemperatura() {
        // Zero absoluto exato - aceito
        MonitoramentoDepois monitorFrio = new MonitoramentoDepois(-273.15);
        assertThat(monitorFrio.getTemperatura(), is(closeTo(-273.15, 0.01)));

        // Máximo exato - aceito
        MonitoramentoDepois monitorQuente = new MonitoramentoDepois(1000.0);
        assertThat(monitorQuente.getTemperatura(), is(closeTo(1000.0, 0.01)));

        // Além dos limites - rejeitado
        assertThrows(IllegalArgumentException.class,
                () -> new MonitoramentoDepois(-273.16));
        assertThrows(IllegalArgumentException.class,
                () -> new MonitoramentoDepois(1000.01));
    }
}
