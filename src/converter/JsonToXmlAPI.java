package converter;

import org.json.JSONObject;
import org.json.XML;

//  imports nécessaires pour la mise en forme (Pretty Print)
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import java.io.StringReader;
import java.io.StringWriter;

public class JsonToXmlAPI {

    public static String convert(String jsonStr) {
        try {
            // 1. Conversion brute (JSON -> XML compact)
            JSONObject json = new JSONObject(jsonStr);
            String rawXml = "<root>" + XML.toString(json) + "</root>";

            // 2. Appel de la fonction pour embellir le XML (ajouter les retours à la ligne)
            return formatXML(rawXml);

        } catch (Exception e) {
            return "Erreur conversion JSON->XML : " + e.getMessage();
        }
    }

    // --- Méthode utilitaire pour formater le XML ---
    private static String formatXML(String inputXml) throws TransformerException {
        // Configurer le transformateur pour indenter
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        
        // Activer l'indentation
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        // Définir la taille de l'indentation (ex: 4 espaces)
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        // Enlever la déclaration <?xml version...?> si on ne la veut pas (optionnel)
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

        // Transformer la chaîne brute en chaîne formatée
        StreamSource xmlInput = new StreamSource(new StringReader(inputXml));
        StringWriter stringWriter = new StringWriter();
        StreamResult xmlOutput = new StreamResult(stringWriter);
        
        transformer.transform(xmlInput, xmlOutput);
        
        return stringWriter.toString();
    }
}