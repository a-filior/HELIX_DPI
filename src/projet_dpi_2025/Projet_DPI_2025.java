/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projet_dpi_2025;


import BACASABLE.Calculator;
import BACASABLE.CalculatorImpl;
import BACASABLE_UI.Fenetre;
import DAO.AbstractDAOFactory;
import DAO.SQL.PatientDAO;
import INTEROP.HL7Receiver;
import INTEROP.HL7Sender;
import NOYAU.patient.Patient;
import NOYAU.patient.PatientIdentifiant;
import NOYAU.structure.Intervenant;
import NOYAU.structure.Role;
import BACASABLE_UI.Fenetre2;
import ca.uhn.hl7v2.model.Message;
import java.io.IOException;
import java.util.List;
import BACASABLE_UI.Fenetre1;
import INTEROP.FACTORIES.ADTFactory;
import INTEROP.FACTORIES.ADT_v25;
import INTEROP.HL7Services;
import NOYAU.configuration.InitialisationApplication;
import NOYAU.configuration.ParametrageInterop;
import NOYAU.patient.Patient.Sexe;
import UI.Frames.FenetreConnexion;
import UI.Frames.PortailAdministrateur;
import UI.Frames.PortailBDA;
import ca.uhn.hl7v2.model.AbstractMessage;
import ca.uhn.hl7v2.model.v23.message.ADT_A01;
import javax.swing.JOptionPane;

/**
 *
 * @author antoi
 */
public class Projet_DPI_2025 {

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
     
        //FenetreConnexion fc = new FenetreConnexion();
        //fc.setVisible(true);

        
        
        
        
//    Fenetre pf = new Fenetre();


//Intervenant inter = new Intervenant("1000001", "FILIOR", "ANTOINE", new Role("admindev"));
//PortailBDA np = new PortailBDA(inter);
//np.setVisible(true);


//        new Role("admindev","Administrateur Développeur").ajouterRole();
//        new Role("adminsupport","Administrateur Support").ajouterRole();
//        new Role("secr","Secrétaire").ajouterRole();
//        new Role("ide","IDE").ajouterRole();
//        new Role("medecin","Médecin").ajouterRole();
        
//        new Intervenant("1000001", "FILIOR", "ANTOINE", new Role("admindev")).ajouterIntervenant();
//        new Intervenant("1000002", "GASPARD", "GUILLAUME", new Role("admindev")).ajouterIntervenant();
//        new Intervenant("1000003", "IV", "ANNE-LISE", new Role("admindev")).ajouterIntervenant();
//        new Intervenant("1000004", "BERNARD", "ARMELLE", new Role("admindev")).ajouterIntervenant();
//        new Intervenant("1000005", "FARHI", "FARES").ajouterIntervenant();

//        new Intervenant("1000005").modifierRoleIntervenant(new Role("admindev"));

//        new Intervenant("1000005").supprimerIntervenant();

//        JOptionPane jop1, jop2, jop3;
// 
////Boîte du message d'information
//jop1 = new JOptionPane();
//jop1.showMessageDialog(null, "Message informatif", "Information", JOptionPane.INFORMATION_MESSAGE);
//		
////Boîte du message préventif
//jop2 = new JOptionPane();
//jop2.showMessageDialog(null, "Message préventif", "Attention", JOptionPane.WARNING_MESSAGE);
//		
////Boîte du message d'erreur
//jop3 = new JOptionPane();
//jop3.showMessageDialog(null, "Message d'erreur", "Erreur", JOptionPane.ERROR_MESSAGE);



//        Intervenant interAntoine = new Intervenant("1000001");
//        System.out.println(interAntoine.getNom() + " " + interAntoine.getPrenom());



        //daoIntervenant.ajouterIntervenant(inter1);


//        List<Intervenant> lesIntervenants = Intervenant.getLesIntervenants();
//        List<Role> lesRoles = Role.getLesRoles();

//        lesIntervenants.forEach(i -> {
//                if(i.getRole() != null){
//                    System.out.println(i.getMatricule() + " : " + i.getNom() + " " + i.getPrenom() + " -> " + i.getRole().getLibelle());
//                } else {
//                    System.out.println(i.getMatricule() + " : " + i.getNom() + " " + i.getPrenom() + " -> INTERVENANT SANS ROLE" );
//                }
//        });


//        lesRoles.forEach(r -> {
//           System.out.println(r.getCode() + " - " + r.getLibelle());
//        });

//        List<PatientIdentifiant> lesPID = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY).getPatientIdentifiantDAO().getAll("10000001");
//
//        lesPID.forEach(pid -> {
//           System.out.println(pid.toString());
//        });
        
        System.out.println(Patient.getPatient("10000001","PI").display());

        
        String message = "MSH|^~\\&|ADT_MAGAM|MAGAM|GAM|GAM|20240205161849||ADT^A28^ADT_A05|20240205161849463|P|2.5^FRA^2.6|||||FRA|8859/1|FRA\n" +
"EVN||20240205161849||||20240205161849\n" +
"PID|||30340694^^^MAGAM^PI||AUBRAC^MICHELLE^^^Mme^^L||19720206000000|F|||106 RUE JOUVENET^^ROUEN^^76000^FRA^H|||||||||||||||FRA^FRANCE^ISO 3166 alpha-3||FRA^FRANCE^ISO 3166 alpha-3||||PROV\n" +
"PV1||N";
        
        InitialisationApplication.initialiserLesApplications();
////
        HL7Sender sender = new HL7Sender();
        sender.sendMessage(message, "localhost", 4242, false);  
        
        
        
//        HL7Services.sendADT_A01(Patient.getPatient("10000001","PI")
//                , InitialisationApplication.paramOUT.get("551").getAdresseIp()
//                , InitialisationApplication.paramOUT.get("551").getPort());

//        System.out.println(InitialisationApplication.TEST);
//        InitialisationApplication.TEST = "Changement";
//        System.out.println(InitialisationApplication.TEST);


        

        
        
//        AbstractMessage a = ADTFactory.getADTFactory(ADTFactory.v23).getA01();
        
//        System.out.println(ADTFactory.getADTFactory(ADTFactory.v23).getA01().getClass());
                
//        ADT_A01 b = ADTFactory.getADTFactory(ADTFactory.v23).getA01();
        
//        ADT_v25 av25 = (ADT_v25) ADTFactory.getADTFactory(ADTFactory.v25);
         


    }
}
