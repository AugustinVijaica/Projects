package Model;

import java.io.Serializable;

public class Sculptura extends Opera implements Serializable {

    private String tip;

    public Sculptura(String titlu, String artist, String data, String tip,String imagine) {
        super(titlu, artist, data,imagine);
        this.tip = tip;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Sculptura(){

    }

    public Sculptura(Sculptura sculptura){
        super(sculptura.getTitlu(),sculptura.getArtist(), sculptura.getData(),sculptura.getImagine());

        this.tip = sculptura.getTip();
    }
}
