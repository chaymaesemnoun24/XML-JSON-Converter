package converter;

import org.json.JSONObject;
import org.json.XML;

public class XmlToJsonAPI {
    public static String convert(String xml) {
    	//conversion automatique XML → JSON.
        JSONObject json = XML.toJSONObject(xml);
        return json.toString(4);
    }
}
