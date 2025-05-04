package Projekt;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Databaze db = new Databaze();
		
		db.pridejStudenta(1, "Jan", "Novak", "3.4.2001");
	    db.pridejStudenta(1, "Eva", "Svobodova", "1.2.2002");
	    db.pridejStudenta(1, "Eva", "Svobodova", "1.2.2002");
	    db.pridejStudenta(2, "Eva", "Svobodova", "1.2.2002");
	    db.zadejZnamku(1,2);
	    db.zadejZnamku(1,5);
	    db.zadejZnamku(2,3);
	    db.zadejZnamku(3,2);
	    db.zadejZnamku(3,1);
	    db.zadejZnamku(4,5);
	    db.vypisObecnehoPrumeru();
	    
	    db.vypisPoctuStudentu();
	    db.odstranitStudenta(2);
	    db.vypisAbecedneVsichni();
	    db.vypisPoctuStudentu();
	    
	    db.provedDovednost(4);
	    db.provedDovednost(1);
	    
	    int volba;
	    boolean run=true;
	    Scanner sc=new Scanner(System.in);
		while(run) {
			System.out.println("\nVyberte požadovanou činnost:");
			System.out.println("1. Přidání studenta");
			System.out.println("2. Smazání studenta");
			System.out.println("3. Spustit dovednost studenta");
			System.out.println("4. Zapsat známku studenta");
			System.out.println("5. Vypsání informací o studentech");
			System.out.println("6. Vypsání obecných průměrů");
			System.out.println("7. Ulož studenta do txt");
			System.out.println("8. Načíst studenta z txt");
			volba=sc.nextInt();
			
			switch(volba) {
				case 1:
					System.out.println("Přidávání studenta:\n1.Kyberbezpečnosti\n2.Telekomunikací");
					volba=sc.nextInt();
					System.out.println("Zadejte jméno: ");
					String jmeno=sc.next();
					System.out.println("Zadejte příjmení: ");
					String prijmeni=sc.next();
					System.out.println("Zadejte datum narození: ");
					String datum=sc.next();
					db.pridejStudenta(volba, jmeno, prijmeni, datum);
					break;
					
				case 2:
					System.out.println("Zadejte id studenta k smazání: ");
					volba=sc.nextInt();
					db.odstranitStudenta(volba);
					break;
					
				case 3:
					System.out.println("Zadejte id studenta s dovedností: ");
					volba=sc.nextInt();
					db.provedDovednost(volba);
					break;
					
				case 4:
					System.out.println("Zadejte id studenta: ");
					volba=sc.nextInt();
					System.out.println("Zadejte známku: ");
					int znamka = sc.nextInt();
					db.zadejZnamku(volba, znamka);
					break;
					
				case 5:
					System.out.println("Vypsat informace o:");
					System.out.println("1. jednom studentu");
					System.out.println("2. studentech kyberbezpečnosti");
					System.out.println("3. studentech telekomunikacích");
					System.out.println("4. všech studentech");
					volba=sc.nextInt();
					switch(volba) {
						case 1:
							System.out.println("Zadejte id studenta: ");
							volba=sc.nextInt();
							db.vypisStudenta(volba);
							break;
							
						case 2:
							db.vypisAbecedneKyber();
							break;
							
						case 3:
							db.vypisAbecedneTele();
							break;
							
						case 4:
							db.vypisAbecedneVsichni();
							break;
					}
					break;
					
				case 6:
					db.vypisObecnehoPrumeru();
					break;
					
				case 7:
					System.out.println("Zadejte název souboru bez koncovky: ");
					String filename=sc.next();
					System.out.println("Zadejte id studenta: ");
					volba=sc.nextInt();
					db.ulozStudenta(filename, volba);
					break;
					
				case 8:
					System.out.println("Zadejte název souboru i s koncovkou: ");
					db.nactiStudenta(sc.next());
					break;
	
				default:
					System.out.println("Neplatná hodnota – zadej číslo od 1 do 8.");
			}
			
			
		}

	}

}
