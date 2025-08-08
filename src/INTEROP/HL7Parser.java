package INTEROP;

import ca.uhn.hl7v2.parser.Parser;

import java.text.SimpleDateFormat;
import java.util.Date;

import NOYAU.patient.Patient;
import NOYAU.patient.Patient.Sexe;
import NOYAU.patient.PatientIdentifiant;
import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v231.message.ADT_A28;
import ca.uhn.hl7v2.model.v25.message.*;
import ca.uhn.hl7v2.model.v25.segment.*;
import ca.uhn.hl7v2.model.v25.datatype.*;

public class HL7Parser {

    HapiContext context = new DefaultHapiContext();
    String message;
    Message parsedMessage;
    Parser parser = context.getGenericParser();
    PID segmentPID;
    PV1 segmentPV1;

    public ADT_A01 getA01Structure(String message) {

        try {
            parsedMessage = parser.parse(message);
            return (ADT_A01) parsedMessage;
        } catch (HL7Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ADT_A05 getA05Structure(String message) {

        try {
            parsedMessage = parser.parse(message);
            return (ADT_A05) parsedMessage;
        } catch (HL7Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Message getStructure(String message) {

        try {
            parsedMessage = parser.parse(message);
            return parsedMessage;
        } catch (HL7Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean verifierStructure(String message) {
        try {
            parser.parse(message);
            return true;
        } catch (HL7Exception e) {
            return false;
        }

    }

    public PID getPID(Message messageStructure) {
        if (messageStructure.getName().equals("ADT_A05")) {
            segmentPID = ((ADT_A05) messageStructure).getPID();
        } else if (messageStructure.getName().equals("ADT_A01")) {
            segmentPID = ((ADT_A01) messageStructure).getPID();
        } else {

        }
        return segmentPID;
    }

    public String getIPP(PID segmentPIDPourIPP) {

        String IPP = "IPP non trouvé. ";

        // System.out.println("Nombre d'identifiants patient : " +
        // segmentPIDPourIPP.getPatientIdentifierList().length);

        for (CX iD : segmentPIDPourIPP.getPatientIdentifierList()) {
            if (iD.getIdentifierTypeCode().toString().equals("PI")) {
                IPP = iD.getIDNumber().getValue();
            }
        }
//        System.out.println("IPP trouvé : " + IPP);
        return IPP;
    }

    public Patient getPatient(String iPP, String codeTypeIdentifiant, String nomNaissance, String nomUsage, String prenom, String sexe,
            Date dateNaissance) {
        Patient patient = new Patient(nomNaissance, nomUsage, prenom, dateNaissance, Sexe.valueOf(sexe));
        patient.getLesIdentifiants().add(new PatientIdentifiant(iPP, codeTypeIdentifiant));

        return patient;
    }

    public String getSexe(PID segmentPID) {
        String sexe = segmentPID.getAdministrativeSex().toString();

        return sexe;
    }

    public Date getDateNaissance(PID segmentPID) throws DataTypeException {
        Date dateNaissance = segmentPID.getDateTimeOfBirth().getTime().getValueAsDate();
        return dateNaissance;
    }

    public String getAutoriteAffectation(PID segmentPID){
        String res = "";
        for (CX iD : segmentPID.getPatientIdentifierList()) {
            if (iD.getIdentifierTypeCode().toString().equals("PI")) {                
                res = iD.getCx4_AssigningAuthority().getNamespaceID().getValue();
            } 
        }
        return res;
    }
    
    public String getNomPatient(PID segmentPID, String typeNom) {

        XPN[] listeNoms = segmentPID.getPatientName();

        String nomFamille = "";

        for (XPN nom : listeNoms) {
            /*
             * switch (nom.getNameTypeCode().getValue()) {
             * case "L":
             * nomNaissance = nom.getFamilyName().getSurname().getValue();
             * break;
             * case "D":
             * nomUsage = nom.getFamilyName().getSurname().getValue();
             * break;
             * default:
             * 
             * break;
             * }
             */

            if (nom.getNameTypeCode().toString().equals(typeNom)) {
                nomFamille = nom.getFamilyName().getSurname().getValue();
            }
        }

        return nomFamille;

    }

    public String getPrenom(PID segmentPID) {
        XPN[] listeNoms = segmentPID.getPatientName();

        String prenom = "";

        for (XPN noms : listeNoms) {
            /*
             * switch (nom.getNameTypeCode().getValue()) {
             * case "L":
             * nomNaissance = nom.getFamilyName().getSurname().getValue();
             * break;
             * case "D":
             * nomUsage = nom.getFamilyName().getSurname().getValue();
             * break;
             * default:
             * 
             * break;
             * }
             */

            if (noms.getNameTypeCode().toString().equals("L")) {
                prenom = noms.getGivenName().toString();
            }

            if (noms.getNameTypeCode().toString().equals("D") && prenom == "") {
                prenom = noms.getGivenName().toString();
            }
        }

        return prenom;
    }

    public PV1 getPV1(Message messageStructure) throws HL7Exception {

        if (messageStructure.getName().equals("ADT_A05")) {
            ADT_A05 messageA05 = (ADT_A05) messageStructure;
            System.out.println(messageA05.printStructure());
            return messageA05.getPV1();
        } else {
            ADT_A01 messageA01 = (ADT_A01) messageStructure;
            System.out.println(messageA01.printStructure());
            return messageA01.getPV1();
        }
    }

    public String getIEP(PV1 segmentPV1) {

        return segmentPV1.getVisitNumber().getIDNumber().getValue();
    }

    public String getDateLisible(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        return dateFormat.format(date);
    }
    


}
