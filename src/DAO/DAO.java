/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author antoi
 */
import java.sql.Connection;
import java.util.List;

public abstract class DAO<T> {
  protected Connection connect = null;
   
  public DAO(Connection connect){
    this.connect = ConnexionBD.getInstance();
  }
   
  /**
  * Méthode de création
  * @param obj
  * @return boolean 
  */
  public abstract boolean insert(T obj);

  /**
  * Méthode pour effacer
  * @param obj
  * @return boolean 
  */
  public abstract boolean delete(T obj);

  /**
  * Méthode de mise à jour
  * @param obj
  * @return boolean
  */
  public abstract boolean update(T obj);

  /**
  * Méthode de recherche des informations
  * @param id
  * @return T
  */
  public abstract T get(int id);
  
  public abstract List<T> getAll();
  
  public abstract List<T> getAll(String param);
}  
