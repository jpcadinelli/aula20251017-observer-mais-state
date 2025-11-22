package padroescomportamentais.observerstate;

import org.junit.jupiter.api.Test;
import padroescomportamentais.observerstate.observer.PlataformaIfood;
import padroescomportamentais.observerstate.observer.Restaurante;
import padroescomportamentais.observerstate.observer.Pedido;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RestauranteTest {

    @Test
    void deveNotificarUmRestauranteComPedidoInicial() {
        PlataformaIfood plataforma = PlataformaIfood.getInstance();
        Restaurante restaurante = new Restaurante("Restaurante Sabor");
        restaurante.inscrever(plataforma);

        Pedido pedido = new Pedido("Pizza Calabresa", 40.0);
        plataforma.lancarPedido(pedido);

        assertEquals("Restaurante Sabor, novo pedido recebido: Pizza Calabresa - Status: Pedido Recebido", restaurante.getUltimaNotificacao());
    }

    @Test
    void deveNotificarRestaurantesComPedidosComEstadoAvancado() {
        PlataformaIfood plataforma = PlataformaIfood.getInstance();
        Restaurante r1 = new Restaurante("Restaurante Sabor");
        Restaurante r2 = new Restaurante("Hamburgueria Legal");
        r1.inscrever(plataforma);
        r2.inscrever(plataforma);

        Pedido pedido = new Pedido("Hambúrguer Artesanal", 40.0);
        plataforma.lancarPedido(pedido);

        assertEquals("Restaurante Sabor, novo pedido recebido: Hambúrguer Artesanal - Status: Pedido Recebido", r1.getUltimaNotificacao());
        assertEquals("Hamburgueria Legal, novo pedido recebido: Hambúrguer Artesanal - Status: Pedido Recebido", r2.getUltimaNotificacao());

        pedido.avancarEstado();
        plataforma.lancarPedido(pedido);

        assertEquals("Restaurante Sabor, novo pedido recebido: Hambúrguer Artesanal - Status: Pedido em Preparação", r1.getUltimaNotificacao());
        assertEquals("Hamburgueria Legal, novo pedido recebido: Hambúrguer Artesanal - Status: Pedido em Preparação", r2.getUltimaNotificacao());
    }

    @Test
    void naoDeveNotificarRestauranteNaoInscrito() {
        PlataformaIfood plataforma = PlataformaIfood.getInstance();
        Restaurante restaurante = new Restaurante("Restaurante Sabor");

        Pedido pedido = new Pedido("Salada Caesar", 40.0);
        plataforma.lancarPedido(pedido);

        assertEquals(null, restaurante.getUltimaNotificacao());
    }

    @Test
    void deveAvancarEstadoAteEntregue() {
        Pedido pedido = new Pedido("Lasanha Bolonhesa", 40.0);
        assertEquals("Pedido Recebido", pedido.getStatus());
        pedido.avancarEstado();
        assertEquals("Pedido em Preparação", pedido.getStatus());
        pedido.avancarEstado();
        assertEquals("Pedido saiu para entrega", pedido.getStatus());
        pedido.avancarEstado();
        assertEquals("Pedido entregue", pedido.getStatus());
        pedido.avancarEstado();
        assertEquals("Pedido entregue", pedido.getStatus());
    }
}

