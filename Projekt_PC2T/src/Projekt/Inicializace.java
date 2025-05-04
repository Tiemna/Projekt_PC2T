package Projekt;

import java.sql.*;

public class Inicializace {

	public static void vytvorTabulky() {
        String sql = """
            CREATE TABLE IF NOT EXISTS studenti (
                id INTEGER PRIMARY KEY,
                jmeno VARCHAR(20) NOT NULL,
                prijmeni VARCHAR(25) NOT NULL,
                rokNarozeni VARCHAR(10),
                obor VARCHAR(5)
            );
        """;
        
        String znamkySql = """
                CREATE TABLE IF NOT EXISTS znamky (
                    student_id INTEGER,
                    znamka INTEGER,
                    FOREIGN KEY(student_id) REFERENCES studenti(id)
                );
            """;

        try (Statement stmt = DBconnect.connect().createStatement()) {
            stmt.execute(sql);
            stmt.execute(znamkySql);
            System.out.println("\nTabulka 'studenti a znamky' připravena.");
        } catch (SQLException e) {
            System.out.println("\nChyba při vytváření tabulky: " + e.getMessage());
        }
    }
	
	public static void nactiVsechnyStudentyZSQL(Databaze db) {
	    String sql = "SELECT * FROM studenti";

	    try (Statement stmt = DBconnect.connect().createStatement();
	    		ResultSet rs = stmt.executeQuery(sql)) {

	    	while (rs.next()) {
	    		int id = rs.getInt("id");
	    		String jmeno = rs.getString("jmeno");
	    		String prijmeni = rs.getString("prijmeni");
	    		String rok = rs.getString("rokNarozeni");
	    		String obor = rs.getString("obor");

	    		if ("kyber".equals(obor)) {
	    			db.pridejStudentaZSQL(1,id,jmeno, prijmeni, rok);
	    		} else if ("tele".equals(obor)) {
	    			db.pridejStudentaZSQL(2,id,jmeno, prijmeni, rok);
	    		}
	    	}

	    	String sqlz = "SELECT * FROM znamky";

	    	try (Statement stmtz = DBconnect.connect().createStatement();
	    			ResultSet rsz = stmtz.executeQuery(sqlz)){
	    		while (rsz.next()) {
	    			int id = rsz.getInt("student_id");
	    			int znamka = rsz.getInt("znamka");
	    			if (db.najdiPodleID(id)!=null) {
						db.najdiPodleID(id).pridejZnamku(znamka);
					}
	    		}
	    	} catch (SQLException e) {
	    		System.out.println("\nChyba při načítání známek: " + e.getMessage());
	    	}

	    } catch (SQLException e) {
	    	System.out.println("\nChyba při načítání studentů: " + e.getMessage());
	    }
	}
}