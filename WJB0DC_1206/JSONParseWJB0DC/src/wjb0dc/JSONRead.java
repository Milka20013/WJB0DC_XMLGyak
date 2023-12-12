package wjb0dc;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONRead {
	public static void main(String[] args) {
		try{
			FileReader reader = new FileReader("src/wjb0dc/orarendWJB0DC.json");
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
			JSONObject root = (JSONObject) jsonObject.get("WJB0DC_orarend");
			JSONArray orak = (JSONArray) root.get("ora");

			for (int i = 0; i < orak.size(); i++) {
				JSONObject ora = (JSONObject) orak.get(i);
				JSONObject ido = (JSONObject) ora.get("idopont");
				System.out.println("Tárgy: " + ora.get("targy"));
				System.out.println("Időpont: " + ido.get("nap") + " " + ido.get("tol") + "-" + ido.get("ig"));
				System.out.println("Helyszín: " + ora.get("helyszin"));
				System.out.println("Oktató: " + ora.get("oktato"));
				System.out.println("Szak: " + ora.get("szak") + "\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
