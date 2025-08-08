/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NOYAU.structure;

import DAO.AbstractDAOFactory;
import DAO.SQL.ApplicationDAO;
import java.util.List;

/**
 *
 * @author antoi
 */
public class Application {
    private int appId;
    private String appCode;
    private String appLibelle;
    private String appDescription;

    public Application() {
        this.appId = 0;
        this.appCode = "";
        this.appLibelle = "";
        this.appDescription = "";
    }
    
    

    public Application(String appCode, String appLibelle, String appDescription) {
        this.appCode = appCode;
        this.appLibelle = appLibelle;
        this.appDescription = appDescription;
    }
    
    public Application(String appCode) {
        Application app = ((ApplicationDAO) AbstractDAOFactory.getFactory(AbstractDAOFactory.SQL_DAO_FACTORY).getApplicationDAO()).getApplicationByAppCode(appCode);
        this.appId = app.getAppId();
        this.appCode = appCode;
        this.appLibelle = app.getAppLibelle();
        this.appDescription = app.getAppDescription();
    }
    
    public Application(int appId, String appCode, String appLibelle, String appDescription) {
        this.appId = appId;
        this.appCode = appCode;
        this.appLibelle = appLibelle;
        this.appDescription = appDescription;
    }
    




    public String getAppCode() {
        return appCode;
    }

    public String getAppLibelle() {
        return appLibelle;
    }

    public String getAppDescription() {
        return appDescription;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public void setAppLibelle(String appLibelle) {
        this.appLibelle = appLibelle;
    }

    public void setAppDescription(String appDescription) {
        this.appDescription = appDescription;
    }

    public int getAppId() {
        return appId;
    }
    
    

    @Override
    public String toString() {
        return "Application{" + "appCode=" + appCode + ", appLibelle=" + appLibelle + ", appDescription=" + appDescription + '}';
    }
    
    public static List<Application> getLesApplications(){
        return AbstractDAOFactory.getFactory(AbstractDAOFactory.SQL_DAO_FACTORY).getApplicationDAO().getAll();
    }
    
    public static Application getApplication(int appId){
        return (Application) AbstractDAOFactory.getFactory(AbstractDAOFactory.SQL_DAO_FACTORY).getApplicationDAO().get(appId);
    }
    
}
