package converter;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;

public class XmlToJsonManual {

    public static String convert(String xml) {
        try {
            // 1. Initialiser le parseur
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = db.parse(new ByteArrayInputStream(xml.getBytes()));
            
            // On normalise le document pour éviter les bugs d'espaces
            doc.getDocumentElement().normalize();
            
            Element root = doc.getDocumentElement();
            
            // 2. Début de la construction du JSON
            StringBuilder json = new StringBuilder();
            json.append("{\n");
            // On ouvre l'objet principal (ex: "etudiant": { )
            json.append("  \"").append(root.getNodeName()).append("\": {\n");

            // 3. Récupérer la liste des enfants (id, nom, note...)
            NodeList nList = root.getChildNodes();
            boolean first = true; // Pour gérer la virgule

            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);

                // IMPORTANT : On ne traite que les noeuds de type ELEMENT (les balises)
                // On ignore les espaces/sauts de ligne qui sont considérés comme du texte
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    
                    if (!first) {
                        json.append(",\n"); // Ajouter une virgule si ce n'est pas le premier
                    }
                    
                    // Exemple : "nom": "Jean Dupont"
                    json.append("    \"").append(node.getNodeName()).append("\": ");
                    json.append("\"").append(node.getTextContent().trim()).append("\"");
                    
                    first = false;
                }
            }

            // 4. Fermeture des accolades
            json.append("\n  }\n}");
            return json.toString();

        } catch (Exception e) {
            e.printStackTrace(); // Utile pour voir l'erreur dans la console
            return "Error parsing XML: " + e.getMessage();
        }
    }
}