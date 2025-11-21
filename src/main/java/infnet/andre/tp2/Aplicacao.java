package infnet.andre.tp2;

import infnet.andre.tp2.exercicio01.CalculadoraDepois;
import infnet.andre.tp2.exercicio02.DescontoDepois;
import infnet.andre.tp2.exercicio03.*;
import infnet.andre.tp2.exercicio04.*;
import infnet.andre.tp2.exercicio05.*;
import infnet.andre.tp2.exercicio06.*;
import infnet.andre.tp2.exercicio07.*;
import infnet.andre.tp2.exercicio08.ContaBancariaDepois;
import infnet.andre.tp2.exercicio09.FilaMensagensDepois;
import infnet.andre.tp2.exercicio10.MonitoramentoDepois;
import infnet.andre.tp2.exercicio11.*;
import infnet.andre.tp2.exercicio12.*;

import java.util.Scanner;

/**
 * Classe principal da aplicação com menu interativo.
 * Demonstra a execução de todos os 12 exercícios de Clean Code.
 *
 * @author André Becker
 * @version 1.0.0
 */
public class Aplicacao {

    private static final Scanner scanner = new Scanner(System.in);

    // Constantes de formatação
    private static final int LARGURA_SEPARADOR = 70;
    private static final int NUMERO_TOTAL_EXERCICIOS = 12;

    public static void main(String[] args) {
        exibirBanner();
        executarMenuPrincipal();
    }

    private static void exibirBanner() {
        System.out.println();
        System.out.println("╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║         PROJETO DR2 - TP2: CLEAN CODE EM PRÁTICA          ║");
        System.out.println("║                    André Becker - 2025                    ║");
        System.out.println("║         Instituto Infnet - Engenharia de Software         ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝\n");
    }

    private static void executarMenuPrincipal() {
        boolean continuar = true;

        while (continuar) {
            exibirMenuPrincipal();
            int opcao = lerOpcao();

            switch (opcao) {
                case 1 -> executarExercicio01();
                case 2 -> executarExercicio02();
                case 3 -> executarExercicio03();
                case 4 -> executarExercicio04();
                case 5 -> executarExercicio05();
                case 6 -> executarExercicio06();
                case 7 -> executarExercicio07();
                case 8 -> executarExercicio08();
                case 9 -> executarExercicio09();
                case 10 -> executarExercicio10();
                case 11 -> executarExercicio11();
                case 12 -> executarExercicio12();
                case 13 -> executarTodosExercicios();
                case 0 -> {
                    System.out.println("\n✅ Encerrando aplicação. Até logo!");
                    continuar = false;
                }
                default -> System.out.println("\n❌ Opção inválida! Tente novamente.");
            }

            if (continuar && opcao != 0) {
                aguardarContinuacao();
            }
        }

        scanner.close();
    }

    private static void exibirMenuPrincipal() {
        System.out.println("┌───────────────────────────────────────────────────────────┐");
        System.out.println("│                    MENU PRINCIPAL                         │");
        System.out.println("├───────────────────────────────────────────────────────────┤");
        System.out.println("│  1  - Exercício 01: Nomeação de Variáveis                 │");
        System.out.println("│  2  - Exercício 02: Valores Mágicos                       │");
        System.out.println("│  3  - Exercício 03: Null Object Pattern                   │");
        System.out.println("│  4  - Exercício 04: Imutabilidade                         │");
        System.out.println("│  5  - Exercício 05: Complexidade Ciclomática              │");
        System.out.println("│  6  - Exercício 06: Strategy Pattern                      │");
        System.out.println("│  7  - Exercício 07: Abstract Factory                      │");
        System.out.println("│  8  - Exercício 08: CQS - Conta Bancária                  │");
        System.out.println("│  9  - Exercício 09: CQS - Fila de Mensagens               │");
        System.out.println("│  10 - Exercício 10: CQS - Monitoramento                   │");
        System.out.println("│  11 - Exercício 11: Switch Exaustivo - Pedidos            │");
        System.out.println("│  12 - Exercício 12: Switch Exaustivo - Notificações       │");
        System.out.println("│  13 - Executar TODOS os exercícios                        │");
        System.out.println("│  0  - Sair                                                │");
        System.out.println("└───────────────────────────────────────────────────────────┘");
        System.out.print("\n➤ Escolha uma opção: ");
    }

    private static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void aguardarContinuacao() {
        System.out.print("\n⏎ Pressione ENTER para continuar...");
        scanner.nextLine();
    }

