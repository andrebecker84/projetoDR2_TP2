package infnet.andre.tp2.exercicio09;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Fila de mensagens refatorada seguindo o princípio CQS.
 *
 * <p><strong>MELHORIAS IMPLEMENTADAS:</strong></p>
 * <ul>
 *   <li>peek() é Query pura: consulta sem alterar estado</li>
 *   <li>poll() é Command puro: remove sem retornar (void)</li>
 *   <li>Cliente pode consultar antes de decidir remover</li>
 *   <li>Código mais previsível e seguro</li>
 *   <li>Facilita implementação de lógica condicional</li>
 *   <li>Previne perda acidental de dados</li>
 *   <li>Melhor separação de responsabilidades</li>
 * </ul>
 *
 * @author André Becker
 * @version 1.0.0
 */
public class FilaMensagensDepois {

    private final Queue<String> mensagens;

    /**
     * Construtor que inicializa a fila de mensagens.
     */
    public FilaMensagensDepois() {
        this.mensagens = new LinkedList<>();
    }

    /**
     * COMMAND: Adiciona mensagem à fila.
     *
     * @param mensagem mensagem a adicionar
     * @throws IllegalArgumentException se mensagem for null ou vazia
     */
    public void adicionar(String mensagem) {
        if (mensagem == null || mensagem.trim().isEmpty()) {
            throw new IllegalArgumentException("Mensagem não pode ser null ou vazia");
        }
        mensagens.offer(mensagem);
    }

    /**
     * QUERY: Consulta a próxima mensagem sem removê-la.
     *
     * <p>Query pura que permite ao cliente verificar qual é a próxima
     * mensagem antes de decidir se deseja removê-la.</p>
     *
     * @return próxima mensagem na fila
     * @throws NoSuchElementException se a fila estiver vazia
     */
    public String peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Fila está vazia");
        }
        return mensagens.peek();
    }

    /**
     * COMMAND: Remove a próxima mensagem da fila.
     *
     * <p>Command puro que apenas altera o estado. O cliente deve
     * usar peek() se desejar consultar a mensagem antes de remover.</p>
     *
     * @throws NoSuchElementException se a fila estiver vazia
     */
    public void poll() {
        if (isEmpty()) {
            throw new NoSuchElementException("Não há mensagens para remover");
        }
        mensagens.poll();
    }

    /**
     * QUERY: Verifica se a fila está vazia.
     *
     * <p>Query pura sem efeitos colaterais.</p>
     *
     * @return true se a fila não contém mensagens
     */
    public boolean isEmpty() {
        return mensagens.isEmpty();
    }

    /**
     * QUERY: Obtém a quantidade de mensagens na fila.
     *
     * <p>Query pura sem efeitos colaterais.</p>
     *
     * @return número de mensagens na fila
     */
    public int size() {
        return mensagens.size();
    }
}
