package Projekt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Databaze {

	private List<Kyberbezpečnost> studentiKyber;
	private List<Telekomunikace> studentiTele;
	
	private List<List<? extends Student>> seznamStudentu;
	
	private int dalsiID = 1;
	
	public Databaze(){
		studentiKyber = new ArrayList<Kyberbezpečnost>();
		studentiTele = new ArrayList<Telekomunikace>();
		
		seznamStudentu = new ArrayList<>();
		seznamStudentu.add(studentiKyber);
		seznamStudentu.add(studentiTele);
	}
	
	public void pridejStudenta(int obor, String jmeno, String prijmeni, String datumNarozeni){
		while(najdiPodleID(dalsiID)!=null) {
			dalsiID++;
		}
		if(obor==1) {
			studentiKyber.add(new Kyberbezpečnost(dalsiID, jmeno, prijmeni, datumNarozeni));
			dalsiID++;
		}
		else if(obor==2) {
			studentiTele.add(new Telekomunikace(dalsiID, jmeno, prijmeni, datumNarozeni));
			dalsiID++;
		}
		else {
			System.out.println("\nNeplatný výběr.");
		}
	}
	
	public Student najdiPodleID(int id) {

		for (List<? extends Student> obor : seznamStudentu) {
			for (Student s : obor) {
				if (s.getiD() == id) {
					return s;
				}
			}
		}
		return null;

    }
	
	public void zadejZnamku(int id, int z) {
		try {
			if(z>=1 && z<=5) {
				najdiPodleID(id).pridejZnamku(z);
				System.out.println("\nZnámka zapsána.");
			}
			else {
				System.out.println("\nZnámka může mít hodnotu 1 až 5.");
			}
		} catch (Exception e) {
			System.out.println("\nStudent nenalezen.");
		}
	}
	
	public void provedDovednost(int id) {
		try {
			System.out.println(najdiPodleID(id).dovednost());
		} catch (Exception e) {
			System.out.println("\nStudent nenalezen.");
		}
	}
	
	public void odstranitStudenta(int id) {
		try {
			for (List<? extends Student> obor : seznamStudentu) {
			    for (Student s : obor) {
			        if (s.getiD() == id) {
			        	obor.remove(s);
			        	System.out.println("\nStudent úspěšně smazán.");
			        }
			    }
			}
		} catch (Exception e) {
			System.out.println("\nStudent nenalezen.");
		}
	}
	
	
	
	
	public void vypisObecnehoPrumeru() {
		float prumerkyber=0;
		float prumertele=0;
		for (Kyberbezpečnost k : studentiKyber) {
			prumerkyber+=k.prumer();
		}
		for (Telekomunikace k : studentiTele) {
			prumertele+=k.prumer();
		}
		prumerkyber/=studentiKyber.size();
		prumertele/=studentiTele.size();
		System.out.println("\nObecný průměr oboru Kyberbezpečnost je: "+String.format("%.2f",prumerkyber));
		System.out.println("Obecný průměr oboru Telekomunikací je: "+String.format("%.2f",prumertele));
	}
	
	public void vypisPoctuStudentu() {
		System.out.println("\nPočet studentů oboru Kyberbezpečnosti: "+studentiKyber.size());
		System.out.println("Počet studentů oboru Telekomunikací: "+studentiTele.size());
		System.out.println("Počet všech studentů: "+(studentiKyber.size()+studentiTele.size()));
	}
	
	public void vypisStudenta(int id) {
		System.out.println(najdiPodleID(id));
	}
	
	public void vypisAbecedneKyber() {
		studentiKyber.sort(Comparator
		        .comparing(Student::getPrijmeni)
		        .thenComparing(Student::getJmeno));

		System.out.println("\n\nVýpis studentů kyberbezpečnosti:\n");
		for (Student s : studentiKyber) {
		        System.out.println(s+"\n");
		}
	}
	
	public void vypisAbecedneTele() {
		studentiTele.sort(Comparator
		        .comparing(Student::getPrijmeni)
		        .thenComparing(Student::getJmeno));

		System.out.println("\n\nVýpis studentů Telekomunikací:\n");
		for (Student s : studentiTele) {
		        System.out.println(s+"\n");
		}
	}
	
	public void vypisAbecedneVsichni() {
		List<Student> listProSerazeni = new ArrayList<>();

	    for (List<? extends Student> obor : seznamStudentu) {
	    	listProSerazeni.addAll(obor);
	    }

	    listProSerazeni.sort(Comparator
	        .comparing(Student::getPrijmeni)
	        .thenComparing(Student::getJmeno));

	    System.out.println("\n\nVýpis všech studentů:\n");
	    for (Student s : listProSerazeni) {
	        System.out.println(s+"\n");
	    }
	}
	
	
	
	public void ulozStudenta(String filename, int id) {
		try {
			FileWriter fw = new FileWriter(filename+".txt");
			Student s = najdiPodleID(id);
			fw.write(s.getClass().toString()+"\n");
			fw.write(id+"\n");
			fw.write(s.getJmeno()+"\n");
			fw.write(s.getPrijmeni()+"\n");
			fw.write(s.getDatumNarozeni()+"\n");
			fw.write(s.getZnamky().toString()+"\n");
			fw.close();
		}
		catch (Exception e){
			System.out.println("\nV ukládání nastala chyba.");
		}
		
	}
	
	public void nactiStudenta(String filename) {
		try {
			BufferedReader br = new BufferedReader( new FileReader(filename));
			String obor=br.readLine();
			int ob = 0;
			
			int id = Integer.parseInt(br.readLine());
			String jmeno = br.readLine();
			String prijmeni = br.readLine();
			String datumnarozeni = br.readLine();
			
			char znamky[] = br.readLine().toCharArray();
			
			List<Integer> znamkyL = new ArrayList<Integer>();
			for (char s : znamky) {
				if(Character.isDigit(s)) {
					znamkyL.add(Integer.parseInt(String.valueOf(s)));
				}
			}
			
			if(obor.contains("class Projekt.Kyberbezpečnost")) {
				ob=1;
			}
			else if(obor.contains("class Projekt.Telekomunikace")) {
				ob=2;
			}
			else {
				System.out.println("\nV souboru není platný obor studenta.");
				br.close();
				return;
			}
			
			if(najdiPodleID(id)!=null) {
				System.out.println("\nStudenské id už je zabrané, studentu se přiřadí jiné.");
				pridejStudenta(ob,jmeno,prijmeni,datumnarozeni);
				najdiPodleID(dalsiID-1).setZnamky(znamkyL);
				System.out.println("\nStudent byl úspěšně zapsán. S ID: "+(dalsiID-1));
			}
			else {
				if(ob==1) {
					studentiKyber.add(new Kyberbezpečnost(id,jmeno,prijmeni,datumnarozeni));
					najdiPodleID(id).setZnamky(znamkyL);
					System.out.println("\nStudent byl úspěšně zapsán.");
				}
				else {
					studentiTele.add(new Telekomunikace(id,jmeno,prijmeni,datumnarozeni));
					najdiPodleID(id).setZnamky(znamkyL);
					System.out.println("\nStudent byl úspěšně zapsán.");
				}
			}
			
			br.close();
			
			
		} catch (IOException e) {
			System.out.println("\nVe čtení nastala chyba.");
		}
	}
	
	public void pridejStudentaZSQL(int obor, int id, String jmeno, String prijmeni, String datumNarozeni){
		if(obor==1) {
			studentiKyber.add(new Kyberbezpečnost(id, jmeno, prijmeni, datumNarozeni));
		}
		else if(obor==2) {
			studentiTele.add(new Telekomunikace(id, jmeno, prijmeni, datumNarozeni));
		}
		else {
			System.out.println("\nNeplatný výběr.");
		}
	}
	
	public List<Student> ukladaniDoSQL() {
		List<Student> listProUkladani = new ArrayList<>();

	    for (List<? extends Student> obor : seznamStudentu) {
	    	listProUkladani.addAll(obor);
	    }
	    return listProUkladani;
	}
}
