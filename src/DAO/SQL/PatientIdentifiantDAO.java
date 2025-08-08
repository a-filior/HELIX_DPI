/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.SQL;

import DAO.DAO;
import NOYAU.patient.PatientIdentifiant;
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
public class PatientIdentifiantDAO extends DAO<PatientIdentifiant>{

    public PatientIdentifiantDAO(Connection connect) {
        System.out.println("constructeur PatientIdentifiantDAO");
        super(connect);
    }

    @Override
    public boolean insert(PatientIdentifiant obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(PatientIdentifiant obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(PatientIdentifiant obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PatientIdentifiant get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<PatientIdentifiant> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<PatientIdentifiant> getAll(String param) {
        String requete = "SELECT pid.pid_valeur, pid.pid_autorite, pidt.pidt_code, pidt.pidt_libelle "
                + "FROM dpi.PATIENT_IDENTIFIANT pid "
                + "INNER JOIN dpi.PATIENT_IDENTIFIANT_TYPE pidt on pid.pidt_id = pidt.pidt_id "
                + "INNER JOIN (SELECT pat_id FROM dpi.PATIENT_IDENTIFIANT pid "
                    + "INNER JOIN dpi.PATIENT_IDENTIFIANT_TYPE pidt on pid.pidt_id = pidt.pidt_id and pidt.pidt_code = 'PI' "
                    + "WHERE pid.pid_valeur = '" + param + "'"
                + ") patid on pid.pat_id = patid.pat_id;";
        try {
            List<PatientIdentifiant> res = new ArrayList<>();
            ResultSet resultat = this.connect.createStatement( 
                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_READ_ONLY
            ).executeQuery(requete);
            while (resultat.next()) {
                    res.add(new PatientIdentifiant(
                        resultat.getString("pid.pid_valeur")
                        , resultat.getString("pid.pid_autorite")
                        , resultat.getString("pidt.pidt_code") 
                        , resultat.getString("pidt.pidt_libelle")
                    ));                    
                }
            return res;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Problème  PatientIdentifiant getAll(param)");
            return null;
        } 
    }
    
    public PatientIdentifiant getIPPByPatId(int patId){
        String requete = "select pid.pid_id, pid.pat_id, pid.pid_autorite, pid.pid_valeur, pidt.pidt_id, pidt.pidt_code, pidt.pidt_libelle " +
                        "from dpi.patient_identifiant pid " +
                        "inner join dpi.patient_identifiant_type pidt on pid.pidt_id = pidt.pidt_id " +
                        "where pidt.pidt_code = 'PI' " +
                        "and pat_id = " + patId + ";";
        try {
            PatientIdentifiant res = null;
            ResultSet resultat = this.connect.createStatement( 
                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_READ_ONLY
            ).executeQuery(requete);
            if (resultat.next()){
                res = new PatientIdentifiant(
                    resultat.getString("pid.pid_valeur")
                    , resultat.getString("pid.pid_autorite")
                    , resultat.getString("pidt.pidt_code") 
                    , resultat.getString("pidt.pidt_libelle")
                );     
            }
                
            return res;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Problème PatientIdentifiant getIPPByPatId(int patId)");
            return null;
        } 
    }
    
    
}
