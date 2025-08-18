/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NOYAU.patient;

import DAO.AbstractDAOFactory;
import DAO.SQL.PatientDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author antoi
 */
public class Patient {
    private int patId;
    private String nomNaissance;
    private String nomUsuel;
    private String prenomPrincipal;
    private Date dateNaissance;
    private Sexe sexe;
    private String codePostalLieuNaissance;
    private String codeNationalite;    
    private String prenomsComplets;
    private Date dateDeces;
//    private List<Venue> lesVenues;
    private List<PatientIdentifiant> lesIdentifiants;

    public Patient() {
        this.patId = 0;
        this.nomNaissance = "";
        this.nomUsuel = ""; 
        this.prenomPrincipal = "";
        this.dateNaissance = null;
        this.sexe = Sexe.U;
        this.codePostalLieuNaissance = "";
        this.codeNationalite = "";
        this.prenomsComplets = "";
        this.dateDeces = null;
        this.lesIdentifiants = new ArrayList<PatientIdentifiant>();
    }

    public Patient(int patId, String nomNaissance, String nomUsuel, String prenomPrincipal, Date dateNaissance, Sexe sexe, String codePostalLieuNaissance, String codeNationalite, String prenomsComplets, Date dateDeces, List<PatientIdentifiant> lesIdentifiants) {
        this.patId = patId;
        this.nomNaissance = nomNaissance;
        this.nomUsuel = nomUsuel;
        this.prenomPrincipal = prenomPrincipal;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
        this.codePostalLieuNaissance = codePostalLieuNaissance;
        this.codeNationalite = codeNationalite;
        this.prenomsComplets = prenomsComplets;
        this.dateDeces = dateDeces;
        this.lesIdentifiants = lesIdentifiants;
    }
    

    
    public Patient(String nomNaissance, String nomUsuel, String prenomPrincipal, Date dateNaissance, Sexe sexe){
        this.nomNaissance = nomNaissance;
        this.nomUsuel = nomUsuel;
        this.prenomPrincipal = prenomPrincipal;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
        this.lesIdentifiants = new ArrayList<>();
    }
    
    public Patient(String nomNaissance, String nomUsuel, String prenomPrincipal, Date dateNaissance, Sexe sexe, List<PatientIdentifiant> lesIdentifiants){
        this.nomNaissance = nomNaissance;
        this.nomUsuel = nomUsuel;
        this.prenomPrincipal = prenomPrincipal;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
        this.lesIdentifiants = lesIdentifiants;
    }

    public int getPatId() {
        return patId;
    }

    public void setPatId(int patId) {
        this.patId = patId;
    }

    public String getNomNaissance() {
        return nomNaissance;
    }

    public void setNomNaissance(String nomNaissance) {
        this.nomNaissance = nomNaissance;
    }

    public String getNomUsuel() {
        return nomUsuel;
    }

    public void setNomUsuel(String nomUsuel) {
        this.nomUsuel = nomUsuel;
    }

    public String getPrenomPrincipal() {
        return prenomPrincipal;
    }

    public void setPrenomPrincipal(String prenomPrincipal) {
        this.prenomPrincipal = prenomPrincipal;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    public String getCodePostalLieuNaissance() {
        return codePostalLieuNaissance;
    }

    public void setCodePostalLieuNaissance(String codePostalLieuNaissance) {
        this.codePostalLieuNaissance = codePostalLieuNaissance;
    }

    public String getCodeNationalite() {
        return codeNationalite;
    }

    public void setCodeNationalite(String codeNationalite) {
        this.codeNationalite = codeNationalite;
    }

    public String getPrenomsComplets() {
        return prenomsComplets;
    }

    public void setPrenomsComplets(String prenomsComplets) {
        this.prenomsComplets = prenomsComplets;
    }

    public Date getDateDeces() {
        return dateDeces;
    }

    public void setDateDeces(Date dateDeces) {
        this.dateDeces = dateDeces;
    }

    public List<PatientIdentifiant> getLesIdentifiants() {
        return lesIdentifiants;
    }

    public void setLesIdentifiants(List<PatientIdentifiant> lesIdentifiants) {
        this.lesIdentifiants = lesIdentifiants;
    }
    
    
   
    
    public static Patient getPatient(String IPP, String codeType){
        return ((PatientDAO) AbstractDAOFactory.getFactory(AbstractDAOFactory.SQL_DAO_FACTORY).getPatientDAO()).get(IPP,codeType);
    }
    
    public static List<Patient> getPatientByNom(String nom){
        return ((PatientDAO) AbstractDAOFactory.getFactory(AbstractDAOFactory.SQL_DAO_FACTORY).getPatientDAO()).getAll(nom);
    }
    
    public static String capitalize(String param){
        return param.substring(0, 1).toUpperCase() + param.substring(1).toLowerCase();
    }
    
    public void ajouterPatient(){
            AbstractDAOFactory.getFactory(AbstractDAOFactory.SQL_DAO_FACTORY).getPatientDAO().insert(this);
    }

    public List<Venue> getListeSejoursOuverts(){
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public String toString(){
        return (this.nomUsuel + " - " + this.nomNaissance + " - " + this.prenomPrincipal);
    }
    
    public String display(){
        StringBuilder res = new StringBuilder().append(this.nomNaissance + " - " + 
                this.nomUsuel + " - " + 
                this.prenomPrincipal + " -- " + 
                this.dateNaissance + " -- " + 
                this.sexe.getName()+ "\r\n");
        
        if(this.lesIdentifiants != null){
        this.lesIdentifiants.forEach(pid -> {
                res.append("\r\n" + pid.toString());
            });
        }
        return res.toString();
    }
    
    public String getIPP(){
        if(PatientIdentifiant.getIdentifiantPI(this.patId) != null){
            return PatientIdentifiant.getIdentifiantPI(this.patId).getValeur();
        } else {
            return null;
        }
    }
    
    public String trouverlIPP(){
        String s = null;
        if(this.lesIdentifiants != null){
            for(int i = 0; i < this.lesIdentifiants.size(); i++){
                if(this.lesIdentifiants.get(i).getTypeCode().equals("PI")){
                    return this.getLesIdentifiants().get(i).getValeur();
                }
            }
        }
        return s;
    }
    
    public enum Sexe {

        M("Male"),
        F("Female"),
        U("Intéterminé"),
        O("Autre");

        private final String name;

        private Sexe(String s){
            this.name = s;
        }

        public String getName(){
            return this.name;
        }
        
    }
}
