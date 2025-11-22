package padroescomportamentais.observerstate.observer;

public class PagamentoPix implements PagamentoStrategy {
    private String chavePix;

    public PagamentoPix(String chavePix) {
        this.chavePix = chavePix;
    }

    @Override
    public String pagar(double valor) {
        return "[PIX] Pagamento de R$ " + valor +
                " realizado com chave PIX: " + chavePix;
    }
}
