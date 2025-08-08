/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.SQL;

import DAO.DAO;
import NOYAU.structure.Application;
import NOYAU.structure.Identifiant;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author antoi
 */
public class IdentifiantDAO extends DAO<Identifiant>{

    public IdentifiantDAO(Connection connect) {
        super(connect);
    }

    @Override
    public boolean insert(Identifiant obj) {
        boolean check;
        try {
            String requete = "INSERT INTO dpi.IDENTIFIANTS_CONNEXION (int_id, iden_login, iden_password) "
                    + "VALUES('" + obj.getIntId()+ "', '" + obj.getLogin() + "', '" + obj.getPassword()+ "');";           
                     
            this.connect.createStatement( 
                ResultSet.TYPE_SCROLL_INSENSITIVE,  
                ResultSet.CONCUR_READ_ONLY
            ).executeUpdate(requete);
            check = true;
        
        } catch (SQLException ex) {
            ex.printStackTrace();           
            System.out.println("Problème boolean insert(Identifiant obj)");
            check = false;
        } 
        return check;
    }

    @Override
    public boolean delete(Identifiant obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Identifiant obj) {
        boolean check;
        try {
            String requete = "update dpi.IDENTIFIANTS_CONNEXION ic "
                    + "SET ic.int_id = " + obj.getIntId() + ", ic.iden_login = '" + obj.getLogin() + "', ic.iden_password = '" + obj.getPassword() + "' "
                    + "where ic.iden_id = " + obj.getIdenId() + ";";
            this.connect.createStatement( 
                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_READ_ONLY
            ).executeUpdate(requete);
            check = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Problème boolean update(Identifiant obj)");
            check = false;
        }
        return check;
    }

    @Override
    public Identifiant get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Identifiant> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Identifiant> getAll(String param) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public Identifiant getIdentifiantByLogin(String login){
        String requete =    "SELECT iden_id, int_id, iden_login, iden_password  "
                            + "FROM dpi.IDENTIFIANTS_CONNEXION "
                            + "WHERE iden_login = '" + login + "';";
        try {
            Identifiant res;
            ResultSet resultat = this.connect.createStatement( 
                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_READ_ONLY
            ).executeQuery(requete);
            resultat.next();
            res = (new Identifiant(    
                    resultat.getInt("iden_id"),
                    resultat.getInt("int_id"),
                    resultat.getString("iden_login"),
                    resultat.getString("iden_password")
             ));
            System.out.println("getIdentifiantByLogin(String login)");
            
            return res;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Problème getIdentifiantByLogin(String login)");
            return null;
        }
    }
    
    
    public Identifiant getIdentifiantByIntervenantId(int intId){
        String requete =    "SELECT iden_id, int_id, iden_login, iden_password  "
                            + "FROM dpi.IDENTIFIANTS_CONNEXION "
                            + "WHERE int_id = '" + intId + "';";
        try {
            Identifiant res = null;
            ResultSet resultat = this.connect.createStatement( 
                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_READ_ONLY
            ).executeQuery(requete);
            if(resultat.next()){
                if(resultat.getString("iden_login") != null){
                    res = (new Identifiant(  
                            resultat.getInt("iden_id"),
                            resultat.getInt("int_id"),
                            resultat.getString("iden_login"),
                            resultat.getString("iden_password")
                     ));
                } 
            
            }else {res = new Identifiant();}
            
            return res;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Identifiant getIdentifiantByIntervenantId(int intId)");
            return null;
        }
    }
    
    
}
