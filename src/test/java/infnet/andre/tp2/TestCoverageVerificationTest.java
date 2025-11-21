package infnet.andre.tp2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Teste automatizado que verifica se todos os exercícios possuem classes de teste correspondentes.
 *
 * <p>Este teste faz varredura automática nos diretórios de código e teste,
 * garantindo que nenhum exercício fique sem cobertura de testes.</p>
 *
 * @author André Becker
 * @version 1.0.0
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Verificação Automatizada de Cobertura de Testes")
class TestCoverageVerificationTest {

    private static final String SRC_MAIN_PATH = "src/main/java/infnet/andre/tp2";
    private static final String SRC_TEST_PATH = "src/test/java/infnet/andre/tp2";
    private static final String EXERCISE_PREFIX = "exercicio";
    private static final int NUMERO_MINIMO_EXERCICIOS = 12;
    private static final String SUFIXO_CLASSE_ANTES = "Antes.java";
    private static final String SUFIXO_CLASSE_DEPOIS = "Depois.java";
    private static final String SUFIXO_CLASSE_TESTE = "Test.java";

    @Test
    @DisplayName("Deve ter pasta de testes para cada pasta de exercício")
    void deveTerPastaDeTestesParaCadaExercicio() throws Exception {
        Set<String> exerciceFolders = findExerciseFolders(SRC_MAIN_PATH);
        Set<String> testFolders = findExerciseFolders(SRC_TEST_PATH);

        assertThat("Deve haver pelo menos um exercício",
                exerciceFolders.size(), greaterThan(0));

        for (String exercicio : exerciceFolders) {
            assertThat(
                    String.format("Pasta de teste não encontrada para %s", exercicio),
                    testFolders,
                    hasItem(exercicio)
            );
        }
    }

    @Test
    @DisplayName("Deve ter pelo menos uma classe de teste para cada exercício")
    void deveTerClasseDeTesteParaCadaExercicio() throws Exception {
        Set<String> exerciceFolders = findExerciseFolders(SRC_MAIN_PATH);
        Map<String, Integer> testCountPerExercise = contarClassesPorExercicio(
                exerciceFolders, SRC_TEST_PATH, SUFIXO_CLASSE_TESTE);

        verificarContadorPositivo(testCountPerExercise, "classes de teste");
    }

    @Test
    @DisplayName("Deve ter classes 'Depois' testadas para cada exercício")
    void deveTerClassesDepoisTestadasParaCadaExercicio() throws Exception {
        Set<String> exerciceFolders = findExerciseFolders(SRC_MAIN_PATH);
        Map<String, List<String>> depoisClassesPerExercise = encontrarClassesPorSufixo(
                exerciceFolders, SRC_MAIN_PATH, SUFIXO_CLASSE_DEPOIS);

        verificarListaNaoVazia(depoisClassesPerExercise, "classes refatoradas (Depois)");
    }

    @Test
    @DisplayName("Deve ter classes 'Antes' para comparação em cada exercício")
    void deveTerClassesAntesParaComparacaoEmCadaExercicio() throws Exception {
        Set<String> exerciceFolders = findExerciseFolders(SRC_MAIN_PATH);
        Map<String, List<String>> antesClassesPerExercise = encontrarClassesPorSufixo(
                exerciceFolders, SRC_MAIN_PATH, SUFIXO_CLASSE_ANTES);

        verificarListaNaoVazia(antesClassesPerExercise, "classes de comparação (Antes)");
    }

    @Test
    @DisplayName("Deve manter estrutura de pastas organizada por exercício")
    void deveManterEstruturaOrganizadaPorExercicio() throws Exception {
        Set<String> mainExerciseFolders = findExerciseFolders(SRC_MAIN_PATH);
        Set<String> testExerciseFolders = findExerciseFolders(SRC_TEST_PATH);

        // Verifica que temos pelo menos 12 exercícios conforme especificação do TP2
        assertThat("Deve haver pelo menos 12 exercícios implementados",
                mainExerciseFolders.size(), greaterThanOrEqualTo(NUMERO_MINIMO_EXERCICIOS));

        // Verifica nomenclatura consistente (exercicio01, exercicio02, etc.)
        for (String exercicio : mainExerciseFolders) {
            assertThat("Nomenclatura deve seguir padrão 'exercicioXX'",
                    exercicio, matchesPattern(EXERCISE_PREFIX + "\\d{2}"));
        }
    }