    private static void exibirCabecalhoExercicio(int numero, String titulo) {
        System.out.println("\n" + "=".repeat(LARGURA_SEPARADOR));
        System.out.println("  EXERCÍCIO " + numero + ": " + titulo.toUpperCase());
        System.out.println("=".repeat(LARGURA_SEPARADOR));
    }

    private static void executarExercicio01() {
        exibirCabecalhoExercicio(1, "Nomeação de Variáveis e Funções");

        System.out.println("\n📝 Demonstração: Calculadora com nomes claros");
        System.out.println("─".repeat(LARGURA_SEPARADOR));

        int num1 = 10;
        int num2 = 5;

        int resultado = CalculadoraDepois.calcularDobroDaSoma(num1, num2);
        int resultadoConciso = CalculadoraDepois.calcularDobroDaSomaConcisa(num1, num2);

        System.out.println("➤ Entrada: " + num1 + " e " + num2);
        System.out.println("➤ Dobro da soma (método detalhado): " + resultado);
        System.out.println("➤ Dobro da soma (método conciso): " + resultadoConciso);
        System.out.println("\n✅ Princípio aplicado: Nomes reveladores de intenção");
    }

    private static void executarExercicio02() {
        exibirCabecalhoExercicio(2, "Código Autoexplicativo e Valores Mágicos");

        System.out.println("\n💰 Demonstração: Sistema de descontos");
        System.out.println("─".repeat(LARGURA_SEPARADOR));

        DescontoDepois desconto = new DescontoDepois();

        double[] precos = {500.0, 1000.0, 1500.0};

        for (double preco : precos) {
            double precoFinal = desconto.calcularPrecoComDesconto(preco);
            double economia = preco - precoFinal;

            System.out.printf("➤ Preço: R$ %.2f | Final: R$ %.2f | Economia: R$ %.2f%n",
                    preco, precoFinal, economia);
        }

        System.out.println("\n✅ Princípios aplicados: Constantes nomeadas, sem valores mágicos");
    }

    private static void executarExercicio03() {
        exibirCabecalhoExercicio(3, "Null Object Pattern");

        System.out.println("\n👤 Demonstração: Sistema de pedidos com Null Object");
        System.out.println("─".repeat(LARGURA_SEPARADOR));

        Cliente clienteReal = new ClienteReal("João Silva");
        Cliente clienteNulo = new ClienteNulo();

        PedidoDepois pedido1 = new PedidoDepois(clienteReal);
        PedidoDepois pedido2 = new PedidoDepois(clienteNulo);
        PedidoDepois pedido3 = new PedidoDepois(null); // Tratado automaticamente

        System.out.println("➤ Pedido 1 - Cliente: " + pedido1.getNomeCliente() +
                " | Cadastrado: " + pedido1.possuiClienteCadastrado());
        System.out.println("➤ Pedido 2 - Cliente: " + pedido2.getNomeCliente() +
                " | Cadastrado: " + pedido2.possuiClienteCadastrado());
        System.out.println("➤ Pedido 3 - Cliente: " + pedido3.getNomeCliente() +
                " | Cadastrado: " + pedido3.possuiClienteCadastrado());

        System.out.println("\n✅ Princípio aplicado: Null Object Pattern (zero NPE!)");
    }

    private static void executarExercicio04() {
        exibirCabecalhoExercicio(4, "Imutabilidade - Evitando Mutação");

        System.out.println("\n📦 Demonstração: Produtos imutáveis");
        System.out.println("─".repeat(LARGURA_SEPARADOR));

        ProdutoImutavel produtoOriginal = new ProdutoImutavel("Notebook", 3000.0);
        AjusteDepois ajuste = new AjusteDepois();

        ProdutoImutavel produtoComDesconto = ajuste.aplicarDesconto(produtoOriginal);

        System.out.println("➤ Produto Original: " + produtoOriginal);
        System.out.println("➤ Produto com Desconto: " + produtoComDesconto);
        System.out.println("\n💡 Observe: o produto original permanece inalterado!");
        System.out.println("✅ Princípio aplicado: Imutabilidade (sem efeitos colaterais)");
    }

