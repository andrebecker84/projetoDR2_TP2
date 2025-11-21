package infnet.andre.tp2.exercicio10;

/**
 * Sistema de monitoramento que viola o princípio CQS.
 *
 * <p><strong>PROBLEMAS IDENTIFICADOS:</strong></p>
 * <ul>
 *   <li>getTemperatura() consulta temperatura E incrementa contador</li>
 *   <li>Viola CQS: método getter com efeito colateral oculto</li>
 *   <li>Nome enganoso: prefixo "get" sugere Query pura, mas altera estado</li>
 *   <li>Impossível consultar temperatura sem incrementar contador</li>
 *   <li>Dificulta testes e debugging</li>
 *   <li>Comportamento não intuitivo e potencialmente bug-prone</li>
 *   <li>Quebra expectativas convencionais de getters</li>
 * </ul>
 *
 * @author André Becker
 * @version 1.0.0
 * @deprecated Usar {@link MonitoramentoDepois} que separa Query de Command
 */
@Deprecated
public class MonitoramentoAntes {

    private double temperatura;
    private int contadorAcessos;

    /**
     * Construtor que inicializa o sistema de monitoramento.
     *
     * @param temperaturaInicial temperatura inicial
     */
    public MonitoramentoAntes(double temperaturaInicial) {
        this.temperatura = temperaturaInicial;
        this.contadorAcessos = 0;
    }

    /**
     * Obtém temperatura E incrementa contador de acessos.
     *
     * <p><strong>VIOLA CQS:</strong> Este getter tem um efeito colateral
     * oculto - incrementa o contador. Comportamento não esperado para
     * um método com prefixo "get".</p>
     *
     * @return temperatura atual
     */
    public double getTemperatura() {
        contadorAcessos++;  // EFEITO COLATERAL OCULTO!
        return temperatura;  // QUERY
    }

    /**
     * Define nova temperatura.
     *
     * @param temperatura nova temperatura
     */
    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    /**
     * Obtém contador de acessos.
     *
     * @return número de acessos
     */
    public int getContadorAcessos() {
        return contadorAcessos;
    }
}