    @Test
    @DisplayName("Deve listar todos os exercícios com estatísticas")
    void deveListarTodosExerciciosComEstatisticas() throws Exception {
        Set<String> exerciceFolders = findExerciseFolders(SRC_MAIN_PATH);

        System.out.println("│                                                                  │");
        System.out.println("│        ESTATÍSTICAS DE COBERTURA DE TESTES POR EXERCÍCIO         │");
        System.out.println("├──────────────────────────────────────────────────────────────────┤");
        System.out.println("│ Exercício                              │ Antes │ Depois │ Testes │");
        System.out.println("├──────────────────────────────────────────────────────────────────┤");

        int totalExercicios = 0;
        int totalClassesAntes = 0;
        int totalClassesDepois = 0;
        int totalClassesTeste = 0;

        List<String> sortedExercises = new ArrayList<>(exerciceFolders);
        Collections.sort(sortedExercises);

        for (String exercicio : sortedExercises) {
            long antesCount = contarArquivosPorSufixo(SRC_MAIN_PATH, exercicio, SUFIXO_CLASSE_ANTES);
            long depoisCount = contarArquivosPorSufixo(SRC_MAIN_PATH, exercicio, SUFIXO_CLASSE_DEPOIS);
            long testCount = contarArquivosPorSufixo(SRC_TEST_PATH, exercicio, SUFIXO_CLASSE_TESTE);

            totalExercicios++;
            totalClassesAntes += antesCount;
            totalClassesDepois += depoisCount;
            totalClassesTeste += testCount;

            System.out.printf("│ %-38s │ %5d │ %6d │ %6d │%n",
                    exercicio, antesCount, depoisCount, testCount);
        }

        System.out.println("├──────────────────────────────────────────────────────────────────┤");
        System.out.printf("│ TOTAL: %d exercícios                   │    %d │     %d │     %d │%n",
                totalExercicios, totalClassesAntes, totalClassesDepois, totalClassesTeste);

        // Validações finais
        assertThat("Deve haver classes 'Antes' implementadas",
                totalClassesAntes, greaterThan(0));
        assertThat("Deve haver classes 'Depois' implementadas",
                totalClassesDepois, greaterThan(0));
        assertThat("Deve haver classes de teste implementadas",
                totalClassesTeste, greaterThan(0));
        System.out.println("└──────────────────────────────────────────────────────────────────┘");
    }

    /**
     * Encontra todas as pastas de exercícios em um diretório base.
     *
     * @param basePath caminho base para busca
     * @return conjunto com nomes das pastas de exercícios
     */
    private Set<String> findExerciseFolders(String basePath) throws Exception {
        File baseDir = new File(basePath);

        if (!baseDir.exists() || !baseDir.isDirectory()) {
            return Collections.emptySet();
        }

        return Arrays.stream(baseDir.listFiles())
                .filter(File::isDirectory)
                .map(File::getName)
                .filter(name -> name.startsWith(EXERCISE_PREFIX))
                .collect(Collectors.toSet());
    }

    /**
     * Conta arquivos com sufixo específico em um caminho.
     *
     * @param basePath caminho base
     * @param exercicio nome do exercício
     * @param sufixo sufixo do arquivo (.java)
     * @return contagem de arquivos
     */
    private long contarArquivosPorSufixo(String basePath, String exercicio, String sufixo) throws Exception {
        Path path = Paths.get(basePath, exercicio);
        if (!Files.exists(path)) {
            return 0;
        }
        return Files.walk(path)
                .filter(Files::isRegularFile)
                .filter(p -> p.toString().endsWith(sufixo))
                .count();
    }

    /**
     * Conta classes de teste por exercício.
     *
     * @param exerciceFolders conjunto de exercícios
     * @param basePath caminho base
     * @param sufixo sufixo do arquivo
     * @return mapa com contagem por exercício
     */
    private Map<String, Integer> contarClassesPorExercicio(
            Set<String> exerciceFolders, String basePath, String sufixo) throws Exception {
        Map<String, Integer> counts = new HashMap<>();
        for (String exercicio : exerciceFolders) {
            long count = contarArquivosPorSufixo(basePath, exercicio, sufixo);
            counts.put(exercicio, (int) count);
        }
        return counts;
    }

    /**
     * Encontra classes por sufixo em todos os exercícios.
     *
     * @param exerciceFolders conjunto de exercícios
     * @param basePath caminho base
     * @param sufixo sufixo do arquivo
     * @return mapa com lista de classes por exercício
     */
    private Map<String, List<String>> encontrarClassesPorSufixo(
            Set<String> exerciceFolders, String basePath, String sufixo) throws Exception {
        Map<String, List<String>> classes = new HashMap<>();
        for (String exercicio : exerciceFolders) {
            Path path = Paths.get(basePath, exercicio);
            if (Files.exists(path)) {
                List<String> classList = Files.walk(path)
                        .filter(Files::isRegularFile)
                        .filter(p -> p.toString().endsWith(sufixo))
                        .map(p -> p.getFileName().toString().replace(".java", ""))
                        .collect(Collectors.toList());
                classes.put(exercicio, classList);
            }
        }
        return classes;
    }

    /**
     * Verifica se todos os contadores são positivos.
     *
     * @param counts mapa de contagens
     * @param tipoClasse descrição do tipo de classe
     */
    private void verificarContadorPositivo(Map<String, Integer> counts, String tipoClasse) {
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            assertThat(
                    String.format("Exercício %s não possui %s", entry.getKey(), tipoClasse),
                    entry.getValue(),
                    greaterThan(0)
            );
        }
    }

    /**
     * Verifica se todas as listas não estão vazias.
     *
     * @param classLists mapa de listas de classes
     * @param tipoClasse descrição do tipo de classe
     */
    private void verificarListaNaoVazia(Map<String, List<String>> classLists, String tipoClasse) {
        for (Map.Entry<String, List<String>> entry : classLists.entrySet()) {
            assertThat(
                    String.format("Exercício %s não possui %s", entry.getKey(), tipoClasse),
                    entry.getValue(),
                    not(empty())
            );
        }
    }
}