    private static void executarExercicio05() {
        exibirCabecalhoExercicio(5, "Redução de Complexidade Ciclomática");

        System.out.println("\n📊 Demonstração: Classificação de clientes");
        System.out.println("─".repeat(LARGURA_SEPARADOR));

        ClienteClassificadorDepois classificador = new ClienteClassificadorDepois();

        Object[][] clientes = {
                {true, 65, 6000.0},
                {false, 65, 6000.0},
                {false, 65, 1500.0},
                {true, 30, 8000.0},
                {false, 30, 8000.0},
                {false, 30, 1500.0}
        };

        for (Object[] cliente : clientes) {
            boolean isPremium = (boolean) cliente[0];
            int idade = (int) cliente[1];
            double renda = (double) cliente[2];

            TipoCliente tipo = classificador.classificarCliente(isPremium, idade, renda);

            System.out.printf("➤ Premium: %s | Idade: %d | Renda: R$ %.2f → %s%n",
                    isPremium ? "Sim" : "Não", idade, renda, tipo);
        }

        System.out.println("\n✅ Princípio aplicado: Guard Clauses (redução de IFs aninhados)");
    }

    private static void executarExercicio06() {
        exibirCabecalhoExercicio(6, "Strategy Pattern - Personalização");

        System.out.println("\n🎌 Demonstração: Cores de bandeiras");
        System.out.println("─".repeat(LARGURA_SEPARADOR));

        PersonalizacaoDepois personalizacao = new PersonalizacaoDepois();

        Nationality[] nacionalidades = {
                Nationality.DUTCH, Nationality.GERMAN, Nationality.BELGIAN,
                Nationality.FRENCH, Nationality.ITALIAN, Nationality.UNCLASSIFIED
        };

        for (Nationality nacionalidade : nacionalidades) {
            var cores = personalizacao.getCoresBandeira(nacionalidade);
            System.out.println("➤ " + nacionalidade + ": " + cores);
        }

        System.out.println("\n✅ Princípio aplicado: Strategy Pattern com configuração");
    }

    private static void executarExercicio07() {
        exibirCabecalhoExercicio(7, "Abstract Factory Pattern");

        System.out.println("\n📄 Demonstração: Geração de relatórios");
        System.out.println("─".repeat(LARGURA_SEPARADOR));

        RelatorioServiceDepois service = new RelatorioServiceDepois();

        String dadosExemplo = "Dados de exemplo para demonstração";

        System.out.println("\n1. Relatório PDF:");
        service.gerarRelatorio(TipoRelatorio.PDF, dadosExemplo);

        System.out.println("\n2. Relatório CSV:");
        service.gerarRelatorio(TipoRelatorio.CSV, dadosExemplo);

        System.out.println("\n3. Relatório JSON:");
        service.gerarRelatorio(TipoRelatorio.JSON, dadosExemplo);

        System.out.println("\n✅ Princípio aplicado: Abstract Factory + Switch Exaustivo");
    }

    private static void executarExercicio08() {
        exibirCabecalhoExercicio(8, "CQS - Command Query Separation (Conta Bancária)");

        System.out.println("\n💳 Demonstração: Operações bancárias");
        System.out.println("─".repeat(LARGURA_SEPARADOR));

        ContaBancariaDepois conta = new ContaBancariaDepois(1000.0);

        System.out.println("➤ Saldo inicial: R$ " + conta.getSaldo());

        // Query (não altera estado)
        boolean pode = conta.podeComprar(500.0);
        System.out.println("➤ Pode comprar R$ 500? " + pode);
        System.out.println("➤ Saldo após consulta: R$ " + conta.getSaldo() + " (inalterado!)");

        // Command (altera estado)
        conta.realizarCompra(300.0);
        System.out.println("➤ Compra de R$ 300 realizada");
        System.out.println("➤ Saldo final: R$ " + conta.getSaldo());

        System.out.println("\n✅ Princípio aplicado: CQS (Query não altera, Command não retorna)");
    }

    private static void executarExercicio09() {
        exibirCabecalhoExercicio(9, "CQS - Fila de Mensagens");

        System.out.println("\n📨 Demonstração: Fila FIFO");
        System.out.println("─".repeat(LARGURA_SEPARADOR));

        FilaMensagensDepois fila = new FilaMensagensDepois();

        fila.adicionar("Primeira mensagem");
        fila.adicionar("Segunda mensagem");
        fila.adicionar("Terceira mensagem");

        System.out.println("➤ Tamanho da fila: " + fila.size());

        // Query (visualiza sem remover)
        String proxima = fila.peek();
        System.out.println("➤ Próxima mensagem (peek): " + proxima);
        System.out.println("➤ Tamanho após peek: " + fila.size() + " (não alterou!)");

        // Command (remove)
        fila.poll();
        System.out.println("➤ Mensagem removida (poll)");
        System.out.println("➤ Tamanho após poll: " + fila.size());

        System.out.println("\n✅ Princípio aplicado: CQS (peek vs poll)");
    }

