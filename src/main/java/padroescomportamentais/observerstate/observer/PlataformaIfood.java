package padroescomportamentais.observerstate.observer;

import java.util.Observable;

public class PlataformaIfood extends Observable {
    public void lancarPedido(Pedido pedido) {
        setChanged();
        notifyObservers(pedido);
    }
}

