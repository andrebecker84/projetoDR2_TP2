package infnet.andre.tp2.exercicio10;

/**
 * Sistema de monitoramento refatorado seguindo o princípio CQS.
 *
 * <p><strong>MELHORIAS IMPLEMENTADAS:</strong></p>
 * <ul>
 *   <li>getTemperatura() é Query pura: apenas retorna valor</li>
 *   <li>incrementarAcessos() é Command explícito: apenas altera estado</li>
 *   <li>Cliente tem controle total sobre quando incrementar contador</li>
 *   <li>Comportamento previsível e transparente</li>
 *   <li>Nome dos métodos reflete exatamente seu comportamento</li>
 *   <li>Facilita testes e manutenção</li>
 *   <li>Respeita princípio de menor surpresa</li>
 * </ul>
 *
 * @author André Becker
 * @version 1.0.0
 */
public class MonitoramentoDepois {

    private static final double TEMPERATURA_ABSOLUTO_ZERO = -273.15;
    private static final double TEMPERATURA_MAXIMA = 1000.0;

    private double temperatura;
    private int contadorAcessos;

    /**
     * Construtor que inicializa o sistema de monitoramento.
     *
     * @param temperaturaInicial temperatura inicial em Celsius
     * @throws IllegalArgumentException se temperatura for inválida
     */
    public MonitoramentoDepois(double temperaturaInicial) {
        validarTemperatura(temperaturaInicial);
        this.temperatura = temperaturaInicial;
        this.contadorAcessos = 0;
    }

    /**
     * QUERY: Obtém a temperatura atual.
     *
     * <p>Query pura sem efeitos colaterais. Pode ser chamada
     * múltiplas vezes sem alterar o estado do sistema.</p>
     *
     * @return temperatura atual em Celsius
     */
    public double getTemperatura() {
        return temperatura;
    }

    /**
     * COMMAND: Define nova temperatura.
     *
     * @param temperatura nova temperatura em Celsius
     * @throws IllegalArgumentException se temperatura for inválida
     */
    public void setTemperatura(double temperatura) {
        validarTemperatura(temperatura);
        this.temperatura = temperatura;
    }

    /**
     * QUERY: Obtém o contador de acessos.
     *
     * <p>Query pura sem efeitos colaterais.</p>
     *
     * @return número de vezes que acessos foram registrados
     */
    public int getContadorAcessos() {
        return contadorAcessos;
    }

    /**
     * COMMAND: Incrementa o contador de acessos.
     *
     * <p>Command explícito que deve ser chamado quando se deseja
     * registrar um acesso. Dá controle total ao cliente sobre
     * quando incrementar o contador.</p>
     */
    public void incrementarAcessos() {
        contadorAcessos++;
    }

    /**
     * COMMAND: Reseta o contador de acessos para zero.
     */
    public void resetarContadorAcessos() {
        contadorAcessos = 0;
    }

    /**
     * Valida se a temperatura está dentro de limites físicos razoáveis.
     *
     * @param temperatura temperatura a validar
     * @throws IllegalArgumentException se temperatura for inválida
     */
    private void validarTemperatura(double temperatura) {
        if (temperatura < TEMPERATURA_ABSOLUTO_ZERO) {
            throw new IllegalArgumentException(
                String.format("Temperatura não pode ser inferior ao zero absoluto (%.2f°C): %.2f°C",
                    TEMPERATURA_ABSOLUTO_ZERO, temperatura)
            );
        }

        if (temperatura > TEMPERATURA_MAXIMA) {
            throw new IllegalArgumentException(
                String.format("Temperatura não pode exceder %.2f°C: %.2f°C",
                    TEMPERATURA_MAXIMA, temperatura)
            );
        }
    }
}
