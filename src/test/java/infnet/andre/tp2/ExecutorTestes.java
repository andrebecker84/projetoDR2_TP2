package infnet.andre.tp2;

import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Executor de testes com menu interativo.
 *
 * <p>Permite escolher e executar testes individualmente ou em lote,
 * mostrando resultados detalhados de cada execução.</p>
 *
 * @author André Becker
 * @version 1.0.0
 */
public class ExecutorTestes {

    private static final String TEST_PATH = "infnet.andre.tp2";
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<String, String> exerciseDescriptions = new LinkedHashMap<>();

    // Constantes de formatação
    private static final int LARGURA_LINHA = 66;
    private static final int LARGURA_EXERCICIO = 15;
    private static final int LARGURA_DESCRICAO = 35;
    private static final int LARGURA_MENSAGEM_ERRO = 60;
    private static final int TAMANHO_BUFFER_SLEEP = 100;

    static {
        exerciseDescriptions.put("exercicio01", "Nomeação de Variáveis");
        exerciseDescriptions.put("exercicio02", "Valores Mágicos");
        exerciseDescriptions.put("exercicio03", "Null Object Pattern");
        exerciseDescriptions.put("exercicio04", "Imutabilidade");
        exerciseDescriptions.put("exercicio05", "Complexidade Ciclomática");
        exerciseDescriptions.put("exercicio06", "Strategy Pattern");
        exerciseDescriptions.put("exercicio07", "Abstract Factory");
        exerciseDescriptions.put("exercicio08", "CQS - Conta Bancária");
        exerciseDescriptions.put("exercicio09", "CQS - Fila de Mensagens");
        exerciseDescriptions.put("exercicio10", "CQS - Monitoramento");
        exerciseDescriptions.put("exercicio11", "Switch Exaustivo - Pedidos");
        exerciseDescriptions.put("exercicio12", "Switch Exaustivo - Notificações");
    }

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            exibirMenuPrincipal();
            String opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "0" -> running = false;
                case "13" -> executarTodosOsTestes();
                case "14" -> executarTestesDeVerificacao();
                case "15" -> exibirEstatisticas();
                default -> {
                    try {
                        int exercicio = Integer.parseInt(opcao);
                        if (exercicio >= 1 && exercicio <= 12) {
                            executarTestePorExercicio(exercicio);
                        } else {
                            System.out.println("\n❌ Opção inválida! Escolha entre 0 e 15.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("\n❌ Opção inválida! Digite um número.");
                    }
                }
            }

            if (running) {
                aguardarContinuacao();
            }
        }

