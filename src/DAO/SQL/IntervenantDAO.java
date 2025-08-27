/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.SQL;

import DAO.DAO;
import NOYAU.structure.Identifiant;
import NOYAU.structure.Intervenant;
import NOYAU.structure.Role;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author antoi
 */
public class IntervenantDAO extends DAO<Intervenant>{

    public IntervenantDAO(Connection connect) {
        super(connect);
    }
    
    @Override
    public boolean insert(Intervenant obj) {
        boolean check;
        try {
            String requete = "INSERT INTO dpi.INTERVENANT (int_matricule, int_nom, int_prenom, int_supprime) "
                    + "VALUES('" + obj.getMatricule() + "', '" + obj.getNom() + "', '" + obj.getPrenom() + "', 0);";           
                     
            this.connect.createStatement( 
                ResultSet.TYPE_SCROLL_INSENSITIVE,  
                ResultSet.CONCUR_READ_ONLY
            ).executeUpdate(requete);
            check = true;
        
        } catch (SQLException ex) {
            ex.printStackTrace();           
            System.out.println("Problème ajouterIntervenant");
            check = false;
        } 
        return check;
    }

    @Override
    public boolean delete(Intervenant obj) {
        boolean check;
        try {
            String requete = "DELETE "
                    + "FROM dpi.INTERVENANT "
                    + "WHERE int_matricule = '" + obj.getMatricule() +  "';";
            this.connect.createStatement( 
                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_READ_ONLY
            ).executeUpdate(requete);
            check = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Problème supprimerIntervenant()");
            check = false;
        
        }
        return check;
    }

    @Override
    public boolean update(Intervenant obj) {
        boolean check;
        try {
            String requete = "update dpi.INTERVENANT i "
                    + "SET i.rol_id = (select rol_id from dpi.ROLE where rol_code = '" + obj.getRole().getCode() + "') "
                    + "where i.int_matricule = '" + obj.getMatricule() + "';";
            this.connect.createStatement( 
                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_READ_ONLY
            ).executeUpdate(requete);
            check = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Problème updateIntervenant()");
            check = false;
        }
        return check;
    }

    @Override
    public Intervenant get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Intervenant> getAll() {
        String requete = "SELECT distinct i.int_id, int_matricule, int_nom, int_prenom, r.rol_id, r.rol_code, r.rol_libelle, ic.iden_id, ic.iden_login, ic.iden_password "
                + "FROM dpi.INTERVENANT i "
                + "LEFT JOIN dpi.IDENTIFIANTS_CONNEXION ic on i.int_id = ic.int_id "
                + "LEFT JOIN dpi.ROLE r on i.rol_id = r.rol_id;";
        try {
            List<Intervenant> res = new ArrayList<>();
            ResultSet resultat = this.connect.createStatement( 
                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_READ_ONLY
            ).executeQuery(requete);
            while (resultat.next()) {
                if(resultat.getString("r.rol_code") != null){
                    res.add(new Intervenant(
                        resultat.getInt("int_id"),
                        resultat.getString("int_matricule"),
                        resultat.getString("int_nom"),
                        resultat.getString("int_prenom"),
                        new Role(resultat.getInt("r.rol_id")
                                , resultat.getString("r.rol_code")
                                , resultat.getString("r.rol_libelle")
                            ),
                        new Identifiant(resultat.getInt("ic.iden_id")
                                        , resultat.getInt("i.int_id")
                                        , resultat.getString("ic.iden_login")
                                        , resultat.getString("ic.iden_password"))
                        )
                        
                    );
                    System.out.println("1");
                } else {
                    res.add(new Intervenant(
                        resultat.getInt("int_id"),    
                        resultat.getString("int_matricule"),
                        resultat.getString("int_nom"),
                        resultat.getString("int_prenom")
//                        , new Identifiant(resultat.getString("ic.iden_login"), resultat.getString("ic.iden_password"))
                    )
                    );
                    
                    System.out.println("2");
                }
            }
            return res;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Problème getLesIntervenants()");
            return null;
        } 
    }

    @Override
    public List<Intervenant> getAll(String param) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    public boolean updateRoleIntervenant(Intervenant inter) {
        boolean check;
        try {
            String requete = "update dpi.INTERVENANT i "
                    + "SET i.rol_id = (select rol_id from dpi.ROLE where rol_code = '" + inter.getRole().getCode() + "') "
                    + "where i.int_matricule = '" + inter.getMatricule() + "';";
            this.connect.createStatement( 
                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_READ_ONLY
            ).executeUpdate(requete);
            check = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Problème modifierRoleIntervenant()");
            check = false;        
        }
        return check;
    }
    
    public Role getRolByIntId(int intId){
        String requete;
        requete = "select r.rol_id, r.rol_code, r.rol_libelle "
                + "from dpi.INTERVENANT i "
                + "INNER JOIN dpi.ROLE r on i.rol_id = r.rol_id "
                + "where i.int_id = " + intId + ";";
        try {
            ResultSet resultat = this.connect.createStatement( 
                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_READ_ONLY
            ).executeQuery(requete);
            resultat.next();
            Role rol = new Role(resultat.getInt("r.rol_id")
                    , resultat.getString("r.rol_code")
                    , resultat.getString("r.rol_libelle"));
            return rol;
        } catch (SQLException ex) {
            System.out.println("probleme Role getRolByIntId(int intId)");
            ex.printStackTrace();
            return null;
        }
    }
    
    public Intervenant get(String matricule) {
        String requete;
        requete = "select int_matricule, int_nom, int_prenom "
                + "from dpi.INTERVENANT "
                + "where int_matricule = '" + matricule + "';";
        try {
            ResultSet resultat = this.connect.createStatement( 
                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_READ_ONLY
            ).executeQuery(requete);
            resultat.next();
            Intervenant inter = new Intervenant(resultat.getString("int_matricule"), resultat.getString("int_nom"), resultat.getString("int_prenom"));
            return inter;
        } catch (SQLException ex) {
            System.out.println("probleme getIntervenant");
            ex.printStackTrace();
            return null;
        }
    }
    
    public Intervenant getIntervenantParLogin(String login){
        String requete;
        requete = "select idc.iden_id, idc.int_id, idc.iden_password, inter.int_matricule, inter.int_nom, inter.int_prenom "
                + "from dpi.IDENTIFIANTS_CONNEXION idc "
                + "inner join dpi.INTERVENANT inter on idc.int_id = inter.int_id "
                + "where iden_login = '" + login + "';";
        System.out.println("getIntervenantParLogin getAll()");
        try {
            ResultSet resultat = this.connect.createStatement( 
                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_READ_ONLY
            ).executeQuery(requete);
            resultat.next();
            Intervenant inter = new Intervenant(resultat.getInt("idc.int_id"), resultat.getString("inter.int_matricule"), resultat.getString("inter.int_nom"), resultat.getString("inter.int_prenom"));
            inter.setIdentifiants(new Identifiant(resultat.getInt("idc.iden_id"), resultat.getInt("idc.int_id"), login, resultat.getString("idc.iden_password")));
            return inter;
        } catch (SQLException ex) {
            System.out.println("probleme getIntervenantParLogin");
            ex.printStackTrace();
            return null;
        }
    }

}
