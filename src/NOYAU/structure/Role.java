/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NOYAU.structure;

import DAO.AbstractDAOFactory;
import java.util.List;

/**
 *
 * @author antoi
 */
public class Role {
    private int rolId;
    private String code;
    private String libelle;
    
    public Role(){
        this.rolId = 0;
        this.code = null;
        this.libelle = null;
    }
    
    public Role(int rolId, String code, String libelle){
        this.rolId = rolId;
        this.code = code;
        this.libelle = libelle;
    }
    
    public Role(String code){
        this.code = code;
        this.libelle = null;
    }
    
    public static List<Role> getLesRoles(){
        return AbstractDAOFactory.getFactory(AbstractDAOFactory.SQL_DAO_FACTORY).getRoleDAO().getAll();
    }
    
    public void ajouterRole(){
            AbstractDAOFactory.getFactory(AbstractDAOFactory.SQL_DAO_FACTORY).getRoleDAO().insert(this);
        }

    public int getRolId() {
        return rolId;
    }
    
    public String getCode(){
        return this.code;
    }
    
    public String getLibelle(){
        return this.libelle;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }
    
    public void setCode(String code){
        this.code = code;
    }
    
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }
    
    public static Role getRole(int rolId){
        return (Role) AbstractDAOFactory.getFactory(AbstractDAOFactory.SQL_DAO_FACTORY).getRoleDAO().get(rolId);
    }
}
