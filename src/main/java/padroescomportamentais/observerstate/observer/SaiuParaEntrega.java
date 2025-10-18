package padroescomportamentais.observerstate.observer;

public class SaiuParaEntrega implements EstadoPedido {

    @Override
    public void avancarEstado(Pedido pedido) {
        pedido.setEstado(new Entregue());
    }

    @Override
    public String getStatus() {
        return "Pedido saiu para entrega";
    }
}
