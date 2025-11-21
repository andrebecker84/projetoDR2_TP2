package infnet.andre.tp2.exercicio07;

/**
 * Implementação de relatório em formato CSV.
 *
 * <p>Gera relatórios no formato CSV (Comma-Separated Values),
 * ideal para importação em planilhas Excel, Google Sheets, etc.</p>
 *
 * @author André Becker
 * @version 1.0.0
 */
public class RelatorioCSV implements Relatorio {

    private static final String CSV_SEPARATOR = ",";
    private static final String CSV_HEADER = "Campo,Valor\n";

    /**
     * Gera relatório em formato CSV.
     *
     * @param dados dados a serem incluídos no relatório
     * @return conteúdo do relatório em formato CSV
     */
    @Override
    public String gerar(String dados) {
        if (dados == null || dados.trim().isEmpty()) {
            throw new IllegalArgumentException("Dados do relatório não podem ser vazios");
        }

        StringBuilder csv = new StringBuilder();
        csv.append(CSV_HEADER);

        String[] linhas = dados.split("\n");
        for (String linha : linhas) {
            String[] campos = linha.split(":");
            if (campos.length >= 2) {
                csv.append(campos[0].trim())
                   .append(CSV_SEPARATOR)
                   .append(campos[1].trim())
                   .append("\n");
            }
        }

        return csv.toString();
    }

    /**
     * Obtém o tipo do relatório.
     *
     * @return TipoRelatorio.CSV
     */
    @Override
    public TipoRelatorio getTipo() {
        return TipoRelatorio.CSV;
    }
}
