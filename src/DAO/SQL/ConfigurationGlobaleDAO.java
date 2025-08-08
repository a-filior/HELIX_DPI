/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.SQL;

import DAO.DAO;
import NOYAU.configuration.ConfigurationGlobale;
import NOYAU.structure.Identifiant;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author antoi
 */
public class ConfigurationGlobaleDAO extends DAO<ConfigurationGlobale>{

    public ConfigurationGlobaleDAO(Connection connect) {
        super(connect);
    }

    @Override
    public boolean insert(ConfigurationGlobale obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(ConfigurationGlobale obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(ConfigurationGlobale obj) {
        boolean check;
        try {
            String requete = "update dpi.CONFIGURATION_GLOBALE cg "
                    + "SET cg.param_valeur '" + obj.getParamCode() + "' "
                    + "where cg.param_code = '" + obj.getParamCode()+ "';";
            this.connect.createStatement( 
                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_READ_ONLY
            ).executeUpdate(requete);
            check = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("update(ConfigurationGlobale obj)");
            check = false;
        }
        return check;
    }

    @Override
    public ConfigurationGlobale get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ConfigurationGlobale> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ConfigurationGlobale> getAll(String param) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public ConfigurationGlobale getConfigGlobaleByCode(String code){
        String requete =    "SELECT cg.param_id, cg.param_code, cg.param_libelle, cg.param_valeur "
                            + "FROM CONFIGURATION_GLOBALE "
                            + "WHERE param_code = '" + code + "';";
         try {
            ConfigurationGlobale res = null;
            ResultSet resultat = this.connect.createStatement( 
                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_READ_ONLY
            ).executeQuery(requete);
            if(resultat.next()){
                if(resultat.getString("iden_login") != null){
                    res = (new ConfigurationGlobale(                   
                            resultat.getInt("cg.param_id"),
                            resultat.getString("cg.param_code"),
                            resultat.getString("cg.param_libelle"),
                            resultat.getString("cg.param_valeur")
                     ));
                } 
            
            }else {res = new ConfigurationGlobale();}
            
            return res;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("ConfigurationGlobale getConfigGlobaleByCode(String code)");
            return null;
        }
    }
    
}
