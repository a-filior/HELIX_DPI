/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.SQL;

import DAO.DAO;
import NOYAU.configuration.ParametrageInterop;
import NOYAU.structure.Application;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author antoi
 */
public class ApplicationDAO extends DAO<Application>{

    public ApplicationDAO(Connection connect) {
        super(connect);
    }

    @Override
    public boolean insert(Application obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(Application obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Application obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Application get(int id) {
        String requete =    "SELECT app_code, app_libelle, app_description "
                            + "FROM dpi.APPLICATION "
                            + "WHERE app_id = '" + id + "';";
        try {
            Application res;
            ResultSet resultat = this.connect.createStatement( 
                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_READ_ONLY
            ).executeQuery(requete);
            resultat.next();
            res = (new Application(
                    id,
                    resultat.getString("app_code"),
                    resultat.getString("app_libelle"),
                    resultat.getString("app_description")
             ));        
            return res;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Problème Application get(int id)");
            return null;
        } 
    }

    @Override
    public List<Application> getAll() {
        String requete = "SELECT app_id, app_code, app_libelle, app_description "
                + "FROM dpi.APPLICATION a;";
        try {
            List<Application> res = new ArrayList<>();
            ResultSet resultat = this.connect.createStatement( 
                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_READ_ONLY
            ).executeQuery(requete);
            while (resultat.next()) { 
                res.add(new Application(
                    resultat.getInt("app_id"),
                    resultat.getString("app_code"),
                    resultat.getString("app_libelle"),
                    resultat.getString("app_description")));
            }
            return res;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Problème List<Application> getAll()");
            return null;
        } 
    }

    @Override
    public List<Application> getAll(String param) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public Application getApplicationByAppCode(String appCode){
        String requete =    "SELECT app_libelle, app_description "
                            + "FROM dpi.APPLICATION "
                            + "WHERE app_code = '" + appCode + "';";
        try {
            Application res;
            ResultSet resultat = this.connect.createStatement( 
                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_READ_ONLY
            ).executeQuery(requete);
            resultat.next();
            res = (new Application(
                    appCode,
                    resultat.getString("app_libelle"),
                    resultat.getString("app_description")
             ));        
            return res;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Problème Application getApplicationByAppCode(String appCode)");
            return null;
        } 
    }
    
    
    
    
    
}
