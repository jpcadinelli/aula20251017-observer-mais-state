package padroescomportamentais.observerstate.observer;

public class Pedido {
    private EstadoPedido estado;
    private String detalhesPedido;
    private PagamentoStrategy pagamentoStrategy;
    private double valor;

    public Pedido(String detalhesPedido, double v) {
        this.detalhesPedido = detalhesPedido;
        this.estado = new Recebido();
    }

    public void setPagamentoStrategy(PagamentoStrategy strategy) {
        this.pagamentoStrategy = strategy;
    }

    public String realizarPagamento() {
        if (pagamentoStrategy == null) {
            throw new IllegalStateException("Nenhuma forma de pagamento selecionada!");
        }
        return pagamentoStrategy.pagar(valor);
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
