package hu.domparse.WJB0DC;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class DOMWriteWJB0DC {
	public static void WriteDocument(String inputFilePath, String outputFilePath)
	{
		try 
        {
            File inputFile = new File(inputFilePath);
            
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(inputFile);
            document.getDocumentElement().normalize();

            System.out.println("Writing to file");
            
            //a tranformer osztállyal készítünk egy XML filet
            
            Document ujDocument = dBuilder.newDocument();
            Element esportok = ujDocument.createElement("WJB0DC_ESportok");
            esportok.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            esportok.setAttribute("xsi:noNamespaceSchemaLocation", "XMLSchemaWJB0DC.xsd");
            ujDocument.appendChild(esportok);
            
            
            String[] alapitoNevek = {"Kiss János", "Nagy Martin"};
            createCsapat(ujDocument,esportok,"Kerge Kecskék", "2002-04-06","Nestlé","50000",createAlapitok(ujDocument,alapitoNevek));
            String[] alapitoNevek1 = {"Magyar Márk", "Kossuth Máté"};
            createCsapat(ujDocument,esportok,"Zöld Békák", "2002-04-06","Nestlé","75000",createAlapitok(ujDocument,alapitoNevek1));
            String[] alapitoNevek2 = {"Nemes Géza"};
            createCsapat(ujDocument,esportok,"Kutyások", "2002-05-01","FIFA","100000",createAlapitok(ujDocument,alapitoNevek2));

            createJatekos(ujDocument,esportok,"Kerge Kecskék", "1", "1","Havas Henrik","Magyarország","1998-12-01","24");
            createJatekos(ujDocument,esportok,"Kerge Kecskék", "2", "1","Arany Anna","Magyarország","2003-11-18","20");
            createJatekos(ujDocument,esportok,"Zöld Békák", "3", "1","Kiss Anita","Magyarország","1999-10-11","24");
            createJatekos(ujDocument,esportok,"Zöld Békák", "4", "1","Nagy Hugó","Magyarország","1999-07-11","24");
            createJatekos(ujDocument,esportok,"Kutyások", "5", "2","Németh Ottó","Magyarország","2000-05-07","23");
            createJatekos(ujDocument,esportok,"Kutyások", "6", "2","Ding Liren","Kína","1995-12-01","28");
            //kiirjuk az xmlt a consolera
            
            String[] jatekTipusok = {"Lövöldözős", "Több játékos", "Akció"};
            createJatek(ujDocument,esportok,"1","CS-GO",createJatekTipus(ujDocument, jatekTipusok));
            String[] jatekTipusok2 = {"MOBA", "Több játékos", "Stratégia"};
            createJatek(ujDocument,esportok,"2","LoL",createJatekTipus(ujDocument, jatekTipusok2));
            String[] jatekTipusok3 = {"Labdarúgás", "Több játékos", "Ügyességi"};
            createJatek(ujDocument,esportok,"3","FIFA",createJatekTipus(ujDocument, jatekTipusok3));
            
            createVersenyszam(ujDocument,esportok,"1","1");
            createVersenyszam(ujDocument,esportok,"1","2");
            createVersenyszam(ujDocument,esportok,"2","3");
            createVersenyszam(ujDocument,esportok,"1","3");
            createVersenyszam(ujDocument,esportok,"2","4");
            
            createVezeto(ujDocument, esportok, "Kerge Kecskék", "1", "Alantas Balázs", "1975-04-01", "2002", "2008", "48");
            createVezeto(ujDocument, esportok, "Kerge Kecskék", "2", "Kovács Ágnes", "1982-09-21", "2008", "", "41");
            createVezeto(ujDocument, esportok, "Zöld Békák", "3", "Tóth Szilvi", "1979-12-24", "2002", "", "41");
            createVezeto(ujDocument, esportok, "Kutyások", "4", "Németh László", "1970-01-16", "2002", "", "53");
            
            createVersenyez(ujDocument, esportok, "Kerge Kecskék", "1", "1");
            createVersenyez(ujDocument, esportok, "Zöld Békák", "1", "2");
            createVersenyez(ujDocument, esportok, "Kutyások", "3", "1");
            createVersenyez(ujDocument, esportok, "Kutyások", "4", "1");
            
            createVerseny(ujDocument, esportok, "1", "CS-GO Championship Las Vegas", "2010-10-04", "Las Vegas");
            createVerseny(ujDocument, esportok, "2", "CS-GO Championship Dublin", "2012-10-04", "Dublin");
            createVerseny(ujDocument, esportok, "3", "ESport Cup", "2014-05-04", "Berlin");
            createVerseny(ujDocument, esportok, "4", "Play IT!", "2020-10-10", "Budapest");
            
            FileWriter writer = new FileWriter(outputFilePath);
            PrintDocument(ujDocument, writer);
            writer.close();
            
            //kiirjuk fájlba az xmlt
            
            

        } 
        catch (SAXException | IOException | ParserConfigurationException e) 
        {
            e.printStackTrace();
        }
	}
	
	private static Element[] createAlapitok(Document doc,String[] nevek)
	{
		Element[] elements = new Element[nevek.length];
		for (int i = 0; i < nevek.length; i++) {
			elements[i] = doc.createElement("alapito_tag");
			elements[i].setTextContent(nevek[i]);
		}
		return elements;
	}
	
	 private static void createCsapat(Document doc, Element root, String CSAzon, String alapitasDatum, String szponzorNev, String szponzorOsszeg
			 , Element[] alapitok)
	 {
		Element csapat = doc.createElement("Csapat"); 
		csapat.setAttribute("CSAzon", CSAzon);
		root.appendChild(csapat);
		
		Element alapitas =  doc.createElement("alapitas_datuma"); 
		alapitas.setTextContent(alapitasDatum);
		csapat.appendChild(alapitas);
		
		Element szponzor =  doc.createElement("Szponzor"); 
		Element nev = doc.createElement("nev"); 
		nev.setTextContent(szponzorNev);
		Element osszeg = doc.createElement("osszeg"); 
		osszeg.setTextContent(szponzorOsszeg);
		szponzor.appendChild(nev);
		szponzor.appendChild(osszeg);
		
		for (int i = 0; i < alapitok.length; i++) {
			szponzor.appendChild(alapitok[i]);
			csapat.appendChild(szponzor);
		}
		
		
	 }
	 
	 private static void createJatekos(Document doc, Element root, String CSAzon, String PAzon,String JAzon
			 , String nevStr, String orszagStr, String datumStr, String eletkorStr)
	 {
		Element jatekos = doc.createElement("Jatekos"); 
		jatekos.setAttribute("PAzon", PAzon);
		jatekos.setAttribute("CSAzon", CSAzon);
		jatekos.setAttribute("JAzon", JAzon);
		root.appendChild(jatekos);
		
		Element nev = doc.createElement("nev"); 
		nev.setTextContent(nevStr);
		jatekos.appendChild(nev);
		
		Element orszag = doc.createElement("orszag"); 
		orszag.setTextContent(orszagStr);
		jatekos.appendChild(orszag);
		
		Element datum = doc.createElement("szuletesi_datum"); 
		datum.setTextContent(datumStr);
		jatekos.appendChild(datum);
		
		Element eletkor = doc.createElement("eletkor"); 
		eletkor.setTextContent(eletkorStr);
		jatekos.appendChild(eletkor);
	 }
	 
	 private static void createJatek(Document doc, Element root,String JAzon
			 , String nevStr, Element[] jatekTipusok)
	 {
		Element jatek = doc.createElement("Jatek"); 
		jatek.setAttribute("JAzon", JAzon);
		root.appendChild(jatek);
		
		Element nev = doc.createElement("nev"); 
		nev.setTextContent(nevStr);
		jatek.appendChild(nev);
		
		for (int i = 0; i < jatekTipusok.length; i++) {
			jatek.appendChild(jatekTipusok[i]);
		}
	 }
	 
	 private static void createVersenyszam(Document doc, Element root,String JAzon
			 , String VAzon)
	 {
		Element jatek = doc.createElement("Versenyszam"); 
		jatek.setAttribute("JAzon", JAzon);
		jatek.setAttribute("VAzon", VAzon);
		root.appendChild(jatek);
		
	 }
	 
	 private static Element[] createJatekTipus(Document doc,String[] nevek)
		{
			Element[] elements = new Element[nevek.length];
			for (int i = 0; i < nevek.length; i++) {
				elements[i] = doc.createElement("jatektipus");
				elements[i].setTextContent(nevek[i]);
			}
			return elements;
		}
	 
	 private static void createVezeto(Document doc, Element root, String CSAzon, String LAzon,
			  String nevStr, String datumStr, String mettolStr, String meddigStr, String eletkorStr)
	 {
		Element vezeto = doc.createElement("Vezeto"); 
		vezeto.setAttribute("LAzon", LAzon);
		vezeto.setAttribute("CSAzon", CSAzon);
		root.appendChild(vezeto);
		
		Element nev = doc.createElement("nev"); 
		nev.setTextContent(nevStr);
		vezeto.appendChild(nev);
		
		Element datum = doc.createElement("szuletesi_datum"); 
		datum.setTextContent(datumStr);
		vezeto.appendChild(datum);
		
		Element mettol = doc.createElement("mettol"); 
		mettol.setTextContent(mettolStr);
		vezeto.appendChild(mettol);
		
		if(!meddigStr.isEmpty())
		{
			Element meddig = doc.createElement("meddig"); 
			meddig.setTextContent(meddigStr);
			vezeto.appendChild(meddig);
		}
		
		Element eletkor = doc.createElement("eletkor"); 
		eletkor.setTextContent(eletkorStr);
		vezeto.appendChild(eletkor);
	 }
	 
	 private static void createVersenyez(Document doc, Element root,String CSAzon
			 , String VAzon, String helyezesStr)
	 {
		Element versenyez = doc.createElement("Versenyez"); 
		versenyez.setAttribute("CSAzon", CSAzon);
		versenyez.setAttribute("VAzon", VAzon);
		root.appendChild(versenyez);
		
		Element helyezes = doc.createElement("helyezes"); 
		helyezes.setTextContent(helyezesStr);
		versenyez.appendChild(helyezes);
		
	 }
	 
	 private static void createVerseny(Document doc, Element root, String VAzon,
			 String nevStr, String datumStr, String helyszinStr)
	 {
		Element verseny = doc.createElement("Verseny"); 
		verseny.setAttribute("VAzon", VAzon);
		root.appendChild(verseny);
		
		Element nev = doc.createElement("nev"); 
		nev.setTextContent(nevStr);
		verseny.appendChild(nev);
		
		
		Element datum = doc.createElement("datum"); 
		datum.setTextContent(datumStr);
		verseny.appendChild(datum);
		
		
		Element helyszin = doc.createElement("orszag"); 
		helyszin.setTextContent(helyszinStr);
		verseny.appendChild(helyszin);
	 }
	 
	 public static void PrintDocument(Document document, FileWriter writer) {
			Element root = document.getDocumentElement();
			// kiirjuk a fájl tetejét ami minden xml dokumentum elején van
			Print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n",writer);
			PrintNodes(root,0,writer);

		}

		public static void PrintNodes(Node node, int nodeRank, FileWriter writer) {
			// ha null lenne a node, akkor nem csinálunk semmit
			if (node.getNodeType() != Node.ELEMENT_NODE) {
				return;
			}
			// ha üres a node akkor sem csinálunk semmit
			if (node instanceof Text) {
				String value = node.getNodeValue().trim();
				if (value.equals("")) {
					return;
				}
			}
			// a sorok behúzása (3 szóköz) * mennyire beágyazott a node (nodeRank)
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < nodeRank; i++) {
				builder.append("   ");
			}
			String formatting = builder.toString();
			// kiirjuk a node elejét, pl. <Csapat
			Print(formatting + "<" + node.getNodeName(),writer);
			NamedNodeMap nodeMap = node.getAttributes();
			for (int i = 0; i < nodeMap.getLength(); i++) {
				// kiirjuk az attribútomokat, értékkel együtt -> <Csapat CSAzon="Kerge Kecskék"
				// ...
				Node attr = nodeMap.item(i);
				Print(" " + attr.getNodeName() + "=\"" + attr.getNodeValue() + "\"",writer);
			}
			// lezárjuk az adott node-ot, pl. <Csapat CSAzon="Kerge Kecskék">
			Print(">",writer);

			// kiirjuk a beágyazott nodeokat (gyerek nodeok)
			NodeList children = node.getChildNodes();
			if (children.getLength() == 1 && children.item(0).getNodeType() == Node.TEXT_NODE) {
				// ha a node maga a szöveg, akkor kiírjuk pl.
				// <nev> volt idáig, majd ebből
				// <nev>Nestlé
				Print(children.item(0).getNodeValue(),writer);
			} else if (!(children.getLength() == 0)) {
				// nem csak egy szöveg, tehát rekurzívan kiirjuk a gyerek node-okat
				Println("",writer);
				for (int i = 0; i < children.getLength(); i++) {
					PrintNodes(children.item(i), nodeRank + 1,writer);
				}
			} else {
				// ez akkor kell, ha üres volt a node
				// ekkor <node></node> íródik ki és visszatérünk
				Print("</" + node.getNodeName() + ">\n",writer);
				return;
			}
			// ez akkor igaz, ha nem szöveg volt a node
			// ekkor eltoljuk a szöveget
			if (!(children.getLength() == 1 && children.item(0).getNodeType() == Node.TEXT_NODE)) {
				Print(formatting,writer);
			}
			// kiírjuk a node végét
			Println("</" + node.getNodeName() + ">",writer);

		}

		private static void Print(String text, FileWriter writer) {
			System.out.print(text);
				try {
					writer.write(text);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}

		private static void Println(String text, FileWriter writer) {
			System.out.println(text);
				try {
					writer.write(text + "\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	 

	
}
