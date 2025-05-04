package Projekt;

import java.util.*;

public class Telekomunikace extends Student {
	
	private static final HashMap<Character, String> morseovka = new HashMap<Character,String>();
	
	static {
        morseovka.put('A', ".-");
        morseovka.put('B', "-...");
        morseovka.put('C', "-.-.");
        morseovka.put('D', "-..");
        morseovka.put('E', ".");
        morseovka.put('F', "..-.");
        morseovka.put('G', "--.");
        morseovka.put('H', "....");
        morseovka.put('I', "..");
        morseovka.put('J', ".---");
        morseovka.put('K', "-.-");
        morseovka.put('L', ".-..");
        morseovka.put('M', "--");
        morseovka.put('N', "-.");
        morseovka.put('O', "---");
        morseovka.put('P', ".--.");
        morseovka.put('Q', "--.-");
        morseovka.put('R', ".-.");
        morseovka.put('S', "...");
        morseovka.put('T', "-");
        morseovka.put('U', "..-");
        morseovka.put('V', "...-");
        morseovka.put('W', ".--");
        morseovka.put('X', "-..-");
        morseovka.put('Y', "-.--");
        morseovka.put('Z', "--..");
        morseovka.put('0', "-----");
        morseovka.put('1', ".----");
        morseovka.put('2', "..---");
        morseovka.put('3', "...--");
        morseovka.put('4', "....-");
        morseovka.put('5', ".....");
        morseovka.put('6', "-....");
        morseovka.put('7', "--...");
        morseovka.put('8', "---..");
        morseovka.put('9', "----.");
        morseovka.put('.', ".-.-.-");
        morseovka.put(',', "--..--");
        morseovka.put('?', "..--..");
        morseovka.put('!', "-.-.--");
        morseovka.put(' ', "/");
    }


	public Telekomunikace(int iD, String jmeno, String prijmeni, String datumNarozeni) {
		super(iD, jmeno, prijmeni, datumNarozeni);
	}

	@Override
	public String dovednost() {
		String morJmenoAPrijmeni="";
		for (char i : this.getJmeno().toCharArray()) {
			morJmenoAPrijmeni+=morseovka.get(Character.toUpperCase(i))+"/";
		}
		for (char i : this.getPrijmeni().toCharArray()) {
			morJmenoAPrijmeni+=morseovka.get(Character.toUpperCase(i))+"/";
		}
		return morJmenoAPrijmeni;
	}

}
