/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author antoi
 */
public class ConnexionBD {
    private static Connection connect;
    private static String url = "jdbc:mysql://localhost/dpi?allowMultiQueries=true";
    private static String login = "root";
    private static String password = "password";

    /**
     * @return the connect
     */
    public static Connection getInstance() {
        if (connect == null){
            try {
//                Class.forName("com.mysql.cj.jdbc.Driver");
                connect = DriverManager.getConnection(url, login, password);
                System.out.println("Instanciation connexion SQL");
            } catch (SQLException ex) {
                System.out.println(ex.toString());   
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERREUR DE CONNEXION ! ", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            System.out.println("Connexion déjà existante");
        }
        return connect;
    }

    /**
     * permet la fermeture d'une connexion
     */
    public static void close() {
        try {
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
}
