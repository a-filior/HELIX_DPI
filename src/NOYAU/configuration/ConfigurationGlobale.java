/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NOYAU.configuration;

import DAO.AbstractDAOFactory;

/**
 *
 * @author antoi
 */
public class ConfigurationGlobale {
    private int paramId;
    private String paramCode;
    private String paramLibelle;
    private String paramValeur;

    public ConfigurationGlobale() {
        this.paramId = 0;
        this.paramCode = "";
        this.paramLibelle = "";
        this.paramValeur = "";
    }

    public ConfigurationGlobale(String paramCode, String paramLibelle, String paramValeur) {
        this.paramCode = paramCode;
        this.paramLibelle = paramLibelle;
        this.paramValeur = paramValeur;
    }

    public ConfigurationGlobale(int paramId, String paramCode, String paramLibelle, String paramValeur) {
        this.paramId = paramId;
        this.paramCode = paramCode;
        this.paramLibelle = paramLibelle;
        this.paramValeur = paramValeur;
    }

    public int getParamId() {
        return paramId;
    }

    public void setParamId(int paramId) {
        this.paramId = paramId;
    }

    public String getParamCode() {
        return paramCode;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode;
    }

    public String getParamLibelle() {
        return paramLibelle;
    }

    public void setParamLibelle(String paramLibelle) {
        this.paramLibelle = paramLibelle;
    }

    public String getParamValeur() {
        return paramValeur;
    }

    public void setParamValeur(String paramValeur) {
        this.paramValeur = paramValeur;
    }

    @Override
    public String toString() {
        return "ConfigurationGlobale{" + "paramId=" + paramId + ", paramCode=" + paramCode + ", paramLibelle=" + paramLibelle + ", paramValeur=" + paramValeur + '}';
    }
    
    public void modifierConfigurationGlobale(){
        AbstractDAOFactory.getFactory(AbstractDAOFactory.SQL_DAO_FACTORY).getConfigurationGlobaleDAO().update(this);
    }
    
}
