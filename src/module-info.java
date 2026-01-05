module XML_JSON_Converter {
    // 1. Nécessaire pour les composants graphiques
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    
    // 2. Nécessaire pour la bibliothèque JSON (si vous l'utilisez déjà)
    requires org.json;
    
    // 3. Nécessaire pour que le parser XML manuel fonctionne
    requires java.xml;

    // 4. IMPORTANT : Ouvrir votre package 'application' à JavaFX
    // Sans cela, JavaFX ne peut pas charger votre classe Main ou Controller
    opens application to javafx.graphics, javafx.fxml;
    
    // 5. Exporter le package pour qu'il soit visible
    exports application;
}