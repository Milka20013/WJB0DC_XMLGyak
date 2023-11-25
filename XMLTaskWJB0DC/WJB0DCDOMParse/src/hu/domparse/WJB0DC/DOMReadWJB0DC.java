package hu.domparse.WJB0DC;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DOMReadWJB0DC {
	public static void ReadDocument(String filePath) {
		//létrehozzuk a szükséges adatstruktúrákat, hogy beolvashassuk a dokumentumot
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try {
			//beolvassuk a dokumentumot
			DocumentBuilder db = dbFactory.newDocumentBuilder();
			Document document = db.parse(new File(filePath));
			document.getDocumentElement().normalize();
			//kiirjuk a tartalmát úgy, ahogy eredetileg volt
			PrintDocument(document);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

	}

	public static void PrintDocument(Document document) {
		Element root = document.getDocumentElement();
		//kiirjuk a fájl tetejét ami minden xml dokumentum elején van
		System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		PrintNodes(root,0);

	}

	public static void PrintNodes(Node node, int nodeRank) {
		//ha null lenne a node, akkor nem csinálunk semmit
		if (node.getNodeType() != Node.ELEMENT_NODE) {
			return;
		}
		//ha üres a node akkor sem csinálunk semmit
		if (node instanceof Text) {
			String value = node.getNodeValue().trim();
			if (value.equals("")) {
				return;
			}
		}
		//a sorok behúzása (3 szóköz) * mennyire beágyazott a node (nodeRank)
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < nodeRank; i++) {
			builder.append("   ");
		}
		String formatting = builder.toString();
		//kiirjuk a node elejét, pl. <Csapat
		System.out.print(formatting +"<" + node.getNodeName());
		NamedNodeMap nodeMap = node.getAttributes();
		for (int i = 0; i < nodeMap.getLength(); i++) {
			//kiirjuk az attribútomokat, értékkel együtt -> <Csapat CSAzon="Kerge Kecskék" ...
			Node attr = nodeMap.item(i);
			System.out.print(" " + attr.getNodeName() + "=\"" + attr.getNodeValue() + "\"");
		}
		//lezárjuk az adott node-ot, pl. <Csapat CSAzon="Kerge Kecskék">
		System.out.print(">");
		
		//kiirjuk  a beágyazott nodeokat (gyerek nodeok)
		NodeList children = node.getChildNodes();
		if (children.getLength() == 1 && children.item(0).getNodeType() == Node.TEXT_NODE) {
			//ha a node maga a szöveg, akkor kiírjuk pl.
			//<nev> volt idáig, majd ebből
			//<nev>Nestlé
			System.out.print(children.item(0).getNodeValue());
		} else if (!(children.getLength() == 0)){
			//nem csak egy szöveg, tehát rekurzívan kiirjuk a gyerek node-okat
			System.out.println();
			for (int i = 0; i < children.getLength(); i++) {
				PrintNodes(children.item(i),nodeRank + 1);
			}
		}
		else {
			//ez akkor kell, ha üres volt a node
			//ekkor <node></node> íródik ki és visszatérünk
			System.out.print("</" + node.getNodeName() + ">\n");
			return;
		}
		//ez akkor igaz, ha nem szöveg volt a node
		//ekkor eltoljuk a szöveget
		if(!(children.getLength() == 1 && children.item(0).getNodeType() == Node.TEXT_NODE))
		{
			System.out.print(formatting);
		}
		//kiírjuk a node végét
		System.out.println("</" + node.getNodeName() + ">");

	}
}
