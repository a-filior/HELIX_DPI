/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NOYAU.structure;

import DAO.AbstractDAOFactory;
import DAO.SQL.IdentifiantDAO;

/**
 *
 * @author antoi
 */
public class Identifiant {
    private int idenId;
    private int intId;
    private String login;
    private String password;
    
    public Identifiant(){
        this.idenId = 0;
        this.intId = 0;
        this.login = null;
        this.password = null;
    }
    
    public Identifiant(int idenId, int intId, String login, String password){
        this.idenId = idenId;
        this.intId = intId;
        this.login = login;
        this.password = password;
    }
    
    public boolean tenterConnexion(String password){
        boolean res = false;
        if(this.password.equals(password)){
            res = true;
        }
        
        return res;
    }
    
    public Identifiant(String login){
        this.login = login;
        this.password = ((IdentifiantDAO) AbstractDAOFactory.getFactory(AbstractDAOFactory.SQL_DAO_FACTORY).getIdentifiantDAO()).getIdentifiantByLogin(login).getPassword();
    }
    
    public String getLogin(){
        return this.login;
    }
    
    public String getPassword(){
        return this.password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdenId() {
        return idenId;
    }

    public int getIntId() {
        return intId;
    }
    
    
    
    
    public static Identifiant getIdentifiant(int intId){
        return ((IdentifiantDAO) AbstractDAOFactory.getFactory(AbstractDAOFactory.SQL_DAO_FACTORY).getIdentifiantDAO()).getIdentifiantByIntervenantId(intId);
    }
    
    public void modifierIdentifiant(){
        AbstractDAOFactory.getFactory(AbstractDAOFactory.SQL_DAO_FACTORY).getIdentifiantDAO().update(this);
    }
    
    public void ajouterIdentifiant(){
        AbstractDAOFactory.getFactory(AbstractDAOFactory.SQL_DAO_FACTORY).getIdentifiantDAO().insert(this);
    }
    
    
    
}
