/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAO.SQL.ApplicationDAO;
import DAO.SQL.ConfigurationGlobaleDAO;
import DAO.SQL.IntervenantDAO;
import DAO.SQL.ParametrageInteropDAO;
import DAO.SQL.IdentifiantDAO;
import DAO.SQL.PatientIdentifiantDAO;
import DAO.SQL.RoleDAO;
import DAO.SQL.PatientDAO;
import java.sql.Connection;

/**
 *
 * @author antoi
 */
public class DAOFactorySQL extends AbstractDAOFactory{
    
    protected static final Connection conn = ConnexionBD.getInstance();
    
    @Override
    public DAO getIntervenantDAO() {
        return new IntervenantDAO(conn);
    }

    @Override
    public DAO getRoleDAO() {
        return new RoleDAO(conn);
    }

    @Override
    public DAO getPatientIdentifiantDAO() {
        return new PatientIdentifiantDAO(conn);
    }

    @Override
    public DAO getPatientDAO() {
        return new PatientDAO(conn);
    }
    
    @Override
    public DAO getIdentifiantDAO() {
        return new IdentifiantDAO(conn);
    }    

    @Override
    public DAO getParametrageInteropDAO() {
        return new ParametrageInteropDAO(conn);
    }

    @Override
    public DAO getApplicationDAO() {
        return new ApplicationDAO(conn);
    }

    @Override
    public DAO getConfigurationGlobaleDAO() {
        return new ConfigurationGlobaleDAO(conn);
    }
    
}