    private static void executarExercicio10() {
        exibirCabecalhoExercicio(10, "CQS - Monitoramento");

        System.out.println("\n🌡️  Demonstração: Monitoramento de temperatura");
        System.out.println("─".repeat(LARGURA_SEPARADOR));

        MonitoramentoDepois monitor = new MonitoramentoDepois(25.5);

        System.out.println("➤ Temperatura inicial: " + monitor.getTemperatura() + "°C");
        System.out.println("➤ Contador de acessos: " + monitor.getContadorAcessos());

        // Múltiplas leituras (queries puras)
        monitor.getTemperatura();
        monitor.getTemperatura();
        monitor.getTemperatura();
        System.out.println("➤ Após 3 leituras, contador: " + monitor.getContadorAcessos() +
                " (não incrementou!)");

        // Incremento explícito (command)
        monitor.incrementarAcessos();
        monitor.incrementarAcessos();
        System.out.println("➤ Após 2 incrementos, contador: " + monitor.getContadorAcessos());

        System.out.println("\n✅ Princípio aplicado: CQS (getters puros)");
    }

    private static void executarExercicio11() {
        exibirCabecalhoExercicio(11, "Switch Exaustivo - Status de Pedidos");

        System.out.println("\n📦 Demonstração: Workflow de pedidos");
        System.out.println("─".repeat(LARGURA_SEPARADOR));

        PedidoServiceDepois service = new PedidoServiceDepois();

        StatusPedido[] statuses = StatusPedido.values();

        for (StatusPedido status : statuses) {
            String mensagem = service.getMensagemStatus(status);
            boolean podeCancelar = service.podeCancelar(status);

            System.out.printf("➤ %s: %s | Pode cancelar: %s%n",
                    status, mensagem, podeCancelar);
        }

        System.out.println("\n✅ Princípio aplicado: Switch Exaustivo SEM default");
    }

    private static void executarExercicio12() {
        exibirCabecalhoExercicio(12, "Switch Exaustivo - Notificações");

        System.out.println("\n🔔 Demonstração: Sistema de notificações");
        System.out.println("─".repeat(LARGURA_SEPARADOR));

        NotificacaoServiceDepois service = new NotificacaoServiceDepois();

        TipoNotificacao[] tipos = TipoNotificacao.values();

        for (TipoNotificacao tipo : tipos) {
            double custo = service.getCustoEstimado(tipo);
            boolean requer = service.requerConfirmacao(tipo);

            System.out.printf("➤ %s: Custo: R$ %.2f | Confirmação: %s%n",
                    tipo, custo, requer);

            service.enviar(tipo, "destinatario@exemplo.com", "Mensagem de teste");
        }

        System.out.println("\n✅ Princípio aplicado: Switch Exaustivo garantido pelo compilador");
    }

    private static void executarTodosExercicios() {
        exibirCabecalhoSecao("EXECUTANDO TODOS OS 12 EXERCÍCIOS");

        for (int i = 1; i <= NUMERO_TOTAL_EXERCICIOS; i++) {
            executarExercicioPorNumero(i);

            if (i < NUMERO_TOTAL_EXERCICIOS) {
                exibirSeparadorEntreExercicios();
            }
        }

        exibirCabecalhoSecao("✅ TODOS OS EXERCÍCIOS EXECUTADOS COM SUCESSO!");
    }

    private static void exibirCabecalhoSecao(String titulo) {
        System.out.println("\n" + "=".repeat(LARGURA_SEPARADOR));
        System.out.println("  " + titulo);
        System.out.println("=".repeat(LARGURA_SEPARADOR));
    }

    private static void exibirSeparadorEntreExercicios() {
        System.out.println("\n" + "─".repeat(LARGURA_SEPARADOR) + "\n");
    }

    private static void executarExercicioPorNumero(int numero) {
        switch (numero) {
            case 1 -> executarExercicio01();
            case 2 -> executarExercicio02();
            case 3 -> executarExercicio03();
            case 4 -> executarExercicio04();
            case 5 -> executarExercicio05();
            case 6 -> executarExercicio06();
            case 7 -> executarExercicio07();
            case 8 -> executarExercicio08();
            case 9 -> executarExercicio09();
            case 10 -> executarExercicio10();
            case 11 -> executarExercicio11();
            case 12 -> executarExercicio12();
            default -> throw new IllegalArgumentException("Exercício inválido: " + numero);
        }
    }
}
