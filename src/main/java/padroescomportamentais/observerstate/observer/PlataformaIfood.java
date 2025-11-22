package padroescomportamentais.observerstate.observer;

import java.util.Observable;

public class PlataformaIfood extends Observable {
    private static PlataformaIfood instance;

    private PlataformaIfood() { }

    public static PlataformaIfood getInstance() {
        if (instance == null) {
            instance = new PlataformaIfood();
        }
        return instance;
    }

    public void lancarPedido(Pedido pedido) {
        setChanged();
        notifyObservers(pedido);
    }

    public void resetObservers() {
        deleteObservers();
    }
}