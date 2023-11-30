package hu.domparse.WJB0DC;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DOMReadWJB0DC {
	private static FileWriter writer;
	public static boolean writeToFile = true;

	public static Document ReadDocument(String filePath, String outputFilePath) {
		// létrehozzuk a szükséges adatstruktúrákat, hogy beolvashassuk a dokumentumot
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try {
			// beolvassuk a dokumentumot
			DocumentBuilder db = dbFactory.newDocumentBuilder();
			Document document = db.parse(new File(filePath));
			document.getDocumentElement().normalize();
			// kiirjuk a tartalmát úgy, ahogy eredetileg volt
			writer = new FileWriter(outputFilePath);
			PrintDocument(document);
			writer.close();
			return document;
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void PrintDocument(Document document) {
		Element root = document.getDocumentElement();
		// kiirjuk a fájl tetejét ami minden xml dokumentum elején van
		Print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		Print("<" + root.getNodeName());
		NamedNodeMap rootAttributes = root.getAttributes();
		for (int i = 0; i < rootAttributes.getLength(); i++) {
			Print(rootAttributes.item(i).getNodeName() + "=\"" + rootAttributes.item(i).getNodeValue() + "\"");
		}
		Print(">\n");
		// DOM fa
		NodeList csapatok = document.getElementsByTagName("Csapat");
		NodeList jatekosok = document.getElementsByTagName("Jatekos");
		NodeList jatekok = document.getElementsByTagName("Jatek");
		NodeList versenyszamok = document.getElementsByTagName("Versenyszam");
		NodeList vezetok = document.getElementsByTagName("Vezeto");
		NodeList versenyezTablak = document.getElementsByTagName("Versenyez");
		NodeList versenyek = document.getElementsByTagName("Verseny");
		PrintNodeList(csapatok);
		PrintNodeList(jatekosok);
		PrintNodeList(jatekok);
		PrintNodeList(versenyszamok);
		PrintNodeList(vezetok);
		PrintNodeList(versenyezTablak);
		PrintNodeList(versenyek);
		Print("<" + root.getNodeName() + "/>");

	}

	public static void PrintNodeList(NodeList nodeList) {
		for (int i = 0; i < nodeList.getLength(); i++) {
			PrintNodes(nodeList.item(i), 1);
		}
	}

	public static void PrintNodes(Node node, int nodeRank) {
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
		Print(formatting + "<" + node.getNodeName());
		NamedNodeMap nodeMap = node.getAttributes();
		for (int i = 0; i < nodeMap.getLength(); i++) {
			// kiirjuk az attribútomokat, értékkel együtt -> <Csapat CSAzon="Kerge Kecskék"
			// ...
			Node attr = nodeMap.item(i);
			Print(" " + attr.getNodeName() + "=\"" + attr.getNodeValue() + "\"");
		}
		// lezárjuk az adott node-ot, pl. <Csapat CSAzon="Kerge Kecskék">
		Print(">");

		// kiirjuk a beágyazott nodeokat (gyerek nodeok)
		NodeList children = node.getChildNodes();
		if (children.getLength() == 1 && children.item(0).getNodeType() == Node.TEXT_NODE) {
			// ha a node maga a szöveg, akkor kiírjuk pl.
			// <nev> volt idáig, majd ebből
			// <nev>Nestlé
			Print(children.item(0).getNodeValue());
		} else if (!(children.getLength() == 0)) {
			// nem csak egy szöveg, tehát rekurzívan kiirjuk a gyerek node-okat
			Println("");
			for (int i = 0; i < children.getLength(); i++) {
				PrintNodes(children.item(i), nodeRank + 1);
			}
		} else {
			// ez akkor kell, ha üres volt a node
			// ekkor <node></node> íródik ki és visszatérünk
			Print("</" + node.getNodeName() + ">\n");
			return;
		}
		// ez akkor igaz, ha nem szöveg volt a node
		// ekkor eltoljuk a szöveget
		if (!(children.getLength() == 1 && children.item(0).getNodeType() == Node.TEXT_NODE)) {
			Print(formatting);
		}
		// kiírjuk a node végét
		Println("</" + node.getNodeName() + ">");

	}

	private static void Print(String text) {
		System.out.print(text);
		if (writeToFile) {
			try {
				writer.write(text);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private static void Println(String text) {
		System.out.println(text);
		if (writeToFile) {
			try {
				writer.write(text + "\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
