package hu.domparse.WJB0DC;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class DOMQueryWJB0DC {
	public static void QueryFiveTimes(Document document) {
		// írjuk ki a szponzorálások együttes összegét
		NodeList csapatok = document.getElementsByTagName("Csapat");
		int osszeg = 0;
		for (int i = 0; i < csapatok.getLength(); i++) {
			Element csapat = (Element) csapatok.item(i);
			Element szponzor = (Element) csapat.getElementsByTagName("Szponzor").item(0);
			osszeg += Integer.parseInt(szponzor.getElementsByTagName("osszeg").item(0).getTextContent());
		}
		System.out.println("Összes szponzorált összeg: " + osszeg);
		System.out.println();

		// írjuk ki azon játékosok nevét, akik nem magyarok
		System.out.println("Játékosok akik nem magyarok:");
		NodeList jatekosok = document.getElementsByTagName("Jatekos");
		for (int i = 0; i < jatekosok.getLength(); i++) {
			Element jatekos = (Element) jatekosok.item(i);
			Element orszag = (Element) jatekos.getElementsByTagName("orszag").item(0);
			if (!orszag.getTextContent().equalsIgnoreCase("Magyarország")) {
				System.out.println(jatekos.getElementsByTagName("nev").item(0).getTextContent());
			}
		}
		System.out.println();

		// írjuk ki azon csapatok azonosítóját, ahol több vezető is volt már
		System.out.println("Csapatok ahol több vezető is volt:");
		NodeList vezetok = document.getElementsByTagName("Vezeto");
		ArrayList<String> csapatAzonositok = new ArrayList<String>();
		for (int i = 0; i < vezetok.getLength(); i++) {
			Element vezeto = (Element) vezetok.item(i);
			if (csapatAzonositok.contains(vezeto.getAttribute("CSAzon"))) {
				//benne volt már a listába, tehát ezen csapatnak több vezetője volt
				System.out.println(vezeto.getAttribute("CSAzon"));
			}
			else
			{
				//nem volt benne, tehát beleteszzük
				csapatAzonositok.add(vezeto.getAttribute("CSAzon"));
			}
		}
		
		//számoljuk meg hány verseny volt összesen
		//kifejezetten egyszerű lekérdezés
		NodeList versenyek = document.getElementsByTagName("Verseny");
		System.out.println("\nÖsszesen " + versenyek.getLength() + " versenyt rendeztek meg");
		
		//írjuk ki azon játékokos születési dátumát, akik 24 év alattiak
		// jatekosok nodelist már létezik
		System.out.println("\nAzon játékosok születési dátuma akik 24 éven aluliak");
		for (int i = 0; i < jatekosok.getLength(); i++) {
			Element jatekos = (Element) jatekosok.item(i);
			Element eletkor = (Element) jatekos.getElementsByTagName("eletkor").item(0);
			if (Integer.parseInt(eletkor.getTextContent()) < 24) {
				System.out.println(jatekos.getElementsByTagName("szuletesi_datum").item(0).getTextContent());
			}
		}
	}
}
