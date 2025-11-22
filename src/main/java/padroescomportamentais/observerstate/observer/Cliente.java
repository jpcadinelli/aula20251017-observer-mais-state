package padroescomportamentais.observerstate.observer;

import java.util.Observable;
import java.util.Observer;

public class Cliente implements Observer {
    private String nome;
    private String ultimaNotificacao;

    public Cliente(String nome) {
        this.nome = nome;
    }

    public String getUltimaNotificacao() {
        return this.ultimaNotificacao;
    }

    public void inscrever(PlataformaIfood platforma) {
        platforma.addObserver(this);
    }

    @Override
    public void update(Observable platform, Object arg) {
        if (arg instanceof Pedido pedido) {
            this.ultimaNotificacao = this.nome + ", novo pedido criado: " + pedido.getDetalhesPedido() + " - Status: " + pedido.getStatus();
        } else {
            this.ultimaNotificacao = this.nome + ", nova notificação recebida.";
        }
    }
}
