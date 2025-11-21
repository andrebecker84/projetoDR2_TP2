package infnet.andre.tp2.exercicio11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para PedidoServiceDepois.
 *
 * Exercita o uso de switch exaustivo sem default,
 * verificando tratamento completo de todos os status
 * de pedido.
 *
 * @author André Becker
 * @version 1.0.0
 */
@DisplayName("PedidoService - Testes de Switch Exaustivo")
class PedidoServiceTest {

    private PedidoServiceDepois service;

    @BeforeEach
    void setUp() {
        service = new PedidoServiceDepois();
    }

    @ParameterizedTest
    @MethodSource("provideMensagensEsperadas")
    @DisplayName("Deve retornar mensagem correta para cada status")
    void deveRetornarMensagemCorreta(StatusPedido status, String mensagemEsperada) {
        // Act
        String mensagem = service.getMensagemStatus(status);

        // Assert
        assertThat(mensagem, is(equalTo(mensagemEsperada)));
    }

    @ParameterizedTest
    @MethodSource("providePodeCancelar")
    @DisplayName("Deve verificar corretamente se pode cancelar")
    void deveVerificarSePodeCancelar(StatusPedido status, boolean esperado) {
        // Act
        boolean podeCancelar = service.podeCancelar(status);

        // Assert
        assertThat(podeCancelar, is(equalTo(esperado)));
    }

    @ParameterizedTest
    @MethodSource("provideProximosStatus")
    @DisplayName("Deve retornar próximo status corretamente")
    void deveRetornarProximoStatus(StatusPedido statusAtual, StatusPedido statusEsperado) {
        // Act
        StatusPedido proximoStatus = service.getProximoStatus(statusAtual);

        // Assert
        assertThat(proximoStatus, is(equalTo(statusEsperado)));
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar obter próximo status de ENTREGUE")
    void deveLancarExcecaoAoTentarProximoStatusDeEntregue() {
        // Act & Assert
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> service.getProximoStatus(StatusPedido.ENTREGUE)
        );

        assertThat(exception.getMessage(), containsString("já foi entregue"));
    }

    @Test
    @DisplayName("Deve validar métodos com status null")
    void deveValidarMetodosComStatusNull() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> service.getMensagemStatus(null));

        assertThrows(IllegalArgumentException.class,
                () -> service.podeCancelar(null));

        assertThrows(IllegalArgumentException.class,
                () -> service.getProximoStatus(null));
    }

    @ParameterizedTest
    @EnumSource(StatusPedido.class)
    @DisplayName("Deve tratar todos os status sem exceção")
    void deveTratarTodosStatusSemExcecao(StatusPedido status) {
        // Act & Assert
        assertDoesNotThrow(() -> service.getMensagemStatus(status));
        assertDoesNotThrow(() -> service.podeCancelar(status));

        if (status != StatusPedido.ENTREGUE) {
            assertDoesNotThrow(() -> service.getProximoStatus(status));
        }
    }

    @Test
    @DisplayName("Deve verificar fluxo completo de status do pedido")
    void deveVerificarFluxoCompletoStatusPedido() {
        // Arrange
        StatusPedido status = StatusPedido.PENDENTE;

        // Act & Assert - Fluxo completo
        assertThat(status, is(equalTo(StatusPedido.PENDENTE)));
        assertThat(service.podeCancelar(status), is(true));

        status = service.getProximoStatus(status);
        assertThat(status, is(equalTo(StatusPedido.PROCESSANDO)));
        assertThat(service.podeCancelar(status), is(true));

        status = service.getProximoStatus(status);
        assertThat(status, is(equalTo(StatusPedido.ENVIADO)));
        assertThat(service.podeCancelar(status), is(false));

        status = service.getProximoStatus(status);
        assertThat(status, is(equalTo(StatusPedido.ENTREGUE)));
        assertThat(service.podeCancelar(status), is(false));
    }

    // ===== Métodos de Source para Testes Parametrizados =====

    private static Stream<Arguments> provideMensagensEsperadas() {
        return Stream.of(
                Arguments.of(StatusPedido.PENDENTE, "Pedido aguardando processamento"),
                Arguments.of(StatusPedido.PROCESSANDO, "Pedido em processamento"),
                Arguments.of(StatusPedido.ENVIADO, "Pedido enviado para entrega"),
                Arguments.of(StatusPedido.ENTREGUE, "Pedido entregue")
        );
    }

    private static Stream<Arguments> providePodeCancelar() {
        return Stream.of(
                Arguments.of(StatusPedido.PENDENTE, true),
                Arguments.of(StatusPedido.PROCESSANDO, true),
                Arguments.of(StatusPedido.ENVIADO, false),
                Arguments.of(StatusPedido.ENTREGUE, false)
        );
    }

    private static Stream<Arguments> provideProximosStatus() {
        return Stream.of(
                Arguments.of(StatusPedido.PENDENTE, StatusPedido.PROCESSANDO),
                Arguments.of(StatusPedido.PROCESSANDO, StatusPedido.ENVIADO),
                Arguments.of(StatusPedido.ENVIADO, StatusPedido.ENTREGUE)
        );
    }
}
