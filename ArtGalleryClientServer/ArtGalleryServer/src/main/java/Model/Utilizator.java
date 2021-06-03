package Model;

import java.io.Serializable;

public class Utilizator implements Serializable {


    public Utilizator(Utilizator utilizator) {
        this.nume = utilizator.getNume();
        this.rol = utilizator.getRol();
        this.cont = utilizator.getCont();
        this.parola = utilizator.getParola();
    }

    public Utilizator(){

    }

    private String nume;
    private String rol;
    private String cont;
    private String parola;

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }


    public Utilizator(String nume, String rol, String cont, String parola) {
        this.nume = nume;
        this.rol = rol;
        this.cont = cont;
        this.parola = parola;
    }
}
