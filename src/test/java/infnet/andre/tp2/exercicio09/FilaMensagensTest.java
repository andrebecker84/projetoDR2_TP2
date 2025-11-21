package infnet.andre.tp2.exercicio09;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para FilaMensagensDepois.
 *
 * Exercita o princípio CQS (Command Query Separation) aplicado
 * a estruturas de dados, verificando que peek é query pura e
 * poll é command puro.
 *
 * @author André Becker
 * @version 1.0.0
 */
@DisplayName("FilaMensagens - Testes de CQS para Estruturas de Dados")
class FilaMensagensTest {

    private FilaMensagensDepois fila;

    @BeforeEach
    void setUp() {
        fila = new FilaMensagensDepois();
    }

    @Test
    @DisplayName("Deve verificar que peek não remove elemento (Query pura - CQS)")
    void deveVerificarQuePeekNaoRemoveElemento() {
        // Arrange
        fila.adicionar("Mensagem 1");
        int tamanhoInicial = fila.size();

        // Act - múltiplas chamadas peek
        String mensagem1 = fila.peek();
        String mensagem2 = fila.peek();
        String mensagem3 = fila.peek();

        // Assert - todas retornam o mesmo e tamanho não muda
        assertThat(mensagem1, is(equalTo("Mensagem 1")));
        assertThat(mensagem2, is(equalTo("Mensagem 1")));
        assertThat(mensagem3, is(equalTo("Mensagem 1")));
        assertThat(fila.size(), is(equalTo(tamanhoInicial)));
    }

    @Test
    @DisplayName("Deve remover elemento ao chamar poll (Command puro - CQS)")
    void deveRemoverElementoAoChamarPoll() {
        // Arrange
        fila.adicionar("Mensagem 1");
        fila.adicionar("Mensagem 2");
        int tamanhoInicial = fila.size();

        // Act
        fila.poll();

        // Assert - tamanho diminui
        assertThat(fila.size(), is(equalTo(tamanhoInicial - 1)));
        assertThat(fila.peek(), is(equalTo("Mensagem 2")));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t", "\n"})
    @DisplayName("Deve lançar exceção para mensagens inválidas")
    void deveLancarExcecaoParaMensagensInvalidas(String mensagemInvalida) {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> fila.adicionar(mensagemInvalida)
        );

        assertThat(exception.getMessage(), containsString("não pode ser null ou vazia"));
    }

    @Test
    @DisplayName("Deve lançar exceção ao acessar fila vazia")
    void deveLancarExcecaoAoAcessarFilaVazia() {
        // Act & Assert - peek em fila vazia
        NoSuchElementException exceptionPeek = assertThrows(
                NoSuchElementException.class,
                () -> fila.peek()
        );
        assertThat(exceptionPeek.getMessage(), containsString("Fila está vazia"));

        // Act & Assert - poll em fila vazia
        NoSuchElementException exceptionPoll = assertThrows(
                NoSuchElementException.class,
                () -> fila.poll()
        );
        assertThat(exceptionPoll.getMessage(), containsString("Não há mensagens para remover"));
    }

    @Test
    @DisplayName("Deve processar mensagens em ordem FIFO")
    void deveProcessarMensagensEmOrdemFIFO() {
        // Arrange
        fila.adicionar("Primeira");
        fila.adicionar("Segunda");
        fila.adicionar("Terceira");

        // Act & Assert - verifica ordem FIFO
        assertThat(fila.peek(), is(equalTo("Primeira")));
        fila.poll();
        assertThat(fila.peek(), is(equalTo("Segunda")));
        fila.poll();
        assertThat(fila.peek(), is(equalTo("Terceira")));
        fila.poll();
        assertThat(fila.isEmpty(), is(true));
    }

    @Test
    @DisplayName("Deve manter consistência de estado após operações")
    void deveManterConsistenciaDeEstadoAposOperacoes() {
        // Estado inicial
        assertThat(fila.isEmpty(), is(true));
        assertThat(fila.size(), is(equalTo(0)));

        // Adiciona mensagens
        fila.adicionar("Msg 1");
        assertThat(fila.isEmpty(), is(false));
        assertThat(fila.size(), is(equalTo(1)));

        fila.adicionar("Msg 2");
        assertThat(fila.size(), is(equalTo(2)));

        // Remove mensagens
        fila.poll();
        assertThat(fila.size(), is(equalTo(1)));
        assertThat(fila.isEmpty(), is(false));

        fila.poll();
        assertThat(fila.size(), is(equalTo(0)));
        assertThat(fila.isEmpty(), is(true));
    }

    @Test
    @DisplayName("Deve manter consistência após operações complexas")
    void deveManterConsistenciaAposOperacoesComplexas() {
        // Arrange
        fila.adicionar("A");
        fila.adicionar("B");
        fila.adicionar("C");

        // Act - mix de operações
        fila.peek(); // Consulta sem remover
        fila.poll(); // Remove A
        fila.adicionar("D");
        fila.adicionar("D"); // Permite duplicatas
        String mensagem = fila.peek(); // Deve ser B

        // Assert
        assertThat(mensagem, is(equalTo("B")));
        assertThat(fila.size(), is(equalTo(4))); // B, C, D, D
    }
}
