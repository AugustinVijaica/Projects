package Model;

import javafx.scene.control.Tab;

import java.io.Serializable;

public class Tablou extends Opera implements Serializable {


    private String gen;
    private String tehnica;

    public Tablou(String titlu, String artist, String data, String gen,String tehnica,String imagine) {
        super(titlu, artist, data,imagine);
        this.gen=gen;
        this.tehnica=tehnica;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public Tablou(){

    }

    public String getTehnica() {
        return tehnica;
    }

    public void setTehnica(String tehnica) {
        this.tehnica = tehnica;
    }

    public Tablou(Tablou tablou){
        super(tablou.getTitlu(),tablou.getArtist(),tablou.getData(),tablou.getImagine());

        this.gen = tablou.getGen();
        this.tehnica = tablou.getTehnica();

    }
}
