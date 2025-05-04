package Projekt;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Kyberbezpečnost extends Student {

	public Kyberbezpečnost(int iD, String jmeno, String prijmeni, String datumNarozeni) {
		super(iD, jmeno, prijmeni, datumNarozeni);
	}

	public String dovednost() {
		String jmenoAPrijmeni=this.getJmeno()+this.getPrijmeni();
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			byte[] encodedhash = md.digest(jmenoAPrijmeni.getBytes(StandardCharsets.UTF_8));
			StringBuilder hexadecimanlni = new StringBuilder();
            for (byte b : encodedhash)
            	hexadecimanlni.append(String.format("%02x", b));
            return hexadecimanlni.toString();
		} catch (NoSuchAlgorithmException e) {
			return "\nHash error";
		}
	}

}
