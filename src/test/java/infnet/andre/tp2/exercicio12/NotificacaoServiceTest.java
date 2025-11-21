package infnet.andre.tp2.exercicio12;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para NotificacaoServiceDepois.
 *
 * Exercita o uso de switch exaustivo sem default para
 * notificações, verificando tratamento completo de todos
 * os tipos de notificação.
 *
 * @author André Becker
 * @version 1.0.0
 */
@DisplayName("NotificacaoService - Testes de Switch Exaustivo")
class NotificacaoServiceTest {

    private NotificacaoServiceDepois service;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        service = new NotificacaoServiceDepois();
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @ParameterizedTest
    @EnumSource(TipoNotificacao.class)
    @DisplayName("Deve enviar notificação corretamente para todos os tipos")
    void deveEnviarNotificacaoCorretamente(TipoNotificacao tipo) {
        // Arrange
        String destinatario = "destinatario@test.com";
        String mensagem = "Mensagem de teste";

        // Act
        service.enviar(tipo, destinatario, mensagem);
        String output = outputStream.toString();

        // Assert
        assertThat(output, not(emptyString()));
        assertThat(output, containsString(destinatario));
        assertThat(output, containsString(mensagem));
    }

    @ParameterizedTest
    @MethodSource("provideCustosEsperados")
    @DisplayName("Deve retornar custo correto para cada tipo")
    void deveRetornarCustoCorreto(TipoNotificacao tipo, int custoEsperado) {
        // Act
        int custo = service.getCustoEstimado(tipo);

        // Assert
        assertThat(custo, is(equalTo(custoEsperado)));
    }

    @ParameterizedTest
    @MethodSource("provideConfirmacaoEsperada")
    @DisplayName("Deve retornar confirmação correta para cada tipo")
    void deveRetornarConfirmacaoCorreta(TipoNotificacao tipo, boolean confirmacaoEsperada) {
        // Act
        boolean requerConfirmacao = service.requerConfirmacao(tipo);

        // Assert
        assertThat(requerConfirmacao, is(equalTo(confirmacaoEsperada)));
    }

    @ParameterizedTest
    @MethodSource("provideEntradasInvalidas")
    @DisplayName("Deve lançar exceção para entradas inválidas")
    void deveLancarExcecaoParaEntradasInvalidas(
            TipoNotificacao tipo,
            String destinatario,
            String mensagem,
            String mensagemEsperada) {

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.enviar(tipo, destinatario, mensagem)
        );

        assertThat(exception.getMessage(), containsString(mensagemEsperada));
    }

    @Test
    @DisplayName("Deve validar métodos com tipo null")
    void deveValidarMetodosComTipoNull() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> service.enviar(null, "dest@test.com", "mensagem"));

        assertThrows(IllegalArgumentException.class,
                () -> service.getCustoEstimado(null));

        assertThrows(IllegalArgumentException.class,
                () -> service.requerConfirmacao(null));
    }

    @Test
    @DisplayName("Deve tratar todos os tipos de notificação sem exceção")
    void deveTratarTodosTiposNotificacaoSemExcecao() {
        // Act & Assert
        for (TipoNotificacao tipo : TipoNotificacao.values()) {
            assertDoesNotThrow(() -> service.enviar(tipo, "destinatario", "mensagem"));
            assertDoesNotThrow(() -> service.getCustoEstimado(tipo));
            assertDoesNotThrow(() -> service.requerConfirmacao(tipo));
        }
    }

    @Test
    @DisplayName("Deve enviar múltiplas notificações sem interferência")
    void deveEnviarMultiplasNotificacoesSemInterferencia() {
        // Act
        service.enviar(TipoNotificacao.EMAIL, "email@test.com", "Msg 1");
        service.enviar(TipoNotificacao.SMS, "1199999999", "Msg 2");
        service.enviar(TipoNotificacao.PUSH, "device-123", "Msg 3");

        String output = outputStream.toString();

        // Assert
        assertThat(output, allOf(
                containsString("Email enviado"),
                containsString("SMS enviado"),
                containsString("Push enviado"),
                containsString("Msg 1"),
                containsString("Msg 2"),
                containsString("Msg 3")
        ));
    }

    @Test
    @DisplayName("Deve verificar ordem de custo: EMAIL < PUSH < SMS")
    void deveVerificarOrdemCusto() {
        // Act
        int custoEmail = service.getCustoEstimado(TipoNotificacao.EMAIL);
        int custoPush = service.getCustoEstimado(TipoNotificacao.PUSH);
        int custoSMS = service.getCustoEstimado(TipoNotificacao.SMS);

        // Assert
        assertThat(custoEmail, is(lessThan(custoPush)));
        assertThat(custoPush, is(lessThan(custoSMS)));
    }

    // ===== Métodos de Source para Testes Parametrizados =====

    private static Stream<Arguments> provideCustosEsperados() {
        return Stream.of(
                Arguments.of(TipoNotificacao.EMAIL, 0),
                Arguments.of(TipoNotificacao.PUSH, 1),
                Arguments.of(TipoNotificacao.SMS, 10)
        );
    }

    private static Stream<Arguments> provideConfirmacaoEsperada() {
        return Stream.of(
                Arguments.of(TipoNotificacao.EMAIL, true),
                Arguments.of(TipoNotificacao.SMS, false),
                Arguments.of(TipoNotificacao.PUSH, true)
        );
    }

    private static Stream<Arguments> provideEntradasInvalidas() {
        return Stream.of(
                Arguments.of(TipoNotificacao.EMAIL, null, "mensagem", "Destinatário não pode ser null ou vazio"),
                Arguments.of(TipoNotificacao.EMAIL, "   ", "mensagem", "Destinatário não pode ser null ou vazio"),
                Arguments.of(TipoNotificacao.EMAIL, "dest@test.com", null, "Mensagem não pode ser null ou vazia"),
                Arguments.of(TipoNotificacao.EMAIL, "dest@test.com", "   ", "Mensagem não pode ser null ou vazia")
        );
    }
}
