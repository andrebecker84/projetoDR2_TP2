package infnet.andre.tp2.exercicio06;

import java.util.List;

/**
 * Interface Strategy para obtenção de cores de bandeiras.
 *
 * <p>Define o contrato para implementações que fornecem as cores
 * específicas de cada nacionalidade.</p>
 *
 * @author André Becker
 * @version 1.0.0
 */
public interface BandeiraStrategy {

    /**
     * Obtém as cores da bandeira.
     *
     * @return lista ordenada de cores que compõem a bandeira
     */
    List<Color> getCores();
}
