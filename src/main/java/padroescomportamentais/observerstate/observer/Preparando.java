package padroescomportamentais.observerstate.observer;

public class Preparando implements EstadoPedido {

    @Override
    public void avancarEstado(Pedido pedido) {
        pedido.setEstado(new SaiuParaEntrega());
    }

    @Override
    public String getStatus() {
        return "Pedido em Preparação";
    }
}
