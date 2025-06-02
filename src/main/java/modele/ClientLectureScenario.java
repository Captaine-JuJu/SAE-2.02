package modele;


import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ClientLectureScenario {
    static ArrayList<String> liste = new ArrayList<>();
    public static void main(String[] args) throws FileNotFoundException, IDException {

        liste.add("Psykokwak");
        liste.add("Jungko");
        liste.add("Méditikka");
        liste.add("Spinda");

        System.out.println(LectureScenario.regrouperParVille("scenario_0.txt"));
    }

}
