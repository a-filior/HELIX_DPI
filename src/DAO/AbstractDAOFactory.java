/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author antoi
 */
public abstract class AbstractDAOFactory {
    public static final int SQL_DAO_FACTORY = 0;
    public static final int XML_DAO_FACTORY = 1;

    //Retourne un objet Intervenant interagissant avec la BDD
    public abstract DAO getIntervenantDAO();

    //Retourne un objet Role interagissant avec la BDD
    public abstract DAO getRoleDAO();
    
    //Retourne un objet PatientIdentifiant interagissant avec la BDD
    public abstract DAO getPatientIdentifiantDAO();
    
    //Retourne un objet Patient interagissant avec la BDD
    public abstract DAO getPatientDAO();
    
    //Retourne un objet Identifiant interagissant avec la BDD
    public abstract DAO getIdentifiantDAO();
    
    //Retourne un objet ParamétrageInterop interagissant avec la BDD
    public abstract DAO getParametrageInteropDAO();
    
    //Retourne un objet Application interagissant avec la BDD
    public abstract DAO getApplicationDAO();
    
    //Retourne un objet Application interagissant avec la BDD
    public abstract DAO getConfigurationGlobaleDAO();

    //Méthode permettant de récupérer les Factory
    public static AbstractDAOFactory getFactory(int type){
      switch(type){
        case SQL_DAO_FACTORY:
          return new DAOFactorySQL();
        case XML_DAO_FACTORY: 
          return new DAOFactoryXML();
        default:
          return null;
      }
    }
}
