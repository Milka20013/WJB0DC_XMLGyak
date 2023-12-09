package hu.saxwjb0dc;

import java.io.File;
import java.io.IOException;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class XsdWJB0DC {
public static boolean Validate()
{
	try {
        SchemaFactory factory = 
                SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        Schema schema = factory.newSchema(new File("src/hu/saxwjb0dc/BM_Kurzusfelvetel.xsd"));
        Validator validator = schema.newValidator();
        validator.validate(new StreamSource(new File("src/hu/saxwjb0dc/BM_Kurzusfelvetel.xml")));
    } catch (IOException | SAXException e) {
        System.out.println("Exception: "+e.getMessage());
        return false;
    }
    return true;
}
}
