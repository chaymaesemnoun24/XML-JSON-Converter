package application;

import converter.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class Controller {

    // Ces variables lient les composants graphiques au code
    @FXML private TextArea inputText;
    @FXML private TextArea outputText;
    @FXML private ComboBox<String> modeSelector;

    // Cette méthode s'exécute automatiquement au lancement
    @FXML
    public void initialize() {
        // On remplit la liste déroulante
        modeSelector.getItems().addAll(
            "XML -> JSON (Avec API)",
            "XML -> JSON (Sans API - Manuel)",
            "JSON -> XML (Avec API)",
            "JSON -> XML (Sans API - Manuel)"
        );
        // On sélectionne le premier choix par défaut
        modeSelector.getSelectionModel().selectFirst();
    }

    // C'est cette méthode que votre FXML ne trouvait pas !
    @FXML
    public void handleConvert() {
        // 1. Récupérer le texte
        String input = inputText.getText();
        
        // Vérification simple
        if (input == null || input.trim().isEmpty()) {
            showAlert("Erreur", "Le champ d'entrée est vide !");
            return;
        }

        String selectedMode = modeSelector.getValue();
        String result = "";

        // 2. Choisir la bonne conversion
        try {
            switch (selectedMode) {
                case "XML -> JSON (Avec API)":
                    result = XmlToJsonAPI.convert(input);
                    break;
                case "XML -> JSON (Sans API - Manuel)":
                    result = XmlToJsonManual.convert(input);
                    break;
                case "JSON -> XML (Avec API)":
                    result = JsonToXmlAPI.convert(input);
                    break;
                case "JSON -> XML (Sans API - Manuel)":
                    result = JsonToXmlManual.convert(input);
                    break;
                default:
                    result = "Mode non reconnu";
            }
        } catch (Exception e) {
            result = "Erreur interne : " + e.getMessage();
        }

        // 3. Afficher le résultat
        outputText.setText(result);
    }

    @FXML
    public void handleClear() {
        inputText.clear();
        outputText.clear();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}