package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class PersistentaFacade implements Serializable {

    private PersistentaOpere po;
    private PersistentaUtilizatori pu;

    public PersistentaOpere getPo() {
        return po;
    }

    public void setPo(PersistentaOpere po) {
        this.po = po;
    }

    public PersistentaUtilizatori getPu() {
        return pu;
    }

    public void setPu(PersistentaUtilizatori pu) {
        this.pu = pu;
    }

    public PersistentaFacade(){

        po = new PersistentaOpere();
        pu = new PersistentaUtilizatori();

    }

    public Institutii citireInstitutii(){
        return this.po.citireInstitutii();
    }

    public boolean salvareOpera(Opera opera, String institutie){
        return this.po.salvare(opera,institutie);
    }

    public boolean actualizareOpera(Opera operaVeche, Opera operaNoua, String institutie){
        return this.po.actualizare(operaVeche,operaNoua,institutie);
    }


    public boolean stergereOpera(Opera opera){
        return this.po.stergere(opera);
    }

    public ArrayList<Opera> filtrareListaArtist(String artist){
        return this.po.filtrareListaArtist(artist);
    }

    public ArrayList<Opera> filtrareListaTip(String tip){
        return this.po.filtrareListaTip(tip);
    }

    public ArrayList<Opera> filtrareListaInstitutie(String institutie){
        return this.po.filtrare(institutie);

    }

    public Opera cautareOperaTitlu(String titlu){
        return this.po.cautare(titlu);
    }

    public Institutie cautareInstitutieNume(String nume){
        return this.po.cautareInstitutieNume(nume);
    }

    public boolean salvareUtilizator(Utilizator utilizator){
        return this.pu.salvare(utilizator,null);
    }

    public boolean actualizareUtilizator(Utilizator utilizatorVechi, Utilizator utilizatorNou){
        return this.pu.actualizare(utilizatorVechi,utilizatorNou,null);
    }

    public boolean stergereUtilizator(Utilizator utilizator){
        return this.pu.stergere(utilizator);
    }

    public Utilizatori citireUtilizatori(){
        return this.pu.citireUtilizatori();
    }

    public ArrayList<Utilizator> filtrareRol(String rol){
        return this.pu.filtrare(rol);
    }

    public Utilizator cautareUtilizator(String username, String password){
        return this.pu.cautareUtilizator(username,password);
    }

    public Utilizator cautareUtilizator(String username){
        return this.pu.cautare(username);
    }


}
