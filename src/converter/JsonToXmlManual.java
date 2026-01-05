package converter;

public class JsonToXmlManual {

    public static String convert(String json) {
        try {
            StringBuilder xml = new StringBuilder();
            
            // 1. Ajouter la racine (Obligatoire pour un XML valide)
            xml.append("<root>\n");

            // 2. Nettoyer les accolades { } et les espaces inutiles au début/fin
            String content = json.replace("{", "").replace("}", "").trim();

            // 3. Séparer les lignes par la virgule ","
            String[] pairs = content.split(",");

            for (String pair : pairs) {
                // 4. Séparer Clé et Valeur par le deux-points ":"
                // On utilise split avec limite 2 pour éviter de couper s'il y a un ':' dans la valeur (ex: heure 12:00)
                String[] parts = pair.split(":", 2);

                if (parts.length == 2) {
                    // Nettoyer les guillemets et les espaces
                    String key = parts[0].replace("\"", "").trim();
                    String value = parts[1].replace("\"", "").trim();

                    // Construire la balise XML
                    xml.append("  <").append(key).append(">");
                    xml.append(value);
                    xml.append("</").append(key).append(">\n");
                }
            }
            
            // 5. Fermer la racine
            xml.append("</root>");
            return xml.toString();

        } catch (Exception e) {
            return "Erreur lors de la conversion : " + e.getMessage();
        }
    }
}