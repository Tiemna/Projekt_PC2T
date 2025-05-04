package Projekt;

import java.sql.*;

public class Ukonceni {
	
	public static void vymazDatabazi() {
	    try (Statement stmt = DBconnect.connect().createStatement()) {
	        stmt.executeUpdate("DELETE FROM znamky");
	        stmt.executeUpdate("DELETE FROM studenti");
	    } catch (SQLException e) {
	        System.out.println("\nChyba při mazání databáze: " + e.getMessage());
	    }
	}
	
	public static void ulozVsechnyStudenty(Databaze db) {
	    String studentSql = "INSERT INTO studenti (id, jmeno, prijmeni, rokNarozeni, obor) VALUES (?, ?, ?, ?, ?)";
	    String znamkaSql = "INSERT INTO znamky (student_id, znamka) VALUES (?, ?)";

	    try (PreparedStatement pstmtStudent = DBconnect.connect().prepareStatement(studentSql);
	        PreparedStatement pstmtZnamka = DBconnect.connect().prepareStatement(znamkaSql)) {
	    	
	    	for (Student s : db.ukladaniDoSQL()) {
	    		pstmtStudent.setInt(1, s.getiD());
	            pstmtStudent.setString(2, s.getJmeno());
	            pstmtStudent.setString(3, s.getPrijmeni());
	            pstmtStudent.setString(4, s.getDatumNarozeni());
	            if(s.getClass().toString().contains("class Projekt.Kyberbezpečnost")) {
	            	pstmtStudent.setString(5, "kyber");
	            }
	            else {
	            	pstmtStudent.setString(5, "tele");
	            }
	            pstmtStudent.executeUpdate();
	            
	            for (int znamka : s.getZnamky()) {
                    pstmtZnamka.setInt(1, s.getiD());
                    pstmtZnamka.setInt(2, znamka);
                    pstmtZnamka.executeUpdate();
                }
	    	}
	    	
	    	System.out.println("\nStudenti byli uloženi");
	     
	    } catch (SQLException e) {
	        System.out.println("\nChyba při ukládání studentů: " + e.getMessage());
	    }
	}


}
