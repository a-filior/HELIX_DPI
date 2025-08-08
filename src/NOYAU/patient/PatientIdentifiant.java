/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NOYAU.patient;

import DAO.AbstractDAOFactory;
import DAO.SQL.PatientIdentifiantDAO;

/**
 *
 * @author antoi
 */
public class PatientIdentifiant {
    
    private int pidId;
    private int pidtId;
    private String valeur;
    private String autoriteAffectation;
    private String typeCode;
    private String typeLibelle;
    
    public PatientIdentifiant(){
        this.pidId = 0;
        this.pidtId = 0;
        this.valeur = null;
        this.autoriteAffectation = null;
        this.typeCode = null;
        this.typeLibelle = null;
    }
    
    public PatientIdentifiant(String valeur, String typeCode){
        this.valeur = valeur;
        this.autoriteAffectation = null;
        this.typeCode = typeCode;
        this.typeLibelle = null;
    }
    
    public PatientIdentifiant(String valeur, String autoriteCode, String typeCode){
        this.valeur = valeur;
        this.autoriteAffectation = autoriteCode;
        this.typeCode = typeCode;
        this.typeLibelle = null;
    }
    
    public PatientIdentifiant(String valeur, String autoriteAffectation, String typeCode, String typeLibelle){
        this.valeur = valeur;
        this.autoriteAffectation = autoriteAffectation;
        this.typeCode = typeCode;
        this.typeLibelle = typeLibelle;
    }

    public PatientIdentifiant(int pidId, int pidtId, String valeur, String autoriteAffectation, String typeCode, String typeLibelle) {
        this.pidId = pidId;
        this.pidtId = pidtId;
        this.valeur = valeur;
        this.autoriteAffectation = autoriteAffectation;
        this.typeCode = typeCode;
        this.typeLibelle = typeLibelle;
    }

    public int getPidId() {
        return pidId;
    }

    public void setPidId(int pidId) {
        this.pidId = pidId;
    }

    public int getPidtId() {
        return pidtId;
    }

    public void setPidtId(int pidtId) {
        this.pidtId = pidtId;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public String getAuthoriteAffectation() {
        return autoriteAffectation;
    }

    public void setAuthoriteAffectation(String authoriteAffectation) {
        this.autoriteAffectation = authoriteAffectation;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeLibelle() {
        return typeLibelle;
    }

    public void setTypeLibelle(String typeLibelle) {
        this.typeLibelle = typeLibelle;
    }
    
    public static PatientIdentifiant getIPP(int patId){
        return ((PatientIdentifiantDAO) AbstractDAOFactory.getFactory(AbstractDAOFactory.SQL_DAO_FACTORY).getPatientIdentifiantDAO()).getIPPByPatId(patId);
    }
    
    
    @Override
    public String toString(){
        return (this.valeur + " " + this.autoriteAffectation + " " + this.typeCode + " " + this.typeLibelle);
    }
}
