package hu.domparse.WJB0DC;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
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
		Element root = document.getDocumentElement();
		NamedNodeMap asd = root.getAttributes();
		System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		PrintNode(root);

	}
	
	public static void PrintNode(Node node)
	{
		if(node.getNodeType() != Node.ELEMENT_NODE)
		{
			return;
		}
		if (node instanceof Text) {
	        String value = node.getNodeValue().trim();
	        if (value.equals("") ) {
	            return;
	        }
	    }
		System.out.print("<" + node.getNodeName());
		NamedNodeMap nodeMap =  node.getAttributes();
		for (int i = 0; i < nodeMap.getLength(); i++) {
			Node attr = nodeMap.item(i);
			System.out.print(" "+ attr.getNodeName() + "=\"" + attr.getNodeValue()+ "\"");
			//Csak akkor rakunk szóközt a tulajdonságok közé, ha az nem az utolsó
			if(i + 1 < nodeMap.getLength())
			{
				System.out.print(" ");
			}
		}
		System.out.println(">");
		
		NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
                PrintNode(children.item(i));
        }
    }
}
