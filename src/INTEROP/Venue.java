package INTEROP;
import java.util.*;

public class Venue {

    String iPP;
    String iEP;
    Date dateDebut;
    Date dateFin;

    public Venue(String iPP) {
        this.iPP = iPP;
    }

    public String getIPP() {
        return iPP;
    }

    public String getIEP() {
        return iEP;
    }

    public void setIEP(String iEP) {
        this.iEP = iEP;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

}