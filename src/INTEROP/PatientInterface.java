package INTEROP;

import javax.swing.*;
import java.awt.*;

import java.util.List;


public class PatientInterface {

    PatientDB patientDB = new PatientDB(); 
    PatientI patient = new PatientI();
    
    //écran de création d'un nouveau patient
    public JPanel creerPatient(){
        JPanel jpanelReturn = new JPanel(new BorderLayout()); 
        jpanelReturn.setSize(300,300);
        return jpanelReturn; 
    }

    //tableau d'affichage des patients connus
    public String afficherPatient(){
        List<PatientI> patients = patientDB.SelectPatients(); 

        int i = 0;

        int nombrePatientsRecus = patients.size();

        String listeIPP = "Nous avons reçu " + nombrePatientsRecus + " patients. \r\n";

        if (patients.isEmpty() == false) {

            for (PatientI p : patients) {
                listeIPP += "Patient n°" + (i + 1) + " : " + patient.getResumePatient(p);
                i++;
            }

        } else {
            listeIPP = "Pas de patients reçus.";
        }

        return listeIPP; 
    }

}
