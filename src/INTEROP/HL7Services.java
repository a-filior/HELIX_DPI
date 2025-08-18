///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package INTEROP;
//
///**
// *
// * @author antoi
// */
//
//import NOYAU.patient.Patient;
//import NOYAU.patient.Patient.Sexe;
//import ca.uhn.hl7v2.DefaultHapiContext;
//import ca.uhn.hl7v2.HL7Exception;
//import ca.uhn.hl7v2.HapiContext;
//import ca.uhn.hl7v2.app.Connection;
//import ca.uhn.hl7v2.app.HL7Service;
//import ca.uhn.hl7v2.app.Initiator;
//import ca.uhn.hl7v2.llp.LLPException;
//import ca.uhn.hl7v2.model.Message;
//import ca.uhn.hl7v2.model.v25.message.ACK;
//import ca.uhn.hl7v2.model.v25.message.ADT_A01;
//import ca.uhn.hl7v2.model.v25.segment.EVN;
//import ca.uhn.hl7v2.model.v25.segment.MSH;
//import ca.uhn.hl7v2.model.v25.segment.PID;
//import ca.uhn.hl7v2.parser.Parser;
//import ca.uhn.hl7v2.protocol.ReceivingApplication;
//import ca.uhn.hl7v2.protocol.ReceivingApplicationException;
//import java.io.IOException;
//import java.util.Date;
//import java.util.Map;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// *
// * @author Adrien Foucart
// */
//public class HL7Services {
//    
//    private HL7Service server = null;
//    private final HapiContext ctxt = new DefaultHapiContext();
//    
//    public HL7Services(){
//        startServer();
//    }
//
//    public boolean isListening() {
//        return (server != null && server.isRunning());
//    }
//    
//    public void stopServer(){
//        if(isListening()){
//            server.stop();
//        }
//    }
//    
//    public void startServer(){
//        if(!isListening()){
////            System.out.println("Starting HL7 Server listening on port " + GlobalConfig.HL7_LISTENING_PORT);
////            server = ctxt.newServer(GlobalConfig.HL7_LISTENING_PORT, GlobalConfig.HL7_TLS);
//
//            ReceivingApplication handler = new ADTReceiverApplication();
////            server.registerApplication("ADT", "A01", handler);
//            try {
//                server.startAndWait();
//            } catch (InterruptedException ex) {
//                Logger.getLogger(HL7Services.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            
//            System.out.println("Server started.");
//        }
//    }
//    
//    
//    
//    public static boolean sendADT_A01(Patient patient, String host, int port){
//        try {
//            ADT_A01 adt = new ADT_A01();
//            adt.initQuickstart("ADT", "A01", "P");
//            
//            MSH msh = adt.getMSH();
//            msh.getSendingApplication().getNamespaceID().setValue("HIS");
////            msh.getMessageControlID().setValue(String.valueOf(GlobalConfig.getNextADTMessageID()));
//            msh.getMessageControlID().setValue("TEST_ID_VALUE");
//            
//            System.out.println(adt.getMessage());
//            
//            EVN evn = adt.getEVN();
//            
//            
////            Date date = new Date().;
//            evn.getEvn2_RecordedDateTime().getTime().setValue("20240205161849");
//            evn.getEvn6_EventOccurred().getTime().setValue("20240205161849");
//            
//            
//            PID pid = adt.getPID();
//            
//            pid.getPid3_PatientIdentifierList(0).getIDNumber().setValue(patient.getLesIdentifiants().get(0).getValeur());
//            pid.getPid3_PatientIdentifierList(0).getAssigningAuthority().getNamespaceID().setValue(patient.getLesIdentifiants().get(0).getAuthoriteAffectation());
//            pid.getPid3_PatientIdentifierList(0).getIdentifierTypeCode().setValue(patient.getLesIdentifiants().get(0).getTypeCode());
//            
//            pid.getPid3_PatientIdentifierList(1).getIDNumber().setValue(patient.getLesIdentifiants().get(1).getValeur());
//            pid.getPid3_PatientIdentifierList(1).getAssigningAuthority().getNamespaceID().setValue(patient.getLesIdentifiants().get(1).getAuthoriteAffectation());
//            pid.getPid3_PatientIdentifierList(1).getIdentifierTypeCode().setValue(patient.getLesIdentifiants().get(1).getTypeCode());
//            
//            pid.getPatientName(0).getFamilyName().getFn1_Surname().setValue(patient.getNomNaissance());
//            pid.getPatientName(0).getGivenName().setValue(patient.getPrenomPrincipal());
//            pid.getPatientName(0).getNameTypeCode().setValue("L");
//            pid.getPatientName(1).getFamilyName().getFn1_Surname().setValue(patient.getNomUsuel());          
//            pid.getPatientName(1).getGivenName().setValue(patient.getPrenomPrincipal());
//            pid.getPatientName(1).getNameTypeCode().setValue("D");
//            pid.getDateTimeOfBirth().getTime().setValue(patient.getDateNaissance());
//            pid.getPid8_AdministrativeSex().setValue(patient.getSexe().toString());
////            pid.getPhoneNumberHome(0).getAnyText().setValue(patient.getPhonenumber());
//            
//            HapiContext ctxt = new DefaultHapiContext();
//            Parser parser = ctxt.getXMLParser();
//            String encoded = parser.encode(adt);
//            
//            Connection conn = ctxt.newClient(host, port, false);
//            Initiator initiator = conn.getInitiator();
//            ACK response = (ACK) initiator.sendAndReceive(adt);
//            
//            System.out.print("SEND : ");
//            System.out.println(adt.getMessage());
//            conn.close();
//            return response.getMSA().getAcknowledgmentCode().getValue().equalsIgnoreCase("AA");
//            
////            return true;
//        } catch (HL7Exception | IOException | LLPException ex) {
//            Logger.getLogger(HL7Services.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("test");
//        }
//        
//        return false;
//    }
//
//    private class ADTReceiverApplication implements ReceivingApplication {
//        
//        private final EntityManagerFactory emfac = Persistence.createEntityManagerFactory("infoh400_PU");
//        private final PatientJpaController patientCtrl = new PatientJpaController(emfac);
//        private final PersonJpaController personCtrl = new PersonJpaController(emfac);
//        
//        public ADTReceiverApplication() {
//        }
//
//        
//
//        @Override
//        public Message processMessage(Message msg, Map<String, Object> map) throws ReceivingApplicationException, HL7Exception {
//            ADT_A01 t = (ADT_A01) msg;
//            String encodedMessage = ctxt.getPipeParser().encode(t);
//            System.out.println("Received:");
//            System.out.println(encodedMessage);
//            
//            PatientI pat = new PatientI();
//            
//            pat.setNomNaissance(t.getPID().getPatientName(0).getFamilyName().getValue());
//            pat.setNomUsage(t.getPID().getPatientName(0).getGivenName().getValue());
//            pat.setDateNaissance(t.getPID().getDateOfBirth().getTimeOfAnEvent().getValueAsDate());
//            pat.setSexe(t.getPID().getSex().getValue());
//            
//            Person person = new Person();
//            person.setFamilyname(t.getPID().getPatientName(0).getFamilyName().getValue());
//            person.setFirstname(t.getPID().getPatientName(0).getGivenName().getValue());
//            person.setDateofbirth(t.getPID().getDateOfBirth().getTimeOfAnEvent().getValueAsDate());
//            
//            
//            Person duplicate = personCtrl.findDuplicate(person);
//            
//            if( duplicate == null ){
//                // No duplicate found: create new patient & person
//                PatientI patient = new PatientI();
//                patient.setIdperson(person);
//                patient.setPhonenumber(t.getPID().getPhoneNumberHome(0).getAnyText().getValue());
//                patient.setStatus("active");
//                
//                personCtrl.create(person);
//                patientCtrl.create(patient);
//            }
//            else {
//                if( duplicate.getPatient() == null ){
//                    System.out.println("Person already exists. Creating new patient");
//                    PatientI patient = new PatientI();
//                    patient.setIdperson(duplicate);
//                    patient.setPhonenumber(t.getPID().getPhoneNumberHome(0).getAnyText().getValue());
//                    patient.setStatus("active");
//                    patientCtrl.create(patient);
//                }
//                else {
//                    System.out.println("Person already exists. Updating patient.");
//                    PatientI patient = duplicate.getPatient();
//                    patient.setPhonenumber(t.getPID().getPhoneNumberHome(0).getAnyText().getValue());
//
//                    try {
//                        patientCtrl.edit(patient);
//                    } catch (Exception ex) {
//                        Logger.getLogger(HL7Services.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//            }
//            
//            try {
//                return t.generateACK();
//            } catch (IOException e) {
//                throw new HL7Exception(e);
//            }
//        }
//
//        @Override
//        public boolean canProcess(Message msg) {
//            return true;
//        }
//    }
//    
//}