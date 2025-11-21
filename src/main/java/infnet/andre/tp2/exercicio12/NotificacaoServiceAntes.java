package infnet.andre.tp2.exercicio12;

/**
 * Service de notificações com switch problemático.
 *
 * <p><strong>PROBLEMAS IDENTIFICADOS:</strong></p>
 * <ul>
 *   <li>Switch com default mascara tipos de notificação não tratados</li>
 *   <li>Compilador não força implementação de novos tipos</li>
 *   <li>Adicionar WEBHOOK ao enum não causaria erro de compilação</li>
 *   <li>Default pode esconder bugs silenciosamente</li>
 *   <li>Viola princípio Fail-Fast</li>
 *   <li>Dificulta manutenção e detecção de casos não implementados</li>
 * </ul>
 *
 * @author André Becker
 * @version 1.0.0
 * @deprecated Usar {@link NotificacaoServiceDepois} com switch exaustivo
 */
@Deprecated
public class NotificacaoServiceAntes {

    /**
     * Envia notificação usando o canal especificado.
     *
     * <p><strong>PROBLEMA:</strong> Se adicionarmos WEBHOOK ao enum,
     * este método não dará erro - apenas executará o default silenciosamente.</p>
     *
     * @param tipo tipo da notificação
     * @param destinatario destinatário da notificação
     * @param mensagem conteúdo da mensagem
     */
    public void enviar(TipoNotificacao tipo, String destinatario, String mensagem) {
        switch (tipo) {
            case EMAIL:
                enviarEmail(destinatario, mensagem);
                break;
            case SMS:
                enviarSMS(destinatario, mensagem);
                break;
            case PUSH:
                enviarPush(destinatario, mensagem);
                break;
            default:
                System.out.println("Tipo de notificação não suportado");  // PERIGOSO!
        }
    }

    private void enviarEmail(String email, String mensagem) {
        System.out.println("Email enviado para " + email + ": " + mensagem);
    }

    private void enviarSMS(String telefone, String mensagem) {
        System.out.println("SMS enviado para " + telefone + ": " + mensagem);
    }

    private void enviarPush(String deviceId, String mensagem) {
        System.out.println("Push enviado para " + deviceId + ": " + mensagem);
    }
}
