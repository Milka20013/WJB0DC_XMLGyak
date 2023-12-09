package hu.saxwjb0dc;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyHandler extends DefaultHandler {
	private static int spaces = 0;

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		for (int i = 0; i < spaces; i++) {
			System.out.print("  ");
		}
		System.out.print(qName + ",");

		for (int i = 0; i < attributes.getLength(); i++) {
			System.out.print(" {" + attributes.getQName(i) + "=" + attributes.getValue(i) + "}");
		}

		System.out.println(" start");
		spaces++;
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		for (int i = 0; i < spaces; i++) {
			System.out.print(" ");
		}
		System.out.println(qName + " end");
		spaces--;
	}

	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		String token = new String(ch, start, length).trim();
		if (!token.isEmpty()) {
			for (int i = 0; i < spaces + 2; i++) {
				System.out.print(" ");
			}
			System.out.println(token);
		}
	}
}
