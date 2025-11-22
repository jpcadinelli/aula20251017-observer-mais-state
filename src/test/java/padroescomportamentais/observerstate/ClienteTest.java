package padroescomportamentais.observerstate;

import org.junit.jupiter.api.Test;
import padroescomportamentais.observerstate.observer.Pedido;
import padroescomportamentais.observerstate.observer.PlataformaIfood;
import padroescomportamentais.observerstate.observer.Cliente;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClienteTest {

    @Test
    void deveNotificarUmClienteComPedidoInicial() {
        PlataformaIfood plataforma = PlataformaIfood.getInstance();
        Cliente cliente = new Cliente("João Pedro");
        cliente.inscrever(plataforma);

        Pedido pedido = new Pedido("Pizza Calabresa", 40.0);
        plataforma.lancarPedido(pedido);

        assertEquals("João Pedro, novo pedido criado: Pizza Calabresa - Status: Pedido Recebido", cliente.getUltimaNotificacao());
    }

    @Test
    void deveNotificarClientesComPedidosComEstadoAvancado() {
        PlataformaIfood plataforma = PlataformaIfood.getInstance();
        Cliente r1 = new Cliente("João Pedro");
        Cliente r2 = new Cliente("Marco Antônio");
        r1.inscrever(plataforma);
        r2.inscrever(plataforma);

        Pedido pedido = new Pedido("Hambúrguer Artesanal", 40.0);
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
        PlataformaIfood plataforma = PlataformaIfood.getInstance();
        Cliente cliente = new Cliente("João Pedro");

        Pedido pedido = new Pedido("Salada Caesar", 40.0);
        plataforma.lancarPedido(pedido);

        assertEquals(null, cliente.getUltimaNotificacao());
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

