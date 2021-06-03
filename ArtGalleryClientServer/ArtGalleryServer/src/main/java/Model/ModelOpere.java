package Model;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModelOpere extends Observatori implements Serializable {

    private PersistentaFacade pf;
    private Opera opera;
    private String operatie;
    private String infoCautata;
    private Limba limba;

    public ModelOpere() throws IOException, SQLException {

        this.pf = new PersistentaFacade();
        this.opera = null;
        this.operatie = "";
        this.infoCautata = "";
        this.listaObs = new ArrayList<Observer>();
        this.limba = new Limba();

    }

    public Limba getLimba() {
        return limba;
    }

    public void setLimba(Limba limba) {
        this.limba = limba;
    }

    public PersistentaFacade getPf() {
        return pf;
    }

    public void setPf(PersistentaFacade pf) {
        this.pf = pf;
    }

    public Opera getOpera() {
        return opera;
    }

    public void setOpera(Opera opera) {
        this.opera = opera;
    }

    public String getOperatie() {
        return operatie;
    }

    public void setOperatie(String operatie) {
        this.operatie = operatie;
        this.Notificare();
        this.opera=null;
    }

    public String getInfoCautata() {
        return infoCautata;
    }

    public void setInfoCautata(String infoCautata) {
        this.infoCautata = infoCautata;
    }
}
