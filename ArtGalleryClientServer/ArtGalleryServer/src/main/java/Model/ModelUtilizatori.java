package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class ModelUtilizatori extends Observatori implements Serializable {


        private PersistentaFacade pf;
        private Utilizator utilizator;
        private String operatie;
        private String infoCautata;
        private Limba limba;

        public ModelUtilizatori() {

            this.pf = new PersistentaFacade();
            this.utilizator = null;
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

    public Utilizator getUtilizator() {
        return utilizator;
    }

    public void setUtilizator(Utilizator utilizator) {
        this.utilizator = utilizator;
    }

    public String getOperatie() {
            return operatie;
        }

        public void setOperatie(String operatie) {
            this.operatie = operatie;
            this.Notificare();
            this.utilizator=null;
        }

        public String getInfoCautata() {
            return infoCautata;
        }

        public void setInfoCautata(String infoCautata) {
            this.infoCautata = infoCautata;
        }
    }

