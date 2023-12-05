package hu.domparse.WJB0DC;

import org.w3c.dom.Document;

public class Main {

	public static void main(String[] args) {
		Document document = DOMReadWJB0DC.ReadDocument("src/hu/domparse/WJB0DC/XMLWJB0DC.xml","src/hu/domparse/WJB0DC/XMLOutput.txt");
		System.out.println("\n\n---Módosítások---\n");
		DOMModifyWJB0DC.ModifyFiveElements(document);
		System.out.println("\n---Query---\n");
		DOMQueryWJB0DC.QueryFiveTimes(document);
		System.out.println("\n---Write---\n");
		DOMWriteWJB0DC.WriteDocument("src/hu/domparse/WJB0DC/XMLWJB0DC.xml");
	}

}
