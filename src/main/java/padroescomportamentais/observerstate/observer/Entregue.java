package padroescomportamentais.observerstate.observer;

public class Entregue implements EstadoPedido {

    @Override
    public void avancarEstado(Pedido pedido) {
        System.out.println("Pedido já foi entregue. Não há próximo estado.");
    }

    @Override
    public String getStatus() {
        return "Pedido entregue";
    }
}
