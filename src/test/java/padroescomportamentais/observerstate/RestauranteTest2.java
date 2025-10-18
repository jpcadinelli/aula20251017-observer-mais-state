package padroescomportamentais.observerstate;

import org.junit.jupiter.api.Test;
import padroescomportamentais.observerstate.observer.PlataformaIfood;
import padroescomportamentais.observerstate.observer.Restaurante;
import padroescomportamentais.observerstate.observer.Pedido;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RestauranteTest2 {

    @Test
    void deveNotificarUmRestauranteComPedidoInicial() {
        PlataformaIfood plataforma = new PlataformaIfood();
        Restaurante restaurante = new Restaurante("Restaurante Sabor");
        restaurante.inscrever(plataforma);

        Pedido pedido = new Pedido("Pizza Calabresa");
        plataforma.lancarPedido(pedido);

        assertEquals("Restaurante Sabor, novo pedido recebido: Pizza Calabresa - Status: Pedido Recebido", restaurante.getUltimaNotificacao());
    }

    @Test
    void deveNotificarRestaurantesComPedidosComEstadoAvancado() {
        PlataformaIfood plataforma = new PlataformaIfood();
        Restaurante r1 = new Restaurante("Restaurante Sabor");
        Restaurante r2 = new Restaurante("Hamburgueria Legal");
        r1.inscrever(plataforma);
        r2.inscrever(plataforma);

        Pedido pedido = new Pedido("Hambúrguer Artesanal");
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
        PlataformaIfood plataforma = new PlataformaIfood();
        Restaurante restaurante = new Restaurante("Restaurante Sabor");

        Pedido pedido = new Pedido("Salada Caesar");
        plataforma.lancarPedido(pedido);

        assertEquals(null, restaurante.getUltimaNotificacao());
    }

    @Test
    void deveNotificarRestaurantesEmPlataformasDiferentes() {
        PlataformaIfood plataformaA = new PlataformaIfood();
        PlataformaIfood plataformaB = new PlataformaIfood();

        Restaurante r1 = new Restaurante("Restaurante Sabor");
        Restaurante r2 = new Restaurante("Hamburgueria Legal");

        r1.inscrever(plataformaA);
        r2.inscrever(plataformaB);

        Pedido pedidoA = new Pedido("Pizza Marguerita");
        Pedido pedidoB = new Pedido("Sushi Especial");

        plataformaA.lancarPedido(pedidoA);
        plataformaB.lancarPedido(pedidoB);

        assertEquals("Restaurante Sabor, novo pedido recebido: Pizza Marguerita - Status: Pedido Recebido", r1.getUltimaNotificacao());
        assertEquals("Hamburgueria Legal, novo pedido recebido: Sushi Especial - Status: Pedido Recebido", r2.getUltimaNotificacao());

        assert(!r1.getUltimaNotificacao().contains("Sushi Especial"));
        assert(!r2.getUltimaNotificacao().contains("Pizza Marguerita"));
    }

    @Test
    void deveAvancarEstadoAteEntregue() {
        Pedido pedido = new Pedido("Lasanha Bolonhesa");
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

