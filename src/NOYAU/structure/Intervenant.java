/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NOYAU.structure;

import DAO.AbstractDAOFactory;
import DAO.SQL.IntervenantDAO;
import java.util.List;


/**
 *
 * @author antoi
 */
public class Intervenant {
    
    private int intId;
    private String matricule;
    private String nom;
    private String prenom;
    private Role role;
    private Identifiant identifiant;

    public Intervenant(){
        this.intId = 0;
        this.matricule = "";
        this.nom = "";
        this.prenom = "";
        this.role = new Role();
        this.identifiant = new Identifiant();
    }

    public Intervenant(String matricule, String nom, String prenom, Role role){
        this.intId = 99;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.identifiant = new Identifiant();
    }

    public Intervenant(String matricule, String nom, String prenom){
        this.intId = 9999;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.role = null;
        this.identifiant = new Identifiant();
    }

    public Intervenant(int intId,String matricule, String nom, String prenom, Role role, Identifiant identifiant){
        this.intId = intId;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.identifiant = identifiant;
    }

    public Intervenant(int intId, String matricule, String nom, String prenom){
        this.intId = intId;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.role = new Role();
    }

    public Intervenant(String matricule){
        Intervenant inter = ((IntervenantDAO) AbstractDAOFactory.getFactory(AbstractDAOFactory.SQL_DAO_FACTORY).getIntervenantDAO()).get(matricule);
        this.intId = inter.getIntId();
        this.matricule = matricule;
        this.nom = inter.getNom();
        this.prenom = inter.getPrenom();
        this.role = inter.getRole();
    }

    public Intervenant(Identifiant id){
        Intervenant inter = ((IntervenantDAO) AbstractDAOFactory.getFactory(AbstractDAOFactory.SQL_DAO_FACTORY).getIntervenantDAO()).getIntervenantParLogin(id.getLogin());
        this.intId = inter.getIntId();
        this.matricule = inter.getMatricule();
        this.nom = inter.getNom();
        this.prenom = inter.getPrenom();
        this.role = Intervenant.getRoleByIntId(inter.getIntId()); //FAUX
    }

    public int getIntId() {
        return intId;
    }

    public String getMatricule(){
        return this.matricule;
    }

    public String getPrenom(){
        return this.prenom;
    }

    public String getNom(){
        return this.nom;
    }

    public Role getRole(){
        return this.role;
    }

    public void setIntId(int intId) {
        this.intId = intId;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setRole(Role role){
        this.role = role;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setIdentifiants(Identifiant id){
//            this.identifiant = ((IntervenantDAO) AbstractDAOFactory.getFactory(AbstractDAOFactory.SQL_DAO_FACTORY).getIntervenantDAO()).getIdentifiant(nom);
        this.identifiant = id;
    }

    public Identifiant getIdentifiant(){
        return this.identifiant;
    }
    
    public Identifiant getIdentifiantByIntId(){
        return Identifiant.getIdentifiant(this.intId);
    }

    @Override
    public String toString(){
        return (this.matricule + " : " + this.prenom + " - " + this.nom);
    }
    
    public void ajouterIntervenant(){
        AbstractDAOFactory.getFactory(AbstractDAOFactory.SQL_DAO_FACTORY).getIntervenantDAO().insert(this);
    }

    public void supprimerIntervenant(){
        AbstractDAOFactory.getFactory(AbstractDAOFactory.SQL_DAO_FACTORY).getIntervenantDAO().delete(this);
    }

    public void modifierRoleIntervenant(Role role){
        this.role = role;
        AbstractDAOFactory.getFactory(AbstractDAOFactory.SQL_DAO_FACTORY).getIntervenantDAO().update(this);
    }

    public static List<Intervenant> getLesIntervenants(){
        return AbstractDAOFactory.getFactory(AbstractDAOFactory.SQL_DAO_FACTORY).getIntervenantDAO().getAll();
    }
    
    public void modifierIntervenant(){
        AbstractDAOFactory.getFactory(AbstractDAOFactory.SQL_DAO_FACTORY).getIntervenantDAO().update(this);
    }
    
    public static Intervenant getIntervenant(int intId){
        return (Intervenant) AbstractDAOFactory.getFactory(AbstractDAOFactory.SQL_DAO_FACTORY).getIntervenantDAO().get(intId);
    }
    
    public static Role getRoleByIntId(int intId){
        return ((IntervenantDAO) AbstractDAOFactory.getFactory(AbstractDAOFactory.SQL_DAO_FACTORY).getIntervenantDAO()).getRolByIntId(intId);
    }
  
}
