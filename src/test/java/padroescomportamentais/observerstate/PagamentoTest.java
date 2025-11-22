package padroescomportamentais.observerstate;

import org.junit.jupiter.api.Test;
import padroescomportamentais.observerstate.observer.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PagamentoTest {
    @Test
    void deveRealizarPagamentoEmDinheiro() {
        PagamentoStrategy pagamento = new PagamentoDinheiro();
        String resultado = pagamento.pagar(50.0);

        assertEquals("[DINHEIRO] Pagamento no valor de R$ 50.0", resultado);
    }

    @Test
    void deveRealizarPagamentoComPix() {
        PagamentoStrategy pagamento = new PagamentoPix("joao@pix.com");
        String resultado = pagamento.pagar(75.0);

        assertEquals("[PIX] Pagamento de R$ 75.0 realizado com chave PIX: joao@pix.com", resultado);
    }

    @Test
    void deveRealizarPagamentoComCartao() {
        PagamentoStrategy pagamento = new PagamentoCartao("1234-5678-9999");
        String resultado = pagamento.pagar(100.0);

        assertEquals("[CARTÃO] Pagamento de R$ 100.0 realizado com cartão: 1234-5678-9999", resultado);
    }

    @Test
    void deveLancarErroQuandoPagamentoNaoSelecionado() {
        Pedido pedido = new Pedido("Pizza", 40.0);

        Exception exception = assertThrows(IllegalStateException.class, pedido::realizarPagamento);

        assertEquals("Nenhuma forma de pagamento selecionada!", exception.getMessage());
    }
}
