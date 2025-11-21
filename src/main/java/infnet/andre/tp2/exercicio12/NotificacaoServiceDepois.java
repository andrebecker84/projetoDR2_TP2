package infnet.andre.tp2.exercicio12;

/**
 * Service de notificações com switch exaustivo SEM default.
 *
 * <p><strong>MELHORIAS IMPLEMENTADAS:</strong></p>
 * <ul>
 *   <li>Switch exaustivo sem default força tratamento de todos os casos</li>
 *   <li>Compilador garante que novos tipos sejam implementados</li>
 *   <li>Fail-Fast: erro em tempo de compilação, não runtime</li>
 *   <li>Código mais seguro e confiável</li>
 *   <li>Validações explícitas de parâmetros</li>
 *   <li>Separação clara de responsabilidades</li>
 *   <li>Facilita manutenção e testes</li>
 * </ul>
 *
 * @author André Becker
 * @version 1.0.0
 */
public class NotificacaoServiceDepois {

    private static final String ERRO_TIPO_NULL = "Tipo de notificação não pode ser null";
    private static final String ERRO_DESTINATARIO_INVALIDO = "Destinatário não pode ser null ou vazio";
    private static final String ERRO_MENSAGEM_INVALIDA = "Mensagem não pode ser null ou vazia";

    /**
     * Envia notificação usando o canal especificado.
     *
     * <p><strong>GARANTIA DO COMPILADOR:</strong> Adicionar novo tipo ao enum
     * TipoNotificacao causará erro de compilação forçando implementação.</p>
     *
     * @param tipo tipo da notificação
     * @param destinatario destinatário da notificação
     * @param mensagem conteúdo da mensagem
     * @throws IllegalArgumentException se parâmetros forem inválidos
     */
    public void enviar(TipoNotificacao tipo, String destinatario, String mensagem) {
        validarParametros(tipo, destinatario, mensagem);

        // Switch exaustivo SEM default - garante que todos os tipos sejam tratados
        switch (tipo) {
            case EMAIL -> enviarEmail(destinatario, mensagem);
            case SMS -> enviarSMS(destinatario, mensagem);
            case PUSH -> enviarPush(destinatario, mensagem);
            // SEM default: novo tipo causará erro de compilação
        }
    }

    /**
     * Obtém o custo estimado de envio por tipo de notificação.
     *
     * @param tipo tipo da notificação
     * @return custo em centavos
     * @throws IllegalArgumentException se tipo for null
     */
    public int getCustoEstimado(TipoNotificacao tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException(ERRO_TIPO_NULL);
        }

        // Switch exaustivo com retorno direto
        return switch (tipo) {
            case EMAIL -> 0;    // Gratuito
            case SMS -> 10;     // R$ 0,10
            case PUSH -> 1;     // R$ 0,01
        };
    }

    /**
     * Verifica se o tipo de notificação requer confirmação de leitura.
     *
     * @param tipo tipo da notificação
     * @return true se requer confirmação
     * @throws IllegalArgumentException se tipo for null
     */
    public boolean requerConfirmacao(TipoNotificacao tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException(ERRO_TIPO_NULL);
        }

        return switch (tipo) {
            case EMAIL, PUSH -> true;
            case SMS -> false;
        };
    }

    /**
     * Valida os parâmetros de entrada.
     *
     * @param tipo tipo da notificação
     * @param destinatario destinatário
     * @param mensagem mensagem
     * @throws IllegalArgumentException se algum parâmetro for inválido
     */
    private void validarParametros(TipoNotificacao tipo, String destinatario, String mensagem) {
        if (tipo == null) {
            throw new IllegalArgumentException(ERRO_TIPO_NULL);
        }

        if (destinatario == null || destinatario.trim().isEmpty()) {
            throw new IllegalArgumentException(ERRO_DESTINATARIO_INVALIDO);
        }

        if (mensagem == null || mensagem.trim().isEmpty()) {
            throw new IllegalArgumentException(ERRO_MENSAGEM_INVALIDA);
        }
    }

    /**
     * Envia email para o destinatário especificado.
     *
     * @param email endereço de email
     * @param mensagem conteúdo da mensagem
     */
    private void enviarEmail(String email, String mensagem) {
        System.out.println("Email enviado para " + email + ": " + mensagem);
    }

    /**
     * Envia SMS para o telefone especificado.
     *
     * @param telefone número de telefone
     * @param mensagem conteúdo da mensagem
     */
    private void enviarSMS(String telefone, String mensagem) {
        System.out.println("SMS enviado para " + telefone + ": " + mensagem);
    }

    /**
     * Envia notificação push para o device especificado.
     *
     * @param deviceId identificador do dispositivo
     * @param mensagem conteúdo da mensagem
     */
    private void enviarPush(String deviceId, String mensagem) {
        System.out.println("Push enviado para " + deviceId + ": " + mensagem);
    }
}
