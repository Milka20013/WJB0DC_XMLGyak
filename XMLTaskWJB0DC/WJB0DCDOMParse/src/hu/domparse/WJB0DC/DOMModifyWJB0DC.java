package hu.domparse.WJB0DC;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMModifyWJB0DC {
	public static void ModifyFiveElements(Document document) {
		NodeList csapatok = document.getElementsByTagName("Csapat");
		// minden csapat nevét/azonosítóját "elrejtjük" és egy indexet rendelünk hozzá
		for (int i = 0; i < csapatok.getLength(); i++) {
			Element csapat = (Element) csapatok.item(i);
			csapat.setAttribute("CSAzon", "Csapat" + i);
		}

		NodeList jatekosok = document.getElementsByTagName("Jatekos");
		// az első játékos születési évét átírjuk
		Element jatekos = (Element) jatekosok.item(0);
		Node szuletesiDatumNode = jatekos.getElementsByTagName("szuletesi_datum").item(0);
		szuletesiDatumNode.setTextContent("1999-12-01");

		NodeList jatekok = document.getElementsByTagName("Jatek");
		// az első játék azonosítóját 0 ra állítjuk
		Element jatek = (Element) jatekok.item(0);
		jatek.setAttribute("JAzon", "0");

		NodeList vezetok = document.getElementsByTagName("Vezeto");
		// az utolsó vezető nevét átírjuk
		Element vezeto = (Element) vezetok.item(vezetok.getLength() - 1);
		vezeto.getElementsByTagName("nev").item(0).setNodeValue("Utolsó Vezető");

		NodeList versenyezTablak = document.getElementsByTagName("Versenyez");
		// az utolsó versenyez táblában átírjuk, ki versenyzett és milyen versenyen
		Element versenyezTabla = (Element) versenyezTablak.item(versenyezTablak.getLength() - 1);
		versenyezTabla.setAttribute("CSAzon", "Kerge Kecskék");
		versenyezTabla.setAttribute("VAzon", "2");
		
		//kiiratáshoz használjuk a már megírt metódusokat
		//ne írjunk fileba
		DOMReadWJB0DC.writeToFile = false;
		DOMReadWJB0DC.PrintNodeList(csapatok);
		DOMReadWJB0DC.PrintNodes(szuletesiDatumNode, 1);
		DOMReadWJB0DC.PrintNodes(jatek, 1);
		DOMReadWJB0DC.PrintNodes(vezeto, 1);
		DOMReadWJB0DC.PrintNodes(versenyezTabla, 1);
	}
}