        System.out.println("\n✅ Encerrando o menu interativo de testes. Até logo!\n");
        scanner.close();
    }

    private static void exibirMenuPrincipal() {
        System.out.println();
        System.out.println("┌───────────────────────────────────────────────────────────────┐");
        System.out.println("│              🧪 MENU INTERATIVO DE TESTES                     │");
        System.out.println("├───────────────────────────────────────────────────────────────┤");
        System.out.println("│  1  - Exercício 01: Nomeação de Variáveis                     │");
        System.out.println("│  2  - Exercício 02: Valores Mágicos                           │");
        System.out.println("│  3  - Exercício 03: Null Object Pattern                       │");
        System.out.println("│  4  - Exercício 04: Imutabilidade                             │");
        System.out.println("│  5  - Exercício 05: Complexidade Ciclomática                  │");
        System.out.println("│  6  - Exercício 06: Strategy Pattern                          │");
        System.out.println("│  7  - Exercício 07: Abstract Factory                          │");
        System.out.println("│  8  - Exercício 08: CQS - Conta Bancária                      │");
        System.out.println("│  9  - Exercício 09: CQS - Fila de Mensagens                   │");
        System.out.println("│  10 - Exercício 10: CQS - Monitoramento                       │");
        System.out.println("│  11 - Exercício 11: Switch Exaustivo - Pedidos                │");
        System.out.println("│  12 - Exercício 12: Switch Exaustivo - Notificações           │");
        System.out.println("├───────────────────────────────────────────────────────────────┤");
        System.out.println("│  13 - 🚀 Executar TODOS os testes dos exercícios              │");
        System.out.println("│  14 - 🔍 Executar testes de Verificação Automática            │");
        System.out.println("│  15 - 📊 Exibir Estatísticas Gerais                           │");
        System.out.println("├───────────────────────────────────────────────────────────────┤");
        System.out.println("│  0  - Sair                                                    │");
        System.out.println("└───────────────────────────────────────────────────────────────┘");
        System.out.print("\n➤ Escolha uma opção: ");
        System.out.flush();
    }

    private static void aguardarContinuacao() {
        try {
            aguardarFlushDeBuffers();
            exibirMensagemContinuacao();
            limparBufferDeEntrada();
            scanner.nextLine();
        } catch (Exception e) {
            // Se houver erro, apenas continua
        }
    }

    private static void aguardarFlushDeBuffers() throws InterruptedException {
        Thread.sleep(TAMANHO_BUFFER_SLEEP);
        System.out.flush();
        System.err.flush();
    }

    private static void exibirMensagemContinuacao() {
        System.out.print("\n⏎ Pressione ENTER para continuar...");
        System.out.flush();
    }

    private static void limparBufferDeEntrada() throws Exception {
        while (System.in.available() > 0) {
            System.in.read();
        }
    }

    private static void executarTestePorExercicio(int numeroExercicio) {
        String exercicio = String.format("exercicio%02d", numeroExercicio);
        String descricao = exerciseDescriptions.get(exercicio);

        System.out.println();
        System.out.println("┌──────────────────────────────────────────────────────────────────┐");
        System.out.printf("│  🧪 EXECUTANDO TESTES: %-15s - %-20s │%n", exercicio.toUpperCase(), descricao.toUpperCase());
        System.out.println("├──────────────────────────────────────────────────────────────────┤");

        String packageName = TEST_PATH + "." + exercicio;
        executarTestesDePackage(packageName, true);
    }

    private static void executarTodosOsTestes() {
        System.out.println();
        System.out.println("┌──────────────────────────────────────────────────────────────────┐");
        System.out.println("│          🚀 EXECUTANDO TODOS OS TESTES DOS EXERCÍCIOS            │");
        System.out.println("├──────────────────────────────────────────────────────────────────┤");

        Instant inicio = Instant.now();
        int totalTestes = 0;
        int totalSucesso = 0;
        int totalFalhas = 0;

        for (String exercicio : exerciseDescriptions.keySet()) {
            String packageName = TEST_PATH + "." + exercicio;
            String descricao = exerciseDescriptions.get(exercicio);

            System.out.printf("│ 📦 %-15s - %-43s │%n", exercicio.toUpperCase(), descricao);

            TestExecutionSummary summary = executarTestesDePackage(packageName, false);

            totalTestes += summary.getTestsFoundCount();
            totalSucesso += summary.getTestsSucceededCount();
            totalFalhas += (int) summary.getTestsFailedCount();

            if (summary.getTestsFailedCount() > 0) {
                System.out.printf("│    ❌ %d testes | %d ✅ | %d ❌                                │%n",
                        summary.getTestsFoundCount(),
                        summary.getTestsSucceededCount(),
                        summary.getTestsFailedCount());
            } else {
                System.out.printf("│    ✅ %d testes | Todos passaram!%-32s│%n",
                        summary.getTestsSucceededCount(), "");
            }
            System.out.println("├──────────────────────────────────────────────────────────────────┤");
        }

        Instant fim = Instant.now();
        Duration duracao = Duration.between(inicio, fim);

        System.out.println("│                        📊 RESUMO GERAL                           │");
        System.out.println("├──────────────────────────────────────────────────────────────────┤");
        System.out.printf("│ Total de testes: %-47d │%n", totalTestes);
        System.out.printf("│ Sucesso:         %-38d (%.1f%%) │%n", totalSucesso, (totalSucesso * 100.0 / totalTestes));
        System.out.printf("│ Falhas:          %-40d (%.1f%%) │%n", totalFalhas, (totalFalhas * 100.0 / totalTestes));
        System.out.printf("│ Tempo total:     %-44.3f s  │%n", duracao.toMillis() / 1000.0);
        System.out.println("└──────────────────────────────────────────────────────────────────┘");
    }

    private static void executarTestesDeVerificacao() {
        System.out.println();
        System.out.println("┌──────────────────────────────────────────────────────────────────┐");
        System.out.println("│        🔍 EXECUTANDO TESTES DE VERIFICAÇÃO AUTOMÁTICA            │");
        System.out.println("├──────────────────────────────────────────────────────────────────┤");

        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(DiscoverySelectors.selectClass(TestCoverageVerificationTest.class))
                .build();

        Launcher launcher = LauncherFactory.create();
        DetailedTestListener listener = new DetailedTestListener(true);

        launcher.registerTestExecutionListeners(listener);
        launcher.execute(request);

        exibirResultadosDetalhados(listener.getSummary());
    }

    private static TestExecutionSummary executarTestesDePackage(String packageName, boolean verbose) {
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(DiscoverySelectors.selectPackage(packageName))
                .build();

        Launcher launcher = LauncherFactory.create();
        DetailedTestListener listener = new DetailedTestListener(verbose);

        launcher.registerTestExecutionListeners(listener);
        launcher.execute(request);

        if (verbose) {
            exibirResultadosDetalhados(listener.getSummary());
        }

        return listener.getSummary();
    }

    private static void exibirResultadosDetalhados(TestExecutionSummary summary) {
        System.out.println("├──────────────────────────────────────────────────────────────────┤");
        System.out.println("│                      📊 RESULTADOS                               │");
        System.out.println("├──────────────────────────────────────────────────────────────────┤");
        System.out.printf("│ Testes encontrados: %-44d │%n", summary.getTestsFoundCount());
        System.out.printf("│ Testes executados:  %-44d │%n", summary.getTestsStartedCount());
        System.out.printf("│ ✅ Sucesso:         %-44d │%n", summary.getTestsSucceededCount());
        System.out.printf("│ ❌ Falhas:          %-44d │%n", summary.getTestsFailedCount());
        System.out.printf("│ ⏭️  Ignorados:      %-44d │%n", summary.getTestsSkippedCount());

        // Calcula tempo de execução corretamente
        long tempoMilissegundos = summary.getTimeFinished() - summary.getTimeStarted();
        double tempoSegundos = tempoMilissegundos / 1000.0;
        System.out.printf("│ ⏱️  Tempo:          %-42.3f s │%n", tempoSegundos);

        if (summary.getTestsFailedCount() > 0) {
            System.out.println("├──────────────────────────────────────────────────────────────────┤");
            System.out.println("│                    ❌ TESTES COM FALHA                           │");
            System.out.println("├──────────────────────────────────────────────────────────────────┤");
            summary.getFailures().forEach(failure -> {
                System.out.printf("│ 🔴 %-62s │%n", failure.getTestIdentifier().getDisplayName());
                System.out.printf("│    Exceção: %-54s │%n", failure.getException().getClass().getSimpleName());
                System.out.printf("│    Mensagem: %-53s │%n", failure.getException().getMessage());
            });
        }

        System.out.println("└──────────────────────────────────────────────────────────────────┘");
    }

    private static void exibirEstatisticas() {
        System.out.println();
        System.out.println("┌──────────────────────────────────────────────────────────────────┐");
        System.out.println("│            📊 ESTATÍSTICAS GERAIS DO PROJETO                     │");
        System.out.println("├──────────────────────────────────────────────────────────────────┤");

        try {
            File testDir = new File("src/test/java/infnet/andre/tp2");
            File mainDir = new File("src/main/java/infnet/andre/tp2");

            int totalExercicios = 0;
            int totalClassesTest = 0;
            int totalClassesMain = 0;

            System.out.println("│ 📦 EXERCÍCIOS IMPLEMENTADOS:                                     │");
            System.out.println("├──────────────────────────────────────────────────────────────────┤");
            System.out.println("│ Exercício       │ Descrição                           │ Classes  │");
            System.out.println("├──────────────────────────────────────────────────────────────────┤");

            for (Map.Entry<String, String> entry : exerciseDescriptions.entrySet()) {
                String exercicio = entry.getKey();
                String descricao = entry.getValue();

                File exerciseTestDir = new File(testDir, exercicio);
                File exerciseMainDir = new File(mainDir, exercicio);

                int testClasses = contarArquivosJava(exerciseTestDir);
                int mainClasses = contarArquivosJava(exerciseMainDir);

                System.out.printf("│ %-15s │ %-35s │ %-8d │%n", exercicio, descricao, testClasses);

                totalExercicios++;
                totalClassesTest += testClasses;
                totalClassesMain += mainClasses;
            }

            System.out.println("├──────────────────────────────────────────────────────────────────┤");
            System.out.printf("│ TOTAL: %d exercícios │ %d classes   │ %d classes teste           │%n",
                    totalExercicios, totalClassesMain, totalClassesTest);
            System.out.println("└──────────────────────────────────────────────────────────────────┘");

        } catch (Exception e) {
            System.out.println("│ ❌ Erro ao coletar estatísticas: " + e.getMessage());
            System.out.println("└──────────────────────────────────────────────────────────────────┘");
        }
    }

    private static int contarArquivosJava(File dir) {
        if (!dir.exists() || !dir.isDirectory()) {
            return 0;
        }
        File[] files = dir.listFiles((d, name) -> name.endsWith(".java"));
        return files != null ? files.length : 0;
    }

    /**
     * Listener personalizado para capturar eventos detalhados de execução de testes.
     */
    static class DetailedTestListener extends SummaryGeneratingListener {
        private final boolean verbose;
        private int testCounter = 0;

        public DetailedTestListener(boolean verbose) {
            this.verbose = verbose;
        }

        @Override
        public void executionStarted(TestIdentifier testIdentifier) {
            super.executionStarted(testIdentifier);
            if (verbose && testIdentifier.isTest()) {
                testCounter++;
                // Não imprime nada aqui, aguarda o resultado
            }
        }

        @Override
        public void executionFinished(TestIdentifier testIdentifier,
                                      org.junit.platform.engine.TestExecutionResult testExecutionResult) {
            super.executionFinished(testIdentifier, testExecutionResult);

            if (verbose && testIdentifier.isTest()) {
                String displayName = testIdentifier.getDisplayName();
                String status;
                boolean hasError = false;
                String errorMessage = null;

                switch (testExecutionResult.getStatus()) {
                    case SUCCESSFUL -> status = "✅ PASSOU";
                    case FAILED -> {
                        status = "❌ FALHOU";
                        hasError = true;
                        errorMessage = testExecutionResult.getThrowable()
                                .map(Throwable::getMessage)
                                .orElse("Erro desconhecido");
                    }
                    case ABORTED -> status = "⚠️  ABORTADO";
                    default -> status = "❓ DESCONHECIDO";
                }

                formatarEExibirResultadoTeste(displayName, status, hasError, errorMessage);
            }
        }

        private void formatarEExibirResultadoTeste(String displayName, String status,
                                                     boolean hasError, String errorMessage) {
            int prefixLength = String.format(" %2d. ▶️  ", testCounter).length();
            int maxNameLength = LARGURA_LINHA - prefixLength - status.length() - 1;

            String truncatedName = truncarTexto(displayName, maxNameLength);
            String spaces = calcularPadding(prefixLength, truncatedName, status);

            System.out.printf("│ %2d. ▶️  %s%s%s │%n", testCounter, truncatedName, spaces, status);

            if (hasError && errorMessage != null) {
                exibirMensagemErro(errorMessage);
            }
        }

        private String truncarTexto(String texto, int tamanhoMaximo) {
            if (texto.length() <= tamanhoMaximo) {
                return texto;
            }
            return texto.substring(0, tamanhoMaximo - 3) + "...";
        }

        private String calcularPadding(int prefixLength, String nome, String status) {
            int padding = LARGURA_LINHA - prefixLength - nome.length() - status.length();
            return " ".repeat(Math.max(1, padding));
        }

        private void exibirMensagemErro(String errorMessage) {
            String truncatedError = truncarTexto(errorMessage, LARGURA_MENSAGEM_ERRO - 3);
            System.out.printf("│      Erro: %-58s │%n", truncatedError);
        }
    }
}
