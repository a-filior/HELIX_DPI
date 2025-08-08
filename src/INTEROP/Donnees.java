package INTEROP;
import java.util.*;

public class Donnees {

    List<PatientI> patients = new ArrayList<>();

    public Donnees(){
    }

    public List<PatientI> getPatients() {
        return patients;
    }

    public void addPatient(PatientI p){
        patients.add(p); 
    }

    public boolean isEmpty() {
        return patients.isEmpty();
    }

}
