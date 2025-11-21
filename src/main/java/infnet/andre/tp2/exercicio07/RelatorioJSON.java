package infnet.andre.tp2.exercicio07;

/**
 * Implementação de relatório em formato JSON.
 *
 * <p>Gera relatórios no formato JSON (JavaScript Object Notation),
 * ideal para integração com APIs REST e sistemas web.</p>
 *
 * @author André Becker
 * @version 1.0.0
 */
public class RelatorioJSON implements Relatorio {

    private static final String JSON_HEADER = "{\n  \"relatorio\": {\n";
    private static final String JSON_FOOTER = "  }\n}";
    private static final int INDENT_SPACES = 4;

    /**
     * Gera relatório em formato JSON.
     *
     * @param dados dados a serem incluídos no relatório
     * @return conteúdo do relatório em formato JSON
     */
    @Override
    public String gerar(String dados) {
        if (dados == null || dados.trim().isEmpty()) {
            throw new IllegalArgumentException("Dados do relatório não podem ser vazios");
        }

        StringBuilder json = new StringBuilder();
        json.append(JSON_HEADER);

        String[] linhas = dados.split("\n");
        for (int i = 0; i < linhas.length; i++) {
            String[] campos = linhas[i].split(":");
            if (campos.length >= 2) {
                json.append(" ".repeat(INDENT_SPACES))
                    .append("\"")
                    .append(campos[0].trim())
                    .append("\": \"")
                    .append(campos[1].trim())
                    .append("\"");

                if (i < linhas.length - 1) {
                    json.append(",");
                }
                json.append("\n");
            }
        }

        json.append(JSON_FOOTER);
        return json.toString();
    }

    /**
     * Obtém o tipo do relatório.
     *
     * @return TipoRelatorio.JSON
     */
    @Override
    public TipoRelatorio getTipo() {
        return TipoRelatorio.JSON;
    }
}
