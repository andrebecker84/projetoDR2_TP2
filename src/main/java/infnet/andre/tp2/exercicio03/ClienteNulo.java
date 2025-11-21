package infnet.andre.tp2.exercicio03;

/**
 * Implementação do padrão Null Object para Cliente.
 *
 * Esta classe representa um cliente inexistente ou não cadastrado,
 * fornecendo um comportamento padrão seguro sem causar NullPointerException.
 *
 * Benefícios do padrão Null Object:
 * - Elimina verificações de null em todo o código
 * - Fornece comportamento padrão seguro e previsível
 * - Facilita testes e manutenção
 * - Reduz complexidade ciclomática do código cliente
 *
 * @author André Becker
 * @version 1.0.0
 */
public class ClienteNulo implements Cliente {

    private static final String NOME_CLIENTE_NAO_CADASTRADO = "Cliente não cadastrado";

    /**
     * Retorna um nome padrão para cliente não cadastrado.
     *
     * @return mensagem indicando cliente não cadastrado
     */
    @Override
    public String getNome() {
        return NOME_CLIENTE_NAO_CADASTRADO;
    }

    /**
     * Identifica que este é um objeto nulo.
     *
     * @return sempre true
     */
    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public String toString() {
        return "ClienteNulo{" + NOME_CLIENTE_NAO_CADASTRADO + "}";
    }
}
