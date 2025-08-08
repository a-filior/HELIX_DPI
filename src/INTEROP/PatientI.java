package INTEROP;
import java.util.*;

import INTEROP.HL7Parser;

public class PatientI {

    HL7Parser parser = new HL7Parser();

    String iPP;
    String nomNaissance="";
    String nomUsage="";
    String prenom="";
    String sexe="";
    Date dateNaissance;
    List<Venue> venues;

    public String getIPP() {
        return iPP;
    }

    public void setIPP(String iPP) {
        this.iPP = iPP;
    }

    public String getNomNaissance() {
        return nomNaissance;
    }

    public void setNomNaissance(String nomNaissance) {
        this.nomNaissance = nomNaissance;
    }

    public String getNomUsage() {
        return nomUsage;
    }

    public void setNomUsage(String nomUsage) {
        this.nomUsage = nomUsage;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public List<Venue> getVenues() {
        return venues;
    }

    public void addVenues(Venue venue) {
        venues.add(venue); 
    }

    public String getResumePatient(PatientI p){
        String result; 
        if (p.getNomUsage() == null) {
            result = "";
//            result = "IPP n°" + p.getIPP() + ", " + p.getNomNaissance().toUpperCase() +" "+ (StringUtils.capitalize(p.getPrenom().toLowerCase())) + " le " + parser.getDateLisible(p.getDateNaissance())+ ".\r\n";
        } else {
            result = "";
//            result = "IPP n°" + p.getIPP() + ", " + p.getNomUsage().toUpperCase() +" "+ (StringUtils.capitalize(p.getPrenom().toLowerCase())) + " né(e) " + p.getNomNaissance().toUpperCase() + " le " + parser.getDateLisible(p.getDateNaissance())+ ".\r\n";
        }
        return result; 
    }

}


