package infnet.andre.tp2.exercicio07;

/**
 * Service de geração de relatórios com estrutura problemática.
 *
 * <p><strong>PROBLEMAS IDENTIFICADOS:</strong></p>
 * <ul>
 *   <li>Lógica de criação de objetos misturada com lógica de negócio</li>
 *   <li>Viola Single Responsibility Principle</li>
 *   <li>Não utiliza padrão Factory adequadamente</li>
 *   <li>Código duplicado para cada tipo de relatório</li>
 *   <li>Difícil adicionar novos tipos de relatório</li>
 *   <li>Acoplamento direto com implementações concretas</li>
 * </ul>
 *
 * @author André Becker
 * @version 1.0.0
 * @deprecated Usar {@link RelatorioServiceDepois} com Abstract Factory
 */
@Deprecated
public class RelatorioServiceAntes {

    /**
     * Gera relatório baseado no tipo especificado.
     *
     * <p>Implementação problemática que mistura criação e uso de objetos.</p>
     *
     * @param tipo tipo do relatório
     * @param dados dados para o relatório
     * @return conteúdo do relatório gerado
     */
    public String gerarRelatorio(TipoRelatorio tipo, String dados) {
        if (tipo == TipoRelatorio.PDF) {
            RelatorioPDF relatorio = new RelatorioPDF();
            return relatorio.gerar(dados);
        } else if (tipo == TipoRelatorio.CSV) {
            RelatorioCSV relatorio = new RelatorioCSV();
            return relatorio.gerar(dados);
        } else if (tipo == TipoRelatorio.JSON) {
            RelatorioJSON relatorio = new RelatorioJSON();
            return relatorio.gerar(dados);
        }

        throw new IllegalArgumentException("Tipo de relatório não suportado: " + tipo);
    }
}
