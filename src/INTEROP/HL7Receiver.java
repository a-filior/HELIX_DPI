package INTEROP;

import NOYAU.patient.Patient;
import NOYAU.patient.Patient.Sexe;
import NOYAU.patient.PatientIdentifiant;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.app.*;
import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v25.segment.PID;
import ca.uhn.hl7v2.protocol.ReceivingApplication;
import ca.uhn.hl7v2.protocol.ReceivingApplicationException;


public class HL7Receiver {

    private int port;
    private boolean useTLS;
    private boolean enEcoute = false;

    HapiContext context = new DefaultHapiContext();
    HL7Service server;

    List<Message> messagesRecus = new ArrayList<>();
    List<Patient> patients = new ArrayList<>();

    PatientDB patientDB = new PatientDB();

    HL7Parser parser = new HL7Parser();

    public List<Message> getMessagesRecus() {
        return messagesRecus;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public HL7Receiver(int port) {
        this.port = port;
    }

    ReceivingApplication application = new ReceivingApplication() {

        /**
         * {@inheritDoc}
         * GGAS : cette méthode est appelée par le serveur qui écoute à chaque message
         * reçu, comme la méthode processMessage.
         * Je préfère ne rien faire et utiliser la seconde méthode processMessage.
         */
        public boolean canProcess(Message theIn) {
            return true;
        }

        /**
         * {@inheritDoc}
         * GGAS : on doit réécrire la méthode pour savoir ce qu'on fait avec theMessage.
         * L'appel de la méthode n'est pas à faire, elle est faite par le serveur qui
         * écoute à chaque message reçu.
         * Moi je l'ajoute à la liste des messages reçus, que je peux récupérer avec un
         * get.
         * 
         * @throws HL7Exception
         */
        public Message processMessage(Message theMessage, Map<String, Object> theMetadata) throws HL7Exception {

            System.out.println(theMessage.printStructure());
            
            Message acquittement = null;
            // Now generate a simple acknowledgment message and return it
            try {
                acquittement = theMessage.generateACK();
            } catch (IOException | HL7Exception e) {
                e.printStackTrace();
            }

            // messagesRecus.add(theMessage);
            addPatient(theMessage);

            return acquittement;

        }

    };

    public synchronized void initiateServer(int port, boolean useTLS)
            throws InterruptedException, HL7Exception, ReceivingApplicationException {

        this.port = port;
        server = context.newServer(port, useTLS);

        server.registerApplication("*", "*", application);

        server.start();

        enEcoute = true;

    }

    public void addPatient(Message messageRecu) {

        HL7Parser parser = new HL7Parser();

        PID segmentPID = parser.getPID(messageRecu);
        String iPP = parser.getIPP(segmentPID);
        System.out.println("------------------------------------------------------------------");
        System.out.println(messageRecu.toString());
        System.out.println("------------------------------------------------------------------");
        Patient p = new Patient();
        PatientIdentifiant pidt = new PatientIdentifiant(parser.getIPP(segmentPID), parser.getAutoriteAffectation(segmentPID),"PI");
//        p.setIPP(iPP);

        try {
            p.setNomNaissance(parser.getNomPatient(segmentPID, "L"));
            p.setNomUsuel(parser.getNomPatient(segmentPID, "D"));
            p.setPrenomPrincipal(parser.getPrenom(segmentPID));
            p.setSexe(Sexe.valueOf(parser.getSexe(segmentPID)));
            p.setDateNaissance(parser.getDateNaissance(segmentPID));
//            p = new Patient(parser.getNomPatient(segmentPID, "L")
//                , parser.getNomPatient(segmentPID, "D")
//                , parser.getPrenom(segmentPID)
//                , parser.getDateNaissance(segmentPID)
//                , Sexe.valueOf(parser.getSexe(segmentPID)));
            p.getLesIdentifiants().add(pidt);
            
        } catch (DataTypeException e) {
            e.printStackTrace();
        }

        // patients.add(p);

//        try {
            System.out.print("patient : ");
            System.out.println(p.display());

//        } 
//        catch (SQLException e) {
//            e.printStackTrace();
//
//            System.out.println("Erreur retournée : " + e.getMessage() + "\r\n");
//        }

    }

    public String lancerEcoute() {

        boolean testEcoute = getEnEcoute();
        String result;

        if (testEcoute) {
            result = ("Le serveur est déjà en écoute sur le port " + getPort() + ".");
        } else {

            try {
                initiateServer(this.port, false);
                result = ("Ecoute sur le port " + getPort() + ".");

            } catch (HL7Exception | InterruptedException | ReceivingApplicationException e) {
                e.printStackTrace();
                result = "Erreur d'écoute.";
            }
        }

        return result;

    }

    public String arreterEcouteFonction() {

        boolean testEcoute = getEnEcoute();
        String result; 

        if (testEcoute) {
            try {
                closeServer();
                result =("Ecoute terminée.");
            } catch (IOException e) {
                e.printStackTrace();
                result = "Erreur de fermeture d'écoute. ";
            }
        } else {
            result =("Petit malin, le serveur n'est pas en écoute.");
        }
        return result; 

    }

    public void closeServer() throws IOException {
        server.stop();

        enEcoute = false;
    }

    public int getPort() {
        return port;
    }

    public boolean isUseTLS() {
        return useTLS;
    }

    public boolean getEnEcoute() {
        return enEcoute;
    }

}
