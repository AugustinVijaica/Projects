package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Institutii implements Serializable {


    private ArrayList<Institutie> listaInstitutii;

    public Institutii(ArrayList<Institutie> listaInstitutii) {
        this.listaInstitutii = listaInstitutii;
    }

    public ArrayList<Institutie> getListaInstitutii() {
        return listaInstitutii;
    }

    public void setListaInstitutii(ArrayList<Institutie> listaInstitutii) {
        this.listaInstitutii = listaInstitutii;
    }
}
