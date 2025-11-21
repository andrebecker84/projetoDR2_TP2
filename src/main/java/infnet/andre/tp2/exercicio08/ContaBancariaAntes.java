package infnet.andre.tp2.exercicio08;

/**
 * Classe de conta bancária que viola o princípio CQS (Command-Query Separation).
 *
 * <p><strong>PROBLEMAS IDENTIFICADOS:</strong></p>
 * <ul>
 *   <li>Método realizarCompra() mistura Command (altera estado) e Query (retorna boolean)</li>
 *   <li>Viola CQS: métodos devem OU alterar estado OU retornar valor, nunca ambos</li>
 *   <li>Dificulta raciocínio sobre efeitos colaterais</li>
 *   <li>Complica testes unitários e mocking</li>
 *   <li>Pode causar bugs ao usar retorno em condicionais</li>
 *   <li>Semântica confusa: nome sugere comando, mas comporta-se como query</li>
 * </ul>
 *
 * @author André Becker
 * @version 1.0.0
 * @deprecated Usar {@link ContaBancariaDepois} que respeita CQS
 */
@Deprecated
public class ContaBancariaAntes {

    private double saldo;

    /**
     * Construtor que inicializa a conta com saldo.
     *
     * @param saldoInicial saldo inicial da conta
     */
    public ContaBancariaAntes(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    /**
     * Realiza compra E retorna se foi bem-sucedida.
     *
     * <p><strong>VIOLA CQS:</strong> Este método altera o estado (debita saldo)
     * E retorna um valor (sucesso/falha). Isso torna o código imprevisível
     * e difícil de manter.</p>
     *
     * @param valor valor da compra
     * @return true se a compra foi realizada com sucesso
     */
    public boolean realizarCompra(double valor) {
        if (saldo >= valor) {
            saldo -= valor;  // COMMAND: altera estado
            return true;      // QUERY: retorna informação
        }
        return false;
    }

    /**
     * Obtém o saldo atual da conta.
     *
     * @return saldo atual
     */
    public double getSaldo() {
        return saldo;
    }
}
