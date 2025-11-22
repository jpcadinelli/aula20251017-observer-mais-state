package padroescomportamentais.observerstate.observer;

public class PagamentoDinheiro implements PagamentoStrategy {

    @Override
    public String pagar(double valor) {
        return "[DINHEIRO] Pagamento no valor de R$ " + valor;
    }
}
