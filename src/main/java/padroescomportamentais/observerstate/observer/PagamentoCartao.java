package padroescomportamentais.observerstate.observer;

public class PagamentoCartao implements PagamentoStrategy {
    private String numeroCartao;

    public PagamentoCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    @Override
    public String pagar(double valor) {
        return "[CARTÃO] Pagamento de R$ " + valor +
                " realizado com cartão: " + numeroCartao;
    }
}
