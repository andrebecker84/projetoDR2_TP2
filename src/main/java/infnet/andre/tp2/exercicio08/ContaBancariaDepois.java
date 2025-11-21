package infnet.andre.tp2.exercicio08;

/**
 * Classe de conta bancária refatorada seguindo o princípio CQS.
 *
 * <p><strong>MELHORIAS IMPLEMENTADAS:</strong></p>
 * <ul>
 *   <li>Separação clara entre Commands e Queries</li>
 *   <li>podeComprar() é Query pura: apenas retorna informação, sem efeitos colaterais</li>
 *   <li>realizarCompra() é Command puro: apenas altera estado, void</li>
 *   <li>Código mais previsível e fácil de entender</li>
 *   <li>Facilita testes e debugging</li>
 *   <li>Previne bugs causados por efeitos colaterais não intencionais</li>
 *   <li>Melhora composição e reuso de código</li>
 * </ul>
 *
 * @author André Becker
 * @version 1.0.0
 */
public class ContaBancariaDepois {

    private static final double SALDO_MINIMO = 0.0;
    private static final String MENSAGEM_SALDO_INSUFICIENTE =
        "Saldo insuficiente para realizar a compra. Saldo: %.2f, Valor: %.2f";

    private double saldo;

    /**
     * Construtor que inicializa a conta com saldo.
     *
     * @param saldoInicial saldo inicial da conta
     * @throws IllegalArgumentException se saldo inicial for negativo
     */
    public ContaBancariaDepois(double saldoInicial) {
        if (saldoInicial < SALDO_MINIMO) {
            throw new IllegalArgumentException(
                "Saldo inicial não pode ser negativo: " + saldoInicial
            );
        }
        this.saldo = saldoInicial;
    }

    /**
     * QUERY: Verifica se há saldo suficiente para realizar uma compra.
     *
     * <p>Este método é uma Query pura - não altera nenhum estado,
     * apenas retorna informação. Pode ser chamado múltiplas vezes
     * sem efeitos colaterais.</p>
     *
     * @param valor valor da compra a verificar
     * @return true se há saldo suficiente
     * @throws IllegalArgumentException se valor for negativo
     */
    public boolean podeComprar(double valor) {
        validarValor(valor);
        return saldo >= valor;
    }

    /**
     * COMMAND: Realiza a compra debitando o valor do saldo.
     *
     * <p>Este método é um Command puro - altera o estado mas não
     * retorna valor. O cliente deve usar podeComprar() antes para
     * verificar se a operação é possível.</p>
     *
     * @param valor valor da compra
     * @throws IllegalArgumentException se valor for inválido
     * @throws IllegalStateException se saldo for insuficiente
     */
    public void realizarCompra(double valor) {
        validarValor(valor);

        if (!podeComprar(valor)) {
            throw new IllegalStateException(
                String.format(MENSAGEM_SALDO_INSUFICIENTE, saldo, valor)
            );
        }

        saldo -= valor;
    }

    /**
     * QUERY: Obtém o saldo atual da conta.
     *
     * <p>Query pura sem efeitos colaterais.</p>
     *
     * @return saldo atual
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * Valida se o valor é positivo.
     *
     * @param valor valor a validar
     * @throws IllegalArgumentException se valor for negativo ou zero
     */
    private void validarValor(double valor) {
        if (valor <= SALDO_MINIMO) {
            throw new IllegalArgumentException(
                "Valor deve ser positivo: " + valor
            );
        }
    }
}
