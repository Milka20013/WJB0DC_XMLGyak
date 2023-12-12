package hu.domparse.WJB0DC;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
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
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            
            //kiirjuk az xmlt a consolera
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
            
            //kiirjuk fájlba az xmlt
            StreamResult result = new StreamResult(new File(outputFilePath));
            transformer.transform(source, result);

        } 
        catch (SAXException | IOException | ParserConfigurationException | TransformerException e) 
        {
            e.printStackTrace();
        }
	}
	
}
