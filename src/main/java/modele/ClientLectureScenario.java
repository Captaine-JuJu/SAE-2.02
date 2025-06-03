package modele;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static modele.LectureScenario.remplacementNomVille;

public class ClientLectureScenario {
    static ArrayList<String> liste = new ArrayList<>();
    public static void main(String[] args) throws FileNotFoundException, IDException {

        liste.add("Psykokwak");
        liste.add("Jungko");
        liste.add("Méditikka");
        liste.add("Spinda");


        int [] [] tabVoisinsVille = LectureScenario.grapheAvecSuffixesEnTabVoisins("scenario_0.txt");
        Graphe graphe = new Graphe(tabVoisinsVille);
        System.out.println(graphe.toString());
        System.out.println(graphe.degreeEntrant());
//        System.out.println("ordre : " + graphe.ordre());
    }

}
