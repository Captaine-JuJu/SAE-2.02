package modele;

import java.io.File;
import java.io.FileNotFoundException;


public class ClientLectureScenario {
    public static void main(String args[]) throws IDException, FileNotFoundException {
        File scenario0 = new File("Scénario"+File.separator+"scenario_0");
        LectureScenario lectureScenario = new LectureScenario();
        System.out.println(lectureScenario.LectureScenario(scenario0));
    }
}
