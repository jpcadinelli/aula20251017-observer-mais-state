package padroescomportamentais.observerstate;

import org.junit.jupiter.api.Test;
import padroescomportamentais.observerstate.observer.Pedido;
import padroescomportamentais.observerstate.observer.PlataformaIfood;
import padroescomportamentais.observerstate.observer.Cliente;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClienteTest {

    @Test
    void deveNotificarUmClienteComPedidoInicial() {
        PlataformaIfood plataforma = new PlataformaIfood();
        Cliente cliente = new Cliente("João Pedro");
        cliente.inscrever(plataforma);

        Pedido pedido = new Pedido("Pizza Calabresa");
        plataforma.lancarPedido(pedido);

        assertEquals("João Pedro, novo pedido criado: Pizza Calabresa - Status: Pedido Recebido", cliente.getUltimaNotificacao());
    }

    @Test
    void deveNotificarClientesComPedidosComEstadoAvancado() {
        PlataformaIfood plataforma = new PlataformaIfood();
        Cliente r1 = new Cliente("João Pedro");
        Cliente r2 = new Cliente("Marco Antônio");
        r1.inscrever(plataforma);
        r2.inscrever(plataforma);

        Pedido pedido = new Pedido("Hambúrguer Artesanal");
        plataforma.lancarPedido(pedido);

        assertEquals("João Pedro, novo pedido criado: Hambúrguer Artesanal - Status: Pedido Recebido", r1.getUltimaNotificacao());
        assertEquals("Marco Antônio, novo pedido criado: Hambúrguer Artesanal - Status: Pedido Recebido", r2.getUltimaNotificacao());

        pedido.avancarEstado();
        plataforma.lancarPedido(pedido);

        assertEquals("João Pedro, novo pedido criado: Hambúrguer Artesanal - Status: Pedido em Preparação", r1.getUltimaNotificacao());
        assertEquals("Marco Antônio, novo pedido criado: Hambúrguer Artesanal - Status: Pedido em Preparação", r2.getUltimaNotificacao());
    }

    @Test
    void naoDeveNotificarClienteNaoInscrito() {
        PlataformaIfood plataforma = new PlataformaIfood();
        Cliente cliente = new Cliente("João Pedro");

        Pedido pedido = new Pedido("Salada Caesar");
        plataforma.lancarPedido(pedido);

        assertEquals(null, cliente.getUltimaNotificacao());
    }

    @Test
    void deveNotificarClientesEmPlataformasDiferentes() {
        PlataformaIfood plataformaA = new PlataformaIfood();
        PlataformaIfood plataformaB = new PlataformaIfood();

        Cliente r1 = new Cliente("João Pedro");
        Cliente r2 = new Cliente("Marco Antônio");

        r1.inscrever(plataformaA);
        r2.inscrever(plataformaB);

        Pedido pedidoA = new Pedido("Pizza Marguerita");
        Pedido pedidoB = new Pedido("Sushi Especial");

        plataformaA.lancarPedido(pedidoA);
        plataformaB.lancarPedido(pedidoB);

        assertEquals("João Pedro, novo pedido criado: Pizza Marguerita - Status: Pedido Recebido", r1.getUltimaNotificacao());
        assertEquals("Marco Antônio, novo pedido criado: Sushi Especial - Status: Pedido Recebido", r2.getUltimaNotificacao());

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

