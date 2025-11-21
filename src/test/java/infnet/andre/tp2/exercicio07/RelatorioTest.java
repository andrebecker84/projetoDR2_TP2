package infnet.andre.tp2.exercicio07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para o padrão Abstract Factory aplicado a relatórios.
 *
 * @author André Becker
 * @version 1.0.0
 */
@DisplayName("Relatorio - Testes de Abstract Factory")
class RelatorioTest {

    private RelatorioServiceDepois service;
    private RelatorioFactory factory;

    @BeforeEach
    void setUp() {
        factory = new RelatorioFactory();
        service = new RelatorioServiceDepois(factory);
    }

    @ParameterizedTest
    @EnumSource(TipoRelatorio.class)
    @DisplayName("Deve criar relatórios corretos para todos os tipos")
    void deveCriarRelatoriosCorretosParaTodosTipos(TipoRelatorio tipo) {
        // Act
        Relatorio relatorio = factory.criar(tipo);

        // Assert
        assertThat(relatorio, is(notNullValue()));

        switch (tipo) {
            case PDF -> assertThat(relatorio, is(instanceOf(RelatorioPDF.class)));
            case CSV -> assertThat(relatorio, is(instanceOf(RelatorioCSV.class)));
            case JSON -> assertThat(relatorio, is(instanceOf(RelatorioJSON.class)));
        }
    }

    @Test
    @DisplayName("Deve gerar relatórios com dados corretos")
    void deveGerarRelatoriosComDadosCorretos() {
        String dados = "Dados de teste";

        // Act & Assert - Verifica que não lança exceção
        assertDoesNotThrow(() -> service.gerarRelatorio(TipoRelatorio.PDF, dados));
        assertDoesNotThrow(() -> service.gerarRelatorio(TipoRelatorio.CSV, dados));
        assertDoesNotThrow(() -> service.gerarRelatorio(TipoRelatorio.JSON, dados));
    }

    @Test
    @DisplayName("Deve lançar exceção para tipo null")
    void deveLancarExcecaoParaTipoNull() {
        assertThrows(IllegalArgumentException.class,
                () -> factory.criar(null));
        assertThrows(IllegalArgumentException.class,
                () -> service.gerarRelatorio(null, "dados"));
    }

    @Test
    @DisplayName("Deve lançar exceção para dados inválidos")
    void deveLancarExcecaoParaDadosInvalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> service.gerarRelatorio(TipoRelatorio.PDF, null));
        assertThrows(IllegalArgumentException.class,
                () -> service.gerarRelatorio(TipoRelatorio.PDF, ""));
    }
}
