package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Observatori implements Serializable {

    protected ArrayList<Observer> listaObs;

    public void adaugare(Observer obs)
    {
        this.listaObs.add(obs);
    }
    public void stergere(Observer obs)
    {
        this.listaObs.remove(obs);
    }
    public void Notificare()
    {
        for (Observer o: listaObs
             ) {
            o.update();
        }
    }


}
