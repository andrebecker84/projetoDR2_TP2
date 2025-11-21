package infnet.andre.tp2.exercicio07;

/**
 * Interface que define o contrato para geração de relatórios.
 *
 * <p>Implementa o padrão Strategy, permitindo diferentes implementações
 * de geração de relatórios sem acoplar o código cliente.</p>
 *
 * @author André Becker
 * @version 1.0.0
 */
public interface Relatorio {

    /**
     * Gera o relatório com os dados fornecidos.
     *
     * @param dados dados a serem incluídos no relatório
     * @return conteúdo do relatório gerado
     */
    String gerar(String dados);

    /**
     * Obtém o tipo do relatório.
     *
     * @return tipo do relatório
     */
    TipoRelatorio getTipo();
}
