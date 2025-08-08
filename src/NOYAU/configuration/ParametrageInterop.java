/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NOYAU.configuration;

import DAO.AbstractDAOFactory;
import NOYAU.structure.Application;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author antoi
 */
public class ParametrageInterop {

    private int confId;
    private String adresseIp;
    private int port;
    private Application application;
    private int sens;
    private boolean isActif;
    
    public ParametrageInterop() {
        this.confId = 0;
        this.adresseIp = "";
        this.port = 0;
        this.application = new Application();
        this.sens = 0;
        this.isActif = false;
        
    }
    
    public ParametrageInterop(String adresse_ip, int port, int sens, boolean isActif) {

        this.adresseIp = adresse_ip;
        this.port = port;
        this.sens = sens;
        this.isActif = isActif;
    }

    public ParametrageInterop(String adresse_ip, int port, Application application, int sens, boolean isActif) {
        this.adresseIp = adresse_ip;
        this.port = port;
        this.application = application;
        this.sens = sens;
        this.isActif = isActif;
    }
    
    public ParametrageInterop(int confId, String adresse_ip, int port, int sens, boolean isActif) {
        this.confId = confId;
        this.adresseIp = adresse_ip;
        this.port = port;
        this.sens = sens;
        this.isActif = isActif;
    }

    public ParametrageInterop(int confId, String adresse_ip, int port, Application application, int sens, boolean isActif) {
        this.confId = confId;
        this.adresseIp = adresse_ip;
        this.port = port;
        this.application = application;
        this.sens = sens;
        this.isActif = isActif;
    }
    



    public String getAdresseIp() {
        return adresseIp;
    }

    public int getPort() {
        return port;
    }



    public void setAdresseIp(String adresse_ip) {
        this.adresseIp = adresse_ip;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public int getSens() {
        return sens;
    }

    public void setSens(int sens) {
        this.sens = sens;
    }

    public boolean getIsActif() {
        return isActif;
    }

    public void setIsActif(boolean isActif) {
        this.isActif = isActif;
    }

    public int getConfId() {
        return confId;
    }

    public void modifierParametrageInterop(){
        AbstractDAOFactory.getFactory(AbstractDAOFactory.SQL_DAO_FACTORY).getParametrageInteropDAO().update(this);
    }
    
    public void ajouterParametrageInterop(){
        AbstractDAOFactory.getFactory(AbstractDAOFactory.SQL_DAO_FACTORY).getParametrageInteropDAO().insert(this);
    }
    
    public void supprimerParametrageInterop(){
        AbstractDAOFactory.getFactory(AbstractDAOFactory.SQL_DAO_FACTORY).getParametrageInteropDAO().delete(this);
    }
    

    @Override
    public String toString() {
        return "ParametrageInterop{ adresse_ip=" + adresseIp + ", port=" + port + ", application=" + application + ", sens=" + sens + ", isActif=" + isActif + '}';
    }
    
   
    public ParametrageInterop getParametrageByApplicationEtSens(Application app, String sens){
        return null;
    }

    public static List<ParametrageInterop> getLesParam(){
            return AbstractDAOFactory.getFactory(AbstractDAOFactory.SQL_DAO_FACTORY).getParametrageInteropDAO().getAll();
    }
    
    
    
}
