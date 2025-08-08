package INTEROP;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.app.Connection;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.app.Initiator;
import ca.uhn.hl7v2.llp.LLPException;

public class HL7Sender {

    private List<String> listeMessage = new ArrayList<>();
    private HL7Parser parser = new HL7Parser(); 
    private Message parsedMessage;
    private HapiContext context = new DefaultHapiContext();

    public List<String> getListeMessage() {
        return this.listeMessage;
    }

    public String sendMessage(String messageAEnvoyer, String server, int port, boolean useTLS) {
        
        String acquittements;
        getListeMessage().clear();

        String messageUnitaire = messageAEnvoyer
                .replaceAll("\r\n", System.getProperty("line.separator"))
                .replaceAll("\r", System.getProperty("line.separator"))
                .replaceAll("\n", System.getProperty("line.separator"));

        boolean structCorrecte = parser.verifierStructure(messageUnitaire);

        

        if (structCorrecte) {

            addMessageListe(messageUnitaire);
            
            try {

                String responseString = "";

                Connection connection = context.newClient(server, port, useTLS);
                Initiator initiator = connection.getInitiator();

                for (String message : listeMessage) {
                    Parser parser = context.getGenericParser();
                    parsedMessage = parser.parse(message);

                    Message response = initiator.sendAndReceive(parsedMessage);
                    System.out.println("Envoi OK");
                    responseString += "ACK reçu : \r\n" + response.toString() + "\r\n";
                    System.out.println("Réponse OK");

                }

                acquittements=("Acquittement reçu : \r\n" + responseString);

                connection.close();

            } catch (HL7Exception | LLPException | IOException e1) {
                e1.printStackTrace();
                acquittements="Erreur d'envoi. ";
            }

        } else {
            acquittements=(
                    "Message HL7 invalide. \r\n Vérifiez qu'il contient :\r\n \t - un segment MSH (entête),\r\n \t - un segment PID (patient),\r\n \t - et éventuellement un segment PV1 (venue). ");
        }

        listeMessage.clear();
        return acquittements;
    }

    public void addMessageListe(String message) {
        listeMessage.add(message);
    }

}
