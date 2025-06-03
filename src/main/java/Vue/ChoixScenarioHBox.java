package Vue;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import modele.Graphe;

public class ChoixScenarioHBox extends VBox {

    private final ComboBox<String> comboBox;
    private final Button boutonValider;
    private final controleur.ChoixScenarioControleur controleur;

    public ChoixScenarioHBox() {
        super(20); // espacement vertical
        this.controleur = new controleur.ChoixScenarioControleur();

        comboBox = new ComboBox<>();
        comboBox.getItems().addAll(controleur.getListeScenarios());
        comboBox.setPromptText("Choisissez un scénario");

        boutonValider = new Button("Valider");
        boutonValider.setOnAction(e -> traiterChoix());

        this.getChildren().addAll(comboBox, boutonValider);
    }

    private void traiterChoix() {
        String scenarioChoisi = comboBox.getValue();

        if (scenarioChoisi == null || scenarioChoisi.isEmpty()) {
            afficherAlerte("Erreur", "Veuillez sélectionner un scénario.");
            return;
        }

        try {
            Graphe graphe = controleur.chargerGrapheDepuisScenario(scenarioChoisi);
            System.out.println(graphe);
            System.out.println(graphe.degreeEntrant());
        } catch (Exception e) {
            afficherAlerte("Erreur", "Impossible de charger le scénario : " + e.getMessage());
        }
    }

    private void afficherAlerte(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
