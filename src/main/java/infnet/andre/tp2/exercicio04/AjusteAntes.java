package infnet.andre.tp2.exercicio04;

/**
 * Código original que modifica parâmetros de entrada.
 *
 * Problemas identificados:
 * - Modifica diretamente o objeto recebido como parâmetro
 * - Causa efeitos colaterais inesperados
 * - Dificulta rastreamento de mudanças
 * - Problemas em relatórios financeiros e auditorias
 * - Comportamento imprevisível em múltiplas compras
 * - Compromete histórico de preços
 *
 * Impactos negativos:
 * 1. Relatórios financeiros: Preços alterados sem controle
 * 2. Múltiplas compras: Valor no carrinho pode mudar inesperadamente
 * 3. Logs e auditorias: Histórico de preços comprometido
 *
 * @author André Becker
 * @deprecated Use {@link AjusteDepois} para código refatorado
 */
@Deprecated
public class AjusteAntes {

    /**
     * Aplica desconto modificando diretamente o produto.
     * PROBLEMA: Altera o estado do objeto passado como parâmetro.
     *
     * @param produto produto a ser modificado (efeito colateral!)
     */
    public void aplicarDesconto(Produto produto) {
        produto.setPreco(produto.getPreco() - 10); // Modifica diretamente o parâmetro - PROBLEMA!
    }
}
