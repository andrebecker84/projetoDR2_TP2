package infnet.andre.tp2.exercicio03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Testes unitários para Null Object Pattern aplicado a Pedidos.
 *
 * @author André Becker
 * @version 1.0.0
 */
@DisplayName("Pedido - Testes de Null Object Pattern")
class PedidoTest {

    @Test
    @DisplayName("Deve funcionar corretamente com cliente real")
    void deveFuncionarCorretamenteComClienteReal() {
        // Arrange & Act
        Cliente cliente = new ClienteReal("João Silva");
        PedidoDepois pedido = new PedidoDepois(cliente);

        // Assert
        assertThat(pedido.getNomeCliente(), is(equalTo("João Silva")));
        assertThat(pedido.getCliente(), is(instanceOf(ClienteReal.class)));
        assertThat(pedido.possuiClienteCadastrado(), is(true));
    }

    @Test
    @DisplayName("Deve usar ClienteNulo quando cliente é null ou inválido")
    void deveUsarClienteNuloQuandoClienteNullOuInvalido() {
        // Act
        PedidoDepois pedido1 = new PedidoDepois(null);
        PedidoDepois pedido2 = new PedidoDepois(new ClienteNulo());

        // Assert
        assertThat(pedido1.getNomeCliente(), is(equalTo("Cliente não cadastrado")));
        assertThat(pedido1.possuiClienteCadastrado(), is(false));
        assertThat(pedido1.getCliente(), is(instanceOf(ClienteNulo.class)));

        assertThat(pedido2.getNomeCliente(), is(equalTo("Cliente não cadastrado")));
        assertThat(pedido2.possuiClienteCadastrado(), is(false));
    }

    @Test
    @DisplayName("Deve evitar NullPointerException com Null Object Pattern")
    void deveEvitarNullPointerExceptionComNullObjectPattern() {
        // Arrange
        PedidoDepois pedidoSemCliente = new PedidoDepois(null);

        // Act & Assert - Não lança NPE
        assertThat(pedidoSemCliente.getNomeCliente(), is(equalTo("Cliente não cadastrado")));
        assertThat(pedidoSemCliente.getCliente(), is(notNullValue()));
        assertThat(pedidoSemCliente.possuiClienteCadastrado(), is(false));
    }
}
