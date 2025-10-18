package padroescomportamentais.observerstate.observer;

public class Recebido implements EstadoPedido {

    @Override
    public void avancarEstado(Pedido pedido) {
        pedido.setEstado(new Preparando());
    }

    @Override
    public String getStatus() {
        return "Pedido Recebido";
    }
}
