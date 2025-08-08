/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.SQL;

import DAO.DAO;
import NOYAU.structure.Role;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author antoi
 */
public class RoleDAO extends DAO<Role>{

    public RoleDAO(Connection connect) {
        super(connect);
    }

    @Override
    public boolean insert(Role obj) {
        boolean check;
        try {
            String requete = "INSERT INTO dpi.ROLE (rol_code, rol_libelle) "
                    + "VALUES('" + obj.getCode() + "', '" + obj.getLibelle() + "');";
             this.connect.createStatement( 
                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_READ_ONLY
            ).executeUpdate(requete);
            check = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Problème ajoutRole");
            check = false;
        } 
        return check;
    }

    @Override
    public boolean delete(Role obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Role obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Role get(int id) {
        String requete;
        requete = "select rol_id, rol_code, rol_libelle "
                + "from dpi.ROLE "
                + "where rol_id = '" + id + "';";
        try {
            ResultSet resultat = this.connect.createStatement( 
                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_READ_ONLY
            ).executeQuery(requete);
            resultat.next();
            Role rol = new Role(
                    resultat.getInt("rol_id")
                    , resultat.getString("rol_code")
                    , resultat.getString("rol_libelle"));        
            return rol;
        } catch (SQLException ex) {
            System.out.println("Role get(int id)");
            ex.printStackTrace();
            return null;
        } 
    }

    @Override
    public List<Role> getAll() {
        String requete = "SELECT rol_id, rol_code, rol_libelle "
                + "FROM dpi.ROLE r;";
        try {
            List<Role> res = new ArrayList<>();
            ResultSet resultat = this.connect.createStatement( 
                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_READ_ONLY
            ).executeQuery(requete);
            while (resultat.next()) {

                res.add(new Role(
                    resultat.getInt("rol_id"),
                    resultat.getString("rol_code"),
                    resultat.getString("rol_libelle")));
            }
            return res;           
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Problème getLesRoles()");
            return null;
        } 
    }

    @Override
    public List<Role> getAll(String param) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public Role get(String code){
        String requete;
        requete = "select rol_id, rol_code, rol_libelle "
                + "from dpi.ROLE "
                + "where rol_code = '" + code + "';";
        try {
            ResultSet resultat = this.connect.createStatement( 
                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_READ_ONLY
            ).executeQuery(requete);
            resultat.next();
            Role rol = new Role(
                    resultat.getInt("rol_id")
                    , resultat.getString("rol_code")
                    , resultat.getString("rol_libelle"));        
            
            System.out.println("3");
            return rol;
        } catch (SQLException ex) {
            System.out.println("probleme getRole");
            ex.printStackTrace();
            return null;
        } 
    }
    
}
