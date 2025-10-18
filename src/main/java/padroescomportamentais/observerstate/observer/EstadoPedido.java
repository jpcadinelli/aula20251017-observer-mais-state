package padroescomportamentais.observerstate.observer;

public interface EstadoPedido {
    void avancarEstado(Pedido pedido);
    String getStatus();
}
