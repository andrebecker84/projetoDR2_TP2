package infnet.andre.tp2.exercicio06;

import java.util.List;

/**
 * Classe de personalização de interface com switch problemático.
 *
 * <p><strong>PROBLEMAS IDENTIFICADOS:</strong></p>
 * <ul>
 *   <li>Switch extenso e repetitivo dificulta manutenção</li>
 *   <li>Viola o princípio Open/Closed - precisa modificar para adicionar nacionalidade</li>
 *   <li>Duplicação de código na criação de listas</li>
 *   <li>Lógica de negócio espalhada em estruturas de controle</li>
 *   <li>Difícil de testar cada caso individualmente</li>
 *   <li>Não permite configuração dinâmica</li>
 * </ul>
 *
 * @author André Becker
 * @version 1.0.0
 * @deprecated Usar {@link PersonalizacaoDepois} com Strategy Pattern ou Map
 */
@Deprecated
public class PersonalizacaoAntes {

    /**
     * Obtém as cores da bandeira baseado na nacionalidade.
     *
     * <p>Implementação problemática usando switch extenso.</p>
     *
     * @param nationality nacionalidade do cliente
     * @return lista de cores da bandeira
     */
    public List<Color> getCoresBandeira(Nationality nationality) {
        switch (nationality) {
            case DUTCH:
                return List.of(Color.RED, Color.WHITE, Color.BLUE);
            case GERMAN:
                return List.of(Color.BLACK, Color.RED, Color.YELLOW);
            case BELGIAN:
                return List.of(Color.BLACK, Color.YELLOW, Color.RED);
            case FRENCH:
                return List.of(Color.BLUE, Color.WHITE, Color.RED);
            case ITALIAN:
                return List.of(Color.GREEN, Color.WHITE, Color.RED);
            case UNCLASSIFIED:
                return List.of(Color.GRAY);
            default:
                return List.of(Color.GRAY);
        }
    }
}
