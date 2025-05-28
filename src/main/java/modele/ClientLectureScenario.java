package modele;


import java.io.FileNotFoundException;

public class ClientLectureScenario {

    public static void main(String[] args) throws FileNotFoundException, IDException {
        LectureScenario.lectureScenario("scenario_0.txt");
        LectureScenario.lectureVilleMembre();
    }

}
