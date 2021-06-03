package Model;

import javax.swing.*;
import java.io.Serializable;

public class Opera implements Serializable {

    private String titlu;
    private String artist;
    private String data;
    private String imagine;

    public String getImagine() {
        return imagine;
    }

    public void setImagine(String imagine) {
        this.imagine = imagine;
    }

    public Opera(String titlu, String artist, String data,String imagine) {
        this.titlu = titlu;
        this.artist = artist;
        this.data = data;
        this.imagine = imagine;
    }

    public Opera(){

    }

    public Opera(Opera opera){
        this.titlu = opera.getTitlu();
        this.artist = opera.getArtist();
        this.data = opera.getData();
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Opera)) {
            return false;
        }


        return this.getTitlu().contentEquals(((Opera) obj).getTitlu());

    }

    @Override
    public int hashCode() {
        return this.getTitlu().hashCode();
    }
}


