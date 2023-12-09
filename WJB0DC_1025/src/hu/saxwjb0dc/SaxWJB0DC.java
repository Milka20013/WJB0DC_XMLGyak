package hu.saxwjb0dc;

import java.io.File;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class SaxWJB0DC {
	public static void main(String[] args)
	{
		try
		{

			File file = new File("src/hu/saxwjb0dc/BM_Kurzusfelvetel.xml");
	        SAXParserFactory factory = SAXParserFactory.newInstance();
	        SAXParser saxParser = factory.newSAXParser();
	        MyHandler handler = new MyHandler();
	        saxParser.parse(file, handler);
	        boolean result = XsdWJB0DC.Validate();
	        if(result)
	        {
	        	System.out.println("XML document is valid");
	        }
	        else
	        {
	        	System.out.println("XML document is invalid");
	        }
	    }
		catch (Exception e)
		{
	         e.printStackTrace();
	    }

	}
	

}
