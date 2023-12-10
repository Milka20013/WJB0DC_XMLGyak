package xpathwjb0dc;

	import org.w3c.dom.*;
	import java.io.*;
	import javax.xml.parsers.*;
	import javax.xml.xpath.*;

	public class xPathWJB0DC {

	    public static void main(String[] args) {
	        try {
	            File inputFile = new File("src/xpathwjb0dc/studentWJB0DC.xml");
	            
	            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder builder = factory.newDocumentBuilder();
	            Document document = builder.parse(inputFile);
	            document.getDocumentElement().normalize();

	            XPath xPath = XPathFactory.newInstance().newXPath();
	            String str;
	            NodeList nodeList;
	            
	            
	            /*System.out.println("1.");
	            str = "class/student";
	            nodeList = (NodeList) xPath.compile(str).evaluate(document, XPathConstants.NODESET);
	            kiir(nodeList);*/

	            /*System.out.println("\n2.");
	            str = "class/student[@student_id='2']";
	            nodeList = (NodeList) xPath.compile(str).evaluate(document, XPathConstants.NODESET);
	            kiir(nodeList);*/

	            /*System.out.println("\n3.");
	            str = "//student";
	            nodeList = (NodeList) xPath.compile(str).evaluate(document, XPathConstants.NODESET);
	            kiir(nodeList);*/

	            /*System.out.println("\n4.");
	            str = "class/student[2]";
	            nodeList = (NodeList) xPath.compile(str).evaluate(document, XPathConstants.NODESET);
	            kiir(nodeList);*/

	            /*System.out.println("\n5.");
	            str = "class/student[last()]";
	            nodeList = (NodeList) xPath.compile(str).evaluate(document, XPathConstants.NODESET);
	            kiir(nodeList);*/

	            /*System.out.println("\n6.");
	            str = "class/student[last()-1]";
	            nodeList = (NodeList) xPath.compile(str).evaluate(document, XPathConstants.NODESET);
	            kiir(nodeList);*/

	            /*System.out.println("\n7.");
	            str = "class/student[position() <= 2]";
	            nodeList = (NodeList) xPath.compile(str).evaluate(document, XPathConstants.NODESET);
	            kiir(nodeList);*/

	            /*System.out.println("\n8.");
	            str = "class/*";
	            nodeList = (NodeList) xPath.compile(str).evaluate(document, XPathConstants.NODESET);
	            kiir(nodeList);*/

	            /*System.out.println("\n9.");
	            str = "//student[@*]";
	            nodeList = (NodeList) xPath.compile(str).evaluate(document, XPathConstants.NODESET);
	            kiir(nodeList);*/

	            /*System.out.println("\n10.");
	            str = "//*";
	            nodeList = (NodeList) xPath.compile(str).evaluate(document, XPathConstants.NODESET);
	            kiir(nodeList);*/

	            /*System.out.println("\n11.");
	            str = "class/student[kor > 20]";
	            nodeList = (NodeList) xPath.compile(str).evaluate(document, XPathConstants.NODESET);
	            kiir(nodeList);*/

	            System.out.println("\n12.");
	            str = "//student/keresztnev | //student/vezeteknev";
	            nodeList = (NodeList) xPath.compile(str).evaluate(document, XPathConstants.NODESET);
	            kiir(nodeList);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    public static void kiir(NodeList nodeList) {
	        for (int i = 0; i < nodeList.getLength(); i++) {
	            Node node = nodeList.item(i);

	            if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")) {
	                Element element = (Element) node;
	                System.out.println();
	                System.out.println("ID: " + element.getAttribute("student_id"));
	                System.out.println("Vezetéknév: " + element.getElementsByTagName("vezeteknev").item(0).getTextContent());
	                System.out.println("Keresztnév: " + element.getElementsByTagName("keresztnev").item(0).getTextContent());
	                System.out.println("Becenév : " + element.getElementsByTagName("becenev").item(0).getTextContent());
	                System.out.println("Kor : " + element.getElementsByTagName("kor").item(0).getTextContent());
	            }
	        }
	    }
	}
