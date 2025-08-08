package INTEROP;


import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;

public class MainInterface implements ActionListener {

    JFrame frame;

    JMenuItem quiSuisJeItem;
    JMenuItem helpItem;
    JMenuItem resetItem;
    JMenuItem afficherPatientsItem;
    JMenuItem nouveauMessageItem;
    JMenuItem lancerEcouteItem;
    JMenuItem arreterEcouteItem;
    JMenuItem creerPatientItem;
    JMenuItem afficherPatientItem;
    JTextArea quiSuisJeText = new JTextArea("Veuillez choisir un menu. ");

    JTextArea messageAEnvoyer = new JTextArea("");
    JButton bouttonEnvoyer = new JButton("Envoyer");

    HL7Receiver receiver = new HL7Receiver();

    PatientInterface patientInterface = new PatientInterface();

    List<String> listeMessage = new ArrayList<>();
    HL7Sender sender = new HL7Sender();
    HL7Parser parser = new HL7Parser();

    JMenuBar menuBar = new JMenuBar();
    JMenu generalMenuN1 = new JMenu("General");
    JMenu senderMenuN1 = new JMenu("HL7 Sender");
    JMenu receiverMenuN1 = new JMenu("HL7 Receiver");
    JMenu patientMenuN1 = new JMenu("Menu patient");
    JMenu aProposN1 = new JMenu("A propos");

    URL iconURL = getClass().getResource("/images/GRINCHEUX.jpg");
    ImageIcon icon = new ImageIcon(iconURL);

    PatientDB patientDB = new PatientDB();

    public MainInterface() {
    }

    public void afficher() {
        frame = new JFrame("Hardly");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 400);
        frame.setIconImage(icon.getImage());
        quiSuisJeText.setLayout(new FlowLayout(FlowLayout.CENTER));
        quiSuisJeText.setTabSize(1);
        frame.add(quiSuisJeText);
        menuBar.add(generalMenuN1);
        menuBar.add(senderMenuN1);
        menuBar.add(receiverMenuN1);
        menuBar.add(patientMenuN1);
        menuBar.add(aProposN1);

        afficherPatientsItem = new JMenuItem("Afficher les patients");
        afficherPatientsItem.addActionListener(this);
        generalMenuN1.add(afficherPatientsItem);
        resetItem = new JMenuItem("Réinitialiser l'application");
        resetItem.addActionListener(this);
        generalMenuN1.add(resetItem);

        nouveauMessageItem = new JMenuItem("Nouveau message HL7");
        nouveauMessageItem.addActionListener(this);
        senderMenuN1.add(nouveauMessageItem);

        lancerEcouteItem = new JMenuItem("Lancer l'écoute");
        lancerEcouteItem.addActionListener(this);
        receiverMenuN1.add(lancerEcouteItem);

        arreterEcouteItem = new JMenuItem("Arrêter l'écoute");
        arreterEcouteItem.addActionListener(this);
        receiverMenuN1.add(arreterEcouteItem);

        creerPatientItem = new JMenuItem("Créer un patient");
        creerPatientItem.addActionListener(this);
        patientMenuN1.add(creerPatientItem);

        afficherPatientItem = new JMenuItem("Afficher les patients");
        afficherPatientItem.addActionListener(this);
        patientMenuN1.add(afficherPatientItem);

        quiSuisJeItem = new JMenuItem("Qui suis-je ? ");
        quiSuisJeItem.addActionListener(this);
        aProposN1.add(quiSuisJeItem);
        helpItem = new JMenuItem("Aide");
        helpItem.addActionListener(this);
        aProposN1.add(helpItem);

        menuBar.setSize(300, 20);
        frame.setJMenuBar(menuBar);

        frame.setVisible(true);

    }

    public synchronized void actionPerformed(ActionEvent e) {
        if (e.getSource() != nouveauMessageItem) {
            messageAEnvoyer.setVisible(false);
            bouttonEnvoyer.setVisible(false);
        }

        if (e.getSource() == quiSuisJeItem) {
            quiSuisJeFonction();
        } else if (e.getSource() == helpItem) {
            helpFonction();
        } else if (e.getSource() == lancerEcouteItem) {
            setQuiSuisJeText(receiver.lancerEcoute());
        } else if (e.getSource() == arreterEcouteItem) {
            setQuiSuisJeText(receiver.arreterEcouteFonction());
        } else if (e.getSource() == resetItem) {
            resetFonction();
        } else if (e.getSource() == afficherPatientsItem) {
            setQuiSuisJeText(patientInterface.afficherPatient());
        } else if (e.getSource() == nouveauMessageItem) {
            nouveauMessageFonction();
        } else if (e.getSource() == creerPatientItem) {
            frame.getContentPane().add(patientInterface.creerPatient(), BorderLayout.CENTER);
        } else if (e.getSource() == afficherPatientItem) {
            setQuiSuisJeText(patientInterface.afficherPatient());
        }
    }

    private void resetFonction() {

        if (receiver.getEnEcoute() == true) {
            try {
                receiver.closeServer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        receiver.getPatients().clear();
        receiver.getMessagesRecus().clear();
        sender.getListeMessage().clear();
        messageAEnvoyer.setVisible(false);
        bouttonEnvoyer.setVisible(false);
        setQuiSuisJeText("Tout a été réinitialisé. ");
    }

    private void nouveauMessageFonction() {

        setQuiSuisJeText("Veuillez renseigner un HL7 dans l'encart de droite. ");

        Border border = BorderFactory.createLineBorder(Color.GRAY);

        messageAEnvoyer.setText("Remplacez ce texte par votre HL7.");
        messageAEnvoyer
                .setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        bouttonEnvoyer.setLayout(new FlowLayout(FlowLayout.RIGHT));

        quiSuisJeText.add(messageAEnvoyer);
        quiSuisJeText.add(bouttonEnvoyer);
        messageAEnvoyer.setVisible(true);
        bouttonEnvoyer.setVisible(true);

        bouttonEnvoyer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (receiver.getEnEcoute() == false) {
                    setQuiSuisJeText("Petit malin, le serveur n'est pas en écoute.");
                } else {
                    setQuiSuisJeText(sender.sendMessage(messageAEnvoyer.getText(), "localhost", 4242, false));

                    messageAEnvoyer.setVisible(false);
                    bouttonEnvoyer.setVisible(false);
                }
            }
        });

    }

    private void quiSuisJeFonction() {
        setQuiSuisJeText(
                "Bonjour, je m'appelle Guillaume Gaspard, diplômé TIS en 2017, je suis chef de projet Interopérabilité. \r\n"
                        +
                        "Pour mon stage de fin d'études, je me suis lancé sans trop savoir à quoi m'attendre dans l'interopérabilité ... Eh quelle découverte ! Aujourd'hui, j'en suis devenu passionné. \r\n"
                        +
                        "Pour vous faire un peu pratiquer, voici donc un petit outil permettant d'envoyer ou recevoir du HL7, la norme la plus utilisée au monde. \r\n"
                        +
                        "N'hésitez pas à me demander les sources. Ps : je suis très mauvais développeur. ");
    }

    private void helpFonction() {
        setQuiSuisJeText(
                "Voici quelques liens intéressants sur le HL7 :\r\n" +
                        "1) Caristix, la description pratique du HL7 : https://hl7-definition.caristix.com/v2/HL7v2.5.1 \r\n"
                        +
                        "2) HAPI HL7, l'API utilisée pour cette application : https://hapifhir.github.io/hapi-hl7v2/ \r\n");
    }

    public void setQuiSuisJeText(String quiSuisJe) {
        quiSuisJeText.setText(quiSuisJe);

    }

}
