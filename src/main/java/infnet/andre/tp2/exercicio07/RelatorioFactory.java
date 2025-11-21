package infnet.andre.tp2.exercicio07;

/**
 * Factory para criação de relatórios usando switch exaustivo.
 *
 * <p>Implementa o padrão Abstract Factory, centralizando a criação
 * de diferentes tipos de relatórios.</p>
 *
 * <p><strong>CARACTERÍSTICAS:</strong></p>
 * <ul>
 *   <li>Switch exaustivo garante tratamento de todos os tipos</li>
 *   <li>Sem default - compilador força tratamento de novos enums</li>
 *   <li>Responsabilidade única: apenas criar objetos</li>
 *   <li>Facilita extensão sem modificar código cliente</li>
 * </ul>
 *
 * @author André Becker
 * @version 1.0.0
 */
public class RelatorioFactory {

    /**
     * Cria uma instância de relatório baseado no tipo especificado.
     *
     * <p>Usa switch exaustivo sem default para garantir que todos
     * os tipos de relatório sejam tratados explicitamente.</p>
     *
     * @param tipo tipo do relatório a ser criado
     * @return instância do relatório apropriado
     * @throws IllegalArgumentException se tipo for null
     */
    public Relatorio criar(TipoRelatorio tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("Tipo de relatório não pode ser null");
        }

        // Switch exaustivo sem default - compilador garante tratamento de todos os casos
        return switch (tipo) {
            case PDF -> new RelatorioPDF();
            case CSV -> new RelatorioCSV();
            case JSON -> new RelatorioJSON();
        };
    }
}
