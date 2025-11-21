package infnet.andre.tp2.exercicio09;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Fila de mensagens que viola o princípio CQS.
 *
 * <p><strong>PROBLEMAS IDENTIFICADOS:</strong></p>
 * <ul>
 *   <li>Método poll() retorna dado E altera estado simultaneamente</li>
 *   <li>Viola CQS: impossível consultar próximo elemento sem removê-lo</li>
 *   <li>Dificulta implementação de lógica condicional sem efeitos colaterais</li>
 *   <li>Cliente não pode "espiar" próximo elemento sem consumir</li>
 *   <li>Complica testes e raciocínio sobre código</li>
 *   <li>Pode causar perda de dados se usado incorretamente em condicionais</li>
 * </ul>
 *
 * @author André Becker
 * @version 1.0.0
 * @deprecated Usar {@link FilaMensagensDepois} que separa peek() de poll()
 */
@Deprecated
public class FilaMensagensAntes {

    private final Queue<String> mensagens;

    /**
     * Construtor que inicializa a fila de mensagens.
     */
    public FilaMensagensAntes() {
        this.mensagens = new LinkedList<>();
    }

    /**
     * Adiciona mensagem à fila.
     *
     * @param mensagem mensagem a adicionar
     */
    public void adicionar(String mensagem) {
        mensagens.offer(mensagem);
    }

    /**
     * Remove E retorna a próxima mensagem.
     *
     * <p><strong>VIOLA CQS:</strong> Este método altera o estado (remove)
     * E retorna informação (mensagem). Cliente não pode consultar sem remover.</p>
     *
     * @return próxima mensagem ou null se fila vazia
     */
    public String poll() {
        return mensagens.poll();  // COMMAND + QUERY simultâneos
    }

    /**
     * Verifica se a fila está vazia.
     *
     * @return true se vazia
     */
    public boolean isEmpty() {
        return mensagens.isEmpty();
    }
}
