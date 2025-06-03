package Vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import modele.Graphe;
import modele.IDException;
import modele.LectureScenario;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static modele.LectureScenario.remplacementNomVille;

public class ChoixScenarioHBox extends VBox {
    static File repertoire = new File("Scenario");
    static File[] Scenarios = repertoire.listFiles();
    public ChoixScenarioHBox() {
        ComboBox<String> choix = new ComboBox<>();
        ArrayList<String> liste = new ArrayList<>();

        for (File file : Scenarios) {
            choix.getItems().add(file.getName().toString());
            liste.add(file.getName().toString());
        }

        Button boutonValider = new Button("Valider");

        boutonValider.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                int IdChoixValider = choix.getSelectionModel().getSelectedIndex();
                String choixValider = liste.get(IdChoixValider).toString();
                System.out.println("Valider");
                System.out.println(choixValider);
                try {
                    int [] [] tabVoisinsVille = LectureScenario.grapheAvecSuffixesEnTabVoisins(choixValider);
                    Graphe graphe = new Graphe(tabVoisinsVille);
                    System.out.println(graphe.toString());
                    System.out.println(graphe.degreeEntrant());
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IDException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        this.getChildren().addAll(choix, boutonValider);
    }
};
