/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.SQL;

import DAO.DAO;
import NOYAU.configuration.ParametrageInterop;
import NOYAU.structure.Application;
import NOYAU.structure.Intervenant;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author antoi
 */
public class ParametrageInteropDAO extends DAO<ParametrageInterop> {

    public ParametrageInteropDAO(Connection connect) {
        super(connect);
    }

    @Override
    public boolean insert(ParametrageInterop obj) {
        boolean check;
        try {
            System.out.println("dfjdflfkjdklfg");
            System.out.println(obj.getIsActif());
            String requete = "INSERT INTO dpi.CONFIGURATION_INTERFACES (app_id, conf_adresse_ip, conf_port, conf_sens, conf_actif) "
                    + "VALUES('" + obj.getApplication().getAppId() + "'"
                    + ", '" + obj.getAdresseIp()+ "'"
                    + ", '" + obj.getPort() + "'"
                    + ", '" + obj.getSens() + "'"
                    + ", " + obj.getIsActif() + ");";           
                     
            this.connect.createStatement( 
                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_READ_ONLY
            ).executeUpdate(requete);
            check = true;
        
        } catch (SQLException ex) {
            ex.printStackTrace();           
            System.out.println("Problème insert(ParametrageInterop obj)");
            check = false;
        } 
        return check;
    }

    @Override
    public boolean delete(ParametrageInterop obj) {
        boolean check;
        try {
            String requete = "delete ci " +
                            "from configuration_interfaces ci " +
                            "where ci.conf_id = " + obj.getConfId() + ";";       
                     
            this.connect.createStatement( 
                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_READ_ONLY
            ).executeUpdate(requete);
            check = true;
        
        } catch (SQLException ex) {
            ex.printStackTrace();           
            System.out.println("Problème delete(ParametrageInterop obj)");
            check = false;
        } 
        return check;
    }

    @Override
    public boolean update(ParametrageInterop obj){
        boolean check;
        try {
            String requete = "update dpi.CONFIGURATION_INTERFACES ci "
                    + "SET ci.app_id = '" + obj.getApplication().getAppId() 
                    + "', ci.conf_adresse_ip = '" + obj.getAdresseIp() 
                    + "', ci.conf_port = '" + obj.getPort() 
                    + "', ci.conf_sens = '" + obj.getSens()
                    + "', ci.conf_actif = " + obj.getIsActif()
                    + " "
                    + "where ci.conf_id = '" + obj.getConfId() + "';";
            this.connect.createStatement( 
                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_READ_ONLY
            ).executeUpdate(requete);
            check = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Problème boolean update(ParametrageInterop obj)");
            check = false;        
        }
        return check;
    }

    @Override
    public ParametrageInterop get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ParametrageInterop> getAll() {
        String requete =    "SELECT ci.conf_id, ci.app_id, ci.conf_adresse_ip, ci.conf_port, ci.conf_sens, app.app_libelle, app.app_description, app.app_code, ci.conf_actif " +
                            "FROM dpi.configuration_interfaces ci " +
                            "INNER JOIN dpi.application app ON app.app_id = ci.app_id;";
        
        try {
            List<ParametrageInterop> res = new ArrayList<>();
            ResultSet resultat = this.connect.createStatement( 
                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_READ_ONLY
            ).executeQuery(requete);
            while (resultat.next()) {

                res.add(new ParametrageInterop(
                    resultat.getInt("ci.conf_id"),
                    resultat.getString("conf_adresse_ip"),
                    resultat.getInt("conf_port"),
                    new Application(resultat.getInt("ci.app_id"), resultat.getString("app.app_code"), resultat.getString("app.app_libelle"), resultat.getString("app.app_description")),
                    resultat.getInt("ci.conf_sens"),
                    resultat.getBoolean("ci.conf_actif")
                ));
                
                
                System.out.println("1");

            }
            return res;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Problème ParametrageInterop getAll()");
            return null;
        } 
    }

    @Override
    public List<ParametrageInterop> getAll(String param) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
//    public ParametrageInterop getParametrageByApplicationEtSens(Application app, String sens){
//        String requete =    "SELECT iden_id, int_id, iden_login, iden_password  "
//                            + "FROM dpi.IDENTIFIANTS_CONNEXION "
//                            + "WHERE int_id = '" + intId + "';";
//        try {
//            Identifiant res = null;
//            ResultSet resultat = this.connect.createStatement( 
//                ResultSet.TYPE_SCROLL_INSENSITIVE, 
//                ResultSet.CONCUR_READ_ONLY
//            ).executeQuery(requete);
//            if(resultat.next()){
//                if(resultat.getString("iden_login") != null){
//                    res = (new Identifiant(  
//                            resultat.getInt("iden_id"),
//                            resultat.getInt("int_id"),
//                            resultat.getString("iden_login"),
//                            resultat.getString("iden_password")
//                     ));
//                } 
//            
//            }else {res = new Identifiant();}
//            
//            return res;
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            System.out.println("Probleme ParametrageInterop getParametrageByApplicationEtSens(Application app, String sens)");
//            return null;
//    }
    
}
