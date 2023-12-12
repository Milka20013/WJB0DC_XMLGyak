package xpathwjb0dc;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class xPathModify {
	public static void main(String[] args) {
        try {
            File inputFile = new File("src/xpathwjb0dc/BM_Kurzusfelvetel.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputFile);
            document.getDocumentElement().normalize();

            XPath xPath = XPathFactory.newInstance().newXPath();

            modfifyThreeTimes(document, xPath);
           

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void modfifyThreeTimes(Document document, XPath xPath) throws XPathExpressionException {
    	//változtassuk meg az első kurzus id-jét
        Element kurzus = (Element) xPath.compile("//WJB0DC_kurzusfelvetel/kurzusok/kurzus[1]").evaluate(document, XPathConstants.NODE);
        kurzus.setAttribute("id", "10");
        
        //változtassuk meg az első előforduló kurzusnevet adatbázisra
        NodeList kurzus1nev = (NodeList)xPath.compile("//WJB0DC_kurzusfelvetel/kurzusok/kurzus/kurzusnev").evaluate(document, XPathConstants.NODESET);
        kurzus1nev.item(0).setTextContent("Adatbázis");
        
        //töröljük az óraadót a 2. kurzusnál
        NodeList kurzusoraado = (NodeList)xPath.compile("//WJB0DC_kurzusfelvetel/kurzusok/kurzus[2]/oraado").evaluate(document, XPathConstants.NODESET);
        kurzus = (Element) xPath.compile("//WJB0DC_kurzusfelvetel/kurzusok/kurzus[2]").evaluate(document, XPathConstants.NODE);
        kurzus.removeChild(kurzusoraado.item(0));
        
        WriteDocument(document);
    }
    
    private static void WriteDocument(Document document)
	{
		try 
        {

            System.out.println("Writing to file");
            
            //a tranformer osztállyal készítünk egy XML filet
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            //kiirjuk az xmlt a consolera
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
            
            //kiirjuk fájlba az xmlt
            StreamResult result = new StreamResult(new File("BM_Kurzusfelvetel1.xml"));
            transformer.transform(source, result);

        } 
        catch (TransformerException e) 
        {
            e.printStackTrace();
        }
	}
}
