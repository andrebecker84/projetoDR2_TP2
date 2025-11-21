package infnet.andre.tp2.exercicio06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para PersonalizacaoDepois.
 *
 * Exercita o uso de Strategy Pattern com configuração,
 * eliminando condicionais complexas.
 *
 * @author André Becker
 * @version 1.0.0
 */
@DisplayName("Personalizacao - Testes de Strategy Pattern")
class PersonalizacaoTest {

    private PersonalizacaoDepois personalizacao;

    @BeforeEach
    void setUp() {
        personalizacao = new PersonalizacaoDepois();
    }

    @ParameterizedTest
    @EnumSource(Nationality.class)
    @DisplayName("Deve retornar cores válidas para todas as nacionalidades")
    void deveRetornarCoresValidasParaTodasNacionalidades(Nationality nacionalidade) {
        // Act
        List<Color> cores = personalizacao.getCoresBandeira(nacionalidade);

        // Assert
        assertThat(cores, is(notNullValue()));
        assertThat(cores, is(not(empty())));
        assertThat(cores.stream().allMatch(cor -> cor != null), is(true));
    }

    @Test
    @DisplayName("Deve retornar cores corretas para nacionalidades específicas")
    void deveRetornarCoresCorretasParaNacionalidadesEspecificas() {
        // Act & Assert
        assertThat(personalizacao.getCoresBandeira(Nationality.DUTCH),
                contains(Color.RED, Color.WHITE, Color.BLUE));
        assertThat(personalizacao.getCoresBandeira(Nationality.GERMAN),
                contains(Color.BLACK, Color.RED, Color.YELLOW));
        assertThat(personalizacao.getCoresBandeira(Nationality.BELGIAN),
                contains(Color.BLACK, Color.YELLOW, Color.RED));
        assertThat(personalizacao.getCoresBandeira(Nationality.FRENCH),
                contains(Color.BLUE, Color.WHITE, Color.RED));
        assertThat(personalizacao.getCoresBandeira(Nationality.ITALIAN),
                contains(Color.GREEN, Color.WHITE, Color.RED));
        assertThat(personalizacao.getCoresBandeira(Nationality.UNCLASSIFIED),
                contains(Color.GRAY));
    }

    @Test
    @DisplayName("Deve lançar exceção para nacionalidade null")
    void deveLancarExcecaoParaNacionalidadeNull() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> personalizacao.getCoresBandeira(null));
    }

    @Test
    @DisplayName("Deve garantir distinção entre nacionalidades")
    void deveGarantirDistincaoEntreNacionalidades() {
        List<Color> coresDutch = personalizacao.getCoresBandeira(Nationality.DUTCH);
        List<Color> coresGerman = personalizacao.getCoresBandeira(Nationality.GERMAN);

        assertThat(coresDutch, is(not(equalTo(coresGerman))));
    }
}
