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

    @Test
    @DisplayName("Deve retornar tipo correto para CSV")
    void deveRetornarTipoCorretoParaCSV() {
        RelatorioCSV csv = new RelatorioCSV();
        assertThat(csv.getTipo(), is(equalTo(TipoRelatorio.CSV)));
    }

    @Test
    @DisplayName("Deve retornar tipo correto para JSON")
    void deveRetornarTipoCorretoParaJSON() {
        RelatorioJSON json = new RelatorioJSON();
        assertThat(json.getTipo(), is(equalTo(TipoRelatorio.JSON)));
    }

    @Test
    @DisplayName("CSV deve gerar formato correto com múltiplas linhas")
    void csvDeveGerarFormatoCorretoComMultiplasLinhas() {
        RelatorioCSV csv = new RelatorioCSV();
        String dados = "Nome: João\nIdade: 30\nCidade: Rio";

        String resultado = csv.gerar(dados);

        assertThat(resultado, containsString("Campo,Valor"));
        assertThat(resultado, containsString("Nome,João"));
        assertThat(resultado, containsString("Idade,30"));
        assertThat(resultado, containsString("Cidade,Rio"));
    }

    @Test
    @DisplayName("JSON deve gerar formato correto com múltiplas linhas")
    void jsonDeveGerarFormatoCorretoComMultiplasLinhas() {
        RelatorioJSON json = new RelatorioJSON();
        String dados = "Nome: Maria\nIdade: 25";

        String resultado = json.gerar(dados);

        assertThat(resultado, containsString("\"relatorio\""));
        assertThat(resultado, containsString("\"Nome\": \"Maria\""));
        assertThat(resultado, containsString("\"Idade\": \"25\""));
    }

    @Test
    @DisplayName("CSV deve lançar exceção para dados null")
    void csvDeveLancarExcecaoParaDadosNull() {
        RelatorioCSV csv = new RelatorioCSV();
        assertThrows(IllegalArgumentException.class, () -> csv.gerar(null));
    }

    @Test
    @DisplayName("CSV deve lançar exceção para dados vazios")
    void csvDeveLancarExcecaoParaDadosVazios() {
        RelatorioCSV csv = new RelatorioCSV();
        assertThrows(IllegalArgumentException.class, () -> csv.gerar(""));
        assertThrows(IllegalArgumentException.class, () -> csv.gerar("   "));
    }

    @Test
    @DisplayName("JSON deve lançar exceção para dados null")
    void jsonDeveLancarExcecaoParaDadosNull() {
        RelatorioJSON json = new RelatorioJSON();
        assertThrows(IllegalArgumentException.class, () -> json.gerar(null));
    }

    @Test
    @DisplayName("JSON deve lançar exceção para dados vazios")
    void jsonDeveLancarExcecaoParaDadosVazios() {
        RelatorioJSON json = new RelatorioJSON();
        assertThrows(IllegalArgumentException.class, () -> json.gerar(""));
        assertThrows(IllegalArgumentException.class, () -> json.gerar("   "));
    }
}
