/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NOYAU.patient;

/**
 *
 * @author antoi
 */
public class Adresse {
    private int adrId;
    private int numeroVoie1;
    private String voie1;
    private String ville1;
    private String pays1;
    private String codePostal1;
    private String iso3;

    public Adresse() {
    }

    public Adresse(int adrId, int numeroVoie1, String voie1, String ville1, String pays1, String codePostal1, String iso3) {
        this.adrId = adrId;
        this.numeroVoie1 = numeroVoie1;
        this.voie1 = voie1;
        this.ville1 = ville1;
        this.pays1 = pays1;
        this.codePostal1 = codePostal1;
        this.iso3 = iso3;
    }

    public int getAdrId() {
        return adrId;
    }

    public void setAdrId(int adrId) {
        this.adrId = adrId;
    }

    public int getNumeroVoie1() {
        return numeroVoie1;
    }

    public void setNumeroVoie1(int numeroVoie1) {
        this.numeroVoie1 = numeroVoie1;
    }

    public String getVoie1() {
        return voie1;
    }

    public void setVoie1(String voie1) {
        this.voie1 = voie1;
    }

    public String getVille1() {
        return ville1;
    }

    public void setVille1(String ville1) {
        this.ville1 = ville1;
    }

    public String getPays1() {
        return pays1;
    }

    public void setPays1(String pays1) {
        this.pays1 = pays1;
    }

    public String getCodePostal1() {
        return codePostal1;
    }

    public void setCodePostal1(String codePostal1) {
        this.codePostal1 = codePostal1;
    }

    public String getIso3() {
        return iso3;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }
    
    

    @Override
    public String toString() {
        return "Adresse{" + "adrId=" + adrId + ", numeroVoie1=" + numeroVoie1 + ", voie1=" + voie1 + ", ville1=" + ville1 + ", pays1=" + pays1 + ", codePostal1=" + codePostal1 + '}';
    }
    
    
}
