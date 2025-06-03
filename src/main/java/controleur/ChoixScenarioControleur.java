package controleur;

import modele.Graphe;
import modele.IDException;
import modele.LectureScenario;

import java.io.File;
import java.io.FileNotFoundException;

public class ChoixScenarioControleur {

    public Graphe chargerGrapheDepuisScenario(String nomScenario) throws FileNotFoundException, IDException {
        int[][] tabVoisinsVille = LectureScenario.grapheAvecSuffixesEnTabVoisins(nomScenario);
        return new Graphe(tabVoisinsVille);
    }

    public String[] getListeScenarios() {
        File repertoire = new File("Scenario");
        File[] fichiers = repertoire.listFiles((dir, name) -> name.endsWith(".txt"));

        if (fichiers == null || fichiers.length == 0) {
            return new String[]{"Aucun scénario trouvé"};
        }

        String[] noms = new String[fichiers.length];
        for (int i = 0; i < fichiers.length; i++) {
            noms[i] = fichiers[i].getName();
        }
        return noms;
    }
}
