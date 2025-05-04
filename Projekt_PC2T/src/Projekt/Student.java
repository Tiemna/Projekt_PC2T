package Projekt;

import java.util.*;

public abstract class Student {

	private int iD;
	private String jmeno;
	private String prijmeni;
	private String datumNarozeni;
	private List<Integer> znamky;
	
	
	
	public Student(int iD, String jmeno, String prijmeni, String datumNarozeni) {
		super();
		this.iD = iD;
		this.jmeno = jmeno;
		this.prijmeni = prijmeni;
		this.datumNarozeni = datumNarozeni;
		this.znamky = new ArrayList<Integer>();
	}



	public float prumer() {
		float prumer=0;
		for (int i : znamky) {
			prumer+=i;
		}
		prumer/=znamky.size();
		return prumer;
	}
	
	public abstract String dovednost();
	
	public void pridejZnamku(int z) {
		znamky.add(z);
	}
	
	public String toString() {
        return "Student\nID: "+iD+"\nJméno: "+jmeno+"\nPříjmení: "+prijmeni+"\nDatum narození: "+datumNarozeni+"\nStudijní průměr: "+prumer();
    }


	public int getiD() {
		return iD;
	}

	public void setiD(int iD) {
		this.iD = iD;
	}



	public String getJmeno() {
		return jmeno;
	}

	public void setJmeno(String jmeno) {
		this.jmeno = jmeno;
	}



	public String getPrijmeni() {
		return prijmeni;
	}

	public void setPrijmeni(String prijmeni) {
		this.prijmeni = prijmeni;
	}



	public String getDatumNarozeni() {
		return datumNarozeni;
	}

	public void setDatumNarozeni(String datumNarozeni) {
		this.datumNarozeni = datumNarozeni;
	}



	public List<Integer> getZnamky() {
		return znamky;
	}

	public void setZnamky(List<Integer> znamky) {
		this.znamky = znamky;
	}
	
	
}
