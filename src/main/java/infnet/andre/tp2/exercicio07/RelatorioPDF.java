package infnet.andre.tp2.exercicio07;

/**
 * Implementação de relatório em formato PDF.
 *
 * <p>Gera relatórios no formato PDF, adequado para impressão
 * e distribuição formal de documentos.</p>
 *
 * @author André Becker
 * @version 1.0.0
 */
public class RelatorioPDF implements Relatorio {

    private static final String HEADER_PDF = "%PDF-1.4\n";
    private static final String FOOTER_PDF = "\n%%EOF";

    /**
     * Gera relatório em formato PDF.
     *
     * @param dados dados a serem incluídos no relatório
     * @return conteúdo do relatório em formato PDF
     */
    @Override
    public String gerar(String dados) {
        if (dados == null || dados.trim().isEmpty()) {
            throw new IllegalArgumentException("Dados do relatório não podem ser vazios");
        }

        StringBuilder pdf = new StringBuilder();
        pdf.append(HEADER_PDF);
        pdf.append("Relatório PDF\n");
        pdf.append("=============\n\n");
        pdf.append(dados);
        pdf.append(FOOTER_PDF);

        return pdf.toString();
    }

    /**
     * Obtém o tipo do relatório.
     *
     * @return TipoRelatorio.PDF
     */
    @Override
    public TipoRelatorio getTipo() {
        return TipoRelatorio.PDF;
    }
}
