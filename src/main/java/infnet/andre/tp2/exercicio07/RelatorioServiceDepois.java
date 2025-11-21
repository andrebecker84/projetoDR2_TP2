package infnet.andre.tp2.exercicio07;

/**
 * Service de geração de relatórios refatorado usando Factory Pattern.
 *
 * <p><strong>MELHORIAS IMPLEMENTADAS:</strong></p>
 * <ul>
 *   <li>Separação de responsabilidades: Service usa, Factory cria</li>
 *   <li>Respeita Single Responsibility Principle</li>
 *   <li>Desacoplamento de implementações concretas</li>
 *   <li>Fácil adição de novos tipos via Factory</li>
 *   <li>Código mais limpo e testável</li>
 *   <li>Facilita injeção de dependências</li>
 * </ul>
 *
 * @author André Becker
 * @version 1.0.0
 */
public class RelatorioServiceDepois {

    private final RelatorioFactory factory;

    /**
     * Construtor padrão que inicializa a factory.
     */
    public RelatorioServiceDepois() {
        this.factory = new RelatorioFactory();
    }

    /**
     * Construtor que permite injeção de factory personalizada.
     *
     * @param factory factory de relatórios a ser utilizada
     */
    public RelatorioServiceDepois(RelatorioFactory factory) {
        if (factory == null) {
            throw new IllegalArgumentException("Factory não pode ser null");
        }
        this.factory = factory;
    }

    /**
     * Gera relatório baseado no tipo especificado.
     *
     * <p>Delega a criação do relatório para a factory, mantendo
     * a responsabilidade do service apenas na orquestração.</p>
     *
     * @param tipo tipo do relatório
     * @param dados dados para o relatório
     * @return conteúdo do relatório gerado
     * @throws IllegalArgumentException se tipo ou dados forem null/vazios
     */
    public String gerarRelatorio(TipoRelatorio tipo, String dados) {
        validarParametros(tipo, dados);

        Relatorio relatorio = factory.criar(tipo);
        return relatorio.gerar(dados);
    }

    /**
     * Valida os parâmetros de entrada.
     *
     * @param tipo tipo do relatório
     * @param dados dados do relatório
     * @throws IllegalArgumentException se parâmetros inválidos
     */
    private void validarParametros(TipoRelatorio tipo, String dados) {
        if (tipo == null) {
            throw new IllegalArgumentException("Tipo de relatório não pode ser null");
        }

        if (dados == null || dados.trim().isEmpty()) {
            throw new IllegalArgumentException("Dados do relatório não podem ser vazios");
        }
    }
}
