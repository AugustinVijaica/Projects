package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Institutie implements Serializable {


    private String nume;

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    private ArrayList<Opera> listaOpere;

    public ArrayList<Opera> getListaOpere() {
        return listaOpere;
    }

    public void setListaOpere(ArrayList<Opera> listaOpere) {
        this.listaOpere = listaOpere;
    }

    public Institutie(String nume, ArrayList<Opera> listaOpere) {

        this.nume = nume;
        this.listaOpere = listaOpere;
    }

    public Institutie(){

    }
}



