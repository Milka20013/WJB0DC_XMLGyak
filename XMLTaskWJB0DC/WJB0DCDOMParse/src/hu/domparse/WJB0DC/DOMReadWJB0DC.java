package hu.domparse.WJB0DC;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class DOMReadWJB0DC {
	public static void ReadDocument(String filePath)
	{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbFactory.newDocumentBuilder();
			Document document = db.parse(new File(filePath));
			document.getDocumentElement().normalize();
			PrintDocument(document);
		} catch (ParserConfigurationException |SAXException |IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void PrintDocument(Document document)
	{
		System.out.println(document.getDocumentElement().getTagName());
	}
}
