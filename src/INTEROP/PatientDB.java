package INTEROP;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class PatientDB {

    private static Connection connection;
    private static String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
    private static String user = "GUILLAUME";
    private static String password = "admin";

    public List<PatientI> SelectPatientsFromIPP(String iPP) throws SQLException {

        connection = DriverManager.getConnection(url, user, password);

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("select * from PATIENT where pat_ipp=" + iPP);

            List<PatientI> patientList = new ArrayList<>();

            while (rs.next()) {

                PatientI patientRow = new PatientI();
                patientRow.setIPP(rs.getString("pat_ipp"));
                patientRow.setNomNaissance(rs.getString("pat_nom_naissance"));
                patientRow.setNomUsage(rs.getString("pat_nom_usage"));
                patientRow.setPrenom(rs.getString("pat_prenom"));
                patientRow.setSexe(rs.getString("pat_sexe"));
                patientRow.setDateNaissance(rs.getDate("pat_date_naissance"));
                patientList.add(patientRow);

            }

            return patientList;
        }

    }

    public List<PatientI> SelectPatients() {

        try {
            connection = DriverManager.getConnection(url, user, password);

            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery("select * from PATIENT order by pat_id");

                List<PatientI> patientList = new ArrayList<>();

                while (rs.next()) {

                    PatientI patientRow = new PatientI();
                    patientRow.setIPP(rs.getString("pat_ipp"));
                    patientRow.setNomNaissance(rs.getString("pat_nom_naissance"));
                    patientRow.setNomUsage(rs.getString("pat_nom_usage"));
                    patientRow.setPrenom(rs.getString("pat_prenom"));
                    patientRow.setSexe(rs.getString("pat_sexe"));
                    patientRow.setDateNaissance(rs.getDate("pat_date_naissance"));
                    patientList.add(patientRow);

                }

                return patientList;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public int UpdatePatient(String conditionColumn, String conditionValue, String updateName,
            String updateValue) throws SQLException {

        connection = DriverManager.getConnection(url, user, password);

        try (Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY)) {

            ResultSet rs = stmt
                    .executeQuery(
                            "select count(*) from PATIENT where " + conditionColumn + "='" + conditionValue + "'");

            rs.absolute(1);

            int updateCount;

            System.out.println("Retour select verif : " + rs.getInt(1));

            if (rs.getInt(1) != 1) {
                updateCount = 0;
                System.out.println("Erreur : plus d'une ligne va être mise à jour. ");
            } else {
                if (updateName.toUpperCase() == "PAT_DATE_NAISSANCE") {
                    updateCount = stmt.executeUpdate("UPDATE PATIENT set " + updateName + "= to_date('" + updateValue
                            + "','YYYYMMDD') where " + conditionColumn + "='" + conditionValue + "'");
                } else {
                    updateCount = stmt
                            .executeUpdate("UPDATE PATIENT set " + updateName + "='" + updateValue + "' where "
                                    + conditionColumn + "='" + conditionValue + "'");
                }

            }

            return updateCount;

        }

    }

    public int InsertOrUpdatePatient(PatientI patient) throws SQLException {

        connection = DriverManager.getConnection(url, user, password);

        boolean patientExists = false;

        try (Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = stmt.executeQuery("select pat_id from PATIENT where pat_ipp='" + patient.getIPP() + "'");
            if (rs.absolute(1)) {
                patientExists = true;
            }
        }

        try (Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE)) {

            String query;
            String traceOperation;

            if (patientExists) {
                SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMdd");
                String dateNaissance = formatDate.format(patient.getDateNaissance());

                query = "UPDATE PATIENT set pat_nom_naissance= '" + patient.getNomNaissance() + "', pat_prenom ='"
                        + patient.getPrenom() + "', pat_sexe ='"
                        + patient.getSexe() + "',pat_date_naissance =to_date('" + dateNaissance
                        + "','YYYYMMDD'), pat_nom_usage='" + patient.getNomUsage() + "' where pat_ipp='"
                        + patient.getIPP() + "'";

                traceOperation = "Patient mis à jour : " + patient.getIPP();

            } else {
                SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMdd");
                String dateNaissance = formatDate.format(patient.getDateNaissance());

                query = "INSERT INTO PATIENT (pat_ipp, pat_nom_naissance, pat_prenom, pat_sexe, pat_date_naissance, pat_nom_usage) VALUES ('"
                        + patient.getIPP() + "','" + patient.getNomNaissance() + "','" + patient.getPrenom() + "','"
                        + patient.getSexe() + "',to_date('" + dateNaissance + "','YYYYMMDD'),'" + patient.getNomUsage()
                        + "')";

                traceOperation = "Patient intégré : " + patient.getIPP();
            }
            int result = stmt.executeUpdate(query);

            System.out.println(traceOperation + ", code retourné = " + result + ".");

            return result;

        }

    }
}
