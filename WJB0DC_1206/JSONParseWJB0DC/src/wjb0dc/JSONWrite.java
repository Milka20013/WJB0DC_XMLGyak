package wjb0dc;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONWrite {
	public static void main(String[] args) 
	{
        try {
        	FileReader reader = new FileReader("src/wjb0dc/kurzusfelvetelWJB0DC.json",Charset.forName("UTF-8"));
        	File file = new File("src/wjb0dc/kurzusfelvetelWJB0DC_1.json");
        	JSONParser parser = new JSONParser();
            Object obj = parser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            Print(jsonObject.toString(),file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void Print(String text, File file)
	{ 
		System.out.println(prettyPrint(text));
		try {
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(prettyPrint(text));
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static String prettyPrint(String jsonString) {
        StringBuilder pretty = new StringBuilder();
        int indentLevel = 0;
        boolean inQuote = false;

        for (char charFromJsonString : jsonString.toCharArray()) {
            switch (charFromJsonString) {
                case '{':
                case '[':
                    pretty.append(charFromJsonString).append("\n");
                    indentLevel++;
                    addIndentation(indentLevel, pretty);
                    break;
                case '}':
                case ']':
                    pretty.append("\n");
                    indentLevel--;
                    addIndentation(indentLevel, pretty);
                    pretty.append(charFromJsonString);
                    break;
                case ',':
                    pretty.append(charFromJsonString).append("\n");
                    if (!inQuote) {
                        addIndentation(indentLevel, pretty);
                    }
                    break;
                case '"':
                    inQuote = !inQuote;
                    pretty.append(charFromJsonString);
                    break;
                default:
                    pretty.append(charFromJsonString);
            }
        }
        return pretty.toString();
    }

    private static void addIndentation(int indentLevel, StringBuilder stringBuilder) {
        for (int i = 0; i < indentLevel; i++) {
            stringBuilder.append("  "); // Két szóköz a behúzás
        }
    }
}
