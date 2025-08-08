/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.SQL;

import DAO.AbstractDAOFactory;
import DAO.DAO;
import NOYAU.patient.Patient;
import NOYAU.patient.Patient.Sexe;
import NOYAU.patient.PatientIdentifiant;
import NOYAU.structure.Identifiant;
import NOYAU.structure.Intervenant;
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
public class PatientDAO extends DAO<Patient>{

    public PatientDAO(Connection connect) {
        System.out.println("constructeur PatientDAO");
        super(connect);
    }

    @Override
    public boolean insert(Patient obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(Patient obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Patient obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Patient get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Patient> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Patient> getAll(String param) {
        String requete = "select pat_id, pat_nom_usuel, pat_nom_naissance, pat_prenom_principal, pat_date_naissance, pat_sexe, pat_date_deces, pat_cp_naissance, pat_nationalite_insee "
                        + "from dpi.PATIENT "
                        + "where pat_supprime = 0 "
                        + "and (pat_nom_usuel like '" + param + "%' " 
                        + "or pat_nom_naissance like '" + param + "%');";
        try {
            List<Patient> res = new ArrayList<>();
            ResultSet resultat = this.connect.createStatement( 
                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_READ_ONLY
            ).executeQuery(requete);
            while (resultat.next()) {

                res.add(new Patient(
                        resultat.getInt("pat_id"),
                        resultat.getString("pat_nom_naissance"),
                        resultat.getString("pat_nom_usuel"),
                        resultat.getString("pat_prenom_principal"),
                        resultat.getDate("pat_date_naissance"),
                        Patient.Sexe.valueOf(resultat.getString("pat_sexe")),
                        resultat.getString("pat_cp_naissance"),
                        resultat.getString("pat_nationalite_insee"),
                        "",
                        resultat.getDate("pat_date_deces"),
                        null
                        
                    )

                );
                System.out.println("1");

            }
            return res;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Probleme : List<Patient> getAll(String param)");
            return null;
        } 
    }
    
    public Patient get(String IPP, String codeType){
        String requete;
        requete = "select pat.pat_nom_usuel, pat.pat_nom_naissance, pat.pat_prenom_principal, pat.pat_date_naissance, pat.pat_sexe "
                + "from dpi.PATIENT pat "
                + "inner join dpi.PATIENT_IDENTIFIANT pid on pat.pat_id = pid.pat_id "
                + "inner join dpi.PATIENT_IDENTIFIANT_TYPE pidt on pid.pidt_id = pidt.pidt_id "
                + "where pid.pid_valeur = '" + IPP + "' "
                + "and pidt.pidt_code = '" + codeType + "';";
        try {
            ResultSet resultat = this.connect.createStatement( 
                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_READ_ONLY
            ).executeQuery(requete);
            resultat.next();            
            Patient pat = new Patient(resultat.getString("pat.pat_nom_usuel")
                    , resultat.getString("pat.pat_nom_naissance")
                    , resultat.getString("pat.pat_prenom_principal") 
                    , resultat.getDate("pat.pat_date_naissance")
                    , Sexe.valueOf(resultat.getString("pat.pat_sexe"))
                    , AbstractDAOFactory.getFactory(AbstractDAOFactory.SQL_DAO_FACTORY).getPatientIdentifiantDAO().getAll(IPP));
            return pat;
        } catch (SQLException ex) {
            System.out.println("probleme getPatient(IPP, codeType)");
            ex.printStackTrace();
            return null;
        }
    }
    
}
