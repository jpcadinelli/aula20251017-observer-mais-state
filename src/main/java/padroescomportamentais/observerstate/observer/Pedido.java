package padroescomportamentais.observerstate.observer;

public class Pedido {
    private EstadoPedido estado;

    private String detalhesPedido;

    public Pedido(String detalhesPedido) {
        this.detalhesPedido = detalhesPedido;
        this.estado = new Recebido();
    }

    public void avancarEstado() {
        estado.avancarEstado(this);
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public String getStatus() {
        return estado.getStatus();
    }

    public String getDetalhesPedido() {
        return detalhesPedido;
    }
}
