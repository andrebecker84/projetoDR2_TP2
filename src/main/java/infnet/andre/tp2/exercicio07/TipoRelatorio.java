package infnet.andre.tp2.exercicio07;

/**
 * Enumeração que representa os tipos de relatórios disponíveis.
 *
 * <p>Define os formatos de exportação suportados pelo sistema de relatórios.</p>
 *
 * @author André Becker
 * @version 1.0.0
 */
public enum TipoRelatorio {
    /**
     * Relatório em formato PDF (Portable Document Format).
     * Ideal para impressão e distribuição.
     */
    PDF,

    /**
     * Relatório em formato CSV (Comma-Separated Values).
     * Ideal para importação em planilhas e análise de dados.
     */
    CSV,

    /**
     * Relatório em formato JSON (JavaScript Object Notation).
     * Ideal para integração com APIs e sistemas externos.
     */
    JSON
}
