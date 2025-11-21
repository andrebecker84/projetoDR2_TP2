package infnet.andre.tp2.exercicio06;

import java.util.List;
import java.util.Map;
import java.util.EnumMap;

/**
 * Classe de personalização de interface refatorada usando Map de configuração.
 *
 * <p><strong>MELHORIAS IMPLEMENTADAS:</strong></p>
 * <ul>
 *   <li>Usa Map para eliminar switch e tornar código mais limpo</li>
 *   <li>Configuração centralizada e fácil de modificar</li>
 *   <li>Respeita Open/Closed - novas nacionalidades adicionadas sem modificar código</li>
 *   <li>Melhor performance com EnumMap</li>
 *   <li>Fácil manutenção e teste</li>
 *   <li>Permite configuração externa futura (arquivo, banco de dados)</li>
 *   <li>Código mais expressivo e autodocumentado</li>
 * </ul>
 *
 * @author André Becker
 * @version 1.0.0
 */
public class PersonalizacaoDepois {

    private static final List<Color> CORES_PADRAO = List.of(Color.GRAY);

    private final Map<Nationality, List<Color>> coresPorNacionalidade;

    /**
     * Construtor que inicializa o mapa de cores por nacionalidade.
     */
    public PersonalizacaoDepois() {
        this.coresPorNacionalidade = inicializarCoresBandeiras();
    }

    /**
     * Inicializa o mapa com as cores de cada nacionalidade.
     *
     * <p>Centraliza toda a configuração em um único local,
     * facilitando manutenção e visualização.</p>
     *
     * @return mapa imutável de nacionalidades para suas cores
     */
    private Map<Nationality, List<Color>> inicializarCoresBandeiras() {
        Map<Nationality, List<Color>> cores = new EnumMap<>(Nationality.class);

        cores.put(Nationality.DUTCH, List.of(Color.RED, Color.WHITE, Color.BLUE));
        cores.put(Nationality.GERMAN, List.of(Color.BLACK, Color.RED, Color.YELLOW));
        cores.put(Nationality.BELGIAN, List.of(Color.BLACK, Color.YELLOW, Color.RED));
        cores.put(Nationality.FRENCH, List.of(Color.BLUE, Color.WHITE, Color.RED));
        cores.put(Nationality.ITALIAN, List.of(Color.GREEN, Color.WHITE, Color.RED));
        cores.put(Nationality.UNCLASSIFIED, CORES_PADRAO);

        return Map.copyOf(cores);
    }

    /**
     * Obtém as cores da bandeira baseado na nacionalidade.
     *
     * <p>Busca as cores no mapa de configuração. Se a nacionalidade
     * não for encontrada, retorna cores padrão.</p>
     *
     * @param nationality nacionalidade do cliente (não pode ser null)
     * @return lista imutável de cores da bandeira
     * @throws IllegalArgumentException se nationality for null
     */
    public List<Color> getCoresBandeira(Nationality nationality) {
        if (nationality == null) {
            throw new IllegalArgumentException("Nationality não pode ser null");
        }

        return coresPorNacionalidade.getOrDefault(nationality, CORES_PADRAO);
    }

    /**
     * Verifica se uma nacionalidade está cadastrada no sistema.
     *
     * @param nationality nacionalidade a verificar
     * @return true se a nacionalidade possui configuração de cores
     */
    public boolean isNacionalidadeCadastrada(Nationality nationality) {
        return nationality != null && coresPorNacionalidade.containsKey(nationality);
    }
}
