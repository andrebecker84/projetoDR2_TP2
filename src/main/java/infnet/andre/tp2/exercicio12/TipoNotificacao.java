package infnet.andre.tp2.exercicio12;

/**
 * Enumeração que representa os tipos de notificações suportadas.
 *
 * <p>Define os canais de comunicação disponíveis para envio
 * de notificações aos usuários do sistema.</p>
 *
 * @author André Becker
 * @version 1.0.0
 */
public enum TipoNotificacao {
    /**
     * Notificação por email.
     * Ideal para comunicações formais e detalhadas.
     */
    EMAIL,

    /**
     * Notificação por SMS (Short Message Service).
     * Ideal para alertas urgentes e importantes.
     */
    SMS,

    /**
     * Notificação push para aplicativos mobile.
     * Ideal para engajamento em tempo real.
     */
    PUSH
}
