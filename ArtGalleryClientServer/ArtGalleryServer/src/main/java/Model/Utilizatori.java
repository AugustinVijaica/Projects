package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Utilizatori implements Serializable {


    private ArrayList<Utilizator> listaUtilizatori;

    public ArrayList<Utilizator> getListaUtilizatori() {
        return listaUtilizatori;
    }

    public void setListaUtilizatori(ArrayList<Utilizator> listaUtilizatori) {
        this.listaUtilizatori = listaUtilizatori;
    }

    public Utilizatori(ArrayList<Utilizator> listaUtilizatori) {
        this.listaUtilizatori = listaUtilizatori;
    }
}
